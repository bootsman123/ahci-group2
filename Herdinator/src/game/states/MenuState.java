package game.states;

import game.Game;
import java.awt.geom.Point2D;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author bootsman
 */
public class MenuState extends BasicGameState
{    
    private static final String BACKGROUND_FILE_PATH = "../Resources/Images/Menu/background.jpg";
    private static final String START_BUTTON_FILE_PATH = "../Resources/Images/Menu/buttonStart.png";
    private static final String EXIT_BUTTON_FILE_PATH = "../Resources/Images/Menu/buttonExit.png";
    
    private static final String TITLE = "Herdinator";
    private static final String TITLE_FONT_FILE_PATH = "../Resources/Fonts/Harabara.ttf";
    private static final Integer TITLE_FONT_SIZE = 60;
    
    private static final String SUB_TITLE = "Catching sheep has never been more fun!";
    private static final String SUB_TITLE_FONT_FILE_PATH = "../Resources/Fonts/simplicity.ttf";
    private static final Integer SUB_TITLE_FONT_SIZE = 30;
    
    private UnicodeFont titleFont;
    private UnicodeFont subTitleFont;
    
    private Image background;
    
    private Image buttonNumberOfPlayersOne;
    private Point2D.Double buttonNumberOfPlayersOnePosition;
    
    private Image buttonNumberOfPlayersTwo;
    private Point2D.Double buttonNumberOfPlayersTwoPosition;
    
    private Image buttonNumberOfPlayersThree;
    private Point2D.Double buttonNumberOfPlayersThreePosition;
    
    private Image buttonNumberOfPlayersFour;
    private Point2D.Double buttonNumberOfPlayersFourPosition;
    
    private Image buttonStart;
    private Point2D.Double buttonStartPosition;
    
    private Image buttonExit;
    private Point2D.Double buttonExitPosition;
    
    /**
     * Constructor.
     */
    public MenuState()
    {
    }

    @Override
    public int getID()
    {
        return Game.GAME_STATE_MENU;
    }

    @Override
    public void init( GameContainer container, StateBasedGame game ) throws SlickException
    {
        this.background = new Image( MenuState.BACKGROUND_FILE_PATH );
        
        this.titleFont = new UnicodeFont( MenuState.TITLE_FONT_FILE_PATH, MenuState.TITLE_FONT_SIZE, false, false );
        this.titleFont.addAsciiGlyphs();
        this.titleFont.getEffects().add( new ColorEffect( java.awt.Color.WHITE ) );
        this.titleFont.loadGlyphs();        
        
        this.subTitleFont = new UnicodeFont( MenuState.SUB_TITLE_FONT_FILE_PATH, MenuState.SUB_TITLE_FONT_SIZE, false, false );
        this.subTitleFont.addAsciiGlyphs();
        this.subTitleFont.getEffects().add( new ColorEffect( java.awt.Color.WHITE ) );
        this.subTitleFont.loadGlyphs();
        
        this.buttonStart = new Image( MenuState.START_BUTTON_FILE_PATH );
        this.buttonStartPosition = new Point2D.Double( ( container.getWidth() - this.buttonStart.getWidth() ) / 2, 200 ); // @TODO: Fugly magic number.
       
        this.buttonExit = new Image( MenuState.EXIT_BUTTON_FILE_PATH );
        this.buttonExitPosition = new Point2D.Double( ( container.getWidth() - this.buttonExit.getWidth() ) / 2, 260 ); // @TODO: Fugly magic number.
    }

    @Override
    public void render( GameContainer container, StateBasedGame game, Graphics g ) throws SlickException
    {
        this.background.draw( 0, 0, container.getWidth(), container.getHeight() );
        
        // Draw title.
        Integer titleX = ( container.getWidth() - this.titleFont.getWidth( MenuState.TITLE ) ) / 2;
        Integer titleY = 20;
        
        this.titleFont.drawString( titleX, titleY, MenuState.TITLE );
        
        // Draw sub title.
        Integer subTitleX = ( container.getWidth() - this.subTitleFont.getWidth( MenuState.SUB_TITLE ) ) / 2;
        Integer subTitleY = 80;
        
        this.subTitleFont.drawString( subTitleX, subTitleY, MenuState.SUB_TITLE );
              
        // Draw buttons.
        this.buttonStart.draw( (float)this.buttonStartPosition.getX(), (float)this.buttonStartPosition.getY() );
        this.buttonExit.draw( (float)this.buttonExitPosition.getX(), (float)this.buttonExitPosition.getY() );
    }

    @Override
    public void update( GameContainer container, StateBasedGame game, int delta ) throws SlickException
    {
        Input input = container.getInput();

        Integer mouseX = input.getMouseX();
        Integer mouseY = input.getMouseY();
        
        // Check if inside start-button.
        if( mouseX >= this.buttonStartPosition.getX() && mouseX <= ( this.buttonStartPosition.getX() + this.buttonStart.getWidth() ) &&
            mouseY >= this.buttonStartPosition.getY() && mouseY <= ( this.buttonStartPosition.getY() + this.buttonStart.getHeight() ) )
        {
            if( input.isMousePressed( Input.MOUSE_LEFT_BUTTON ) )
            {
                game.enterState( Game.GAME_STATE_GAME );
            }
        }
        // Check if inside exit-button.
        else if( mouseX >= this.buttonExitPosition.getX() && mouseX <= ( this.buttonExitPosition.getX() + this.buttonExit.getWidth() ) &&
                 mouseY >= this.buttonExitPosition.getY() && mouseY <= ( this.buttonExitPosition.getY() + this.buttonExit.getHeight() ) )
        {
            if( input.isMousePressed( Input.MOUSE_LEFT_BUTTON ) )
            {
                container.exit();
            }
        }
    }
}
