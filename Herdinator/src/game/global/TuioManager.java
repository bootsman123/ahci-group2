package game.global;

import TUIO.TuioClient;
import TUIO.TuioListener;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bootsman
 */
public class TuioManager
{
    // Singleton instance variable.
    private static final TuioManager INSTANCE = new TuioManager();
    
    private TuioClient tuioClient;
    
    /**
     * Constructor.
     */
    private TuioManager()
    {
        this.tuioClient = new TuioClient();
        this.tuioClient.connect();
    }
    
    /**
     * Returns the instance.
     * @return 
     */
    public static TuioManager getInstance()
    {
        return TuioManager.INSTANCE;
    }
    
    /**
     * Add a TUIO listener.
     * @param listener 
     */
    public void addTuioListener( TuioListener listener )
    {
        this.tuioClient.addTuioListener( listener );
    }
    
    /**
     * Remove a TUIO listener.
     * @param listener 
     */
    public void removeTuioListener( TuioListener listener )
    {
        this.tuioClient.removeTuioListener( listener );
    }
}