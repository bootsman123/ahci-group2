package game.gui;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.GUIContext;

/**
 *
 * @author bootsman
 */
public class Button extends AbstractComponent
{    
    private Image imageUp;
    private Image imageDown;
    
    protected Shape area;
    protected Boolean isOver;
    protected Boolean isDown;
    
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
     * @param x
     * @param y
     * @throws SlickException 
     */
    public Button( GUIContext context, String imageUp, String imageDown, Integer x, Integer y ) throws SlickException
    {
        this( context, new Image( imageUp ), new Image( imageDown ), x, y );
    }
    
    /**
     * Constructor.
     * @param context
     * @param imageUp
     * @param imageDown
     * @throws SlickException 
     */
    public Button( GUIContext context, String imageUp, String imageDown ) throws SlickException
    {
        this( context, imageUp, imageDown, 0, 0 );
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
        this.isDown = Boolean.FALSE;
        
        //this.notifyListeners();
    }
}