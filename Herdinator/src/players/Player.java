/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package players;

import actors.Cookie;
import actors.Whistle;
import global.GameManager;
import base.MovableActor;
import java.awt.geom.Point2D;
import org.newdawn.slick.SlickException;

/**
 *
 * @author roland
 */
public abstract class Player {
    private MovableActor currentObject;
    private int id;
    
    public Player( int id ) throws SlickException {
        this.id = id;
        Point2D.Float startingPoint = new Point2D.Float(0,0);
        this.currentObject = new Whistle(GameManager.getInstance().getMap(), startingPoint, this.id );
    }

    public Integer getId(){
        return this.id;
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
