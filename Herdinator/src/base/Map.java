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
    //@TODO: add collisionmatrix
    private int mapWidth;
    private int mapHeight;
    
    public Map() throws SlickException{
       this.map = new TiledMap( "../Resources/Maps/level1.tmx" );
       this.mapWidth = this.map.getWidth() * this.map.getTileWidth();
       this.mapHeight = this.map.getHeight() * this.map.getTileHeight();
    }
    
    public void render()
    {
        this.map.render( 0, 0 );   
    }
    
    public boolean doesCollide(int x, int y){
        int index = this.map.getLayerIndex("Collisions");
        //@TODO: check if the index is -1
        System.out.println("Collision ID: " + this.map.getTileId(x, y, index)); 
        return false ;
    }
    public int getMapWidth(){
        return mapWidth; 
        
    }
    public int getMapHeight(){
        return mapHeight; 
    }
}
