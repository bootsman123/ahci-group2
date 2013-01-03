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
    public Bridge( Point position, Player owner )
    {
        super( position, owner );
    }

    @Override
    public void init()
    {
    }

    @Override
    public void use() {
        
    }
}
