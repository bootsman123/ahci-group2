package game;

import game.states.GameScoreMenuState;
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
    public static final Integer MENU_MODALITY_SELECTOR_STATE = 1;
    public static final Integer MODALITY_MOUSE_AND_TOUCH_MENU_STATE = 2;
    public static final Integer MODALITY_TANGIBLES_MENU_STATE = 3;    
    public static final Integer GAME_SCORE_MENU_STATE = 4; 
    public static final Integer GAME_STATE = 5;
    
    public static final String NAME = "Herdinator";
    public static final Integer WIDTH = 800;
    public static final Integer HEIGHT = 640;

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
        this.addState( new GameScoreMenuState() );
    }

    @Override
    public void initStatesList( GameContainer container ) throws SlickException
    {
    }
}
