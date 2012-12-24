package global;

import players.Player;
import TUIO.TuioClient;
import TUIO.TuioCursor;
import TUIO.TuioListener;
import TUIO.TuioObject;
import TUIO.TuioTime;
import actors.Cookie;
import actors.Whistle;
import base.Map;
import base.MovableActor;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import players.MobilePhonePlayer;
import players.MousePlayer;
import players.TouchPlayer;

/**
 *
 * @author roland
 */
public class GameManager implements TuioListener
{
    // Logging.
    private final static Logger LOGGER = Logger.getLogger( GameManager.class.getName() );     

    // Instance variable.
    private static final GameManager instance = new GameManager();
    
    // Current map.
    private Map map;
    
    // List of all the players.
    private List<Player> players;
    
    private TuioClient tuioClient;
    
    /**
     * Hidden constructor.
     */
    private GameManager()
    {
        this.map = null;
        this.players = new ArrayList<Player>();
        this.tuioClient = new TuioClient();
    }

    /**
     * Returns the instance.
     * @return 
     */
    public static GameManager getInstance()
    {
        return GameManager.instance;
    }
    
    public void init( ... )
    {
        
    }
    
    public void update( ... )
    {
        
    }
    
    

    public void setPlayers(){
        this.players = new ArrayList<Player>();
        try{
            this.players.add(new MousePlayer(0));//@TODO: add the right numbers
            this.players.add(new MobilePhonePlayer(1));//@TODO: add the right numbers
            this.players.add(new MobilePhonePlayer(2));//@TODO: add the right numbers
            this.players.add(new MobilePhonePlayer(3));//@TODO: add the right numbers
        }
        catch(Exception e){
            System.out.println("GameManager: problem with the initialisation of players");
            e.printStackTrace();
        }
        TuioClient client = new TuioClient();
        for(Player p : players){
            if (p instanceof MobilePhonePlayer){
                client.addTuioListener( (MobilePhonePlayer) p);
            }
        }

        client.connect();

        for(Player p : players){
            this.map.addObject(p.getCurrentObject());
        }
    }
    
    public void setMap( Map map )
    {
        this.map = map;
    }

    public Map getMap()
    {
        return this.map;
    }

    public List<Player> getPlayers()
    {
        return this.players;
    }

    
    public void update( GameContainer container, StateBasedGame game, int delta ) throws SlickException
    {

        Input input = container.getInput();
        for (Player player : players){

            if (player instanceof MobilePhonePlayer){
                MobilePhonePlayer currentPlayer = (MobilePhonePlayer) player; 
                if(currentPlayer.locationTelephone != null && currentPlayer.hasTelephoneOnTable){
                    System.out.println("First location: " + (int)currentPlayer.locationTelephone.getX() + " second location: " + (int)currentPlayer.locationTelephone.getY());
                    this.map.setActingPosition((int)currentPlayer.locationTelephone.getX(), (int)currentPlayer.locationTelephone.getY(), currentPlayer.getPlayerID());
                }
            }
            else if (player instanceof MousePlayer){
                if(input.isMouseButtonDown(input.MOUSE_LEFT_BUTTON)){
                    
                    this.map.setActingPosition(input.getMouseX(), input.getMouseY(), player.getPlayerID());
                }
            }
            else if (player instanceof TouchPlayer){
                System.out.println("Touch player not yet supported");
            }
            else{
                System.out.println("Player not supported");
            }
        }
        
        this.map.update( container, game, delta );  
    }

    public void changeObjectOfPlayer(MovableActor c) throws SlickException {
        
        for (Player player : players){
            if(c instanceof Cookie){
                Cookie newc = (Cookie) c;
                if(newc.getOwnerID() == player.getId())
                    player.changeCurrentObject(c);
            }
            else if(c instanceof Whistle){
                Whistle newc = (Whistle) c;
                if(newc.getOwnerID() == player.getId())
                    player.changeCurrentObject(c);
            }
            
            
        }
    }

    @Override
    public void addTuioObject( TuioObject object )
    {
        
    }

    @Override
    public void updateTuioObject( TuioObject object )
    {
        // Update players.
        for( Player player : this.players )
        {
            if( player.getId() == object.getSymbolID() )
            {
                if( player instanceof MobilePhonePlayer )
                {
                    MobilePhonePlayer mobilePhonePlayer = (MobilePhonePlayer)player;
                    mobilePhonePlayer.setMobilePhoneLocation( object.getX(), object.getY() );
                }
            }
        }
    }

    @Override
    public void removeTuioObject( TuioObject object )
    {
        // Update players.
        for( Player player : this.players )
        {
            if( player.getId() == object.getSymbolID() )
            {
                if( player instanceof MobilePhonePlayer )
                {
                    MobilePhonePlayer mobilePhonePlayer = (MobilePhonePlayer)player;
                    mobilePhonePlayer.setMobilePhoneLocation( object.getX(), object.getY() );
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