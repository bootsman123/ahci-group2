package game;

import game.global.GameManager;
import game.states.GameState;
import game.states.ModalityMouseAndTouchMenuState;
import game.states.ModalitySelectorMenuState;
import game.states.ModalityTangiblesMenuState;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author bootsman
 */
public class Game extends StateBasedGame
{
    // Constants of all possible game states.
    public static final int MENU_MODALITY_SELECTOR_STATE = 1;
    public static final int MODALITY_MOUSE_AND_TOUCH_MENU_STATE = 2;
    public static final int MODALITY_TANGIBLES_MENU_STATE = 3;
    public static final int GAME_STATE = 4;
    
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
        
        this.addState( new ModalitySelectorMenuState() );
        this.addState( new ModalityMouseAndTouchMenuState() );
        this.addState( new ModalityTangiblesMenuState() );
        this.addState( new GameState() );
    }

    @Override
    public void initStatesList( GameContainer container ) throws SlickException
    {        
        /*
        this.getState( Game.MENU_MODALITY_SELECTOR_STATE ).init( container, this );
        this.getState( Game.MODALITY_MOUSE_AND_TOUCH_MENU_STATE ).init( container, this );
        this.getState( Game.MODALITY_TANGIBLES_MENU_STATE ).init( container, this );
        this.getState( Game.GAME_STATE ).init( container, this );
        */
    }
}
