/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package levels;

import actors.Sheep;
import base.Level;
import base.Map;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author roland
 */
public class Level2 extends Level {
    
    
    public Level2() throws SlickException{
        super() ; 
        
    }
    
     @Override
    public void init( GameContainer container, StateBasedGame game ) throws SlickException
    {
        //@TODO: Abstract more from this.
        super.init(container, game);
        this.map = new Map( "../Resources/Maps/level1.tmx" );
        sheeps.add(new Sheep(10,10, map.getMapWidth(), map.getMapHeight())) ; 
        sheeps.add(new Sheep(20,20, map.getMapWidth(), map.getMapHeight())) ; 
    }
}
