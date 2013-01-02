package game.players;

import game.actors.Whistle;
import game.base.Actor;
import java.awt.Point;
import org.newdawn.slick.SlickException;

/**
 *
 * @author roland
 */
public abstract class Player {
    private Actor currentObject;
    private int id;
    
    public Player( int id ) throws SlickException {
        this.id = id;
        Point startingPoint = new Point(0,0);
        this.currentObject = new Whistle(startingPoint );
    }

    public Integer getId(){
        return this.id;
    }

    /*
    public void changeCurrentObject(MovableActor newObject) throws SlickException{
        GameManager.getInstance().getMap().removeObject(this.currentObject);
        this.currentObject = newObject;
        GameManager.getInstance().getMap().addObject(this.currentObject);

    }

    public MovableActor getCurrentObject(){
        return currentObject;
    }
    * */
    
}
