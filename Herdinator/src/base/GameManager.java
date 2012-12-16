/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package base;

import TUIO.TuioClient;
import base.Player.MovableObjects;
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
        this.players = new ArrayList<Player>();
        this.players.add(new MousePlayer(0));//@TODO: add the right numbers
       // this.players.add(new MobilePlayer(1));//@TODO: add the right numbers
       //this.players.add(new MobilePlayer(2));//@TODO: add the right numbers
       // this.players.add(new MobilePlayer(3));//@TODO: add the right numbers
        
        TuioClient client = new TuioClient();
        for(Player p : players){
            if (p instanceof MobilePlayer){
                client.addTuioListener( (MobilePlayer) p);
            }
        }
        
        client.connect();
        
        
    }

    public static GameManager getInstance(){
        return instance;
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
                    this.currentMap.setCookiePosition((int)currentPlayer.locationTelephone.getX(), (int)currentPlayer.locationTelephone.getY(), currentPlayer.getPlayerID());
                }
            }
            else if (player instanceof MousePlayer){
                if(input.isMouseButtonDown(input.MOUSE_LEFT_BUTTON)){
                    
                    this.currentMap.setCookiePosition(input.getMouseX(), input.getMouseY(), player.getPlayerID());
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

    public void removeObject(MovableObjects currentObject, int playerID) {
        if(currentObject.equals(MovableObjects.Cookie)){
            this.currentMap.removeCookie(playerID);
        }
    }

    void addObject(MovableObjects currentObject, int playerID) throws SlickException {
        if(currentObject.equals(MovableObjects.Cookie)){
            this.currentMap.addCookie(playerID);
        }
    }

    

}
