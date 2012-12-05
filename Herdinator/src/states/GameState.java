package states;

import TUIO.TuioClient;
import base.Level;
import base.Player;
import connectors.PhoneConnector;
import java.util.ArrayList;
import java.util.List;
import levels.Level1;
import levels.Level2;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import players.MobilePlayer;

/**
 *
 * @author bootsman
 */
public class GameState extends BasicGameState
{
    public static final int ID = 1;
    
    private Level currentLevel; 
    private List<Level> levels; //@TODO: load levels
    private List<Player> players; //@TODO: add list of players
    
    
    public GameState() throws SlickException
    {
        TuioClient client = new TuioClient() ; 
        PhoneConnector phoneConnector = new PhoneConnector(); 
        client.addTuioListener(phoneConnector);
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
        this.levels = new ArrayList<Level>();
        this.levels.add( new Level1() );
        this.levels.add( new Level2() );
        
        this.currentLevel = this.levels.get( 0 );
        this.currentLevel.init( container, game );  
    }

    @Override
    public void render( GameContainer container, StateBasedGame game, Graphics g ) throws SlickException
    {
         this.currentLevel.render( container, game, g );     
    }

    @Override
    public void update( GameContainer container, StateBasedGame game, int delta ) throws SlickException
    {
        Input input = container.getInput();
        this.currentLevel.update( container, game, delta );  
    }
}
