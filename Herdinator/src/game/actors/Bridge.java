package game.actors;

import game.base.UsableActor;
import game.players.Player;
import java.awt.Point;

/**
 *
 * @author roland
 */
public class Bridge extends UsableActor
{
    public Bridge( Point position, Player owner, boolean isOnMap )
    {
        super( position, owner, isOnMap );
    }

    @Override
    public void init()
    {
    }

    @Override
    public void use() {
        
    }
}
