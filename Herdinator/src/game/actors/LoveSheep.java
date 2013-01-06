package game.actors;

import game.base.Map;
import game.base.MovableActor;
import game.global.GameManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import game.util.SpriteSheetUtil;
import java.awt.Point;
import java.util.List;
import java.util.Random;

/**
 *
 * @author roland
 */
public class LoveSheep extends MovableActor
{
    private static final String SPRITE_SHEET_FILE_PATH = "../Resources/Images/sheeps_animation.png";
    private static final Integer SPRITE_SHEET_SPRITE_WIDTH = 32;
    private static final Integer SPRITE_SHEET_SPRITE_HEIGHT = 32;
    private static final Color SPRITE_SHEET_BACKGROUND_COLOR = new Color( 123, 198, 132 );
    
    private static final Double SPEED = 0.003;
    
    private Direction currentDirection;

    /**
     * Constructor.
     * @param position
     * @throws SlickException 
     */
    public LoveSheep( Point position ) throws SlickException
    {
        super( position, LoveSheep.SPEED );
    }

    @Override
    public void init()
    {      
        try
        {
            // Setup animations.
            SpriteSheet spriteSheet = new SpriteSheet( LoveSheep.SPRITE_SHEET_FILE_PATH,
                                                       LoveSheep.SPRITE_SHEET_SPRITE_WIDTH,
                                                       LoveSheep.SPRITE_SHEET_SPRITE_HEIGHT,
                                                       LoveSheep.SPRITE_SHEET_BACKGROUND_COLOR );

            this.animations.put( Direction.UP, SpriteSheetUtil.getAnimation( spriteSheet, 6, 8, 3, 150 ) );
            this.animations.put( Direction.RIGHT, SpriteSheetUtil.getAnimation( spriteSheet, 6, 8, 2, 150 ) );
            this.animations.put( Direction.DOWN, SpriteSheetUtil.getAnimation( spriteSheet, 6, 8, 0, 150 ) );
            this.animations.put( Direction.LEFT, SpriteSheetUtil.getAnimation( spriteSheet, 6, 8, 1, 150 ) );
        }
        catch( SlickException e )
        {
            Logger.getLogger( LoveSheep.class.getName() ).log( Level.SEVERE, e.getLocalizedMessage() );
        }
        
        this.currentDirection = Direction.DOWN;
        this.animation = this.animations.get( this.currentDirection );
    }
    
    @Override
    public void update( int delta )
    {
        super.update( delta );
        
        System.out.println( "LoveSheep::update()" );
        
        if( !this.isMoving() )
        {            
            // Determine new direction.
            Direction direction;
            List<Direction> directions = this.directionsToNonCollidableTiles();
            Map map = GameManager.getInstance().getMap();
            
            // Check for cookies.
            direction = this.directionTowardsClosestActorFromList( this, map.getCookies(), directions, 200, 0.8 );
            
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
}
