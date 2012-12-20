/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package base;

import TUIO.TuioClient;
import actors.Cookie;
import actors.Whistle;
import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import players.MobilePlayer;
import players.MousePlayer;
import players.TouchPlayer;

/**
 *
 * @author roland
 */
public class GameManager {
    private static final GameManager instance = new GameManager();
    private Map currentMap;
    private List<Player> players; //@TODO: add list of players
   // private PhoneConnector phoneConnector;
    private GameManager(){
        
    }

    public static GameManager getInstance(){
        return instance;
    }

    public void setPlayers(){
        this.players = new ArrayList<Player>();
        try{
            this.players.add(new MousePlayer(0));//@TODO: add the right numbers
            this.players.add(new MobilePlayer(1));//@TODO: add the right numbers
            this.players.add(new MobilePlayer(2));//@TODO: add the right numbers
            this.players.add(new MobilePlayer(3));//@TODO: add the right numbers
        }
        catch(Exception e){
            System.out.println("GameManager: problem with the initialisation of players");
            e.printStackTrace();
        }
        TuioClient client = new TuioClient();
        for(Player p : players){
            if (p instanceof MobilePlayer){
                client.addTuioListener( (MobilePlayer) p);
            }
        }

        client.connect();

        for(Player p : players){
            this.currentMap.addObject(p.getCurrentObject());
        }
    }
    public void setMap(Map newMap){
        this.currentMap = newMap;
    }

    public Map getMap(){
        return this.currentMap;
    }

    public List<Player> getPlayers(){
        return this.players;
    }

    
    public void update( GameContainer container, StateBasedGame game, int delta ) throws SlickException
    {

        Input input = container.getInput();
        for (Player player : players){

            if (player instanceof MobilePlayer){
                MobilePlayer currentPlayer = (MobilePlayer) player; 
                if(currentPlayer.locationTelephone != null && currentPlayer.hasTelephoneOnTable){
                    System.out.println("First location: " + (int)currentPlayer.locationTelephone.getX() + " second location: " + (int)currentPlayer.locationTelephone.getY());
                    this.currentMap.setActingPosition((int)currentPlayer.locationTelephone.getX(), (int)currentPlayer.locationTelephone.getY(), currentPlayer.getPlayerID());
                }
            }
            else if (player instanceof MousePlayer){
                if(input.isMouseButtonDown(input.MOUSE_LEFT_BUTTON)){
                    
                    this.currentMap.setActingPosition(input.getMouseX(), input.getMouseY(), player.getPlayerID());
                }
            }
            else if (player instanceof TouchPlayer){
                System.out.println("Touch player not yet supported");
            }
            else{
                System.out.println("Player not supported");
            }
        }
        
        this.currentMap.update( container, game, delta );  
    }

    void changeObjectOfPlayer(MovableActor c) throws SlickException {
        
        for (Player player : players){
            if(c instanceof Cookie){
                Cookie newc = (Cookie) c;
                if(newc.getOwnerID() == player.getPlayerID())
                    player.changeCurrentObject(c);
            }
            else if(c instanceof Whistle){
                Whistle newc = (Whistle) c;
                if(newc.getOwnerID() == player.getPlayerID())
                    player.changeCurrentObject(c);
            }
            
            
        }
    }

    

    


    

}
