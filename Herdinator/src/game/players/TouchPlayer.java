/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.players;

import TUIO.TuioCursor;
import TUIO.TuioListener;
import TUIO.TuioObject;
import TUIO.TuioTime;
import java.awt.Point;
import java.awt.geom.Point2D;
import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;

/**
 *
 * @author roland
 */
public class TouchPlayer extends Player implements TuioListener{
    private Point2D.Double fingerLocation ;
    private boolean hasFingerOnTable ;
    private int assignedBlobID;

    public TouchPlayer(int id, Color color) throws SlickException{
        super(id, color);
        this.hasFingerOnTable = false;
    }
    @Override
    public void addTuioObject(TuioObject to) {
       System.out.println("Added tuioobject");
    }

    @Override
    public void updateTuioObject(TuioObject to) {
        
    }

    @Override
    public void removeTuioObject(TuioObject to) {
        
    }

    @Override
    public void addTuioCursor(TuioCursor tc) {
       System.out.println("Added tuiocursor");
    }

    @Override
    public void updateTuioCursor(TuioCursor tc) {
        
    }

    @Override
    public void removeTuioCursor(TuioCursor tc) {
        
    }

    @Override
    public void refresh(TuioTime tt) {
        
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
