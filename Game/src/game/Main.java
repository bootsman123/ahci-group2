package game;

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
     * @param args the command line arguments
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
            Logger.getLogger(Main.class.getName()).log( Level.SEVERE, null, ex );
        }
        
    }
}
