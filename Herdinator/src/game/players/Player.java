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
    }

    public Integer getId(){
        return this.id;
    }

    /**
     * Set the object to be used by this player
     * @param newObject 
     */
    public void setObject(UsableActor newObject) {
        GameManager.getInstance().getMap().removeUsableActor(this.object);//@TODO: do not let the player object remove the current object from the map?
        this.object = newObject;
        GameManager.getInstance().getMap().addUsableActor(this.object);
        
    }

    public UsableActor getObject(){
        return object;
    }

    public void moveObject(Point newPoint){
        if(object !=null){
            this.object.setPosition(newPoint);
        }
        else{
            System.err.println("Player.moveObject: object == null");
        }
    }
    
    public Color getColor(){
        return this.color;
    }
    
}
