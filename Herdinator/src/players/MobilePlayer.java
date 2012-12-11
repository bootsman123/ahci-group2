/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package players;

import TUIO.TuioCursor;
import TUIO.TuioListener;
import TUIO.TuioObject;
import TUIO.TuioTime;
import base.Player;
import java.awt.Point;
import java.util.ArrayList;

/**
 * Mobile Players
 * @author roland
 */
public class MobilePlayer extends Player implements TuioListener{
    private Point locationTelephone ; 

    private boolean hasTelephoneOnTable ;    
    private ArrayList<TuioObject> currentObjects;
    

    public MobilePlayer(int id){
        super(id);
        currentObjects = new ArrayList<TuioObject>();
    }
    

    public ArrayList<TuioObject> getCurrentObjects(){
        return currentObjects;
    }
    
    @Override
    public void addTuioCursor(TuioCursor arg0) {
            System.out.println("Added TuioCursor: " + arg0.getCursorID()) ;

    }

    @Override
    public void addTuioObject(TuioObject arg0) {
            System.out.println("Added TuioObject: " + arg0.getSymbolID()) ;
            currentObjects.add(arg0);
    }

    @Override
    public void refresh(TuioTime arg0) {

    }

    @Override
    public void removeTuioCursor(TuioCursor arg0) {
            System.out.println("Removed TuioCursor: " + arg0.getCursorID()) ;
    }

    @Override
    public void removeTuioObject(TuioObject arg0) {
            System.out.println("Removed TuioObject: " + arg0.getSymbolID()) ;
            currentObjects.remove(arg0);
    }

    @Override
    public void updateTuioCursor(TuioCursor arg0) {
            System.out.println("Updated TuioCursor: " + arg0.getCursorID() + " location: " + arg0.getX() + " " + arg0.getY()) ;
    }

    @Override
    public void updateTuioObject(TuioObject arg0) {
            System.out.println("Updated TuioObject: " + arg0.getSymbolID() + " location: " + arg0.getX() + " " + arg0.getY()) ;
    }

}
