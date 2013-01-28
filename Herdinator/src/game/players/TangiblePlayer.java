package game.players;

import java.awt.geom.Point2D;

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
    public TangiblePlayer( Integer markId )
    {
        super();
        
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
