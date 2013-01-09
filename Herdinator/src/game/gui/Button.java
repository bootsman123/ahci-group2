package game.gui;


import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.gui.MouseOverArea;

/**
 *
 * @author bootsman
 */
public class Button extends MouseOverArea
{
    /**
     * Constructor.
     * @param context
     * @param imageNormal
     * @param imageOver
     * @param imageDown
     * @param x
     * @param y 
     */
    public Button( GUIContext context, String imageNormal, String imageOver, String imageDown, Integer x, Integer y ) throws SlickException
    {
        super( context, new Image( imageNormal ), x, y );
        
        this.setMouseOverImage( new Image( imageOver ) );
        this.setMouseDownImage( new Image( imageDown ) );
    }    
}