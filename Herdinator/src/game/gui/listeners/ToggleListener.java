package game.gui.listeners;

import game.gui.ToggleButton;
import java.util.EventListener;

/**
 *
 * @author Bas Bootsma
 */
public interface ToggleListener extends EventListener
{
    public void onToggle( ToggleButton buttonToggle );
}