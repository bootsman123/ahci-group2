package game.gui;

import TUIO.TuioCursor;
import TUIO.TuioListener;
import TUIO.TuioObject;
import TUIO.TuioPoint;
import TUIO.TuioTime;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.GUIContext;

/**
 *
 * @author bootsman
 */
public class ButtonTouch extends Button implements TuioListener
{
    /**
     * Constructor.
     * @param context
     * @param imageNormal
     * @param imageOver
     * @param imageDown
     * @param x
     * @param y
     * @throws SlickException
     */
    public ButtonTouch( GUIContext context, String imageNormal, String imageDown ) throws SlickException
    {
        super( context, imageNormal, imageDown );
    }

    @Override
    public void addTuioObject( TuioObject o )
    {
    }

    @Override
    public void updateTuioObject( TuioObject o )
    {
    }

    @Override
    public void removeTuioObject( TuioObject o )
    {
    }

    @Override
    public void addTuioCursor( TuioCursor c )
    {
        TuioPoint position = c.getPosition();
        
        
    }

    @Override
    public void updateTuioCursor( TuioCursor c )
    {
    }

    @Override
    public void removeTuioCursor( TuioCursor c )
    {
    }

    @Override
    public void refresh( TuioTime t )
    {
    }
}