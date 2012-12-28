package game.base;

import java.awt.geom.Point2D;
import java.util.EnumMap;
import org.newdawn.slick.Animation;

/**
 *
 * @author bootsman
 */
public abstract class MovableActor extends Actor implements Movable
{  
    private Double speed;
    
    // Map of all directions to animations.
    // @TODO: Protected is fugly, but sufficient for now.
    protected java.util.Map<Direction, Animation> animations;
    
    /**
     * Constructor.
     * @param position
     * @param speed 
     */
    public MovableActor( Point2D.Double position, Double speed )
    {
        super( position );
        this.speed = speed;
        this.animations = new EnumMap<Direction, Animation>( Direction.class );
    }
    
    public MovableActor()
    {
        this( new Point2D.Double( 0.0, 0.0 ), 0.0 );
    }
    
    public void setSpeed( Double speed )
    {
        this.speed = speed;
    }
    
    public Double getSpeed()
    {
        return this.speed;
    }
    
    @Override
    public void move( Direction direction )
    {
        Double x = this.getX();
        Double y = this.getY();
        
        switch( direction )
        {
            case UP:
                y -= this.getLastDelta() * this.getSpeed();
                break;
            case RIGHT:
                x += this.getLastDelta() * this.getSpeed();
                break;
            case DOWN:
                y += this.getLastDelta() * this.getSpeed();
                break;
            case LEFT:
                x -= this.getLastDelta() * this.getSpeed();
                break;
        }
        
        this.setAnimation( this.animations.get( direction ) );
        this.setPosition( x, y );
        
    }
    
    public void moveTo( Point2D.Double target )
    {
        if( Math.abs( this.getPosition().x - target.x ) < Math.abs( this.getPosition().y - target.y ) )
        {
            if( this.getPosition().x > target.x )
            {
                this.move( Direction.LEFT );
            }
            else
            {
                this.move( Direction.RIGHT );
            }
        }
        else
        {
            if( this.getPosition().y > target.y )
            {
                this.move( Direction.UP );
            }
            else
            {
                this.move( Direction.DOWN  );
            }
        }
    }
    
    public void moveAwayFrom( Point2D.Double target )
    {
        // Something like this?
        target.x = this.getPosition().x - target.x;
        target.y = this.getPosition().y - target.y;
        this.moveTo( target );
        
        /*
         *      if( Math.abs(position.x-target.x) < Math.abs(position.y-target.y)){
            if(position.x > target.x)
                move( Direction.RIGHT, delta);
            else
                move( Direction.LEFT, delta );
        }
        else
             if(position.y > target.y)
                move( Direction.DOWN, delta);
            else
                move( Direction.UP, delta ); 
         */
    }
}
