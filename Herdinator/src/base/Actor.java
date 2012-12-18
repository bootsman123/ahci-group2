package base;

import java.awt.geom.Point2D;

/**
 * An object which is positioned and animated in the game.
 * @author Bas Bootsma
 */
public abstract class Actor implements Animatable
{
    private Point2D.Float position;
    
    /**
     * Constructor.
     * @param position 
     */
    public Actor( Point2D.Float position )
    {
        
        this.position = position;
    }
 
    
    public void setPosition( Point2D.Float position )
    {
        this.position = position;
    }
    
    public Point2D.Float getPosition()
    {
        return this.position;
    }
    
    public Float getX()
    {
        return this.position.x;
    }
    
    public void setX( Float x )
    {
        this.position.x = x;
    }
    
    public void setY( Float y )
    {
        this.position.y = y;
    }
    
    public Float getY()
    {
        return this.position.y;
    } 
}
