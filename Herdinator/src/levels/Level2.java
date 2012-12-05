/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package levels;

import actors.Sheep;
import base.Level;
import org.newdawn.slick.SlickException;

/**
 *
 * @author roland
 */
public class Level2 extends Level {
    
    
    public Level2() throws SlickException{
        super() ; 
        sheeps.add(new Sheep(50,40, map.getMapWidth(), map.getMapHeight())) ; 
        sheeps.add(new Sheep(20,20, map.getMapWidth(), map.getMapHeight())) ; 
    }
}
