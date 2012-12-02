/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

/**
 *
 * @author bootsman
 */
public class Game extends BasicGame
{
    public static final String TITLE = "Sheep game";
    
    private TiledMap map;
    
    public Game() throws SlickException
    {
       super( Game.TITLE ); 
       this.map = new TiledMap( "../map.tmx" );
    }

    @Override
    public void init( GameContainer gc ) throws SlickException
    {
    }

    @Override
    public void update( GameContainer gc, int i ) throws SlickException
    {
    }

    @Override
    public void render( GameContainer gc, Graphics graphics ) throws SlickException
    {
        this.map.render( 0, 0 );
    }
    
}
