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
    private static final String SPRITE_SHEET_FILE_PATH = "../Resources/Images/sheeps_animation.png";
    private static final Integer SPRITE_SHEET_SPRITE_WIDTH = 32;
    private static final Integer SPRITE_SHEET_SPRITE_HEIGHT = 32;
    private static final Color SPRITE_SHEET_BACKGROUND_COLOR = new Color( 123, 198, 132 );
    
    private static final Double SPEED = 0.001;
    
    // Distances in Manhatten tiles.
    private static final Integer OTHER_SHEEP_DISTANCE = 6;
    private static final Double OTHER_SHEEP_PERCENTAGE = 0.5;
    
    private static final Integer DOG_DISTANCE = 12;
    private static final Double DOG_PERCENTAGE = 0.8;

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
        this.isInGoalTile = false;
    }
    
    @Override
    public void update( int delta )
    {
        super.update( delta );
        
        // Check if in goal.
        if( this.isInGoalTile )
        {
            // Don't do anything for now.
            return;
        }
        else
        {
            if( !this.isMoving() )
            {
                // Check if in goal tile.
                Map map = GameManager.getInstance().getMap();

                if( map.isGoalTile( this.getPosition() ) )
                {
                    this.isInGoalTile = true;
                    return;
                }
                else
                {
                    // Determine new direction.
                    Direction direction;
                    List<Direction> directions = this.directionsToNonCollidableTiles();
                    
                    // Check for a dog.
                    direction = this.directionAwayFromClosestActorFromList( this, map.getDogs(), directions, Sheep.DOG_DISTANCE, Sheep.DOG_PERCENTAGE );
                    
                    if( direction == null )
                    {
                        // Check for other sheep.
                        direction = this.directionTowardsClosestActorFromList( this, map.getSheeps(), directions, Sheep.OTHER_SHEEP_DISTANCE, Sheep.OTHER_SHEEP_PERCENTAGE );
                    }
                    
                    if( direction == null )
                    {
                        // Pick a random element.
                        Integer r = ( new Random() ).nextInt( directions.size() );
                        direction = directions.get( r );
                    }
                    
                    this.currentDirection = direction;
                }
            }

            this.move( this.currentDirection );
        }
    }
        
    /*
    Iterator<Direction> iterator = directions.iterator();

    while( iterator.hasNext() )
    {
        Direction direction = iterator.next();

        if( GameManager.getInstance().getMap().isGoalTile( direction.toPosition( this.getPosition() ) ) )
        {
            iterator.remove();
        }
    }
    */
}