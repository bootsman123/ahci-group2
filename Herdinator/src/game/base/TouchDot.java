/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.base;

import game.global.GameManager;
import java.awt.Point;
import java.awt.geom.Point2D;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;

/**
 *
 * @author roland
 */
public class TouchDot extends Actor {
    private static final Integer BLOB_WIDTH = 32;
    private static final Integer BLOB_HEIGHT = 32;
    
    //private static final Color COLOR = new Color( 123, 198, 132 );
    private int cursorID; 
    
    public TouchDot(Point position, int cursorID){
        super(position);
        this.cursorID = cursorID; 
    }

    /**
     * Return the cursorID associated with this touchDot
     * @return 
     */
    public int getCursorID(){
        return this.cursorID;
    }
    
    
    @Override
    public void init()
    {    

    }
    
    @Override
    public void update( int delta )
    {
     
    }

    @Override
    public void render(Graphics g) {
        System.out.println("Dot rendering at " + this.getX() + " " + this.getY());
        Point2D.Double position = GameManager.getInstance().getMap().toPositionInPixels( this.getX(), this.getY() );
        Shape shape = new Circle( (float)position.x, (float)position.y, (float)(TouchDot.BLOB_WIDTH/ 2.0) );

        g.setColor( Color.black);
        g.fill( shape );
        //g.setColor(Color.magenta);
        g.setLineWidth(3f);
        g.draw(shape);

    }
    
    
}
