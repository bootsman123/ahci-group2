package game.base;

import game.base.listeners.UseListener;
import game.global.GameManager;
import game.players.Player;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;

/**
 *
 * @author bootsman
 */
public abstract class UsableActor extends Actor implements Usable
{
    private static final double OFFSET = 3.0;
    
    protected List<UseListener> useListeners;
    
    protected Animation animation;
    
    private Player owner;
    
    /**
     * Constructor.
     * @param position 
     */
    public UsableActor( Point position, Player owner )
    {
        super( position );
        this.owner = owner;
        
        this.useListeners = new ArrayList<UseListener>();
    }

    @Override
    public void render( Graphics g )
    {
        Point2D.Double position = GameManager.getInstance().getMap().toPositionInPixels( this.getX(), this.getY() );
        Shape shape = new Circle( (float)position.x, (float)position.y, (float)(this.animation.getWidth() / 2.0 + UsableActor.OFFSET ) );
        
        g.setColor( this.owner.getColor() );
        g.fill( shape );
        g.setColor( Color.blue );
        g.setLineWidth(3f);
        g.draw(shape);
        
        float x = (float)( position.x - 0.5 * this.animation.getWidth() );
        float y = (float)( position.y - 0.5 * this.animation.getHeight() );
        this.animation.draw( x, y );
    }
    
    @Override
    public void update( int delta )
    {
        super.update( delta );
        this.animation.update( delta );
    }
    
    @Override
    public void addUseListener( UseListener listener )
    {
        this.useListeners.add( listener );
    }
    
    @Override
    public void removeUseListener( UseListener listener )
    {
        this.useListeners.remove( listener );
    }

    public Player getOwner()
    {
        return this.owner;
    }
}