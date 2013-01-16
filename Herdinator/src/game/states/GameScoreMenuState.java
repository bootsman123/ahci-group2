package game.states;

import game.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author bootsman
 */
public class GameScoreMenuState extends MenuState
{
    public GameScoreMenuState()
    {
        super();
    }
    
    @Override
    public int getID()
    {
        return Game.GAME_SCORE_MENU_STATE;
    }
    
    @Override
    public void render( GameContainer container, StateBasedGame game, Graphics g ) throws SlickException
    {
        
    }
}