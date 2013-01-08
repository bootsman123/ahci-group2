package game.actors;

import game.base.UsableActor;
import game.players.Player;
import java.awt.Point;

/**
 *
 * @author roland
 */
public class Fence extends UsableActor
{
    public Fence( Point position, Player owner )
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
