package actors;

import base.GameManager;
import base.Map;
import base.MovableActor;
import java.awt.geom.Point2D;
import java.util.List;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import util.SpriteSheetUtil;

/**
 *
 * @author roland
 */
public class LoveSheep extends MovableActor
{
    private static final Float SPEED = 0.1f;
    private static final String SPRITE_SHEET_FILE_PATH = "../Resources/Images/sheeps_animation.png";
    private static final int SPRITE_SHEET_SPRITE_WIDTH = 32;
    private static final int SPRITE_SHEET_SPRITE_HEIGHT = 32;
    private static final Color SPRITE_SHEET_BACKGROUND_COLOR = new Color( 123, 198, 132 );

    private Animation animation, animationUp, animationRight, animationDown, animationLeft;

    SpriteSheet spriteSheet;

   
    private static final Float GOAL_MOVEMENT = 0.8f;
    private static final Float GOAL_DISTANCE = 100.0f;
    private static final Float MAX_DISTANCE_TO_LOVE_SHEEP = 100.0f ;

    private Point2D.Float closestCookie;
    private Point2D.Float goalPosition;
    
    public LoveSheep( Map map, Point2D.Float position ) throws SlickException
    {
        super( position, LoveSheep.SPEED );
        spriteSheet = new SpriteSheet( LoveSheep.SPRITE_SHEET_FILE_PATH,
                                                   LoveSheep.SPRITE_SHEET_SPRITE_WIDTH,
                                                   LoveSheep.SPRITE_SHEET_SPRITE_HEIGHT,
                                                   LoveSheep.SPRITE_SHEET_BACKGROUND_COLOR );

        this.animationUp = SpriteSheetUtil.getAnimation( spriteSheet, 6, 8, 3, 150 );
        this.animationRight = SpriteSheetUtil.getAnimation( spriteSheet,6, 8, 2, 150 );
        this.animationDown = SpriteSheetUtil.getAnimation( spriteSheet, 6, 8, 0, 150 );
        this.animationLeft = SpriteSheetUtil.getAnimation( spriteSheet, 6, 8, 1, 150 );

        this.animation = this.animationDown;
        closestCookie = new Point2D.Float(0.0f,0.0f);

        this.goalPosition = new Point2D.Float( 0, 0 );
//        this.animation = SpriteSheetUtil.getAnimation( spriteSheet, 6, 8, 0, 150 );
    }

    private void moveRandom( int delta )
    {
       
        if( Math.abs( this.getX() - this.goalPosition.x ) + Math.abs( this.getY() - this.goalPosition.y ) < LoveSheep.GOAL_MOVEMENT )
        {
           
            this.determineRandomPosition();
        }
        else{
            System.out.println("Updating the lovesheep" + closestCookie.getX() + "  "  + closestCookie.getY());
            // Move left or right.
            if( Math.abs( this.getX() - this.goalPosition.x ) > LoveSheep.GOAL_MOVEMENT / 2 )
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

        if( Math.abs( this.getX() - this.goalPosition.x ) + Math.abs( this.getY() - this.goalPosition.y ) > LoveSheep.MAX_DISTANCE_TO_LOVE_SHEEP )
        {
            this.determineRandomPosition();
        }

        this.animation.update( delta );

        //@TODO: Fugly for now.
        this.getPosition().x = Math.max( 0, Math.min( this.getPosition().x, GameManager.getInstance().getMap().getMapWidth() ) );
        this.getPosition().y = Math.max( 0, Math.min( this.getPosition().y, GameManager.getInstance().getMap().getMapHeight() ) );
    }

    public void updateCookieLocation(List<Cookie> currentCookies){
        Cookie newClosestCookie = currentCookies.get(0);
        for(Cookie cookie : currentCookies){
            
            if (Math.sqrt(Math.pow(cookie.getX()-this.getX(), 2) + Math.pow(cookie.getY()-this.getY(), 2)) < Math.sqrt(Math.pow(newClosestCookie.getX()-this.getX(), 2) + Math.pow(newClosestCookie.getY()-this.getY(), 2))){
                newClosestCookie = cookie ; 
            }
        }
        this.closestCookie = new Point2D.Float(newClosestCookie.getX(),newClosestCookie.getY());
        
        
    }
    private void determineRandomPosition()
    {
        if(closestCookie!=null ){
            
            this.goalPosition = closestCookie;
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
        this.goalPosition.x = Math.max( 0, Math.min( this.goalPosition.x, GameManager.getInstance().getMap().getMapWidth()-LoveSheep.SPRITE_SHEET_SPRITE_WIDTH ) );
        this.goalPosition.y = Math.max( 0, Math.min( this.goalPosition.y, GameManager.getInstance().getMap().getMapHeight()-LoveSheep.SPRITE_SHEET_SPRITE_HEIGHT ) );
    }
    @Override
    public void render(Graphics g) {
       this.animation.draw( this.getX(), this.getY() );
    }

    @Override
    public void update(GameContainer container, int delta) {
       
        this.moveRandom( delta );
    }
    
}
