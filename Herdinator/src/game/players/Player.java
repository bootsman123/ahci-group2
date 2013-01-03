package game.players;

import game.actors.Whistle;
import game.base.UsableActor;
import game.global.GameManager;
import java.awt.Point;
import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;

/**
 *
 * @author roland
 */
public abstract class Player {
    private UsableActor object;
    private int id;
    private Color color;

    public Player( int id, Color color ) throws SlickException {
        this.id = id;
        Point startingPoint = new Point(0,0);
        this.color = color;
        this.object = new Whistle(startingPoint, this );
        
    }

    public Integer getId(){
        return this.id;
    }

    
    public void setObject(UsableActor newObject) throws SlickException{
        GameManager.getInstance().getMap().removeObject(this.object);//@TODO: do not let the player object remove the current object from the map?
        this.object = newObject;
        GameManager.getInstance().getMap().addObject(this.object);
    }

    public UsableActor getObject(){
        return object;
    }

    public void moveObject(Point newPoint){
        this.object.setPosition(newPoint);
    }
    
    public Color getColor(){
        return this.color;
    }
    
}
