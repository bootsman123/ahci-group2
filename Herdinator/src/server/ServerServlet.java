package server;

import game.actors.Cookie;
import game.actors.Whistle;
import game.base.Map;
import game.base.UsableActor;
import game.global.GameManager;
import game.global.PlayerManager;
import game.players.Player;
import game.players.TangiblePlayer;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;

/**
 *
 * @author bootsman
 */
public class ServerServlet extends HttpServlet
{
    // @TODO: Currently not being used.
    private List<ServerListener> serverListeners;
    
    /**
     * Constructor.
     */
    public ServerServlet()
    {
        this.serverListeners = new ArrayList<ServerListener>();
    }
    
    /**
     * Add a server listener.
     * @param serverListener 
     */
    public void addServerListener( ServerListener serverListener )
    {
        this.serverListeners.add( serverListener );
    }
    
    /**
     * Remove server listener.
     * @param serverListener 
     */
    public void removeServerListener( ServerListener serverListener )
    {
        this.serverListeners.remove( serverListener );        
    }
    
    @Override
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {
        // Players.
        List<Player> players = GameManager.getInstance().getPlayers();
        
        // Create a new JSON object.
        JSONObject json = null;
        
        // Check which action has been performed.
        String action = request.getParameter( "action" );
        
        // Action: connect.
        if( "connect".equalsIgnoreCase( action ) )
        {
            json = this.handleConnect( request.getParameter( "markId" ) );
        }
        // Action: disconnect.
        else if( "disconnect".equalsIgnoreCase( action ) )
        {
            json = this.handleDisconnect( request.getParameter( "phoneId" ) );
        }
        // Action: sync.
        else if( "sync".equalsIgnoreCase( action ) )
        {
            json = this.handleSync( request.getParameter( "phoneId" ) );
        }
        // Action: select.
        else if( "select".equalsIgnoreCase( action ) )
        {
            json = this.handleSelect( request.getParameter( "phoneId" ),
                                      request.getParameter( "item" ) );
        }
        // Action: use.
        else if( "use".equalsIgnoreCase( action ) )
        {
            json = this.handleUse( request.getParameter( "phoneId" ) );
        } 
        else
        {
            json = new JSONObject();
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
    
    /**
     * Handles the request: ?action=connect&markId=[INT]
     * @param request
     * @return 
     */
    private JSONObject handleConnect( String markIdString )
    {
        JSONObject json = new JSONObject();
        json.put( "success", Boolean.FALSE );
        
        // Check if a valid mark id has been given.
        if( markIdString == null )
        {
            return json;
        }
        
        PlayerManager playerManager = PlayerManager.getInstance();
        Integer markId = Integer.valueOf( markIdString );
        
        // @TODO: Bas.
        
        // Check if a player, with the same mark id, already exists.
        // If so, remove the player.
        Player player = playerManager.getPlayer( markId );
        
        if( player != null )
        {
            playerManager.removePlayer( player );
        }
        
        /*
        ListIterator<Player> playerListIterator = playerManager.getPlayers().listIterator();
               
        while( playerListIterator.hasNext() )
        {
            Player player = playerListIterator.next();
            
            // @TODO: This should not be necessary.
            if( player instanceof TangiblePlayer )
            {
                TangiblePlayer tangiblePlayer = (TangiblePlayer)player;
                
                if( tangiblePlayer.getMarkId() == markId )
                {
                    playerListIterator.remove();
                }
                
            }
        }
        */
        
        // Check if a player can still connect.
        if( !playerManager.hasReachedPlayerLimit() )
        {
            return json;
        }  
            
        // Create a new player.
        TangiblePlayer tangiblePlayer = new TangiblePlayer( markId );
        
        // Add player.
        playerManager.addPlayer( tangiblePlayer );
        
        json.put( "success", Boolean.TRUE );
        json.put( "phoneId", tangiblePlayer.getId().toString() );
        
        return json;
    }
    
    /**
     * Handles the request: ?action=disconnect&phoneId=[INT]
     * @param request
     * @return 
     */
    private JSONObject handleDisconnect( String phoneIdString )
    {
        JSONObject json = new JSONObject();
        json.put( "success", Boolean.FALSE );
        
        // Check the phone id.
        if( phoneIdString != null )
        {
            PlayerManager playerManager = PlayerManager.getInstance();
            Player player = playerManager.getPlayer( Integer.valueOf( phoneIdString ) );
            
            if( player != null )
            {
                playerManager.removePlayer( player );
                json.put( "success", Boolean.TRUE );
            }
            
            /*
            ListIterator<Player> playerListIterator = PlayerManager.getInstance().getPlayers().listIterator();

            while( playerListIterator.hasNext() )
            {
                Player player = playerListIterator.next();

                // @TODO: This should not be necessary.
                if( player instanceof TangiblePlayer )
                {
                    TangiblePlayer tangiblePlayer = (TangiblePlayer)player;

                    if( tangiblePlayer.getId() == phoneId )
                    {
                        playerListIterator.remove();
                        json.put( "success", Boolean.TRUE );
                    }

                }
            }*/
        }
                    
        return json;
    }
    
    /**
     * Handles request: ?action=sync&phoneId=[INT]
     * @param phoneIdString
     * @return 
     */
    private JSONObject handleSync( String phoneIdString )
    {
        JSONObject json = new JSONObject();
        json.put( "success", Boolean.TRUE );
        
                    /*
            String phoneId = request.getParameter( "phoneId" );
            TangiblePlayer player = this.mobilePhonePlayers.get( phoneId );
            */
     
        return json;
    }
    
    /**
     * Handles the request: ?action=select&phoneId=[INT]&item=[STRING]
     * @param request
     * @return 
     */
    private JSONObject handleSelect( String phoneIdString, String itemString )
    {
        JSONObject json = new JSONObject();
        json.put( "success", Boolean.FALSE );

        // Check the phone id.
        if( phoneIdString != null )
        {
            Player player = PlayerManager.getInstance().getPlayer( Integer.valueOf( phoneIdString ) );
            TangiblePlayer tangiblePlayer = (TangiblePlayer)player;
            
            if( player != null )
            {
                Map map = GameManager.getInstance().getMap();
                
                try
                {
                    if( "whistle".equalsIgnoreCase( itemString ) )
                    {
                        player.setObject( new Whistle( map.fromPositionInPixels( tangiblePlayer.getTangibleLocation() ),
                                                       tangiblePlayer,
                                                       Boolean.FALSE ) );                
                        
                        json.put( "success", Boolean.TRUE );
                    }
                    else if( "cookie".equalsIgnoreCase( itemString ) )
                    {
                        player.setObject( new Cookie( map.fromPositionInPixels( tangiblePlayer.getTangibleLocation() ),
                                                      tangiblePlayer,
                                                      Boolean.FALSE ) );
                        
                        json.put( "success", Boolean.TRUE );
                    }
                }
                catch( SlickException e )
                {
                }
            }
        }
        
        return json;
    }
    
    /**
     * Handles request: ?action=use&phoneId=[INT]
     * @param idString
     * @param itemString
     * @return 
     */
    private JSONObject handleUse( String phoneIdString )
    {
        JSONObject json = new JSONObject();
        json.put( "success", Boolean.FALSE );
        
        // Check the phone id.
        if( phoneIdString != null )
        {
            Player player = PlayerManager.getInstance().getPlayer( Integer.valueOf( phoneIdString ) );
            
            if( player != null )
            {
                UsableActor object = player.getObject();
                
                if( object != null )
                {
                    object.use();
                    json.put( "success", Boolean.TRUE );
                }
            }
        }
        
        return json;
    }
}