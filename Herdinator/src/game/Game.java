package game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import game.states.GameState;
import game.states.MenuState;

/**
 *
 * @author bootsman
 */
public class Game extends StateBasedGame
{
    // Constants of all possible game states.
    public static final int GAME_STATE_MENU = 1;
    public static final int GAME_STATE_GAME = 2;
    
    public static final String NAME = "Herdinator";
    public static final int WIDTH = 800;
    public static final int HEIGHT = 640;

    /**
     * Constructor.
     * @throws SlickException 
     */
    public Game() throws SlickException
    {
        super( Game.NAME );
        
        this.addState( new MenuState() );
        this.addState( new GameState() );
        
        // @TODO: Just for debugging.
        //this.enterState( Game.GAME_STATE_MENU );
        this.enterState( Game.GAME_STATE_GAME );
    }

    @Override
    public void initStatesList( GameContainer container ) throws SlickException
    {
        this.getState( Game.GAME_STATE_MENU ).init( container, this );
        this.getState( Game.GAME_STATE_GAME ).init( container, this );
    }
}
