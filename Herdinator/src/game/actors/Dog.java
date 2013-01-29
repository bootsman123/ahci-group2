package game.actors;

import game.base.Actor;
import game.base.Map;
import game.base.MovableActor;
import game.base.listeners.UseListener;
import game.global.GameManager;
import game.util.SpriteSheetUtil;
import java.awt.Point;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.util.pathfinding.Path;

/**
 * Dog implementation.
 * @author Bas Bootsma
 */
public class Dog extends MovableActor implements UseListener
{
    private static final String SPRITE_SHEET_FILE_PATH = "../Resources/Images/Animations/dogs_animation.png";
    private static final Integer SPRITE_SHEET_SPRITE_WIDTH = 32;
    private static final Integer SPRITE_SHEET_SPRITE_HEIGHT = 32;
    private static final Color SPRITE_SHEET_BACKGROUND_COLOR = new Color( 123, 198, 132 );
    
    private static final Double SPEED = 0.010;
    
    //private static final Integer WHISTLE_DISTANCE = 200;
    //private static final Double WHISTLE_OBEYANCE = 1.0;
    
    private Direction currentDirection;
    
    private Boolean hasReachedPathDestination;
    private Path path;
    private Integer pathIndex;

    /**
     * Constructor.
     * @param position
     * @throws SlickException 
     */
    public Dog( Point position ) throws SlickException
    {
        super( position, Dog.SPEED );        
    }
    
    @Override
    public void init()
    {    
        try
        {
            // Setup animations.
            SpriteSheet spriteSheet = new SpriteSheet( Dog.SPRITE_SHEET_FILE_PATH,
                                                       Dog.SPRITE_SHEET_SPRITE_WIDTH,
                                                       Dog.SPRITE_SHEET_SPRITE_HEIGHT,
                                                       Dog.SPRITE_SHEET_BACKGROUND_COLOR );

            this.animations.put( Direction.UP, SpriteSheetUtil.getAnimation( spriteSheet, 0, 2, 3, 100 ) );
            this.animations.put( Direction.RIGHT, SpriteSheetUtil.getAnimation( spriteSheet, 0, 2, 2, 100 ) );
            this.animations.put( Direction.DOWN, SpriteSheetUtil.getAnimation( spriteSheet, 0, 2, 0, 100 ) );
            this.animations.put( Direction.LEFT, SpriteSheetUtil.getAnimation( spriteSheet, 0, 2, 1, 100 ) );
        }
        catch( SlickException e )
        {
            Logger.getLogger( Dog.class.getName() ).log( Level.SEVERE, e.getLocalizedMessage() );
        }
        
        this.currentDirection = Direction.DOWN;
        this.animation = this.animations.get( this.currentDirection );
        
        this.hasReachedPathDestination = Boolean.FALSE;
        this.path = null;
        this.pathIndex = 0;
    }
    
    @Override
    public void update( int delta )
    {
        super.update( delta );
        
        if( !this.isMoving() )
        {           
            // Check if there is a path to be followed.
            if( this.path != null && !this.hasReachedPathDestination )
            {
                Path.Step step = this.path.getStep( this.pathIndex );
                this.pathIndex++;

                // Check if the step is still valid.
                Map map = GameManager.getInstance().getMap();

                if( map.isBlocked( new Point( step.getX(), step.getY() ) ) )
                {
                    // Calculate a new path.
                    Path.Step finalStep = this.path.getStep( this.path.getLength() - 1 );
                    Point finalPosition = new Point( finalStep.getX(), finalStep.getY() );

                    this.path = map.pathTo( this.getPosition(), finalPosition );
                    this.pathIndex = 0;
                }

                // Determine the direction.

                // @TODO: ...
            }
            
            // Determine new direction.
            Direction direction = null;         
            List<Direction> directions = this.directionsToNonCollidableTiles( this.getPosition() );

            // Check if the dog can move at all.
            if( directions.isEmpty() )
            {
                return;
            }
            
            // Check whistles.
            //direction = this.directionTowardsClosestActorFromList( this, map.getWhistles(), directions, Dog.WHISTLE_DISTANCE, Dog.WHISTLE_OBEYANCE );
            
            if( direction == null )
            {                
                // Pick a random element.
                Integer r = ( new Random() ).nextInt( directions.size() );
                direction = directions.get( r ); 
            }
            
            this.currentDirection = direction;
        }
        
        this.move( this.currentDirection );
    }

    @Override
    public void onUse( Actor actor )
    {
        // A whistle has been pressed.
        this.hasReachedPathDestination = Boolean.FALSE;
        this.path = GameManager.getInstance().getMap().pathTo( this.getPosition(), actor.getPosition() );
        this.pathIndex = 0;
    }
}
