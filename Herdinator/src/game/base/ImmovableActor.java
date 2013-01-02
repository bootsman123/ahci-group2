package game.base;

import java.awt.Point;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;

/**
 *
 * @author bootsman
 */
public abstract class ImmovableActor extends Actor
{
    protected Animation animation;
    
    /**
     * Constructor.
     * @param position 
     */
    public ImmovableActor( Point position )
    {
        super( position );
    }

    @Override
    public void render( Graphics g )
    {
    }
    
    @Override
    public void update( int delta )
    {
        super.update( delta );
        this.animation.update( delta );
    }
}