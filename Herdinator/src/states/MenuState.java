package states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author bootsman
 */
public class MenuState extends BasicGameState
{
    public static final int ID = 2;
    public MenuState(){
        
    }

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
