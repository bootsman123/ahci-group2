package states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author bootsman
 */
public class MenuState extends BasicGameState
{
    private static final String LOGO_FILE_PATH = "../Resources/Images/herdinatorlogo.png";
    private static final String START_BUTTON_FILE_PATH = "../Resources/Images/startbutton.png";
    Image logo = null;
    Image startbuttonlogo = null;
    public static final int ID = 2;
    public MenuState(){
        
    }

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        this.logo = new Image(MenuState.LOGO_FILE_PATH);
        this.startbuttonlogo = new Image(MenuState.START_BUTTON_FILE_PATH);
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        this.logo.draw(0, 0);
        this.startbuttonlogo.draw(10, 10);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {

    }
    
}
