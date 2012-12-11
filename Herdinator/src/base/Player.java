/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

import TUIO.TuioCursor;

/**
 *
 * @author roland
 */
public class Player {
    public enum MovableObjects{Lovesheep, Cookie, Wolf, Bridge, Fence };

    private MovableObjects currentObject = MovableObjects.Lovesheep;
    
    private String name ;
    private int fiducialID;

    
    
    public Player(int fiducialID){
        this.fiducialID = fiducialID;

    }

    public int getFiducialID(){
        return this.fiducialID;
    }
}
