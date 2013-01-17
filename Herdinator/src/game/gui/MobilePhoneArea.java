package game.gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.GUIContext;

/**
 *
 * @author Bas Bootsma
 */
public class MobilePhoneArea extends AbstractComponent
{
    private static final Integer WIDTH = 120;
    private static final Integer HEIGHT = 480;
    
    private static final Float LINE_WIDTH = 4.0f;

    private Shape area;

    /**
     * Constructor.
     * @param context 
     */
    public MobilePhoneArea( GUIContext context, int x, int y )
    {
        super( context );
        
        this.area = new Rectangle( x, y, MobilePhoneArea.WIDTH, MobilePhoneArea.HEIGHT );
    }

    @Override
    public void render( GUIContext container, Graphics g ) throws SlickException
    {
        g.setColor( Color.cyan );
        
        
        g.setLineWidth( MobilePhoneArea.LINE_WIDTH );
        g.draw( this.area );
    }

    @Override
    public void setLocation( int x, int y )
    {
        this.area.setLocation( x, y );
    }

    @Override
    public int getX()
    {
        return (int)this.area.getX();
    }

    @Override
    public int getY()
    {
        return (int)this.area.getY();
    }

    @Override
    public int getWidth()
    {
        return (int)this.area.getWidth();
    }

    @Override
    public int getHeight()
    {
        return (int)this.area.getHeight();
    }
    
    /**
     * Returns true if a position (x, y) is contained within the area.
     * @param x
     * @param y
     * @return 
     */
    public boolean contains( int x, int y )
    {
        return this.area.contains( x, y );
    }
}