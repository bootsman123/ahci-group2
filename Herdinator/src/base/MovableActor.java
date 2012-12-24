package base;

import java.awt.geom.Point2D;
import java.util.EnumMap;
import org.newdawn.slick.Animation;

/**
 *
 * @author bootsman
 */
public abstract class MovableActor extends Actor implements Movable
{  
    private Float speed;
    
    // Map of all directions to animations.
    // @TODO: Protected is fugly, but sufficient for not.
    protected java.util.Map<Direction, Animation> animations;
    
    public MovableActor( Point2D.Float position, Float speed )
    {
        super( position );
        this.speed = speed;
        this.animations = new EnumMap<Direction, Animation>( Direction.class );
    }
    
    public MovableActor()
    {
        this( new Point2D.Float( 0.0f, 0.0f ), 0.0f );
    }
    
    public void setSpeed( Float speed )
    {
        this.speed = speed;
    }
    
    public Float getSpeed()
    {
        return this.speed;
    }
    
    @Override
    public void move( Direction direction )
    {
        Float x = this.getX();
        Float y = this.getY();
        
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
    
    public void moveTo( Point2D.Float target )
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
    
    public void moveAwayFrom( Point2D.Float target )
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
