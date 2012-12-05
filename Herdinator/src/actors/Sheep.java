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
    private static final double DELTAMOVEMENT = 0.1;
    private static final double MOVEMENTGOAL=0.2;
    private static final double RANDOMDISTANCE=100;
    private SpriteSheet sheepSpriteSheet;
    
    private Animation sheepSprite, sheepUp, sheepRight, sheepDown, sheepLeft;
    private double sheepX;
    private double sheepY;
    
    private double goalX;
    private double goalY;
    private int mapWidth;
    private int mapHeight;
    public Sheep(int sheepX, int sheepY, int mapWidth, int mapHeight) throws SlickException{
       this.sheepX = sheepX; 
       this.sheepY = sheepY; 
       this.mapWidth = mapWidth;
       this.mapHeight = mapHeight;
       
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
       this.determineRandomLocation();
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


    public void moveRandom(int delta){
        if (Math.abs(this.sheepX-this.goalX)+Math.abs(this.sheepY-this.goalY)<MOVEMENTGOAL){
            determineRandomLocation();
        }
        if(Math.abs(this.sheepX-this.goalX)>(MOVEMENTGOAL/2)){
            if(this.sheepX>this.goalX){
                this.moveLeft(delta);
            }
            else{
                this.moveRight(delta);
            }
        }
        else{
            if(this.sheepY>this.goalY){
                this.moveUp(delta);
            }
            else{
                this.moveDown(delta);
            }
        }
    }

    private void determineRandomLocation(){
        System.out.println("Determining random location") ;
        
        this.goalX+=RANDOMDISTANCE*(Math.random()-0.5);
        this.goalY+=RANDOMDISTANCE*(Math.random()-0.5);
        
        this.goalX = Math.max( 0, Math.min( this.goalX, mapWidth - Game2.SPRITE_WIDTH ) );
        this.goalY = Math.max( 0, Math.min( this.goalY, mapHeight - Game2.SPRITE_HEIGHT ) );
    }
    public void update() {
        this.sheepX = Math.max( 0, Math.min( this.sheepX, mapWidth - Game2.SPRITE_WIDTH ) );
        this.sheepY = Math.max( 0, Math.min( this.sheepY, mapHeight- Game2.SPRITE_HEIGHT ) );
        System.out.println("SheepY: " + this.sheepY + " GoalY " + this.goalY + " SheepX " + this.sheepX + " GoalX " + this.goalX);
    }

}    

