package main;

import game.Game;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.catalina.LifecycleException;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;
import server.Server;

/**
 *
 * @author bootsman
 */
public class Main
{
    public static final Logger LOGGER = Logger.getLogger( Main.class.getName() );
    
    /**
     * Main function.
     * @param args Command line arguments.
     */
    public static void main( String[] args )
    {
        try
        {
            // Server.
            Server server = new Server();
            server.start();
                       
            // Game.
            AppGameContainer app = new AppGameContainer( new Game() );
            app.setDisplayMode( Game.WIDTH, Game.HEIGHT, false );
            app.setTargetFrameRate( 30 );
            //app.setSmoothDeltas( true );
            app.start();
        }
        catch( LifecycleException e )
        {
            LOGGER.log( Level.SEVERE, null, e );
        }
        catch( SlickException e )
        {
            LOGGER.log( Level.SEVERE, null, e );
        }
    }
}
