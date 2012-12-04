package main;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;
import temp.Game2;

/**
 *
 * @author bootsman
 */
public class Main
{
    /**
     * Main function.
     * @param args Command line arguments.
     */
    public static void main( String[] args )
    {
        try
        {
            AppGameContainer app = new AppGameContainer( new Game() );
            app.setDisplayMode( Game.WIDTH, Game.HEIGHT, false );
            app.start();
        }
        catch( SlickException ex )
        {
            Logger.getLogger( Main.class.getName() ).log( Level.SEVERE, null, ex );
        }
        
    }
}
