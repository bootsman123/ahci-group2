package game.players;

import java.awt.geom.Point2D;
import org.newdawn.slick.Color;

/**
 * Mobile Players
 * @author roland
 */
public class TangiblePlayer extends Player
{
    private Integer markId;    
    private Point2D tangibleLocation;

    /**
     * Constructor.
     * @param color 
     */
    public TangiblePlayer( Color color, Integer markId )
    {
        super( color );
        
        this.markId = markId;        
        this.tangibleLocation = null;
    }
    
    /**
     * Set the location of the tangible.
     * @param tangibleLocation 
     */
    public void setTangibleLocation( Point2D.Double tangibleLocation )
    {
        this.tangibleLocation = tangibleLocation;
    }
    
    /**
     * Get the location of the tangible.
     * @return 
     */
    public Point2D getTangibleLocation()
    {
        return this.tangibleLocation;
    }
    
    /**
     * Returns true if the tangible is on the table, false otherwise.
     * @return 
     */
    public Boolean isTangibleOnTable()
    {
        return this.tangibleLocation != null;
    }
    
    /**
     * Return the mark id.
     * @return 
     */
    public Integer getMarkId()
    {
        return this.markId;
    }
}
