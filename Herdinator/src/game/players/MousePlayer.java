/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package game.players;

import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;

/**
 *
 * @author roland
 */
public class MousePlayer extends Player{

    private boolean isDraggingObject = false;
    public MousePlayer(int id, Color color) throws SlickException{
        super(id, color);
    }

    public boolean isDraggingObject(){
        return this.isDraggingObject;
    }

    public void setIsDraggingObject( boolean newDragging )
    {
        this.isDraggingObject = newDragging;
    }
}
