package game.base;

import java.awt.Point;

/**
 * An object which is positioned and rendered in the game.
 * @author Bas Bootsma
 */
public abstract class Actor implements Renderable
{
    // Current position (in tiles).
    private Point position;
    
    // Last update delta.
    private Integer lastDelta;
    
    /**
     * Constructor.
     * @param position 
     */
    public Actor( Point position )
    {
        this.position = position;
        this.lastDelta = 0;
    }

    public void setPosition( Point position )
    {
        this.position = position;
    }
    
    public void setPosition( Integer x, Integer y )
    {
        this.position.x = x;
        this.position.y = y;
    }
    
    public Point getPosition()
    {
        return this.position;
    }
    
    public void setX( Integer x )
    {
        this.position.x = x;
    }
    
    public Integer getX()
    {
        return this.position.x;
    }
    
    public void setY( Integer y )
    {
        this.position.y = y;
    }
    
    public Integer getY()
    {
        return this.position.y;
    }
    
    protected void setLastDelta( Integer lastDelta )
    {
        this.lastDelta = lastDelta;
    }
    
    public Integer getLastDelta()
    {
        return this.lastDelta;
    }
    
    /**
     * Initialization function, called during initialization.
     */
    public abstract void init();
    
    /**
     * Update function, called each update.
     * @param delta 
     */
    public void update( int delta )
    {
        this.setLastDelta( delta );
    }
}
