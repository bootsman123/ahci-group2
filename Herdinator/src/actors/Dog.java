package actors;

import global.GameManager;
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
        
    }
    
    public void render(Graphics g) {

       this.animation.draw( this.getX(), this.getY() );

         this.animation.draw( this.getX(), this.getY() );

    }

    @Override
    public void update(GameContainer container, int delta) { //de hond wil niet lopen 
        
       // this.moveRandom( delta );

         this.animation.update( delta );

    }

    
   public void setCookieLocation(Point2D.Float cookieLocation){
        this.cookieLocation = cookieLocation;
    }
    
}
