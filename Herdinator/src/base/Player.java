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
public abstract class Player {
    public enum MovableObjects{Lovesheep, Cookie, Wolf, Bridge, Fence };

    private MovableObjects currentObject = MovableObjects.Lovesheep;
    
    private String name ;
    private int playerID;

    
    
    public Player(int playerID){
        this.playerID = playerID;

    }

    public int getPlayerID(){
        return this.playerID;
    }
}
