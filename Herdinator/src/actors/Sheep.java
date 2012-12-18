package actors;

import base.Map;
import base.MovableActor;
import java.awt.geom.Point2D;
import java.util.Random;
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
        super( map, position, Sheep.SPEED );
                
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
        this.determineRandomPosition();
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
        Map map = getMap();
        
        if(map.isGoalTile(this.getPosition())){
            this.hasReachedGoal = true;
            this.goalPosition = this.getPosition();
        }
        else{
            if(this.hasReachedGoal){
               this.determineRandomPosition();
            }
        }

        
        if( Math.abs( this.getX() - this.goalPosition.x ) + Math.abs( this.getY() - this.goalPosition.y ) < Sheep.GOAL_MOVEMENT )

        if( Math.abs( this.getX() - this.goalPosition.x ) +
            Math.abs( this.getY() - this.goalPosition.y ) < Sheep.GOAL_MOVEMENT )

        {
            this.determineRandomPosition();
        }
        else{
            // Move left or right.
            if( Math.abs( this.getX() - this.goalPosition.x ) > Sheep.GOAL_MOVEMENT / 2 )
            {
                if( this.getX() > this.goalPosition.x )
                {
                    this.animation = this.animationLeft;
                    this.moveLeft( delta );
                }
                else
                {
                    this.animation = this.animationRight;
                    this.moveRight( delta );
                }
            }
            // Move up or down.
            else
            {
                if( this.getY() > this.goalPosition.y )
                {
                    this.animation = this.animationUp;
                    this.moveUp( delta );
                }
                else
                {
                    this.animation = this.animationDown;
                    this.moveDown( delta );
                }
            }
        }

        if( Math.abs( this.getX() - this.goalPosition.x ) + Math.abs( this.getY() - this.goalPosition.y ) > Sheep.MAX_DISTANCE_TO_LOVE_SHEEP )
        {
            this.determineRandomPosition();
        }

        this.animation.update( delta );
        
        //@TODO: Fugly for now.
        this.getPosition().x = Math.max( 0, Math.min( this.getPosition().x, this.getMap().getMapWidth() ) );
        this.getPosition().y = Math.max( 0, Math.min( this.getPosition().y, this.getMap().getMapHeight() ) );
    }
    
    private void determineRandomPosition()
    {
        if(loveSheepLocation!=null && !hasReachedGoal){
            this.goalPosition = loveSheepLocation;
        }
        else{
            /*
            Random randomGenerator = new Random();
            do{
                this.goalPosition.x = this.getPosition().x + Sheep.GOAL_DISTANCE * ( randomGenerator.nextInt(3) - 1 );
                this.goalPosition.y = this.getPosition().y + Sheep.GOAL_DISTANCE * ( randomGenerator.nextInt(3) - 1 );
            }while(!this.getMap().isGoalTile(this.goalPosition));*/
        }
        /*
        
        
        */
        //@TODO: Fugly.
        this.goalPosition.x = Math.max( 0, Math.min( this.goalPosition.x, this.getMap().getMapWidth()-Sheep.SPRITE_SHEET_SPRITE_WIDTH ) );
        this.goalPosition.y = Math.max( 0, Math.min( this.goalPosition.y, this.getMap().getMapHeight()-Sheep.SPRITE_SHEET_SPRITE_HEIGHT ) );
    }
    
    /**
     * Vlucht voor de hond of de wolf als in de buurt, of loopt naar het love sheep als die in de buurt is
     * @param delta 
     */
    public void move( int delta ){ 
        if(euclideanDistance( this.getPosition(), wolfLocation) < 200) //TODO: fix de parameters
            moveAwayFrom( delta, this.getPosition(), wolfLocation);
        else if(euclideanDistance( this.getPosition(), dogLocation) < 200) //TODO: fix de parameters
            moveAwayFrom( delta, this.getPosition(), dogLocation);
        else if(euclideanDistance( this.getPosition(), loveSheepLocation) < 200) //TODO: fix de parameters
            moveTo( delta, this.getPosition(), loveSheepLocation);
        else
            moveRandom( delta);
     }
    
    public void setLoveSheepLocation(Point2D.Float loveSheepLocation){
        this.loveSheepLocation = loveSheepLocation;
    }
    
    public void setDogLocation(Point2D.Float dogLocation){
        this.dogLocation = dogLocation;
    }
    
    public void setWolfLocation(Point2D.Float wolfLocation){
        this.wolfLocation = wolfLocation;
    }
    
    public boolean getHasReachedGoal(){
        return this.hasReachedGoal;
    }

    
}