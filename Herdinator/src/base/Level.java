/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

import actors.Dog;
import actors.LoveSheep;
import actors.Sheep;
import actors.Wolf;
import java.util.List;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

/**
 *
 * @author roland
 */
public class Level {
    private Map map; 
    private List<Sheep> sheeps; 
    private Dog dog; 
    private Wolf wolf;
    private LoveSheep lovesheep;
    
    public Level() throws SlickException{

        this.map = new Map() ; 
    }
    
    public void render(){
         this.map.render();
    }
    
    public Map getMap(){
        return map ; 
    }
}
