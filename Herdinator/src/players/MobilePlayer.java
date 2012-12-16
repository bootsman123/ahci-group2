/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package players;

import TUIO.TuioCursor;
import TUIO.TuioListener;
import TUIO.TuioObject;
import TUIO.TuioTime;
import base.GameManager;
import base.Player;
import java.awt.geom.Point2D;

/**
 * Mobile Players
 * @author roland
 */
public class MobilePlayer extends Player implements TuioListener{
    public Point2D locationTelephone ;

    public boolean hasTelephoneOnTable ;
    
    

    public MobilePlayer(int id){
        super(id);
        
    }
    
    @Override
    public void addTuioCursor(TuioCursor arg0) {
         //   System.out.println("Added TuioCursor: " + arg0.getCursorID()) ;

    }

    @Override
    public void addTuioObject(TuioObject arg0) {
        //    System.out.println("Added TuioObject: " + arg0.getSymbolID()) ;
           
    }

    @Override
    public void refresh(TuioTime arg0) {

    }

    @Override
    public void removeTuioCursor(TuioCursor arg0) {
           // System.out.println("Removed TuioCursor: " + arg0.getCursorID()) ;
    }

    @Override
    public void removeTuioObject(TuioObject arg0) {
           
            if (arg0.getSymbolID() ==this.getPlayerID()){
                 System.out.println("Removed TuioObject: " + arg0.getSymbolID()) ;
                this.hasTelephoneOnTable = false; 
            }
    }

    @Override
    public void updateTuioCursor(TuioCursor arg0) {
          //  System.out.println("Updated TuioCursor: " + arg0.getCursorID() + " location: " + arg0.getX() + " " + arg0.getY()) ;
            
    }

    @Override
    public void updateTuioObject(TuioObject arg0) {
         
            if (arg0.getSymbolID() ==this.getPlayerID()){
                   System.out.println("Updated TuioObject: " + arg0.getSymbolID() + " location: " + arg0.getX() + " " + arg0.getY()) ;
                this.hasTelephoneOnTable = true;
                
                this.locationTelephone = new Point2D.Double(arg0.getX()* GameManager.getInstance().getMap().getMapHeight(), arg0.getY()* GameManager.getInstance().getMap().getMapWidth());
            }
    }

}
