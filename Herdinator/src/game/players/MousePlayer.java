package game.players;

import org.newdawn.slick.Color;

/**
 *
 * @author roland
 */
public class MousePlayer extends Player
{
    private Boolean isDraggingObject;
    
    /**
     * Constructor.
     * @param color 
     */
    public MousePlayer( Color color )
    {
        super( color );
        
        this.isDraggingObject = Boolean.FALSE;
    }

    public Boolean isDraggingObject()
    {
        return this.isDraggingObject;
    }

    public void setIsDraggingObject( boolean newDragging )
    {
        this.isDraggingObject = newDragging;
    }
}
