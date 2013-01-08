package herdinatorserver;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

/**
 *
 * @author bootsman
 */
public class ServerHandlerServlet extends HttpServlet
{      
    // Contains a list of all the connected phones.
    private Map<String, MobilePhonePlayer> mobilePhonePlayers;
    
    public ServerHandlerServlet()
    {
        this.mobilePhonePlayers = new HashMap<String, MobilePhonePlayer>();
    }
    
    
    @Override
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {
        // Create a new JSON object.
        JSONObject json = new JSONObject();
        
        // Check which action has been performed.
        String action = request.getParameter( "action" );
        
        // Action: connect.
        if( "connect".equalsIgnoreCase( action ) )
        {
            Boolean success = Boolean.FALSE;
            
            String markId = request.getParameter( "markId" );
                        
            if( markId != null )
            {
                // Create a new player.
                MobilePhonePlayer player = new MobilePhonePlayer( Integer.valueOf( markId ) );
                
                // If a player already exists with this markId remove the old player.
                Iterator<Map.Entry<String, MobilePhonePlayer>> iterator = this.mobilePhonePlayers.entrySet().iterator();
                
                while( iterator.hasNext() )
                {
                    Map.Entry<String, MobilePhonePlayer> entry = iterator.next();
                    
                    if( entry.getValue().getMarkId().equals( player.getMarkId() ) )
                    {
                        iterator.remove();
                    }
                }
                
                // Add player.
                this.mobilePhonePlayers.put( player.getId().toString(), player );
                
                // Set response.
                success = Boolean.TRUE;
                json.put( "phoneId", player.getId().toString() );
            }
            
            // Set response.
            json.put( "success", success );
        }
        // Action: disconnect.
        else if( "disconnect".equalsIgnoreCase( action ) )
        {
            Boolean success = Boolean.FALSE;
            
            // Get player.
            String phoneId = request.getParameter( "phoneId" );
            MobilePhonePlayer player = this.mobilePhonePlayers.remove( phoneId );
 
            if( player != null )
            {
                success = Boolean.TRUE;
            }
            
            // Set response.
            json.put( "success", success );
        }
        // Action: sync.
        else if( "sync".equalsIgnoreCase( action ) )
        {
            /*
            String phoneId = request.getParameter( "phoneId" );
            MobilePhonePlayer player = this.mobilePhonePlayers.get( phoneId );
            */
        }
        // Action: select.
        else if( "select".equalsIgnoreCase( action ) )
        {
            Boolean success = Boolean.FALSE;
            
            // Get player.
            String phoneId = request.getParameter( "phoneId" );
            MobilePhonePlayer player = this.mobilePhonePlayers.get( phoneId );

            if( player != null )
            {
                String item = request.getParameter( "item" );

                if( item != null )
                {
                    MobilePhonePlayer.MobilePhoneObject object = MobilePhonePlayer.MobilePhoneObject.fromName( item );
                    player.setObject( object );
                
                    success = Boolean.TRUE;
                }
            }
            
            json.put( "success", success );
        }
        // Action: use.
        else if( "use".equalsIgnoreCase( action ) )
        {
            Boolean success = Boolean.FALSE;
            
            // Get player.
            String phoneId = request.getParameter( "phoneId" );
            MobilePhonePlayer player = this.mobilePhonePlayers.get( phoneId );
            
            if( player != null )
            {
                MobilePhonePlayer.MobilePhoneObject object = player.getObject();
                
                if( object != null )
                {
                    // @TODO: object.use();
                    success = Boolean.TRUE;
                }
            }
            
            json.put( "success", success );
        } 
        else
        {
            json.put( "success", Boolean.FALSE );
        }

        // Never cache the response.
        response.addHeader( "Cache-Control", "private, no-cache" );
	response.addHeader( "Expires", "Tue, 1 Jan 1970 01:00:00 GMT" );
	response.addHeader( "Pragma", "no-cache" );
        response.setContentType("application/json");
        
        PrintWriter writer = response.getWriter();
        writer.write( json.toJSONString() );
        writer.close();
    }
}