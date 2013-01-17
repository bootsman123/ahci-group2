/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.gui.interfaces;

import game.base.TouchDot;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.GUIContext;

/**
 *
 * @author roland
 */
public class TouchOverlay extends AbstractComponent{

    private List<TouchDot> touchDots;
    
    public TouchOverlay(GUIContext container)
    {
        super(container);
        System.out.println("TouchOverlay started");
        touchDots = Collections.synchronizedList(new ArrayList<TouchDot>());
        
    }
    
    
    @Override
    public void render( GUIContext container , Graphics g ) throws SlickException 
    {   
        synchronized(touchDots){
        ListIterator<TouchDot> iter = touchDots.listIterator();
            while(iter.hasNext())
            {
                TouchDot dot = iter.next();
                dot.render(g);

            }
        }
        /*
        for (TouchDot dot : touchDots)
        {
        //Iterator<TouchDot> iter = this.touchDots.iterator();
       // while(iter.hasNext()){
         //   TouchDot dot = iter.next();
            dot.render(g);
        }*/
    }

    @Override
    public void setLocation( int x , int y ) 
    {
        //@TODO: what is this used for????
    }

    @Override
    public int getX() 
    {
        return 0;//@TODO: what is this used for????
    }

    @Override
    public int getY() 
    {
        return 0;//@TODO: what is this used for????
    }

    @Override
    public int getWidth() 
    {
        return 0;//@TODO: what is this used for????
    }

    @Override
    public int getHeight() 
    {
        return 0;//@TODO: what is this used for???
    }

    /**
     * Removes the touchDot with the given cursorID
     * @param cursorID 
     */
    public void removeTouchDot(int cursorID) 
    {
        synchronized(touchDots){
        ListIterator<TouchDot> iter = touchDots.listIterator();
            while(iter.hasNext())
            {
                TouchDot dot = iter.next();
                if(dot.getCursorID() == cursorID){
                    iter.remove();
                }

            }
        }
    }
    /**
     * Moves the touchDot with the given cursorID
     * @param cursorID 
     */
    public void moveTouchDot(int cursorID, Point newPosition ) 
    {
        synchronized(touchDots){
        ListIterator<TouchDot> iter = touchDots.listIterator();
            while(iter.hasNext())
            {
                TouchDot dot = iter.next();
                if(dot.getCursorID() == cursorID){
                    dot.setPosition(newPosition);
                }

            }
        }
        /*
        for (TouchDot dot : touchDots)
        {
        //Iterator<TouchDot> iter = touchDots.iterator();
        //while(iter.hasNext()){
         //   TouchDot dot = iter.next();
          //  if(dot.getCursorID() == cursorID){
                dot.setPosition(newPosition);
           // }
        }*/
    }

    public void addTouchDot(TouchDot dot) {
        synchronized(touchDots){
            this.touchDots.add(dot);
        }
    }
    
}
