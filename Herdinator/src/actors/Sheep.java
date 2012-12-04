package actors;

import base.Actor;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import temp.Game2;

    
/**
 *
 * @author bootsman
 */
public class Sheep extends Actor
{
    private SpriteSheet sheepSpriteSheet;
    
    private Animation sheepSprite, sheepUp, sheepRight, sheepDown, sheepLeft;
    private double sheepX;
    private double sheepY;
    
    public Sheep(int sheepX, int sheepY) throws SlickException{
       this.sheepX = sheepX; 
       this.sheepY = sheepY; 
       this.sheepSpriteSheet = new SpriteSheet( "../Resources/Images/sheeps_animation.png", Game2.SPRITE_WIDTH, Game2.SPRITE_HEIGHT, new Color( 123, 198, 132 ) );
       
       this.sheepUp = new Animation( false );       
       this.sheepUp.addFrame( this.sheepSpriteSheet.getSprite( 0, 3 ), 150 );
       this.sheepUp.addFrame( this.sheepSpriteSheet.getSprite( 1, 3 ), 150 );
       this.sheepUp.addFrame( this.sheepSpriteSheet.getSprite( 2, 3 ), 150 );
              
       this.sheepRight = new Animation( false );
       this.sheepRight.addFrame( this.sheepSpriteSheet.getSprite( 0, 2 ), 150 );
       this.sheepRight.addFrame( this.sheepSpriteSheet.getSprite( 1, 2 ), 150 );
       this.sheepRight.addFrame( this.sheepSpriteSheet.getSprite( 2, 2 ), 150 );
       
       this.sheepDown = new Animation( false );
       this.sheepDown.addFrame( this.sheepSpriteSheet.getSprite( 0, 0 ), 150 );
       this.sheepDown.addFrame( this.sheepSpriteSheet.getSprite( 1, 0 ), 150 );
       this.sheepDown.addFrame( this.sheepSpriteSheet.getSprite( 2, 0 ), 150 );
       
       this.sheepLeft = new Animation( false );
       this.sheepLeft.addFrame( this.sheepSpriteSheet.getSprite( 0, 1 ), 150 );
       this.sheepLeft.addFrame( this.sheepSpriteSheet.getSprite( 1, 1 ), 150 );
       this.sheepLeft.addFrame( this.sheepSpriteSheet.getSprite( 2, 1 ), 150 );
       
       this.sheepSprite = this.sheepDown;
       
    }
    
    public void render( GameContainer container, Graphics g ) throws SlickException
    {
        this.sheepSprite.draw( (int)this.sheepX, (int)this.sheepY );
    }
    
    public void update( GameContainer container, int delta ) throws SlickException
    {        
        //this.sheepX = Math.max( 0, Math.min( this.sheepX, mapWidth - Game2.SPRITE_WIDTH ) );
        //this.sheepY = Math.max( 0, Math.min( this.sheepY, mapHeight- Game2.SPRITE_HEIGHT ) );
    }

    public void moveUp(int delta) {
        this.sheepSprite = this.sheepUp;
        this.sheepSprite.update( delta );
        this.sheepY -= delta * Game2.SPEED;
    }
    
    public void moveDown(int delta){
        this.sheepSprite = this.sheepDown;
            this.sheepSprite.update( delta );
            this.sheepY += delta * Game2.SPEED;
            
    }
    public void moveRight(int delta){
        
        this.sheepSprite = this.sheepRight;
        this.sheepSprite.update( delta );
        this.sheepX += delta * Game2.SPEED;
    }
    
    public void moveLeft(int delta){
        this.sheepSprite = this.sheepLeft;
            this.sheepSprite.update( delta );
            this.sheepX -= delta * Game2.SPEED;
    }
}    

