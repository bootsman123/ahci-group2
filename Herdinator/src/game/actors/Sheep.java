package game.actors;

import game.base.Map;
import game.base.MovableActor;
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
    
/**
 *
 * @author bootsman
 */
public class Sheep extends MovableActor
{
    private static final String SPRITE_SHEET_FILE_PATH = "../Resources/Images/Animations/sheeps_animation.png";
    private static final Integer SPRITE_SHEET_SPRITE_WIDTH = 32;
    private static final Integer SPRITE_SHEET_SPRITE_HEIGHT = 32;
    private static final Color SPRITE_SHEET_BACKGROUND_COLOR = new Color( 123, 198, 132 );
    
    private static final Double SPEED = 0.0050;
    
    // Distances in Manhatten tiles.
    private static final Integer OTHER_SHEEP_DISTANCE = 6;
    private static final Double OTHER_SHEEP_OBEYANCE = 0.5;
    
    private static final Integer DOG_DISTANCE = 10;
    private static final Double DOG_OBEYANCE = 1.0;
    
    private static final Integer LOVE_SHEEP_DISTANCE = 8;
    private static final Double LOVE_SHEEP_OBEYANCE = 0.7;
    
    private Direction currentDirection;
    
    private Boolean isInGoalTile;

    /**
     * Constructor.
     * @param position
     * @throws SlickException 
     */
    public Sheep( Point position ) throws SlickException
    {
        super( position, Sheep.SPEED );
    }
    
    @Override
    public void init()
    {    
        try
        {
            // Setup animations.
            SpriteSheet spriteSheet = new SpriteSheet( Sheep.SPRITE_SHEET_FILE_PATH,
                                                       Sheep.SPRITE_SHEET_SPRITE_WIDTH,
                                                       Sheep.SPRITE_SHEET_SPRITE_HEIGHT,
                                                       Sheep.SPRITE_SHEET_BACKGROUND_COLOR );

            this.animations.put( Direction.UP, SpriteSheetUtil.getAnimation( spriteSheet, 0, 2, 3, 150 ) );
            this.animations.put( Direction.RIGHT, SpriteSheetUtil.getAnimation( spriteSheet, 0, 2, 2, 150 ) );
            this.animations.put( Direction.DOWN, SpriteSheetUtil.getAnimation( spriteSheet, 0, 2, 0, 150 ) );
            this.animations.put( Direction.LEFT, SpriteSheetUtil.getAnimation( spriteSheet, 0, 2, 1, 150 ) );
        }
        catch( SlickException e )
        {
            Logger.getLogger( Sheep.class.getName() ).log( Level.SEVERE, e.getLocalizedMessage() );
        }
        
        this.currentDirection = Direction.DOWN;
        this.animation = this.animations.get( this.currentDirection );
        
        this.isInGoalTile = Boolean.FALSE;
    }
    
    @Override
    public void update( int delta )
    {        
        super.update( delta );

        if( !this.isMoving() )
        {
            Map map = GameManager.getInstance().getMap();
            Direction direction = null;
            
            // Check if the sheep is in the goal tile.
            if( map.isGoalTile( this.getPosition() ) )
            {
                this.isInGoalTile = Boolean.TRUE;
            }

            // Determine new direction.
            if( this.isInGoalTile )
            {
                // Make sure the sheep stays within the goal tile.
                List<Direction> directions = this.directionsToNonCollidableGoalTiles( this.getPosition() );
                
                // Check if the sheep can move at all.
                if( directions.isEmpty() )
                {
                    return;
                }
                
                Integer r = ( new Random() ).nextInt( directions.size() ); 
                direction = directions.get( r );
            }
            else
            {
                List<Direction> directions = this.directionsToNonCollidableTiles( this.getPosition() );
                
                // Check if the sheep can move at all.
                if( directions.isEmpty() )
                {
                    return;
                }
                
                // Check for dogs.
                direction = this.directionAwayFromClosestActorFromList( this, map.getDogs(), directions, Sheep.DOG_DISTANCE, Sheep.DOG_OBEYANCE );  

                if( direction == null )
                {
                    // Check for love sheeps.
                    direction = this.directionTowardsClosestActorFromList( this, map.getLoveSheeps(), directions, Sheep.LOVE_SHEEP_DISTANCE, Sheep.LOVE_SHEEP_OBEYANCE );

                    if( direction == null )
                    {
                        // Check for other sheep.
                        direction = this.directionTowardsClosestActorFromList( this, map.getSheeps(), directions, Sheep.OTHER_SHEEP_DISTANCE, Sheep.OTHER_SHEEP_OBEYANCE );
                    }
                    
                }
                
                if( direction == null )
                {
                    Integer r = ( new Random() ).nextInt( directions.size() );
                    direction = directions.get( r );
                }
            }
            
            this.currentDirection = direction;
        }
        
        this.move( this.currentDirection );
    }
    
    /**
     * Returns when the sheep is in the goal tile.
     * @return 
     */
    public Boolean isInGoalTile()
    {
        return this.isInGoalTile;
    }
}