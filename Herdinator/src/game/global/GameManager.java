package game.global;

import TUIO.TuioClient;
import game.base.Map;
import game.players.Player;
import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author roland
 */
public class GameManager
{
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

    /*
    public void init( ... )
    {  
    }
    */
    
    public void update( GameContainer container, StateBasedGame game, int delta )
    {
        
    }
    
    
/*
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
    * */
    
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

    /*
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
*/

}