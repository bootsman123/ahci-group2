package actors;

import base.GameManager;
import base.Map;
import base.MovableActor;
import java.awt.geom.Point2D;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
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
    private int moveCount = 0; //hoe vaak hij een kant op loopt voor moveRandom
    double direction = 0; //de richting die het schaap op aan het lopen is
    private boolean hasReachedGoal ;
    
    private Animation animation, animationUp, animationRight, animationDown, animationLeft;

    private Point2D.Float goalPosition;

    private Point2D.Float loveSheepLocation,  dogLocation, wolfLocation;

    /**
     * Constructor.
     * @param position
     * @throws SlickException 
     */
    public Sheep( Map map, Point2D.Float position ) throws SlickException
    {
        super(position, Sheep.SPEED );
                
        // Setup animations.
        SpriteSheet spriteSheet = new SpriteSheet( Sheep.SPRITE_SHEET_FILE_PATH,
                                                   Sheep.SPRITE_SHEET_SPRITE_WIDTH,
                                                   Sheep.SPRITE_SHEET_SPRITE_HEIGHT,
                                                   Sheep.SPRITE_SHEET_BACKGROUND_COLOR );
        
        this.animationUp = SpriteSheetUtil.getAnimation( spriteSheet, 0, 2, 3, 150 );
        this.animationRight = SpriteSheetUtil.getAnimation( spriteSheet, 0, 2, 2, 150 );
        this.animationDown = SpriteSheetUtil.getAnimation( spriteSheet, 0, 2, 0, 150 );
        this.animationLeft = SpriteSheetUtil.getAnimation( spriteSheet, 0, 2, 1, 150 );
        
        this.animation = this.animationDown;

        this.hasReachedGoal = false;
        
        this.goalPosition = new Point2D.Float( this.getX(), this.getY() );
        //this.determineRandomPosition();
    }
    
    @Override
    public void render( Graphics g )
    {
        this.animation.draw( this.getX(), this.getY() );
    }
    
    @Override
    public void update( GameContainer container, int delta )
    {        
        //this.moveUp(delta);
        this.move( delta );
       // this.moveRandom( delta );
        
    }
    
    private void moveRandom( int delta )
    {
        if(moveCount == 9){
            direction = Math.random();
            moveCount = 0;
        }
        if(direction<0.25)
            moveUp( delta );
        else if(direction<0.5)
            moveDown( delta );
        else if(direction<0.75)
            moveLeft( delta );
        else
            moveRight( delta );

        moveCount++;
        
        
<<<<<<< HEAD
    }   
   
            /**
=======
        */
        //@TODO: Fugly.
        if(GameManager.getInstance().getMap()!=null){
            this.goalPosition.x = Math.max( 0, Math.min( this.goalPosition.x, GameManager.getInstance().getMap().getMapWidth()-Sheep.SPRITE_SHEET_SPRITE_WIDTH ) );
            this.goalPosition.y = Math.max( 0, Math.min( this.goalPosition.y, GameManager.getInstance().getMap().getMapHeight()-Sheep.SPRITE_SHEET_SPRITE_HEIGHT ) );
        }
    }
    
    /**
>>>>>>> 63b9a1649171ef4b0dd8436bc261ea189066a06d
     * Vlucht voor de hond of de wolf als in de buurt, of loopt naar het love sheep als die in de buurt is
     * @param delta 
     */
    public void move( int delta ){ 
        if(euclideanDistance( this.getPosition(), wolfLocation) < 100) //TODO: fix de parameters
            moveAwayFrom( delta, this.getPosition(), wolfLocation);
        else if(euclideanDistance( this.getPosition(), dogLocation) < 100) //TODO: fix de parameters
            moveAwayFrom( delta, this.getPosition(), dogLocation);
        else if(euclideanDistance( this.getPosition(), loveSheepLocation) < 100) //TODO: fix de parameters
            moveTo( delta, this.getPosition(), loveSheepLocation);
        else
            moveRandom( delta);
     }

    /**
     * Sets the location of our lovesheep
     * @param loveSheepLocation
     */
    public void setLoveSheepLocation(Point2D.Float loveSheepLocation){
        this.loveSheepLocation = loveSheepLocation;
    }

    /**
     * Sets the location of the dog
     * @param dogLocation
     */
    public void setDogLocation(Point2D.Float dogLocation){
        this.dogLocation = dogLocation;
    }

    /**
     * Sets the location of the wolf
     * @param wolfLocation
     */
    public void setWolfLocation(Point2D.Float wolfLocation){
        this.wolfLocation = wolfLocation;
    }

    /**
     * Returns if this sheep has reached the goal
     * @return
     */
    public boolean getHasReachedGoal(){
        return this.hasReachedGoal;
    }

    
}