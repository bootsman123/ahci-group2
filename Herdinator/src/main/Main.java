package main;

import game.Game;
import java.awt.Point;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;
import game.util.Math;

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
            //app.setTargetFrameRate( 60 );
            //app.setSmoothDeltas( true );
            app.start();
        }
        catch( SlickException ex )
        {
            Logger.getLogger( Main.class.getName() ).log( Level.SEVERE, null, ex );
        }
    }
}
