package actors;

import base.MovableActor;
import java.awt.geom.Point2D;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import util.SpriteSheetUtil;
    
/**
 *
 * @author bootsman
 */
public class Sheep extends MovableActor
{
    private static final String SPRITE_SHEET_FILE_PATH = "../Resources/Images/sheeps_animation.png";
    private static final int SPRITE_SHEET_SPRITE_WIDTH = 32;
    private static final int SPRITE_SHEET_SPRITE_HEIGHT = 32;
    private static final Color SPRITE_SHEET_BACKGROUND_COLOR = new Color( 123, 198, 132 );
    
    private static final Float SPEED = 0.1f;
    private static final Float GOAL_MOVEMENT = 0.8f;
    private static final Float GOAL_DISTANCE = 100.0f;
    private static final Float MAX_DISTANCE_TO_LOVE_SHEEP = 100.0f ;

    private Boolean hasReachedGoal;
    private Point2D.Float goalPosition;

    /**
     * Constructor.
     * @param position
     * @throws SlickException 
     */
    public Sheep( Point2D.Float position ) throws SlickException
    {
        super( position, Sheep.SPEED );

        this.hasReachedGoal = false;  
        this.goalPosition = new Point2D.Float( this.getX(), this.getY() );
        //this.determineRandomPosition();
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
    }
    
    @Override
    public void update( int delta )
    {
        super.update( delta );
        
        this.moveRandom();
    }
    
    private void moveRandom()
    {
        this.move( Direction.DOWN );
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
    
    /**
     * Returns if this sheep has reached the goal
     * @return
     */
    public boolean getHasReachedGoal()
    {
        return this.hasReachedGoal;
    }
}