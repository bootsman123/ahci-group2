package game.states;

import game.Game;
import game.global.GameManager;
import game.gui.ObjectPicker;
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
    private ObjectPicker overlay;

    public GameState() throws SlickException
    {
    }
    
    @Override
    public int getID()
    {
       return Game.GAME_STATE_GAME;
    }

    @Override
    public void init( GameContainer container, StateBasedGame game ) throws SlickException
    {
    }
    
    @Override
    public void enter( GameContainer container, StateBasedGame game ) throws SlickException
    {
        GameManager.getInstance().init( container, game );
        this.overlay = new ObjectPicker();
        this.overlay.init( container, game );
    }

    @Override
    public void render( GameContainer container, StateBasedGame game, Graphics g ) throws SlickException
    {
        GameManager.getInstance().render( container, game, g );
        
        this.overlay.render(container, game, g);
    }

    @Override
    public void update( GameContainer container, StateBasedGame game, int delta ) throws SlickException
    {
        GameManager.getInstance().update( container, game, delta );  
        
        this.overlay.update( container, game, delta );
    }
}
