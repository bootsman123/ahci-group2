/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import org.newdawn.slick.Color;
import org.newdawn.slick.Animation;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.tiled.TiledMap;

/**
 * Awesome tutorial:
 * http://thejavablog.wordpress.com/2008/06/08/using-slick-2d-to-write-a-game/
 * 
 * Javadoc Slick:
 * http://slick.cokeandcode.com/javadoc/
 * 
 * @author Bas Bootsma
 */
public class Game extends BasicGame
{
    public static final String TITLE = "Sheep game";
    public static final int SPRITE_WIDTH = 32;
    public static final int SPRITE_HEIGHT = 32;
    
    private TiledMap map;
    private int mapWidth;
    private int mapHeight;
    
    private SpriteSheet sheepSpriteSheet;
    
    private Animation sheepSprite, sheepUp, sheepRight, sheepDown, sheepLeft;
    private double sheepX;
    private double sheepY;
    
    public Game() throws SlickException
    {
       super( Game.TITLE ); 
    }

    @Override
    public void init( GameContainer gc ) throws SlickException
    {
       this.map = new TiledMap( "../Resources/Maps/map.tmx" );
       this.mapWidth = this.map.getWidth() * this.map.getTileWidth();
       this.mapHeight = this.map.getHeight() * this.map.getTileHeight();
       
       this.sheepSpriteSheet = new SpriteSheet( "../Resources/Images/sheeps_animation.png", Game.SPRITE_WIDTH, Game.SPRITE_HEIGHT, new Color( 123, 198, 132 ) );
       
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
    public void update( GameContainer gc, int delta ) throws SlickException
    {
        Input input = gc.getInput();
        
        if( input.isKeyDown( Input.KEY_UP ) )
        {
            this.sheepSprite = this.sheepUp;
            this.sheepSprite.update( delta );
            this.sheepY -= delta * 0.1f;
        }
        else if( input.isKeyDown( Input.KEY_RIGHT ) )
        {
            this.sheepSprite = this.sheepRight;
            this.sheepSprite.update( delta );
            this.sheepX += delta * 0.1f;
        }
        else if( input.isKeyDown( Input.KEY_DOWN ) )
        {
            this.sheepSprite = this.sheepDown;
            this.sheepSprite.update( delta );
            this.sheepY += delta * 0.1f;
        }
        else if( input.isKeyDown( Input.KEY_LEFT ) )
        {
            this.sheepSprite = this.sheepLeft;
            this.sheepSprite.update( delta );
            this.sheepX -= delta * 0.1f;
        }
        
        this.sheepX = Math.max( 0, Math.min( this.sheepX, this.mapWidth - Game.SPRITE_WIDTH ) );
        this.sheepY = Math.max( 0, Math.min( this.sheepY, this.mapHeight - Game.SPRITE_HEIGHT ) );
    }

    @Override
    public void render( GameContainer gc, Graphics graphics ) throws SlickException
    {
        this.map.render( 0, 0 );
        this.sheepSprite.draw( (int)this.sheepX, (int)this.sheepY );
    }
    
}
