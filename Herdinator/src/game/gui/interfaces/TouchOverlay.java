/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.gui.interfaces;

import game.base.TouchDot;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
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
    public TouchOverlay(GUIContext container){
        super(container);
        touchDots = new ArrayList<TouchDot>();
    }
    
    public void addTouchDot( TouchDot dot )
    {
        touchDots.add( dot );
    }
    
    @Override
    public void render( GUIContext container , Graphics g ) throws SlickException 
    {
        for (TouchDot dot : touchDots )
        {
            dot.render(g);
        }
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
        for (TouchDot dot : touchDots )
        {
            if(dot.getCursorID() == cursorID){
                touchDots.remove(dot);
            }
        }
    }
    /**
     * Moves the touchDot with the given cursorID
     * @param cursorID 
     */
    public void moveTouchDot(int cursorID, Point newPosition ) 
    {
        for (TouchDot dot : touchDots )
        {
           if(dot.getCursorID() == cursorID){
                dot.setPosition(newPosition);
            }
        }
    }
    
}
