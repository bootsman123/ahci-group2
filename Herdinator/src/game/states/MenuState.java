package game.states;

import game.Game;
import game.gui.Button;
import game.gui.ButtonGroup;
import game.gui.ButtonToggle;
import java.awt.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
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

    private static final String BUTTON_START = "../Resources/Images/Menu/buttonStart.png";
    private static final String BUTTON_EXIT = "../Resources/Images/Menu/buttonExit.png";

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
    private static final String TITLE_STRING = "Herdinator";
    private static final String TITLE_FONT_FILE_PATH = "../Resources/Fonts/Harabara.ttf";
    private static final Integer TITLE_FONT_SIZE = 60;
    
    private static final String SUB_TITLE_STRING = "Catching sheep has never been more fun! YOHO, you only herd once.  ";
    private static final String[] POSSIBLE_SUB_TITLE_STRINGS = {
        "Catching sheep has never been more fun!",
        "YOHO, you only herd once!",
        "It's all fun and games until someone gets herd!",
        "You may have herd of our game!",
        "Q:what has 8 legs, 4 ears, and twice as much wool as a sheep? A: 2 sheep.",
        "Why did the ram jump off the cliff? He didn't see the ewe turn.",
        "What did the sheep say to another sheep after they had come back from being ill? How are ewe!",
        "My jokes are sheeeeppppp."
    };
    private String subTitleStringForNow;

    private static final String SUB_TITLE_FONT_FILE_PATH = "../Resources/Fonts/simplicity.ttf";
    private static final Integer SUB_TITLE_FONT_SIZE = 30;
    
    private static final String NUMBER_OF_PLAYERS_STRING = "Number of players:";
    private static final Integer NUMBER_OF_PLAYERS_FONT_SIZE = 16;
    
    private UnicodeFont titleFont;
    private UnicodeFont subTitleFont;
    private UnicodeFont numberOfPlayersFont;
    
    private Image background;
        
    private ButtonToggle buttonNumberOfPlayersOne;
    private ButtonToggle buttonNumberOfPlayersTwo;
    private ButtonToggle buttonNumberOfPlayersThree;
    private ButtonToggle buttonNumberOfPlayersFour;
    
    private ButtonGroup buttonNumberOfPlayers;
    
    private Button buttonStart;
    private Button buttonExit;
    
    /**
     * Constructor.
     */
    public MenuState()
    {
        super();
    }

    @Override
    public int getID()
    {
        return Game.GAME_STATE_MENU;
    }

    @Override
    public void init( GameContainer container, StateBasedGame game ) throws SlickException
    {
     
        this.subTitleStringForNow = MenuState.POSSIBLE_SUB_TITLE_STRINGS[(int)(Math.random()*MenuState.POSSIBLE_SUB_TITLE_STRINGS.length)];
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
        
        java.awt.Font font = new java.awt.Font( "Verdana", Font.BOLD, MenuState.NUMBER_OF_PLAYERS_FONT_SIZE );
        this.numberOfPlayersFont = new UnicodeFont( font );
        this.numberOfPlayersFont.addAsciiGlyphs();
        this.numberOfPlayersFont.getEffects().add( new ColorEffect( java.awt.Color.WHITE ) );
        this.numberOfPlayersFont.loadGlyphs();
        
        // Buttons.        
        this.buttonNumberOfPlayersOne = new ButtonToggle( container, MenuState.BUTTON_NUMBER_OF_PLAYERS_ONE, MenuState.BUTTON_ACTIVE_NUMBER_OF_PLAYERS_ONE );
        this.buttonNumberOfPlayersTwo = new ButtonToggle( container, MenuState.BUTTON_NUMBER_OF_PLAYERS_TWO, MenuState.BUTTON_ACTIVE_NUMBER_OF_PLAYERS_TWO );
        this.buttonNumberOfPlayersThree = new ButtonToggle( container, MenuState.BUTTON_NUMBER_OF_PLAYERS_THREE, MenuState.BUTTON_ACTIVE_NUMBER_OF_PLAYERS_THREE );
        this.buttonNumberOfPlayersFour = new ButtonToggle( container, MenuState.BUTTON_NUMBER_OF_PLAYERS_FOUR, MenuState.BUTTON_ACTIVE_NUMBER_OF_PLAYERS_FOUR );
        
        // Create a button group.
        this.buttonNumberOfPlayers = new ButtonGroup();
        this.buttonNumberOfPlayers.add( this.buttonNumberOfPlayersOne );
        this.buttonNumberOfPlayers.add( this.buttonNumberOfPlayersTwo );
        this.buttonNumberOfPlayers.add( this.buttonNumberOfPlayersThree );
        this.buttonNumberOfPlayers.add( this.buttonNumberOfPlayersFour );
        
        this.buttonStart = new Button( container, MenuState.BUTTON_START, MenuState.BUTTON_START );
        this.buttonExit = new Button( container, MenuState.BUTTON_EXIT, MenuState.BUTTON_EXIT );
        
        // Button positions.
        // Assuming all 'number of players'-buttons have equal width (@TODO: Bit fugly).
        Integer buttonWidthNumberOfPlayers = this.buttonNumberOfPlayersOne.getWidth();
        Double buttonMarginNumberOfPlayers = ( this.buttonStart.getWidth() - buttonWidthNumberOfPlayers * 4 ) / 3.0;
        Double buttonPositionInitial = ( container.getWidth() - buttonWidthNumberOfPlayers * 4 - buttonMarginNumberOfPlayers * 3 ) / 2;
                
        this.buttonNumberOfPlayersOne.setLocation( (int)( buttonPositionInitial + ( buttonWidthNumberOfPlayers + buttonMarginNumberOfPlayers ) * 0 ), 260 );
        this.buttonNumberOfPlayersTwo.setLocation( (int)( buttonPositionInitial + ( buttonWidthNumberOfPlayers + buttonMarginNumberOfPlayers ) * 1 ), 260 );
        this.buttonNumberOfPlayersThree.setLocation( (int)( buttonPositionInitial + ( buttonWidthNumberOfPlayers + buttonMarginNumberOfPlayers ) * 2 ), 260 );
        this.buttonNumberOfPlayersFour.setLocation( (int)( buttonPositionInitial + ( buttonWidthNumberOfPlayers + buttonMarginNumberOfPlayers ) * 3 ), 260 );
        
        this.buttonStart.setLocation( ( container.getWidth() - this.buttonStart.getWidth() ) / 2, 320 );
        this.buttonExit.setLocation( ( container.getWidth() - this.buttonExit.getWidth() ) / 2, 380 );
    }

    @Override
    public void render( GameContainer container, StateBasedGame game, Graphics g ) throws SlickException
    {
        this.background.draw( 0, 0, container.getWidth(), container.getHeight() );
        
        // Draw title.
        Integer titleStringX = ( container.getWidth() - this.titleFont.getWidth( MenuState.TITLE_STRING ) ) / 2;
        Integer titleStringY = 20;
        
        this.titleFont.drawString( titleStringX, titleStringY, MenuState.TITLE_STRING );
        
        // Draw sub title.
        Integer subTitleStringX = ( container.getWidth() - this.subTitleFont.getWidth( this.subTitleStringForNow ) ) / 2;
        Integer subTitleStringY = 80;
        
        this.subTitleFont.drawString( subTitleStringX, subTitleStringY, this.subTitleStringForNow );
        
        // Draw number of players.
        Integer numberOfPlayersStringX = ( container.getWidth() - this.numberOfPlayersFont.getWidth( MenuState.NUMBER_OF_PLAYERS_STRING ) ) / 2;
        Integer numberOfPlayersStringY = 230;
        
        this.numberOfPlayersFont.drawString( numberOfPlayersStringX, numberOfPlayersStringY, MenuState.NUMBER_OF_PLAYERS_STRING );
              
        // Draw buttons.
        this.buttonNumberOfPlayersOne.render( container, g );
        this.buttonNumberOfPlayersTwo.render( container, g );
        this.buttonNumberOfPlayersThree.render( container, g );
        this.buttonNumberOfPlayersFour.render( container, g );
        
        this.buttonStart.render( container, g );
        this.buttonExit.render( container, g );
    }

    @Override
    public void update( GameContainer container, StateBasedGame game, int delta ) throws SlickException
    {
        /*
        Input input = container.getInput();

        Integer mouseX = input.getMouseX();
        Integer mouseY = input.getMouseY();
        
        // Check if inside 'number of players one'-button.
        if( mouseX >= this.buttonPositionNumberOfPlayersOne.getX() && mouseX <= ( this.buttonPositionNumberOfPlayersOne.getX() + this.buttonNumberOfPlayersOne.getWidth() ) &&
            mouseY >= this.buttonPositionNumberOfPlayersOne.getY() && mouseY <= ( this.buttonPositionNumberOfPlayersOne.getY() + this.buttonNumberOfPlayersOne.getHeight() ) )
        {
            if( input.isMousePressed( Input.MOUSE_LEFT_BUTTON ) )
            {
                this.buttonSelectedNumberOfPlayers = ButtonNumberOfPlayers.ONE;
            }
        }
        // Check if inside 'number of players two'-button.
        else if( mouseX >= this.buttonPositionNumberOfPlayersTwo.getX() && mouseX <= ( this.buttonPositionNumberOfPlayersTwo.getX() + this.buttonNumberOfPlayersTwo.getWidth() ) &&
                 mouseY >= this.buttonPositionNumberOfPlayersTwo.getY() && mouseY <= ( this.buttonPositionNumberOfPlayersTwo.getY() + this.buttonNumberOfPlayersTwo.getHeight() ) )
        {
            if( input.isMousePressed( Input.MOUSE_LEFT_BUTTON ) )
            {
                this.buttonSelectedNumberOfPlayers = ButtonNumberOfPlayers.TWO;
            } 
        }
        // Check if inside 'number of players three'-button.
        else if( mouseX >= this.buttonPositionNumberOfPlayersThree.getX() && mouseX <= ( this.buttonPositionNumberOfPlayersThree.getX() + this.buttonNumberOfPlayersThree.getWidth() ) &&
                 mouseY >= this.buttonPositionNumberOfPlayersThree.getY() && mouseY <= ( this.buttonPositionNumberOfPlayersThree.getY() + this.buttonNumberOfPlayersThree.getHeight() ) )
        {
            if( input.isMousePressed( Input.MOUSE_LEFT_BUTTON ) )
            {
                this.buttonSelectedNumberOfPlayers = ButtonNumberOfPlayers.THREE;
            }
        }
        // Check if inside 'number of players three'-button.
        else if( mouseX >= this.buttonPositionNumberOfPlayersFour.getX() && mouseX <= ( this.buttonPositionNumberOfPlayersFour.getX() + this.buttonNumberOfPlayersFour.getWidth() ) &&
                 mouseY >= this.buttonPositionNumberOfPlayersFour.getY() && mouseY <= ( this.buttonPositionNumberOfPlayersFour.getY() + this.buttonNumberOfPlayersFour.getHeight() ) )
        {
            if( input.isMousePressed( Input.MOUSE_LEFT_BUTTON ) )
            {
                this.buttonSelectedNumberOfPlayers = ButtonNumberOfPlayers.FOUR;
            }   
        }
        // Check if inside start-button.
        else if( mouseX >= this.buttonPositionStart.getX() && mouseX <= ( this.buttonPositionStart.getX() + this.buttonStart.getWidth() ) &&
                 mouseY >= this.buttonPositionStart.getY() && mouseY <= ( this.buttonPositionStart.getY() + this.buttonStart.getHeight() ) )
        {
            if( input.isMousePressed( Input.MOUSE_LEFT_BUTTON ) )
            {
                GameManager.getInstance().setNumberOfPlayers( this.buttonSelectedNumberOfPlayers.ordinal() + 1 );
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
        */
    }
}
