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
public class Level1 extends Level {
    
    
    public Level1() throws SlickException{
        super() ; 
        sheeps.add(new Sheep(10,10, map.getMapWidth(), map.getMapHeight())) ; 
        sheeps.add(new Sheep(20,20, map.getMapWidth(), map.getMapHeight())) ; 
    }
}
