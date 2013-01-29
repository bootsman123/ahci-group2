package game.base;

import game.global.GameManager;
import game.util.MathUtil;
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
 * Abstract movable actor class.
 * @author Bas Bootsma
 */
public abstract class MovableActor extends Actor implements Movable
{
    // Logger.
    private static final Logger LOGGER = Logger.getLogger( MovableActor.class.getName() );
    
    private Double speed;
    
    // Map of all directions to animations.
    // @TODO: Protected is fugly, but sufficient for now.
    protected Animation animation;
    protected java.util.Map<Direction, Animation> animations;
    
    private Boolean isMoving;
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
        
        this.movingTime = 0.0;
        
        // Current map.
        Map map = GameManager.getInstance().getMap();
        
        this.movingPositionInitial = map.toPositionInPixels( this.getX(), this.getY() );
        this.movingPositionCurrent = map.toPositionInPixels( this.getX(), this.getY() );
        this.movingPositionTarget = map.toPositionInPixels( this.getX(), this.getY() );
        
        this.isMoving = false;
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

    /*
    @Override
    public void update( int delta )
    {
        super.update( delta );
        
        if( !this.isMoving() )
        {
            // Check if there is a path.
            Path path = this.getPath();
            
            Direction direction = this.getDirection();
            this.move( direction );
        }
    }
    */
    
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
            this.movingTime = MathUtil.clamp( this.movingTime, 0.0, 1.0 );
                        
            this.movingPositionCurrent.x = MathUtil.lerp( this.movingPositionInitial.x, this.movingPositionTarget.x, this.movingTime );
            this.movingPositionCurrent.y = MathUtil.lerp( this.movingPositionInitial.y, this.movingPositionTarget.y, this.movingTime );
            
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
            if( map.isBlocked( positionTarget ) )
            {
                LOGGER.log( Level.WARNING, "Collidable direction given." );
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
    
    /**
     * Returns the closest other actor from the list to the given actor.
     * @param actor
     * @param actors
     * @return 
     */
    protected Actor closestActor( Actor actor, List<? extends Actor> actors )
    {
        Actor closestActor = null;
        Double closestDistance = Double.MAX_VALUE;
        
        for( Actor a : actors )
        {
            // Self-comparison.
            if( actor == a )
            {
                continue;
            }
            
            Double d = MathUtil.distanceManhattan( actor.getPosition(), a.getPosition() );
            
            if( d < closestDistance )
            {
                closestActor = a;
                closestDistance = d;
            }
        }
        
        return closestActor;
    }
    
    /**
     * Returns a list of all the directions which are currently not occupied.
     * @return 
     */
    protected List<Direction> directionsToNonCollidableTiles( Point position )
    {    
        // Fill a list with possible positions.
        List<Direction> directions = new ArrayList<Direction>();

        for( Direction direction : Direction.values() )
        {
            if( !GameManager.getInstance().getMap().isBlocked( direction.toPosition( position ) ) )
            {
                directions.add( direction );
            }
        }
        
        return directions;
    }
    
    /**
     * Returns a list of all the directions which are currently not occupied and reside within the goal area.
     * @return 
     */
    protected List<Direction> directionsToNonCollidableGoalTiles( Point position )
    {
        // Fill a list with possible positions.
        Map map = GameManager.getInstance().getMap();
        List<Direction> directions = new ArrayList<Direction>();

        for( Direction direction : Direction.values() )
        {
            if( !map.isBlocked( direction.toPosition( getPosition() ) ) &&
                map.isGoalTile( getPosition() ) )
            {
                directions.add( direction );
            }
        }
        
        return directions;
    }
    
    /**
     * Returns the best direction from actor a1 to actor a2.
     * @param a1
     * @param a2
     * @return 
     */
    protected Direction directionTowardsActor( Actor a1, Actor a2 )
    {
        return this.directionTowardsPosition( a1.getPosition(), a2.getPosition() );
    }
        
    /**
     * Returns the best direction from position p1 to position p2.
     * @param p1
     * @param p2
     * @return 
     */
    protected Direction directionTowardsPosition( Point p1, Point p2 )
    {
        Integer absX = java.lang.Math.abs( p1.x - p2.x );
        Integer absY = java.lang.Math.abs( p1.y - p2.y  );

        // Move closer on the x-axis.
        if( absX > absY )
        {
            if( p1.x > p2.x )
            {
                return Direction.RIGHT;
            }
            else
            {
                return Direction.LEFT;
            }
        }
        // Move closer on the y-axis.
        else
        {
            if( p1.y > p2.y )
            {
                return Direction.UP;
            }
            else
            {
                return Direction.DOWN;
            }
        }
    }
    
    /**
     * Returns the best direction from actor a1 towards actor a2 given a list of predefined directions.
     * @param direction
     * @param p
     * @return 
     */
    protected Direction directionTowardsActorFromList( Actor a1, Actor a2, List<Direction> directions )
    {
        Direction bestDirection = null;
        Double bestAngle = Double.MAX_VALUE;
        
        // Determine the best direction.
        Double angle = MathUtil.angle( a1.getPosition(), a2.getPosition() );
        
        for( Direction direction : directions )
        {
            Double currentAngle = java.lang.Math.abs( angle - direction.getAngle() );
            
            if( currentAngle < bestAngle )
            {
                bestAngle = currentAngle;
                bestDirection = direction;
            }
        }
        
        return bestDirection;
    }
    
    /**
     * Returns the best direction from actor a1 away from actor a2 given a list of directions.
     * @param a1
     * @param a2
     * @param directions
     * @return 
     */
    protected Direction directionAwayFromActorFromList( Actor a1, Actor a2, List<Direction> directions )
    {
        Direction bestDirection = null;
        Double bestAngle = Double.MIN_VALUE;
        
        // Determine the best direction.
        Double angle = MathUtil.angle( a1.getPosition(), a2.getPosition() );
        
        for( Direction direction : directions )
        {
            Double currentAngle = java.lang.Math.abs( angle - direction.getAngle() );
            
            if( currentAngle > bestAngle )
            {
                bestAngle = currentAngle;
                bestDirection = direction;
            }
        }
        
        return bestDirection;  
    }
    
    /**
     * Returns the best direction towards the closest actor given a list of possible directions.
     * Furthermore the search can be refined if a distance and a percentage (i.e. if percentage is 1.0 than
     * the correct direction is always returned, if available).
     * @param actor
     * @param actors
     * @param directions
     * @param distance
     * @param percentage
     * @return 
     */
    protected Direction directionTowardsClosestActorFromList( Actor actor, List<? extends Actor> actors, List<Direction> directions, Integer distance, Double obeyance )
    {
        Actor closestActor = this.closestActor( actor, actors );
        
        if( closestActor != null )
        {
            if( MathUtil.distanceManhattan( actor.getPosition(), closestActor.getPosition() ) <= distance &&
                java.lang.Math.random() <= obeyance )
            {
                return this.directionTowardsActorFromList( actor, closestActor, directions );
            }
        }
        
        return null;
    }
    
    /**
     * Returns the best direction away from the closest actor given a list of directions.
     * @param actor
     * @param actors
     * @param directions
     * @param distance
     * @param percentage
     * @return 
     */
    protected Direction directionAwayFromClosestActorFromList( Actor actor, List<? extends Actor> actors, List<Direction> directions, Integer distance, Double obeyance )
    {
        Actor closestActor = this.closestActor( actor, actors );
        
        if( closestActor != null )
        {
            if( MathUtil.distanceManhattan( actor.getPosition(), closestActor.getPosition() ) <= distance &&
                java.lang.Math.random() <= obeyance )
            {
                return this.directionAwayFromActorFromList( actor, closestActor, directions );
            }
        }
        
        return null;
    }
}
