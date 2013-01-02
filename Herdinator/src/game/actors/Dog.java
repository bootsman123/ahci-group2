package game.actors;

import game.base.MovableActor;
import game.global.GameManager;
import game.util.SpriteSheetUtil;
import java.awt.Point;
import java.util.Iterator;
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
public class Dog extends MovableActor
{
    private static final String SPRITE_SHEET_FILE_PATH = "../Resources/Images/dogs_animation.png";
    private static final Integer SPRITE_SHEET_SPRITE_WIDTH = 32;
    private static final Integer SPRITE_SHEET_SPRITE_HEIGHT = 32;
    private static final Color SPRITE_SHEET_BACKGROUND_COLOR = new Color( 123, 198, 132 );
    
    private static final Double SPEED = 0.005;
    
    private Direction currentDirection;

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
            this.animation = this.animations.get( Direction.DOWN );
        }
        catch( SlickException e )
        {
            Logger.getLogger( Dog.class.getName() ).log( Level.SEVERE, e.getLocalizedMessage() );
        }        
    }
    
    @Override
    public void update( int delta )
    {
        super.update( delta );
        
        if( !this.isMoving() )
        {
            // Determine new direction.
            List<Direction> directions = this.getDirectionsToNonCollidableTiles();
            Iterator<Direction> iterator = directions.iterator();
            
            while( iterator.hasNext() )
            {
                Direction direction = iterator.next();
                
                if( GameManager.getInstance().getMap().isGoalTile( direction.toPosition( this.getPosition() ) ) )
                {
                    iterator.remove();
                }
            }
            
            // Pick a random element.
            Integer r = ( new Random() ).nextInt( directions.size() );
            this.currentDirection = directions.get( r ); 
        }
        
        this.move( this.currentDirection );
    }
}
