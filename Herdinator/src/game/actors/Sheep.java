package game.actors;

import game.base.MovableActor;
import game.global.GameManager;
import game.util.Distance;
import game.util.SpriteSheetUtil;
import java.awt.geom.Point2D;
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
    
    private static final Double SPEED = 0.1;
    private static final Double DISTANCE_TO_GOAL = 2.0;
    private static final Double DISTANCE_GOAL_FROM_POSITION = 100.0;

    private Point2D.Double goalPosition;

    /**
     * Constructor.
     * @param position
     * @throws SlickException 
     */
    public Sheep( Point2D.Double position ) throws SlickException
    {
        super( position, Sheep.SPEED );
    }
    
    @Override
    public void init()
    {
        super.init();
               
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
            this.setAnimation( this.animations.get( Direction.DOWN ) );
        }
        catch( SlickException e )
        {
            Logger.getLogger( Sheep.class.getName() ).log( Level.SEVERE, e.getLocalizedMessage() );
        }
        
        this.goalPosition = this.determineRandomPosition( this.getPosition() );
    }
    
    @Override
    public void update( int delta )
    {
        super.update( delta );
        
        // Check if the sheep is close to its goal.
        if( Distance.euclidian( this.getPosition(), this.goalPosition ) < Sheep.DISTANCE_TO_GOAL )
        {
            this.goalPosition = this.determineRandomPosition( this.getPosition() );
        }
        
        this.moveTo( this.goalPosition );
    }
    
    private Point2D.Double determineRandomPosition( Point2D.Double position )
    {
        Double x = 0.0;
        Double y = 0.0;
        
        x = Sheep.DISTANCE_GOAL_FROM_POSITION * ( Math.random() - 0.5 );
        y = Sheep.DISTANCE_GOAL_FROM_POSITION * ( Math.random() - 0.5 );
        
        x = Math.max( 0, Math.min( x, GameManager.getInstance().getMap().getMapWidth() ) );
        y = Math.max( 0, Math.min( y, GameManager.getInstance().getMap().getMapHeight() ) );

        return new Point2D.Double( position.x + x, position.y + y );
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