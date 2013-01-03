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
    private enum Button
    {
        ONE, TWO, THREE, FOUR
    };
    
    private static final String BACKGROUND_FILE_PATH = "../Resources/Images/Menu/background.jpg";

    private static final String BUTTON_START_FILE_PATH = "../Resources/Images/Menu/buttonStart.png";
    private static final String BUTTON_EXIT_FILE_PATH = "../Resources/Images/Menu/buttonExit.png";

    // Number of players buttons.
    private static final String BUTTON_NUMBER_OF_PLAYERS_ONE = "../Resources/Images/Menu/buttonNumberOfPlayersOne.png";
    private static final String BUTTON_ACTIVE_NUMBER_OF_PLAYERS_ONE = "../Resources/Images/Menu/buttonActiveNumberOfPlayersOne.png";
    
    private static final String BUTTON_NUMBER_OF_PLAYERS_TWO = "../Resources/Images/Menu/buttonNumberOfPlayersTwo.png";
    private static final String BUTTON_ACTIVE_NUMBER_OF_PLAYERS_TWO = "../Resources/Images/Menu/buttonActiveNumberOfPlayersTwo.png";

    private static final String BUTTON_NUMBER_OF_PLAYERS_THREE = "../Resources/Images/Menu/buttonNumberOfPlayersThree.png";
    private static final String BUTTON_ACTIVE_NUMBER_OF_PLAYERS_THREE = "../Resources/Images/Menu/buttonActiveNumberOfPlayersThree.png";

    private static final String BUTTON_NUMBER_OF_PLAYERS_FOUR = "../Resources/Images/Menu/buttonNumberOfPlayersFour.png";
    private static final String BUTTON_ACTIVE_NUMBER_OF_PLAYERS_FOUR = "../Resources/Images/Menu/buttonActiveNumberOfPlayersFour.png";
    
    // Titles.
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
    private Image buttonActiveNumberOfPlayersOne;
    private Point2D.Double buttonPositionNumberOfPlayersOne;
    
    private Image buttonNumberOfPlayersTwo;
    private Image buttonActiveNumberOfPlayersTwo;
    private Point2D.Double buttonPositionNumberOfPlayersTwo;
    
    private Image buttonNumberOfPlayersThree;
    private Image buttonActiveNumberOfPlayersThree;
    private Point2D.Double buttonPositionNumberOfPlayersThree;
    
    private Image buttonNumberOfPlayersFour;
    private Image buttonActiveNumberOfPlayersFour;
    private Point2D.Double buttonPositionNumberOfPlayersFour;
    
    private Image buttonStart;
    private Point2D.Double buttonPositionStart;
    
    private Image buttonExit;
    private Point2D.Double buttonPositionExit;
    
    private Button buttonSelectedNumberOfPlayers;
    
    /**
     * Constructor.
     */
    public MenuState()
    {
        this.buttonSelectedNumberOfPlayers = null;
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
        
        // Fonts.
        this.titleFont = new UnicodeFont( MenuState.TITLE_FONT_FILE_PATH, MenuState.TITLE_FONT_SIZE, false, false );
        this.titleFont.addAsciiGlyphs();
        this.titleFont.getEffects().add( new ColorEffect( java.awt.Color.WHITE ) );
        this.titleFont.loadGlyphs();        
        
        this.subTitleFont = new UnicodeFont( MenuState.SUB_TITLE_FONT_FILE_PATH, MenuState.SUB_TITLE_FONT_SIZE, false, false );
        this.subTitleFont.addAsciiGlyphs();
        this.subTitleFont.getEffects().add( new ColorEffect( java.awt.Color.WHITE ) );
        this.subTitleFont.loadGlyphs();
        
        // Buttons.
        this.buttonNumberOfPlayersOne = new Image( MenuState.BUTTON_NUMBER_OF_PLAYERS_ONE );
        this.buttonActiveNumberOfPlayersOne = new Image( MenuState.BUTTON_ACTIVE_NUMBER_OF_PLAYERS_ONE );
        this.buttonNumberOfPlayersTwo = new Image( MenuState.BUTTON_NUMBER_OF_PLAYERS_TWO );
        this.buttonActiveNumberOfPlayersTwo = new Image( MenuState.BUTTON_ACTIVE_NUMBER_OF_PLAYERS_TWO );
        this.buttonNumberOfPlayersThree = new Image( MenuState.BUTTON_NUMBER_OF_PLAYERS_THREE );
        this.buttonActiveNumberOfPlayersThree = new Image( MenuState.BUTTON_ACTIVE_NUMBER_OF_PLAYERS_THREE );
        this.buttonNumberOfPlayersFour = new Image( MenuState.BUTTON_NUMBER_OF_PLAYERS_FOUR );
        this.buttonActiveNumberOfPlayersFour = new Image( MenuState.BUTTON_ACTIVE_NUMBER_OF_PLAYERS_FOUR );
        
        this.buttonStart = new Image( MenuState.BUTTON_START_FILE_PATH );
        this.buttonExit = new Image( MenuState.BUTTON_EXIT_FILE_PATH );
        
        // Button positions.
        // Assuming all 'number of players'-buttons have equal width (@TODO: Bit fugly).
        Integer buttonWidthNumberOfPlayers = this.buttonNumberOfPlayersOne.getWidth();
        Double buttonMarginNumberOfPlayers = ( this.buttonStart.getWidth() - buttonWidthNumberOfPlayers * 4 ) / 3.0;
        Double buttonPositionInitial = ( container.getWidth() - buttonWidthNumberOfPlayers * 4 - buttonMarginNumberOfPlayers * 3 ) / 2;
                
        this.buttonPositionNumberOfPlayersOne = new Point2D.Double( buttonPositionInitial + ( buttonWidthNumberOfPlayers + buttonMarginNumberOfPlayers ) * 0, 200 ); // @TODO: Fugly magic number.
        this.buttonPositionNumberOfPlayersTwo = new Point2D.Double( buttonPositionInitial + ( buttonWidthNumberOfPlayers + buttonMarginNumberOfPlayers ) * 1, 200 ); // @TODO: Fugly magic number.
        this.buttonPositionNumberOfPlayersThree = new Point2D.Double( buttonPositionInitial + ( buttonWidthNumberOfPlayers + buttonMarginNumberOfPlayers ) * 2, 200 ); // @TODO: Fugly magic number.
        this.buttonPositionNumberOfPlayersFour = new Point2D.Double( buttonPositionInitial + ( buttonWidthNumberOfPlayers + buttonMarginNumberOfPlayers ) * 3, 200 ); // @TODO: Fugly magic number.
        
        this.buttonPositionStart = new Point2D.Double( ( container.getWidth() - this.buttonStart.getWidth() ) / 2, 260 ); // @TODO: Fugly magic number.
        this.buttonPositionExit = new Point2D.Double( ( container.getWidth() - this.buttonExit.getWidth() ) / 2, 320 ); // @TODO: Fugly magic number.
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
        if( this.buttonSelectedNumberOfPlayers == Button.ONE )
        {
            this.buttonActiveNumberOfPlayersOne.draw( (float)this.buttonPositionNumberOfPlayersOne.getX(), (float)this.buttonPositionNumberOfPlayersOne.getY() );
        }
        else
        {
            this.buttonNumberOfPlayersOne.draw( (float)this.buttonPositionNumberOfPlayersOne.getX(), (float)this.buttonPositionNumberOfPlayersOne.getY() );
        }
        
        if( this.buttonSelectedNumberOfPlayers == Button.TWO )
        {
            this.buttonActiveNumberOfPlayersTwo.draw( (float)this.buttonPositionNumberOfPlayersTwo.getX(), (float)this.buttonPositionNumberOfPlayersTwo.getY() );
        }
        else
        {
            this.buttonNumberOfPlayersTwo.draw( (float)this.buttonPositionNumberOfPlayersTwo.getX(), (float)this.buttonPositionNumberOfPlayersTwo.getY() );
        }
        
        if( this.buttonSelectedNumberOfPlayers == Button.THREE )
        {
            this.buttonActiveNumberOfPlayersThree.draw( (float)this.buttonPositionNumberOfPlayersThree.getX(), (float)this.buttonPositionNumberOfPlayersThree.getY() );
        }
        else
        {
            this.buttonNumberOfPlayersThree.draw( (float)this.buttonPositionNumberOfPlayersThree.getX(), (float)this.buttonPositionNumberOfPlayersThree.getY() );
        }
        
        if( this.buttonSelectedNumberOfPlayers == Button.FOUR )
        {
            this.buttonActiveNumberOfPlayersFour.draw( (float)this.buttonPositionNumberOfPlayersFour.getX(), (float)this.buttonPositionNumberOfPlayersFour.getY() );
        }
        else
        {
            this.buttonNumberOfPlayersFour.draw( (float)this.buttonPositionNumberOfPlayersFour.getX(), (float)this.buttonPositionNumberOfPlayersFour.getY() );
        }
        
        this.buttonStart.draw( (float)this.buttonPositionStart.getX(), (float)this.buttonPositionStart.getY() );
        this.buttonExit.draw( (float)this.buttonPositionExit.getX(), (float)this.buttonPositionExit.getY() );
    }

    @Override
    public void update( GameContainer container, StateBasedGame game, int delta ) throws SlickException
    {
        Input input = container.getInput();

        Integer mouseX = input.getMouseX();
        Integer mouseY = input.getMouseY();
        
        // Check if inside 'number of players one'-button.
        if( mouseX >= this.buttonPositionNumberOfPlayersOne.getX() && mouseX <= ( this.buttonPositionNumberOfPlayersOne.getX() + this.buttonNumberOfPlayersOne.getWidth() ) &&
            mouseY >= this.buttonPositionNumberOfPlayersOne.getY() && mouseY <= ( this.buttonPositionNumberOfPlayersOne.getY() + this.buttonNumberOfPlayersOne.getHeight() ) )
        {
            if( input.isMousePressed( Input.MOUSE_LEFT_BUTTON ) )
            {
                this.buttonSelectedNumberOfPlayers = Button.ONE;
            }
        }
        // Check if inside 'number of players two'-button.
        else if( mouseX >= this.buttonPositionNumberOfPlayersTwo.getX() && mouseX <= ( this.buttonPositionNumberOfPlayersTwo.getX() + this.buttonNumberOfPlayersTwo.getWidth() ) &&
                 mouseY >= this.buttonPositionNumberOfPlayersTwo.getY() && mouseY <= ( this.buttonPositionNumberOfPlayersTwo.getY() + this.buttonNumberOfPlayersTwo.getHeight() ) )
        {
            if( input.isMousePressed( Input.MOUSE_LEFT_BUTTON ) )
            {
                this.buttonSelectedNumberOfPlayers = Button.TWO;
            } 
        }
        // Check if inside 'number of players three'-button.
        else if( mouseX >= this.buttonPositionNumberOfPlayersThree.getX() && mouseX <= ( this.buttonPositionNumberOfPlayersThree.getX() + this.buttonNumberOfPlayersThree.getWidth() ) &&
                 mouseY >= this.buttonPositionNumberOfPlayersThree.getY() && mouseY <= ( this.buttonPositionNumberOfPlayersThree.getY() + this.buttonNumberOfPlayersThree.getHeight() ) )
        {
            if( input.isMousePressed( Input.MOUSE_LEFT_BUTTON ) )
            {
                this.buttonSelectedNumberOfPlayers = Button.THREE;
            }
        }
        // Check if inside 'number of players three'-button.
        else if( mouseX >= this.buttonPositionNumberOfPlayersFour.getX() && mouseX <= ( this.buttonPositionNumberOfPlayersFour.getX() + this.buttonNumberOfPlayersFour.getWidth() ) &&
                 mouseY >= this.buttonPositionNumberOfPlayersFour.getY() && mouseY <= ( this.buttonPositionNumberOfPlayersFour.getY() + this.buttonNumberOfPlayersFour.getHeight() ) )
        {
            if( input.isMousePressed( Input.MOUSE_LEFT_BUTTON ) )
            {
                this.buttonSelectedNumberOfPlayers = Button.FOUR;
            }   
        }
        // Check if inside start-button.
        else if( mouseX >= this.buttonPositionStart.getX() && mouseX <= ( this.buttonPositionStart.getX() + this.buttonStart.getWidth() ) &&
                 mouseY >= this.buttonPositionStart.getY() && mouseY <= ( this.buttonPositionStart.getY() + this.buttonStart.getHeight() ) )
        {
            if( input.isMousePressed( Input.MOUSE_LEFT_BUTTON ) )
            {
                game.enterState( Game.GAME_STATE_GAME );
            }
        }
        // Check if inside exit-button.
        else if( mouseX >= this.buttonPositionExit.getX() && mouseX <= ( this.buttonPositionExit.getX() + this.buttonExit.getWidth() ) &&
                 mouseY >= this.buttonPositionExit.getY() && mouseY <= ( this.buttonPositionExit.getY() + this.buttonExit.getHeight() ) )
        {
            if( input.isMousePressed( Input.MOUSE_LEFT_BUTTON ) )
            {
                container.exit();
            }
        }
    }
}
