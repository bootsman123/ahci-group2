package main;

import temp.Game;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

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
            app.setDisplayMode( 320, 320, false );
            app.start();
        }
        catch( SlickException ex )
        {
            Logger.getLogger( Main.class.getName() ).log( Level.SEVERE, null, ex );
        }
        
    }
}
