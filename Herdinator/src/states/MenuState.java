package states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
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
    private static final String EXIT_BUTTON_FILE_PATH = "../Resources/Images/exitbutton.png";
    Image logo = null;
    Image startbuttonlogo = null;
    Image exitbuttonlogo = null;
    public static final int ID = 2;
    private float startGameScale = 1;
    private float exitScale = 1;
    private float scaleStep = 0.0001f;
    int menuX = 0;
    int menuY = 0;
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
        this.exitbuttonlogo = new Image(MenuState.START_BUTTON_FILE_PATH);
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        this.logo.draw(0, 0);
        this.startbuttonlogo.draw(10, 10);
        this.exitbuttonlogo.draw(20, 30);

        // render the background
        this.logo.draw(0, 0);

        // Draw menu
        this.startbuttonlogo.draw(menuX, menuY, startGameScale);

        this.exitbuttonlogo.draw(menuX, menuY+80, exitScale);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        Input input = container.getInput();

        int mouseX = input.getMouseX();
        int mouseY = input.getMouseY();
        
        boolean insideStartGame = false;
        boolean insideExit = false;

        if( ( mouseX >= menuX && mouseX <= menuX + startbuttonlogo.getWidth()) && ( mouseY >= menuY && mouseY <= menuY + startbuttonlogo.getHeight()) ){
            insideStartGame = true;
        }
        else if( ( mouseX >= menuX && mouseX <= menuX+ exitbuttonlogo.getWidth()) &&
                  ( mouseY >= menuY+80 && mouseY <= menuY+80 + exitbuttonlogo.getHeight()) ){
            insideExit = true;

        }

        if(insideStartGame){
          if(startGameScale < 1.05f)
            startGameScale += scaleStep * delta;

            if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) ){
                System.out.println("Starting game");
                  // sb.enterState(SlickBlocksGame.GAMEPLAYSTATE);
            }
         }
        
        else{
          if(startGameScale > 1.0f){
            startGameScale -= scaleStep * delta;
          }

          if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) ){
            //gc.exit();
          }
        }

        if(insideExit)
        {
           if(exitScale < 1.05f)
             exitScale +=  scaleStep * delta;
        }
        else{
          if(exitScale > 1.0f){
            exitScale -= scaleStep * delta;
          }
        }
 
    }
    
}
