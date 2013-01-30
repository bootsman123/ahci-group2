package game.gui;

import TUIO.TuioCursor;
import TUIO.TuioListener;
import TUIO.TuioObject;
import TUIO.TuioPoint;
import TUIO.TuioTime;
import game.Game;
import game.gui.listeners.ClickAndTouchListener;
import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.GUIContext;

/**
 * Basic button.
 * @author Bas Bootsma
 */
public class Button extends AbstractComponent implements TuioListener
{
    private Image imageUp;
    private Image imageDown;
    
    protected Shape area;
    protected Boolean isOver;
    protected Boolean isDown;    
    
    private List<ClickAndTouchListener> listenersClickAndTouch;
    
    /**
     * Constructor.
     * @param context
     * @param imageUp
     * @param imageDown
     * @param area 
     */
    public Button( GUIContext context, Image imageUp, Image imageDown, Shape area )
    {
        super( context );
       
        this.imageUp = imageUp;
        this.imageDown = imageDown;
        
        this.area = area;
        this.isOver = Boolean.FALSE;
        this.isDown = Boolean.FALSE;
        
        this.listenersClickAndTouch = new ArrayList<ClickAndTouchListener>();
    }
    
    /**
     * Constructor.
     * @param context
     * @param imageUp
     * @param imageDown
     * @param x
     * @param y 
     */
    public Button( GUIContext context, Image imageUp, Image imageDown, Integer x, Integer y ) throws SlickException
    {
        this( context, imageUp, imageDown, new Rectangle( x, y, imageUp.getWidth(), imageUp.getHeight() ) );
    }
    
    /**
     * Constructor.
     * @param context
     * @param imageUp
     * @param imageDown
     * @throws SlickException 
     */
    public Button( GUIContext context, Image imageUp, Image imageDown ) throws SlickException
    {
        this( context, imageUp, imageDown, 0, 0 );
    }
    
    /**
     * Add 'click and touch'-listener.
     * @param listener 
     */
    public void addClickAndTouchListener( ClickAndTouchListener listenerClickAndTouch )
    {
        this.listenersClickAndTouch.add( listenerClickAndTouch );
    }
    
    /**
     * Remove 'click and touch'-listener.
     * @param listener 
     */
    public void removeClickAndTouchListener( ClickAndTouchListener listenerClickAndTouch )
    {
        this.listenersClickAndTouch.remove( listenerClickAndTouch );
    }
    
    /**
     * Notify click and touch listeners.
     */
    private void notifyClickAndTouchListeners()
    {
        for( ClickAndTouchListener listenerClickAndTouch : this.listenersClickAndTouch )
        {
            listenerClickAndTouch.onClickOrTouch( this );
        }
    }

    @Override
    public void render( GUIContext container, Graphics g ) throws SlickException
    {
        Image imageCurrent = imageUp;
        
        if( this.isOver || this.isDown )
        {
            imageCurrent = this.imageDown;
        }
        
        // Render image.
        imageCurrent.draw( this.area.getX(), this.area.getY(), this.area.getWidth(), this.area.getHeight() );
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
    
    @Override
    public void mouseMoved( int xOld, int yOld, int xNew, int yNew )
    {
        this.isOver = this.area.contains( xNew, yNew );
        
        //this.notifyListeners();
    }

    @Override
    public void mouseDragged( int xOld, int yOld, int xNew, int yNew )
    {
        this.mouseMoved( xOld, yOld, xNew, yNew );        
    }

    @Override
    public void mousePressed( int button, int x, int y )
    {
        if( this.area.contains( x, y ) )
        {
            this.isOver = Boolean.TRUE;
            
            if( Input.MOUSE_LEFT_BUTTON == button )
            {
                this.isDown = Boolean.TRUE;
            }
        }
        else
        {
            this.isOver = Boolean.FALSE;
        }
        
        //this.notifyListeners();
    }

    @Override
    public void mouseReleased( int button, int x, int y )
    {
        super.mouseReleased( button, x, y );
        
        this.isOver = this.area.contains( x, y );
        
        if( this.isDown )
        {
            this.notifyClickAndTouchListeners();
        }
        
        this.isDown = Boolean.FALSE;
        
        /*
        this.isOver = this.area.contains( x, y );
        this.isDown = Boolean.FALSE;
        
        this.notifyClickAndTouchListeners();
        */
        
        /*
        if( this.area.contains( x, y ) )
        {
            this.isOver = Boolean.TRUE;
            this.isDown = Boolean.FALSE;
            
            this.notifyClickAndTouchListeners();
        }
        else
        {
            this.isOver = Boolean.FALSE;
        }
        */
        
        //this.notifyListeners();
    }

    @Override
    public void addTuioObject( TuioObject o )
    {
    }

    @Override
    public void updateTuioObject( TuioObject o )
    {
    }

    @Override
    public void removeTuioObject( TuioObject o )
    {
    }

    @Override
    public void addTuioCursor( TuioCursor c )
    {
        // @TODO: Hacky and fugly.
        /*
        if( !this.isAcceptingInput() )
        {
            return;
        }
        */
        
        this.updateTuioCursor( c );
    }

    @Override
    public void updateTuioCursor( TuioCursor c )
    {
        // @TODO: Hacky and fugly.
        /*
        if( !this.isAcceptingInput() )
        {
            return;
        }
        */
        
        TuioPoint point = c.getPosition();
        
        if( this.area.contains( ( point.getX() * Game.WIDTH ), ( point.getY() * Game.HEIGHT ) ) )
        {
            this.isDown = Boolean.TRUE;        
            this.notifyClickAndTouchListeners();
        }        
    }

    @Override
    public void removeTuioCursor( TuioCursor c )
    {
        //TuioPoint point = c.getPosition();
        
        this.isDown = Boolean.FALSE;
    }

    @Override
    public void refresh( TuioTime t )
    {
    }
}
