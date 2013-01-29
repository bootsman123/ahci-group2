package game.gui;

import TUIO.TuioClient;
import TUIO.TuioCursor;
import TUIO.TuioListener;
import TUIO.TuioObject;
import TUIO.TuioPoint;
import TUIO.TuioTime;
import game.util.MathUtil;
import java.awt.Point;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.RoundedRectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.GUIContext;

/**
 *
 * @author Bas Bootsma
 */
public class TangibleArea extends AbstractComponent implements TuioListener
{   
    public static final Integer WIDTH = 160;
    public static final Integer HEIGHT = 240;
    
    private static final Integer CORNER_RADIUS = 10;
    private static final Integer SEGMENTS = 30;
    
    private static final Float LINE_WIDTH = 8.0f;
    private static final Color COLOR_DEFAULT = Color.decode( "#db2864" );
    private static final Color COLOR_OBJECT = Color.cyan;
    
    public static final Double DISTANCE_TO_CENTER = 20.0;

    private Shape area;
        
    private TuioClient tuioClient;
    private TuioObject object;

    /**
     * Constructor.
     * @param context 
     */
    public TangibleArea( GUIContext context )
    {
        super( context );
        
        this.tuioClient = new TuioClient();
        this.tuioClient.addTuioListener( this );
        this.tuioClient.connect();
        
        this.area = new RoundedRectangle( 0, 0, TangibleArea.WIDTH, TangibleArea.HEIGHT, TangibleArea.CORNER_RADIUS, TangibleArea.SEGMENTS );
        this.object = null;
    }

    @Override
    public void render( GUIContext container, Graphics g ) throws SlickException
    {
        g.setColor( ( this.object == null ) ? TangibleArea.COLOR_DEFAULT : TangibleArea.COLOR_OBJECT );
        
        // Draw surrounding area.
        g.setLineWidth( TangibleArea.LINE_WIDTH );
        g.draw( this.area );
        
        // Draw inner area.
        // @TODO: Draw inner area.
    }

    @Override
    public void setLocation( int x, int y )
    {
        if( this.area != null )
        {
            this.area.setLocation( x, y );
        }
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
     * Returns true if a position (x, y) is near the center of the area.
     * @param x
     * @param y
     * @return 
     */
    public Boolean contains( Integer x, Integer y )
    {
        Double distance = MathUtil.distanceEuclidian( new Point( (int)this.area.getX(), (int)this.area.getY() ),
                                                      new Point( x, y ) );
        
        
        return ( distance <= TangibleArea.DISTANCE_TO_CENTER );
    }

    @Override
    public void addTuioObject( TuioObject o )
    {
        System.out.println( "Adding TUIO object: " + o.getSymbolID() );
        
        TuioPoint position = o.getPosition();
        
        if( this.contains( (int)position.getX(), (int)position.getY() ) )
        {
            this.object = o;
        }   
    }

    @Override
    public void updateTuioObject( TuioObject o )
    {            
        TuioPoint position = o.getPosition();
        
        if( this.object == o &&
            !this.contains( (int)position.getX(), (int)position.getY() ) )
        {
            this.object = null;
        }
        else if( this.object == null &&
                 this.contains( (int)position.getX(), (int)position.getY() ) )
        {
            this.object = o;
        }
    }

    @Override
    public void removeTuioObject( TuioObject o )
    {
        if( this.object == o )
        {
            this.object = null;
        }
    }

    @Override
    public void addTuioCursor( TuioCursor c)
    {
    }

    @Override
    public void updateTuioCursor( TuioCursor c )
    {
    }

    @Override
    public void removeTuioCursor( TuioCursor c )
    {
    }

    @Override
    public void refresh( TuioTime t )
    {
    }
}