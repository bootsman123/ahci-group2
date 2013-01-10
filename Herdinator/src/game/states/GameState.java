package game.states;

import game.Game;
import game.global.GameManager;
import game.interfaces.UsableActorContainer;
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
    private UsableActorContainer overlay;

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
        this.overlay = new UsableActorContainer(container);
        System.out.println("GameState.enter: Init overlay!");
        this.overlay.init( container, game );
    }

    @Override
    public void render( GameContainer container, StateBasedGame game, Graphics g ) throws SlickException
    {
        GameManager.getInstance().render( container, game, g );
        
        this.overlay.render(container, g);
    }

    @Override
    public void update( GameContainer container, StateBasedGame game, int delta ) throws SlickException
    {
        GameManager.getInstance().update( container, game, delta );  
        
        this.overlay.update( container, game, delta );
    }
}
