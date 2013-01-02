package game.players;

import game.actors.Whistle;
import game.base.ImmovableActor;
import game.global.GameManager;
import java.awt.Point;
import org.newdawn.slick.SlickException;

/**
 *
 * @author roland
 */
public abstract class Player {
    private ImmovableActor object;
    private int id;
    
    public Player( int id ) throws SlickException {
        this.id = id;
        Point startingPoint = new Point(0,0);
        this.object = new Whistle(startingPoint );
        
    }

    public Integer getId(){
        return this.id;
    }

    
    public void setObject(ImmovableActor newObject) throws SlickException{
        GameManager.getInstance().getMap().removeObject(this.object);//@TODO: do not let the player object remove the current object from the map?
        this.object = newObject;
        GameManager.getInstance().getMap().addObject(this.object);
    }

    public ImmovableActor getObject(){
        return object;
    }

    public void moveObject(Point newPoint){
        this.object.setPosition(newPoint);
    }
    
}
