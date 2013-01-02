package game.base;

import game.global.GameManager;
import game.util.Math;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;

/**
 *
 * @author bootsman
 */
public abstract class MovableActor extends Actor implements Movable
{
    private Double speed;
    
    // Map of all directions to animations.
    // @TODO: Protected is fugly, but sufficient for now.
    protected Animation animation;
    protected java.util.Map<Direction, Animation> animations;
    
    private boolean isMoving;
    private Double movingTime;
    private Point2D.Double movingPositionInitial;
    private Point2D.Double movingPositionCurrent;
    private Point2D.Double movingPositionTarget;
           
    /**
     * Constructor.
     * @param position
     * @param speed 
     */
    public MovableActor( Point position, Double speed )
    {
        super( position );
        
        this.speed = speed;
        
        this.animations = new EnumMap<Direction, Animation>( Direction.class );
        
        this.isMoving = false;
        this.movingTime = 0.0;
        
        // Current map.
        Map map = GameManager.getInstance().getMap();
        
        this.movingPositionInitial = map.toPositionInPixels( this.getX(), this.getY() );
        this.movingPositionCurrent = map.toPositionInPixels( this.getX(), this.getY() );
        this.movingPositionTarget = map.toPositionInPixels( this.getX(), this.getY() );
    }
    
    public MovableActor()
    {
        this( new Point( 0, 0 ), 0.0 );
    }
    
    public void setSpeed( Double speed )
    {
        this.speed = speed;
    }
    
    public Double getSpeed()
    {
        return this.speed;
    }
    
    public Boolean isMoving()
    {
        return this.isMoving;
    }
    
    @Override
    public void render( Graphics g )
    {
        float x = (float)( this.movingPositionCurrent.x - 0.5 * this.animation.getWidth() );
        float y = (float)( this.movingPositionCurrent.y - 0.5 * this.animation.getHeight() );
        this.animation.draw( x, y );            
    }
    
    @Override
    public void move( Direction direction )
    {
        if( this.isMoving() )
        {            
            // Check if the target position has been reached.
            if( this.movingPositionCurrent.equals( this.movingPositionTarget ) )
            {
                this.isMoving = false;
                this.movingTime = 0.0;
                return;
            }
            
            this.movingTime += this.getSpeed() * this.getLastDelta();
            this.movingTime = Math.clamp( this.movingTime, 0.0, 1.0 );
                        
            this.movingPositionCurrent.x = Math.lerp( this.movingPositionInitial.x, this.movingPositionTarget.x, this.movingTime );
            this.movingPositionCurrent.y = Math.lerp( this.movingPositionInitial.y, this.movingPositionTarget.y, this.movingTime );
            
            this.animation.update( this.getLastDelta() );
        }
        else
        {
            // Current map.
            Map map = GameManager.getInstance().getMap();
            
            // Determine new position.
            Point positionCurrent = this.getPosition();
            Point positionTarget = direction.toPosition( positionCurrent );
            
            // Check if the tile in the direction is unoccupied.
            if( map.doesCollideWith( positionTarget ) )
            {
                Logger.getLogger( MovableActor.class.getCanonicalName() ).log( Level.INFO, "Collidable direction given." );
                return;
            }
            
            // Determine initial, current and target positions (calculated from the center).
            this.movingPositionInitial = map.toPositionInPixels( positionCurrent.x, positionCurrent.y );
            this.movingPositionCurrent = map.toPositionInPixels( positionCurrent.x, positionCurrent.y );
            this.movingPositionTarget = map.toPositionInPixels( positionTarget.x , positionTarget.y );
                        
            this.isMoving = true;
            this.animation = this.animations.get( direction );            
            
            // The actor is already 'located' at the target position to make 
            // collision detection much easier.
            this.setPosition( positionTarget );
        }
    }
    
    /*
    public void moveTo( Point2D.Double target )
    {
        Double diffX = java.lang.Math.abs( this.getPosition().x - target.x );
        Double diffY = java.lang.Math.abs( this.getPosition().y - target.y );
        
        // @TODO: Fugly.
        if( diffX < 1.0 && diffY < 1.0 )
        {
            return;
        }
        
        if( diffX > diffY )
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
        // @TODO: Something like this?
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
         *
    }
*/
    
    /**
     * Returns a list of all the directions which are currently not occupied.
     * @return 
     */
    public List<Direction> getDirectionsToNonCollidableTiles()
    {    
        // Fill a list with possible positions.
        List<Direction> directions = new ArrayList<Direction>();
        
        for( Direction direction : Direction.values() )
        {
            if( !GameManager.getInstance().getMap().doesCollideWith( direction.toPosition( this.getPosition() ) ) )
            {
                directions.add( direction );
            }
        }
        
        return directions;
    }
}
