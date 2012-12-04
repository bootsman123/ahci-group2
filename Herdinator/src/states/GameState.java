package states;

import base.Level;
import base.Player;
import java.util.List;
import levels.Level1;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import temp.Game2;

/**
 *
 * @author bootsman
 */
public class GameState extends BasicGameState
{
    private Level currentLevel; 
    private List<Level> levels; //@TODO: load levels
    private List<Player> players; //@TODO: add list of players
    
    private SpriteSheet sheepSpriteSheet;
    
    private Animation sheepSprite, sheepUp, sheepRight, sheepDown, sheepLeft;
    private double sheepX;
    private double sheepY;
    
    public GameState() throws SlickException
    {
       // this.currentLevel = new Level1() ; 
        
    }
    
    
    @Override
    public int getID()
    {
       return 1337; //@TODO: change this to a normal ID
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        this.currentLevel = new Level1() ; 
        
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
       this.sheepX = 0;
       this.sheepY = 0;
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
         currentLevel.render() ; 
         this.sheepSprite.draw( (int)this.sheepX, (int)this.sheepY );
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        System.out.println("whoo, updatesd") ; 
          Input input = container.getInput();
        
        if( input.isKeyDown( Input.KEY_UP ) )
        {
            this.sheepSprite = this.sheepUp;
            this.sheepSprite.update( delta );
            this.sheepY -= delta * Game2.SPEED;
        }
        else if( input.isKeyDown( Input.KEY_RIGHT ) )
        {
            this.sheepSprite = this.sheepRight;
            this.sheepSprite.update( delta );
            this.sheepX += delta * Game2.SPEED;
        }
        else if( input.isKeyDown( Input.KEY_DOWN ) )
        {
            this.sheepSprite = this.sheepDown;
            this.sheepSprite.update( delta );
            this.sheepY += delta * Game2.SPEED;
        }
        else if( input.isKeyDown( Input.KEY_LEFT ) )
        {
            this.sheepSprite = this.sheepLeft;
            this.sheepSprite.update( delta );
            this.sheepX -= delta * Game2.SPEED;
        }
        
        this.sheepX = Math.max( 0, Math.min( this.sheepX, this.currentLevel.getMap().getMapWidth() - Game2.SPRITE_WIDTH ) );
        this.sheepY = Math.max( 0, Math.min( this.sheepY, this.currentLevel.getMap().getMapHeight() - Game2.SPRITE_HEIGHT ) );
    }
    
    
    
}
