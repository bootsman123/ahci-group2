package base;

import java.awt.geom.Point2D;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;

/**
 * An object which is positioned and rendered in the game.
 * @author Bas Bootsma
 */
public abstract class Actor implements Renderable
{
    // Current position.
    private Point2D.Float position;
    
    // Current animation.
    private Animation animation;
    
    // Last update delta.
    private Integer lastDelta;
    
    /**
     * Constructor.
     * @param position 
     */
    public Actor( Point2D.Float position )
    {
        this.position = position;
        this.animation = null;
        this.lastDelta = 0;
    }

    public void setPosition( Point2D.Float position )
    {
        this.position = position;
    }
    
    public void setPosition( Float x, Float y )
    {
        this.setX( x );
        this.setY( y );
    }
    
    public Point2D.Float getPosition()
    {
        return this.position;
    }
    
    public void setX( Float x )
    {
        this.position.x = x;
    }
    
    public Float getX()
    {
        return this.position.x;
    }
    
    public void setY( Float y )
    {
        this.position.y = y;
    }
    
    public Float getY()
    {
        return this.position.y;
    }
    
    public void setAnimation( Animation animation )
    {
        this.animation = animation;
    }
    
    public Animation getAnimation()
    {
        return this.animation;
    }
    
    public void setLastDelta( Integer lastDelta )
    {
        this.lastDelta = lastDelta;
    }
    
    public Integer getLastDelta()
    {
        return this.lastDelta;
    }
    
    @Override
    public void render( Graphics g )
    {
        this.getAnimation().draw( this.getX(), this.getY() );
    }
    
    /**
     * Initialization function, called during initialization.
     */
    public void init()
    {
        // Empty for consistancy.
    }
    
    /**
     * Update function, called each update.
     * @param delta 
     */
    public void update( int delta )
    {
        this.setLastDelta( delta );
    }
}
