package main;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import states.GameState;
import states.MenuState;

/**
 *
 * @author bootsman
 */
public class Game extends StateBasedGame
{
    public static final String NAME = "Herdinator";
    public static final int WIDTH = 800;
    public static final int HEIGHT = 640;
    
    private GameState currentState; 
    private MenuState mainMenu;

    public Game() throws SlickException
    {
        super( Game.NAME );
    }

    @Override
    public void initStatesList( GameContainer container ) throws SlickException
    {
        this.currentState = new GameState();
        this.mainMenu = new MenuState();
        this.addState( this.currentState );
        this.addState( this.mainMenu );
        this.enterState( this.currentState.getID() );
    }
}
