package states;

import TUIO.TuioClient;
import base.Map;
import base.Player;
import connectors.PhoneConnector;
import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author bootsman
 */
public class GameState extends BasicGameState
{
    public static final int ID = 1;
    
    private Map currentMap; 
    private List<Map> maps; //@TODO: load levels
    private List<Player> players; //@TODO: add list of players
    
    
    public GameState() throws SlickException
    {
        TuioClient client = new TuioClient(); 
        PhoneConnector phoneConnector = new PhoneConnector(); 
        client.addTuioListener( phoneConnector );
        client.connect();
    }
    
    @Override
    public int getID()
    {
       return GameState.ID;
    }

    @Override
    public void init( GameContainer container, StateBasedGame game ) throws SlickException
    {
        // Initialize levels.
        this.maps = new ArrayList<Map>();
        this.maps.add( new Map( "../Resources/Maps/level1.tmx" ) );
        this.maps.add( new Map( "../Resources/Maps/level2.tmx" ) );
        
        this.currentMap = this.maps.get( 0 );
        this.currentMap.init( container, game );  
    }

    @Override
    public void render( GameContainer container, StateBasedGame game, Graphics g ) throws SlickException
    {
         this.currentMap.render( container, game, g );
    }

    @Override
    public void update( GameContainer container, StateBasedGame game, int delta ) throws SlickException
    {
        this.currentMap.update( container, game, delta );  
    }
}
