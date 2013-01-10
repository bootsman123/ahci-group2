package game.gui;

import game.gui.ButtonToggle.ToggleListener;
import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.gui.AbstractComponent;

/**
 *
 * @author bootsman
 */
public class ButtonGroup implements ToggleListener
{
    private List<ButtonToggle> buttons;
    
    public ButtonGroup()
    {
        this.buttons = new ArrayList<ButtonToggle>();
    }
    
    /**
     * Add a toggle button.
     * @param button 
     */
    public void add( ButtonToggle buttonToggle )
    {
        buttonToggle.addToggleListener( this );
        this.buttons.add( buttonToggle );
    }
    
    /**
     * Remove a toggle button.
     * @param button 
     */
    public void remove( ButtonToggle buttonToggle )
    {
        buttonToggle.removeListener( this );
        this.buttons.remove( buttonToggle );
    }
    
    @Override
    public void onToggle( ButtonToggle buttonToggle )
    {
        // Toggle off all other buttons.
        for( ButtonToggle button : this.buttons )
        {
            if( buttonToggle != button )
            {
                button.setIsToggled( Boolean.FALSE );
            }
        }
    }

    @Override
    public void componentActivated( AbstractComponent source )
    {
    }
}