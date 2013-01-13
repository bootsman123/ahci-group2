package game.gui;

import game.gui.listeners.ToggleListener;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bootsman
 */
public class ButtonGroup implements ToggleListener
{
    private List<ToggleButton> toggleButtons;
    
    public ButtonGroup()
    {
        this.toggleButtons = new ArrayList<ToggleButton>();
    }
    
    /**
     * Add a toggle button.
     * @param button 
     */
    public void add( ToggleButton toggleButton )
    {
        toggleButton.addToggleListener( this );
        this.toggleButtons.add( toggleButton );
    }
    
    /**
     * Remove a toggle button.
     * @param button 
     */
    public void remove( ToggleButton toggleButton )
    {
        toggleButton.removeToggleListener( this );
        this.toggleButtons.remove( toggleButton );
    }
    
    /**
     * Set a button toggled on.
     * @param index 
     */
    public void setButtonToggledOn( Integer index )
    {
        // Validate index.
        if( index < 0 || index >= this.toggleButtons.size() )
        {
            return;
        }
        
        // Get the button.
        ToggleButton toggleButton = this.toggleButtons.get( index );
        toggleButton.setIsToggled( Boolean.TRUE );
        
        for( Integer i = 0; i < this.toggleButtons.size(); i++ )
        {
            if( i != index )
            {
                this.toggleButtons.get( i ).setIsToggled( Boolean.FALSE );
            }
        }
    }
    
    /**
     * Returns the index toggled on button.
     * @TODO: Inefficient an no complete guarantee only a single button is toggled on.
     * @return 
     */
    public Integer getButtonToggledOn()
    {
        for( Integer i = 0; i < this.toggleButtons.size(); i++ )
        {
            if( this.toggleButtons.get( i ).isToggled() )
            {
                return i;
            }
        }
        
        return -1;
    }
    
    @Override
    public void onToggle( ToggleButton buttonToggle )
    {
        // Toggle off all other buttons.
        for( ToggleButton button : this.toggleButtons )
        {
            if( buttonToggle != button )
            {
                button.setIsToggled( Boolean.FALSE );
            }
        }
    }
}