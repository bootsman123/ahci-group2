package herdinatorserver;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
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
    private Map<String, String> phones;
    
    public ServerHandlerServlet()
    {
        this.phones = new HashMap<String, String>();
    }
    
    
    @Override
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {
        String action = request.getParameter( "action" );
        
        if( action.equalsIgnoreCase( "connect" ) )
        {
            // Generate a unique id.
            UUID phoneId = UUID.randomUUID();
            
        }
        else if( action.equalsIgnoreCase( "disconnect" ) )
        {
            
        }
        else if( action.equalsIgnoreCase( "sync" ) )
        {
            
        }
        else if( action.equalsIgnoreCase( "select" ) )
        {
            
        }
        else if( action.equalsIgnoreCase( "use" ) )
        {
            
        }
        
        
        
        
        //Map<String, String[]> parameterMap = request.getParameterMap();
        //String[] action = parameterMap.get( new String( "action" ) );
        
        
        request.getP

        try
        {

        }
        catch( NullPointerException e )
        {
            
        }
        finally
        {
            
        }
        
        // Create JSON object.
        JSONObject json = new JSONObject();
        json.put( " ", " " );
        
        // Never cache the response.
        response.addHeader( "Cache-Control", "private, no-cache" );
	response.addHeader( "Expires", "Tue, 1 Jan 1970 01:00:00 GMT" );
	response.addHeader( "Pragma", "no-cache" );
        
        PrintWriter writer = response.getWriter();
        writer.write( json.toJSONString() );
        writer.close();
    }
}