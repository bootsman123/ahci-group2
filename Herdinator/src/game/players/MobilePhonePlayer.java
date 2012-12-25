package game.players;

import java.awt.geom.Point2D;
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
    public MobilePhonePlayer( Integer id ) throws SlickException
    {
        super( id ); 
        this.mobilePhoneLocation = null;
    }
    
    public void setMobilePhoneLocation( Point2D.Float mobilePhoneLocation )
    {
        this.mobilePhoneLocation = mobilePhoneLocation;
    }
    
    public void setMobilePhoneLocation( Float x, Float y )
    {
        this.setMobilePhoneLocation( new Point2D.Float( x, y ) );
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
