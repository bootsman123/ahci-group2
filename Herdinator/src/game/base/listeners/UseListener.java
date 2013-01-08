package game.base.listeners;

import game.base.Actor;
import java.util.EventListener;

/**
 *
 * @author bootsman
 */
public interface UseListener extends EventListener
{
    public void onUse( Actor actor );
}