package game.gui.listeners;

import game.gui.Button;
import java.util.EventListener;

/**
 *
 * @author bootsman
 */
public interface ClickAndTouchListener extends EventListener
{
    public void onClickOrTouch( Button button );
}