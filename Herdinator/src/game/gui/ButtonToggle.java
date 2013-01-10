package game.gui;

import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.GUIContext;

/**
 *
 * @author bootsman
 */
public class ButtonToggle extends Button
{
    public interface ToggleListener extends ComponentListener
    {
        public void onToggle( ButtonToggle buttonToggle );
    }
    
    private Boolean isToggled;
    
    private List<ToggleListener> toggleListeners;
    
    /**
     * Constructor.
     * @param context
     * @param imageUp
     * @param imageDown
     * @param x
     * @param y
     * @throws SlickException 
     */
    public ButtonToggle( GUIContext context, String imageUp, String imageDown ) throws SlickException
    {
        super( context, imageUp, imageDown );
        
        this.isToggled = Boolean.FALSE;
        
        this.toggleListeners = new ArrayList<ToggleListener>();
    }
    
    /**
     * Set the value of the toggled button.
     * @param isToggled 
     */
    public void setIsToggled( Boolean isToggled )
    {
        this.isToggled = isToggled;
    }
    
    /**
     * Add toggle listener.
     * @param toggleListener 
     */
    public void addToggleListener( ToggleListener toggleListener )
    {
        this.toggleListeners.add( toggleListener );
    }
    
    /**
     * Remove toggle listener.
     * @param toggleListener 
     */
    public void removeToggleListener( ToggleListener toggleListener )
    {
        this.toggleListeners.remove( toggleListener );
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
                for( ToggleListener listener : this.toggleListeners )
                {
                    listener.onToggle( this );
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
}