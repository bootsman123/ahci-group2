package game.actors;

import game.base.MovableActor;
import game.global.GameManager;
import game.util.SpriteSheetUtil;
import java.awt.Point;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
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

    private Direction currentDirection;

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
    
    
    
    /**

     * Vlucht voor de hond of de wolf als in de buurt, of loopt naar het love sheep als die in de buurt is
     * @param delta 
     *
    public void move( int delta ){ 
        if(euclideanDistance( this.getPosition(), wolfLocation) < 100) //TODO: fix de parameters
            moveAwayFrom( delta, this.getPosition(), wolfLocation);
        else if(euclideanDistance( this.getPosition(), dogLocation) < 100) //TODO: fix de parameters
            moveAwayFrom( delta, this.getPosition(), dogLocation);
        else if(euclideanDistance( this.getPosition(), loveSheepLocation) < 100) //TODO: fix de parameters
            moveTo( this.getPosition(), loveSheepLocation, delta);
        else
            moveRandom( delta);
     }
     * */
}