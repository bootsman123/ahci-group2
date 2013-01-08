package game.players;

import java.awt.geom.Point2D;
import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;

/**
 * Mobile Players
 * @author roland
 */
public class MobilePhonePlayer extends Player
{
    private Point2D mobilePhoneLocation;

    /**
     * Constructor.
     * @param id Mark identifier.
     * @throws SlickException 
     */
    public MobilePhonePlayer( Integer id, Color color ) throws SlickException
    {
        super( id, color );
        this.mobilePhoneLocation = null;
    }
    
    public void setMobilePhoneLocation( Point2D.Double mobilePhoneLocation )
    {
        this.mobilePhoneLocation = mobilePhoneLocation;
    }
    
    public void setMobilePhoneLocation( Double x, Double y )
    {
        this.setMobilePhoneLocation( new Point2D.Double( x, y ) );
    }
    
    public Point2D getMobilePhoneLocation()
    {
        return this.mobilePhoneLocation;
    }
    
    public Boolean isMobilePhoneOnTable()
    {
        return this.mobilePhoneLocation != null;
    }
}
