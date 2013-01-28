package game.states;

import game.Game;
import game.global.GameManager;
import game.gui.Button;
import game.gui.ButtonGroup;
import game.gui.ToggleButton;
import game.gui.listeners.ClickAndTouchListener;
import java.awt.Font;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

/**
 *
 * @author bootsman
 */
public class ModalityMouseAndTouchMenuState extends MenuState implements ClickAndTouchListener
{
    private static final String NUMBER_OF_PLAYERS_STRING = "Number of players:";
    private static final Integer NUMBER_OF_PLAYERS_FONT_SIZE = 16;
    
    // Number of players buttons.
    private static final String BUTTON_NUMBER_OF_PLAYERS_ONE = "../Resources/Images/Menu/buttonNumberOfPlayersOne.png";
    private static final String BUTTON_ACTIVE_NUMBER_OF_PLAYERS_ONE = "../Resources/Images/Menu/buttonActiveNumberOfPlayersOne.png";
    
    private static final String BUTTON_NUMBER_OF_PLAYERS_TWO = "../Resources/Images/Menu/buttonNumberOfPlayersTwo.png";
    private static final String BUTTON_ACTIVE_NUMBER_OF_PLAYERS_TWO = "../Resources/Images/Menu/buttonActiveNumberOfPlayersTwo.png";

    private static final String BUTTON_NUMBER_OF_PLAYERS_THREE = "../Resources/Images/Menu/buttonNumberOfPlayersThree.png";
    private static final String BUTTON_ACTIVE_NUMBER_OF_PLAYERS_THREE = "../Resources/Images/Menu/buttonActiveNumberOfPlayersThree.png";

    private static final String BUTTON_NUMBER_OF_PLAYERS_FOUR = "../Resources/Images/Menu/buttonNumberOfPlayersFour.png";
    private static final String BUTTON_ACTIVE_NUMBER_OF_PLAYERS_FOUR = "../Resources/Images/Menu/buttonActiveNumberOfPlayersFour.png";
    
    private static final String BUTTON_START = "../Resources/Images/Menu/buttonStart.png";
    private static final String BUTTON_BACK = "../Resources/Images/Menu/buttonBack.png";
    private static final String BUTTON_DEBUG = "../Resources/Images/Menu/buttonMouse.png";
    private static final String ACTIVE_BUTTON_DEBUG = "../Resources/Images/Menu/buttonMouseActive.png";
    
    private UnicodeFont numberOfPlayersFont;
    
    private ToggleButton buttonNumberOfPlayersOne;
    private ToggleButton buttonNumberOfPlayersTwo;
    private ToggleButton buttonNumberOfPlayersThree;
    private ToggleButton buttonNumberOfPlayersFour;
    
    private ButtonGroup buttonNumberOfPlayers;
    
    private Button buttonStart;
    private Button buttonBack;
    
    private ToggleButton buttonDebug;
    
    /**
     * Constructor.
     */
    public ModalityMouseAndTouchMenuState()
    {
        super();
    }

    @Override
    public int getID()
    {
        return Game.MODALITY_MOUSE_AND_TOUCH_MENU_STATE;
    }
    
    @Override
    public void init( GameContainer container, StateBasedGame game ) throws SlickException
    {
        super.init( container, game );
        
        java.awt.Font font = new java.awt.Font( "Verdana", Font.BOLD, ModalityMouseAndTouchMenuState.NUMBER_OF_PLAYERS_FONT_SIZE );
        this.numberOfPlayersFont = new UnicodeFont( font );
        this.numberOfPlayersFont.addAsciiGlyphs();
        this.numberOfPlayersFont.getEffects().add( new ColorEffect( java.awt.Color.WHITE ) );
        this.numberOfPlayersFont.loadGlyphs();
        
        // Buttons.        
        this.buttonNumberOfPlayersOne = new ToggleButton( container, ModalityMouseAndTouchMenuState.BUTTON_NUMBER_OF_PLAYERS_ONE, ModalityMouseAndTouchMenuState.BUTTON_ACTIVE_NUMBER_OF_PLAYERS_ONE );
        this.buttonNumberOfPlayersOne.setAcceptingInput( Boolean.FALSE );
        this.buttonNumberOfPlayersTwo = new ToggleButton( container, ModalityMouseAndTouchMenuState.BUTTON_NUMBER_OF_PLAYERS_TWO, ModalityMouseAndTouchMenuState.BUTTON_ACTIVE_NUMBER_OF_PLAYERS_TWO );
        this.buttonNumberOfPlayersTwo.setAcceptingInput( Boolean.FALSE );
        this.buttonNumberOfPlayersThree = new ToggleButton( container, ModalityMouseAndTouchMenuState.BUTTON_NUMBER_OF_PLAYERS_THREE, ModalityMouseAndTouchMenuState.BUTTON_ACTIVE_NUMBER_OF_PLAYERS_THREE );
        this.buttonNumberOfPlayersThree.setAcceptingInput( Boolean.FALSE );
        this.buttonNumberOfPlayersFour = new ToggleButton( container, ModalityMouseAndTouchMenuState.BUTTON_NUMBER_OF_PLAYERS_FOUR, ModalityMouseAndTouchMenuState.BUTTON_ACTIVE_NUMBER_OF_PLAYERS_FOUR );
        this.buttonNumberOfPlayersFour.setAcceptingInput( Boolean.FALSE );
        
        // Create a button group.
        this.buttonNumberOfPlayers = new ButtonGroup();
        this.buttonNumberOfPlayers.add( this.buttonNumberOfPlayersOne );
        this.buttonNumberOfPlayers.add( this.buttonNumberOfPlayersTwo );
        this.buttonNumberOfPlayers.add( this.buttonNumberOfPlayersThree );
        this.buttonNumberOfPlayers.add( this.buttonNumberOfPlayersFour );
        this.buttonNumberOfPlayers.setButtonToggledOn( 0 );
        
        this.buttonStart = new Button( container, ModalityMouseAndTouchMenuState.BUTTON_START, ModalityMouseAndTouchMenuState.BUTTON_START );
        this.buttonStart.setAcceptingInput( Boolean.FALSE );
        this.buttonStart.addClickAndTouchListener( this );
        this.buttonBack = new Button( container, ModalityMouseAndTouchMenuState.BUTTON_BACK, ModalityMouseAndTouchMenuState.BUTTON_BACK );
        this.buttonBack.setAcceptingInput( Boolean.FALSE );
        this.buttonBack.addClickAndTouchListener( this );
        
        this.buttonDebug = new ToggleButton( container, ModalityMouseAndTouchMenuState.BUTTON_DEBUG, ModalityMouseAndTouchMenuState.ACTIVE_BUTTON_DEBUG );
        this.buttonDebug.setAcceptingInput( Boolean.FALSE );
        this.buttonDebug.addClickAndTouchListener( this );
        
        // Button positions.
        // Assuming all 'number of players'-buttons have equal width (@TODO: Bit fugly).
        Integer buttonNumberOfPlayersWidth = this.buttonNumberOfPlayersOne.getWidth();
        Integer buttonNumberOfPlayersHeight = this.buttonNumberOfPlayersOne.getHeight();
        Integer buttonNumberOfPlayersMargin = ( this.buttonStart.getWidth() - buttonNumberOfPlayersWidth * 4 ) / 3;
        Integer buttonNumberOfPlayersPositionX = ( container.getWidth() - buttonNumberOfPlayersWidth * 4 - buttonNumberOfPlayersMargin * 3 ) / 2;
        Integer buttonNumberOfPlayersPositionY = 200;    
        
        this.buttonNumberOfPlayersOne.setLocation( (int)( buttonNumberOfPlayersPositionX + ( buttonNumberOfPlayersWidth + buttonNumberOfPlayersMargin ) * 0 ), buttonNumberOfPlayersPositionY );
        this.buttonNumberOfPlayersTwo.setLocation( (int)( buttonNumberOfPlayersPositionX + ( buttonNumberOfPlayersWidth + buttonNumberOfPlayersMargin ) * 1 ), buttonNumberOfPlayersPositionY );
        this.buttonNumberOfPlayersThree.setLocation( (int)( buttonNumberOfPlayersPositionX + ( buttonNumberOfPlayersWidth + buttonNumberOfPlayersMargin ) * 2 ), buttonNumberOfPlayersPositionY );
        this.buttonNumberOfPlayersFour.setLocation( (int)( buttonNumberOfPlayersPositionX + ( buttonNumberOfPlayersWidth + buttonNumberOfPlayersMargin ) * 3 ), buttonNumberOfPlayersPositionY );
        
        this.buttonStart.setLocation( ( container.getWidth() - this.buttonStart.getWidth() ) / 2, buttonNumberOfPlayersPositionY + buttonNumberOfPlayersHeight + MenuState.BUTTON_MARGIN );
        this.buttonBack.setLocation( ( container.getWidth() - this.buttonBack.getWidth() ) / 2, this.buttonStart.getY() + this.buttonStart.getHeight() + MenuState.BUTTON_MARGIN );
        this.buttonDebug.setLocation( ( container.getWidth() - this.buttonDebug.getWidth() ) / 2, this.buttonStart.getY() + this.buttonStart.getHeight() + this.buttonBack.getHeight() + 2*MenuState.BUTTON_MARGIN );
    }

    @Override
    public void render( GameContainer container, StateBasedGame game, Graphics g ) throws SlickException
    {
        super.render( container, game, g );
        
        // Draw 'number of players'-string.
        Integer numberOfPlayersStringX = ( container.getWidth() - this.numberOfPlayersFont.getWidth( ModalityMouseAndTouchMenuState.NUMBER_OF_PLAYERS_STRING ) ) / 2;
        Integer numberOfPlayersStringY = 170;
        
        this.numberOfPlayersFont.drawString( numberOfPlayersStringX, numberOfPlayersStringY, ModalityMouseAndTouchMenuState.NUMBER_OF_PLAYERS_STRING );
              
        // Draw buttons.
        this.buttonNumberOfPlayersOne.render( container, g );
        this.buttonNumberOfPlayersTwo.render( container, g );
        this.buttonNumberOfPlayersThree.render( container, g );
        this.buttonNumberOfPlayersFour.render( container, g );
        
        this.buttonStart.render( container, g );
        this.buttonBack.render( container, g );
        this.buttonDebug.render( container, g );
    }
    
    @Override
    public void enter( GameContainer containger, StateBasedGame game ) throws SlickException
    {
        super.enter( container, game );
        
        this.buttonNumberOfPlayersOne.setAcceptingInput( Boolean.TRUE );
        this.buttonNumberOfPlayersTwo.setAcceptingInput( Boolean.TRUE );
        this.buttonNumberOfPlayersThree.setAcceptingInput( Boolean.TRUE );
        this.buttonNumberOfPlayersFour.setAcceptingInput( Boolean.TRUE );
        
        this.buttonStart.setAcceptingInput( Boolean.TRUE );
        this.buttonBack.setAcceptingInput( Boolean.TRUE );
        this.buttonDebug.setAcceptingInput( Boolean.TRUE );
    }
    
    @Override
    public void leave( GameContainer containger, StateBasedGame game ) throws SlickException
    {
        super.leave( container, game );
        
        this.buttonNumberOfPlayersOne.setAcceptingInput( Boolean.FALSE );
        this.buttonNumberOfPlayersTwo.setAcceptingInput( Boolean.FALSE );
        this.buttonNumberOfPlayersThree.setAcceptingInput( Boolean.FALSE );
        this.buttonNumberOfPlayersFour.setAcceptingInput( Boolean.FALSE );
        
        this.buttonStart.setAcceptingInput( Boolean.FALSE );
        this.buttonBack.setAcceptingInput( Boolean.FALSE );
        this.buttonDebug.setAcceptingInput( Boolean.FALSE );
    } 

    @Override
    public void onClickOrTouch( Button button )
    {
        Integer state = -1;
        
        if( button == this.buttonStart)
        {
            // Check if a button is toggled on.
            Integer index = this.buttonNumberOfPlayers.getButtonToggledOn();
            
            if( index != -1 )
            {
                try {
                    if (!this.buttonDebug.isToggled()){
                        GameManager.getInstance().startGame( index + 1, GameManager.TOUCH_PLAYER_MODE ); // @TODO: Fugly.
                    }
                    else{
                        GameManager.getInstance().startGame( index + 1, GameManager.MOUSE_PLAYER_MODE ); // @TODO: Fugly.
                    }
                    
                    state = Game.GAME_STATE;
                } catch (SlickException ex) {
                    Logger.getLogger(ModalityMouseAndTouchMenuState.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        }
        else if( button == this.buttonBack )
        {
            state = Game.MENU_MODALITY_SELECTOR_STATE;
        }
        /*else if( button == this.buttonDebug )
        {
            this.startingInTouchMode = !this.startingInTouchMode;
            this.buttonDebug.setIsToggled(startingInTouchMode);
            System.out.println("Toggling");
            
        }*/
        
        if( state != -1 )
        {
            this.game.enterState( state, new FadeOutTransition(), new FadeInTransition() );
        }
    }
}