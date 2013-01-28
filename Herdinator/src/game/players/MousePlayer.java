package game.players;

/**
 *
 * @author roland
 */
public class MousePlayer extends Player
{
    private Boolean isDraggingObject;
    
    /**
     * Constructor.
     */
    public MousePlayer()
    {
        super();
        
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
