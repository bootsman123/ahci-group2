package game.gui;

import TUIO.TuioCursor;
import TUIO.TuioListener;
import TUIO.TuioObject;
import TUIO.TuioTime;
import game.global.GameManager;
import game.players.MobilePhonePlayer;
import game.players.Player;

/**
 *
 * @author bootsman
 */
public class MobilePhoneHandler implements TuioListener
{
    
    public MobilePhoneHandler()
    {
        
    }
    
    @Override
    public void addTuioObject( TuioObject object )
    {
        
    }

    @Override
    public void updateTuioObject( TuioObject object )
    {
        // Update players.
        for( Player player : GameManager.getInstance().getPlayers() )
        {
            if( player.getId() == object.getSymbolID() )
            {
                if( player instanceof MobilePhonePlayer )
                {
                    MobilePhonePlayer mobilePhonePlayer = (MobilePhonePlayer)player;
                    mobilePhonePlayer.setMobilePhoneLocation( (double)object.getX(), (double)object.getY() );
                }
            }
        }
    }

    @Override
    public void removeTuioObject( TuioObject object )
    {
        // Update players.
        for( Player player : GameManager.getInstance().getPlayers() )
        {
            if( player.getId() == object.getSymbolID() )
            {
                if( player instanceof MobilePhonePlayer )
                {
                    MobilePhonePlayer mobilePhonePlayer = (MobilePhonePlayer)player;
                    mobilePhonePlayer.setMobilePhoneLocation( (double)object.getX(), (double)object.getY() );
                }
            }
        }
    }

    @Override
    public void addTuioCursor( TuioCursor cursor )
    {
    }

    @Override
    public void updateTuioCursor( TuioCursor cursor )
    {
    }

    @Override
    public void removeTuioCursor( TuioCursor cursor )
    {
    }

    @Override
    public void refresh( TuioTime time )
    {
    }
}