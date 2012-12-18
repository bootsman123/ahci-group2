package actors;

import base.GameManager;
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
public class Dog extends MovableActor
{
    private static final Float SPEED = 0.1f;
    private static final String SPRITE_SHEET_FILE_PATH = "../Resources/Images/dogs_animation.png";
    private static final int SPRITE_SHEET_SPRITE_WIDTH = 32;
    private static final int SPRITE_SHEET_SPRITE_HEIGHT = 32;
    private static final Color SPRITE_SHEET_BACKGROUND_COLOR = new Color( 123, 198, 132 );


    private static final Float GOAL_DISTANCE = 100.0f;
    private static final Float GOAL_MOVEMENT = 0.8f;
    private static final Float MAX_DISTANCE_TO_LOVE_SHEEP = 100.0f ;

    SpriteSheet spriteSheet;
    private Animation animation, animationUp, animationRight, animationDown, animationLeft;

    private Point2D.Float goalPosition;
    private Point2D.Float cookieLocation;
    

    
    public Dog(Point2D.Float position ) throws SlickException
    {
        super( position, Dog.SPEED );

        spriteSheet = new SpriteSheet( Dog.SPRITE_SHEET_FILE_PATH,
                                                   Dog.SPRITE_SHEET_SPRITE_WIDTH,
                                                   Dog.SPRITE_SHEET_SPRITE_HEIGHT,
                                                   Dog.SPRITE_SHEET_BACKGROUND_COLOR );

        this.animation = SpriteSheetUtil.getAnimation( spriteSheet, 0, 2, 0, 150 );


         
        this.animationUp = SpriteSheetUtil.getAnimation( spriteSheet, 0, 2, 3, 150 );
        this.animationRight = SpriteSheetUtil.getAnimation( spriteSheet, 0, 2, 2, 150 );
        this.animationDown = SpriteSheetUtil.getAnimation( spriteSheet, 0, 2, 0, 150 );
        this.animationLeft = SpriteSheetUtil.getAnimation( spriteSheet, 0, 2, 1, 150 );

        this.animation = this.animationDown;


        this.goalPosition = new Point2D.Float( this.getX(), this.getY() );
        this.determineRandomPosition();
    }

    private void determineRandomPosition()
    {
       
           
        Random randomGenerator = new Random();

        this.goalPosition.x = this.getPosition().x + Dog.GOAL_DISTANCE * ( randomGenerator.nextInt(3) - 1 );
        this.goalPosition.y = this.getPosition().y + Dog.GOAL_DISTANCE * ( randomGenerator.nextInt(3) - 1 );

        //@TODO: Fugly.
        if(GameManager.getInstance().getMap()!=null){

            this.goalPosition.x = Math.max( 0, Math.min( this.goalPosition.x, GameManager.getInstance().getMap().getMapWidth()-Dog.SPRITE_SHEET_SPRITE_WIDTH ) );
            this.goalPosition.y = Math.max( 0, Math.min( this.goalPosition.y, GameManager.getInstance().getMap().getMapHeight()-Dog.SPRITE_SHEET_SPRITE_HEIGHT ) );
        }
        this.animation = SpriteSheetUtil.getAnimation( spriteSheet, 6, 8, 0, 150 );

    }

    @Override
    public void render(Graphics g) {

       this.animation.draw( this.getX(), this.getY() );

         this.animation.draw( this.getX(), this.getY() );

    }

    @Override
    public void update(GameContainer container, int delta) { //de hond wil niet lopen 
        
       // this.moveRandom( delta );

         this.animation.update( delta );

    }

    

    private void moveRandom( int delta )
    { 
        if( Math.abs( this.getX() - this.goalPosition.x ) + Math.abs( this.getY() - this.goalPosition.y ) < Dog.GOAL_MOVEMENT )
        {
            this.determineRandomPosition();
        }
        else{
            // Move left or right.
            if( Math.abs( this.getX() - this.goalPosition.x ) > Dog.GOAL_MOVEMENT / 2 )
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

        if( Math.abs( this.getX() - this.goalPosition.x ) + Math.abs( this.getY() - this.goalPosition.y ) > Dog.MAX_DISTANCE_TO_LOVE_SHEEP )
        {
            this.determineRandomPosition();
        }

        this.animation.update( delta );

        //@TODO: Fugly for now.
        this.getPosition().x = Math.max( 0, Math.min( this.getPosition().x, GameManager.getInstance().getMap().getMapWidth() ) );
        this.getPosition().y = Math.max( 0, Math.min( this.getPosition().y, GameManager.getInstance().getMap().getMapHeight() ) );
    }

    

    public void setCookieLocation(Point2D.Float cookieLocation){
        this.cookieLocation = cookieLocation;
    }
    
}
