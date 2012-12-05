/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package connectors;

import TUIO.TuioCursor;
import TUIO.TuioListener;
import TUIO.TuioObject;
import TUIO.TuioTime;

/**
 *
 * @author roland
 */
public class PhoneConnector implements TuioListener{
    
    public PhoneConnector(){
        
    }
    @Override
    public void addTuioCursor(TuioCursor arg0) {
            System.out.println("Added TuioCursor: " + arg0.getCursorID()) ; 
    }   

    @Override
    public void addTuioObject(TuioObject arg0) {
            System.out.println("Added TuioObject: " + arg0.getSymbolID()) ; 
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
