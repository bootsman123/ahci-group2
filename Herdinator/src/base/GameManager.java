/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package base;

import TUIO.TuioClient;
import TUIO.TuioObject;
import connectors.PhoneConnector;
import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author roland
 */
public class GameManager {
    private static final GameManager instance = new GameManager();
    private Map currentMap;
    private List<Player> players; //@TODO: add list of players
    private PhoneConnector phoneConnector;
    private GameManager(){
        this.players = new ArrayList<Player>();
        TuioClient client = new TuioClient();
        this.phoneConnector = new PhoneConnector();
        client.addTuioListener( phoneConnector );
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
    
    public void update( GameContainer container, StateBasedGame game, int delta ) throws SlickException
    {
        Input input = container.getInput();
        ArrayList<TuioObject> currentObjects = phoneConnector.getCurrentObjects();
        if(currentObjects.size() > 0){
            TuioObject firstObject = currentObjects.get(0);
        
//            this.currentMap.setMousePosition(input.getMouseX(), input.getMouseY());
            this.currentMap.setMousePosition((int)(firstObject.getX()*currentMap.getMapWidth()), (int)(firstObject.getY()*currentMap.getMapHeight()));
        }
        this.currentMap.update( container, game, delta );  
    }

    

}
