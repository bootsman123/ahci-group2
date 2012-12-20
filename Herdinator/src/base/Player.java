/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

import actors.Cookie;
import actors.Whistle;
import java.awt.geom.Point2D;
import org.newdawn.slick.SlickException;

/**
 *
 * @author roland
 */
public abstract class Player {
    private MovableActor currentObject;
    private int playerID;
    
    public Player(int playerID) throws SlickException {
        this.playerID = playerID;
        Point2D.Float startingPoint = new Point2D.Float(0,0);
        this.currentObject = new Whistle(GameManager.getInstance().getMap(), startingPoint, this.playerID );
    }

    public int getPlayerID(){
        return this.playerID;
    }

    public void changeCurrentObject(MovableActor newObject) throws SlickException{
        GameManager.getInstance().getMap().removeObject(this.currentObject);
        this.currentObject = newObject;
        GameManager.getInstance().getMap().addObject(this.currentObject);

    }

    public MovableActor getCurrentObject(){
        return currentObject;
    }
    
}
