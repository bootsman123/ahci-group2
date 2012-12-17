/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

import TUIO.TuioCursor;
import org.newdawn.slick.SlickException;

/**
 *
 * @author roland
 */
public abstract class Player {
    private MovableActor currentObject;
    private int playerID;
    
    public Player(int playerID){
        this.playerID = playerID;

    }

    public int getPlayerID(){
        return this.playerID;
    }

    public void changeCurrentObject(MovableActor newObject) throws SlickException{
        GameManager.getInstance().getMap().removeObject(this.currentObject);
        this.currentObject = newObject;
        GameManager.getInstance().getMap().addObject(this.currentObject);

    }
    
}
