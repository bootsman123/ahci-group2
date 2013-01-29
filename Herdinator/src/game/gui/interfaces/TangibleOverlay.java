/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package game.gui.interfaces;

import TUIO.TuioClient;
import TUIO.TuioCursor;
import TUIO.TuioListener;
import TUIO.TuioObject;
import TUIO.TuioTime;
import game.Game;
import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.GUIContext;

/**
 *
 * @author bootsman
 */
public class TangibleOverlay extends AbstractComponent implements TuioListener
{
    private Shape area;
    
    private List<TuioObject> tuioObjects;
    private TuioClient tuioClient;
    
    public TangibleOverlay( GUIContext container )
    {
        super( container );
        
        // Fullscreen.
        this.area = new Rectangle( 0, 0, container.getWidth(), container.getHeight() );
        
        this.tuioObjects = new ArrayList<TuioObject>();
        this.tuioClient = new TuioClient();
        this.tuioClient.addTuioListener( this );
        this.tuioClient.connect();
        
        //System.out.println("Connected the tuioClient!");
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
    public void setLocation( int x, int y )
    {
    }

    @Override
    public void render( GUIContext container, Graphics g ) throws SlickException
    {
        //System.out.printf( "[TangibleOverlay]: rendering %d tangibles.\n", this.tuioObjects.size() );
        
        g.setColor( Color.red );
        
        for( TuioObject tuioObject : this.tuioObjects )
        {
            Float x = tuioObject.getX() * container.getWidth();
            Float y = tuioObject.getY() * container.getHeight();
            
            g.fillRect( x, y, 20, 20 );
            g.drawString( String.valueOf( tuioObject.getSymbolID() ), x, y );
        }
    }


    @Override
    public void addTuioObject( TuioObject o )
    {        
        System.out.printf( "[TangibleOverlay]: addTuioObject: %d\n", o.getSymbolID() );

        this.tuioObjects.add( o );
    }

    @Override
    public void updateTuioObject( TuioObject o )
    {
        // @TODO: ?
    }

    @Override
    public void removeTuioObject( TuioObject o )
    {        
        System.out.printf( "[TangibleOverlay]: removeTuioObject: %d\n", o.getSymbolID() );

        this.tuioObjects.remove( o );
    }

    @Override
    public void addTuioCursor( TuioCursor c )
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
    public void disconnect(){
        this.tuioClient.disconnect();
    }
}