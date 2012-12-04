/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

/**
 *
 * @author roland
 */
public class Map {
    private TiledMap map;
    private int mapWidth;
    private int mapHeight;
    
    public Map() throws SlickException{
       this.map = new TiledMap( "../Resources/Maps/map.tmx" );
       this.mapWidth = this.map.getWidth() * this.map.getTileWidth();
       this.mapHeight = this.map.getHeight() * this.map.getTileHeight();
    }
    
    public void render( GameContainer gc, Graphics graphics ) throws SlickException
    {
        this.map.render( 0, 0 );
        
    }
}
