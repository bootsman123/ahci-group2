/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.players;

import java.awt.geom.Point2D;
import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;

/**
 *
 * @author roland
 */
public class TouchPlayer extends Player {
    private Point2D.Double fingerLocation ;
    private boolean hasFingerOnTable ;
    private int assignedBlobID;

    public TouchPlayer(Color color) throws SlickException{
        super(color);
        this.hasFingerOnTable = false;
    }
    
    public int getAssignedBlobID(){
        return assignedBlobID;
    }

    public void setAssignedBlobID(int newBlobID){
        this.assignedBlobID = newBlobID;
    }

    public boolean hasFingerOnTable(){
        return hasFingerOnTable;
    }
    
    public void setHasFingerOnTable(boolean hasFingerOnTable){
        this.hasFingerOnTable = hasFingerOnTable;
    }

    public Point2D.Double getFingerLocation(){
        return fingerLocation;
    }
    public void setFingerLocation(Point2D.Double fingerLocation){
        this.fingerLocation = fingerLocation;
    }

}
