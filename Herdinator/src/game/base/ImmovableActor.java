package game.base;

import game.global.GameManager;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
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
        Point2D.Double position = GameManager.getInstance().getMap().toPositionInPixels(this.getX(), this.getY());
        float x = (float)( position.x - 0.5 * this.animation.getWidth() );
        float y = (float)( position.y - 0.5 * this.animation.getHeight() );
        this.animation.draw( x, y );
    }

    
    
    @Override
    public void update( int delta )
    {
        super.update( delta );
        this.animation.update( delta );
    }
}