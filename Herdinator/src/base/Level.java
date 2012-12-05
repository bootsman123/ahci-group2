/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

import actors.Dog;
import actors.LoveSheep;
import actors.Sheep;
import actors.Wolf;
import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;
import temp.Game2;

/**
 *
 * @author roland
 */
public class Level {
    protected Map map; 
    protected List<Sheep> sheeps; 
    //private Sheep sheep; 
    private Dog dog; 
    private Wolf wolf;
    private LoveSheep lovesheep;
    
    public Level() throws SlickException{

        this.map = new Map() ; 
        sheeps = new ArrayList<Sheep>();
        
    }
    
    public void render(){
        this.map.render();
        for (Sheep sheep : sheeps){
            sheep.draw() ; 
        }
    }
    
    public Map getMap(){
        return map ; 
    }

    public void update(Input input, int delta) {
        /*
         if( input.isKeyDown( Input.KEY_UP ) )
        {
            for (Sheep sheep : sheeps){
                sheep.moveUp(delta); 
            }
        }
        else if( input.isKeyDown( Input.KEY_RIGHT ) )
        {
            for (Sheep sheep : sheeps){
                sheep.moveRight(delta); 
            }
        }
        else if( input.isKeyDown( Input.KEY_DOWN ) )
        {
            for (Sheep sheep : sheeps){
                sheep.moveDown(delta); 
            }
        }
        else if( input.isKeyDown( Input.KEY_LEFT ) )
        {
            for (Sheep sheep : sheeps){
                sheep.moveLeft(delta); 
            }
        }*/
       for (Sheep sheep : sheeps){
           sheep.moveRandom(delta);
           sheep.update();; 
       }
       
    }
}
