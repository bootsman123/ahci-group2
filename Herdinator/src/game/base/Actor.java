package game.base;

import java.awt.geom.Point2D;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;

/**
 * An object which is positioned and rendered in the game.
 * @author Bas Bootsma
 */
public abstract class Actor implements Renderable
{
    public enum Action
    {
        IDLE, MOVE
    };
    
    // Current position.
    private Point2D.Double position;
    
    // Current animation.
    private Animation animation;
    private Action action;
    
    // Last update delta.
    private Integer lastDelta;
    
    /**
     * Constructor.
     * @param position 
     */
    public Actor( Point2D.Double position )
    {
        this.position = position;
        
        this.animation = null;
        this.action = Action.IDLE;
        
        this.lastDelta = 0;
    }

    public void setPosition( Point2D.Double position )
    {
        this.position = position;
    }
    
    public void setPosition( Double x, Double y )
    {
        this.setX( x );
        this.setY( y );
    }
    
    public Point2D.Double getPosition()
    {
        return this.position;
    }
    
    public void setX( Double x )
    {
        this.position.x = x;
    }
    
    public Double getX()
    {
        return this.position.x;
    }
    
    public void setY( Double y )
    {
        this.position.y = y;
    }
    
    public Double getY()
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
    
    public void setAction( Action action )
    {
        this.action = action;
    }
    
    public Action getAction()
    {
        return this.action;
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
        this.getAnimation().draw( this.getX().floatValue(), this.getY().floatValue() );
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
