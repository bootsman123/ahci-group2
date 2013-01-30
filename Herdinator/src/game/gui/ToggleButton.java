package game.gui;

import TUIO.TuioCursor;
import TUIO.TuioPoint;
import game.Game;
import game.gui.listeners.ToggleListener;
import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.GUIContext;

/**
 *
 * @author bootsman
 */
public class ToggleButton extends Button
{   
    private Boolean isToggled;
    
    private List<ToggleListener> listenersToggle;
    
    /**
     * Constructor.
     * @param context
     * @param imageUp
     * @param imageDown
     * @param x
     * @param y
     * @throws SlickException 
     */
    public ToggleButton( GUIContext context, Image imageUp, Image imageDown ) throws SlickException
    {
        super( context, imageUp, imageDown );
        
        this.isToggled = Boolean.FALSE;
        
        this.listenersToggle = new ArrayList<ToggleListener>();
    }
    
    /**
     * Returns whether the button is toggled on or off.
     * @return 
     */
    public Boolean isToggled()
    {
        return this.isToggled;
    }
    
    /**
     * Set the value of the toggled button.
     * @param isToggled 
     */
    public void setIsToggled( Boolean isToggled )
    {
        this.isToggled = isToggled;
        
        if( this.isToggled )
        {
            this.isDown = Boolean.TRUE;
        }
    }
    
    /**
     * Add toggle listener.
     * @param toggleListener 
     */
    public void addToggleListener( ToggleListener listenerToggle )
    {
        this.listenersToggle.add( listenerToggle );
    }
    
    /**
     * Remove toggle listener.
     * @param toggleListener 
     */
    public void removeToggleListener( ToggleListener listenerToggle )
    {
        this.listenersToggle.remove( listenerToggle );
    }
    
    @Override
    public void mousePressed( int button, int x, int y )
    {
        super.mousePressed( button, x, y );
        
        if( Input.MOUSE_LEFT_BUTTON == button )
        {
            if( this.area.contains( x, y ) )
            {
                this.isToggled = !this.isToggled;
                
                // Notify listeners.
                for( ToggleListener listenerToggle : this.listenersToggle )
                {
                    listenerToggle.onToggle( this );
                }
            }
        }
    }
    
    @Override
    public void mouseReleased( int button, int x, int y )
    {
        super.mouseReleased( button, x, y );
               
        if( this.isToggled )
        {
            this.isDown = Boolean.TRUE;
        }
    }
    
    @Override
    public void updateTuioCursor( TuioCursor c )
    {
        super.updateTuioCursor( c );
        
        TuioPoint point = c.getPosition();
        
        if( this.area.contains( ( point.getX() * Game.WIDTH ), ( point.getY() * Game.HEIGHT ) ) )
        {
            this.isToggled = !this.isToggled;

            // Notify listeners.
            for( ToggleListener listenerToggle : this.listenersToggle )
            {
                listenerToggle.onToggle( this );
            }
        }
    }
    
    @Override
    public void removeTuioCursor( TuioCursor c )
    {
        super.removeTuioCursor( c );
        
        if( this.isToggled )
        {
            this.isDown = Boolean.TRUE;
        }
    }
}