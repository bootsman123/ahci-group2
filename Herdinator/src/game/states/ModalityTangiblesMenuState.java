package game.states;

import game.Game;
import game.global.GameManager;
import game.global.TuioManager;
import game.gui.Button;
import game.gui.TangibleArea;
import game.gui.interfaces.TangibleOverlay;
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
public class ModalityTangiblesMenuState extends MenuState implements ClickAndTouchListener
{
    private static final String TANGIBLE_AREA_STRING = "Place your mobile phone in one of the areas:";
    private static final Integer TANGIBLE_AREA_FONT_SIZE = 16;
    
    public static final Integer TANGIBLE_AREA_MARGIN = 30;
    
    private static final String BUTTON_START = "../Resources/Images/Menu/buttonStart.png";
    private static final String BUTTON_BACK = "../Resources/Images/Menu/buttonBack.png";
    
    private TangibleArea tangibleAreaOne;
    private TangibleArea tangibleAreaTwo;
    private TangibleArea tangibleAreaThree;
    private TangibleArea tangibleAreaFour;
    
    private UnicodeFont tangibleAreaFont;
    
    private Button buttonStart;
    private Button buttonBack;
    
    private TangibleOverlay tangibleOverlay;
    
    /**
     * Constructor.
     */
    public ModalityTangiblesMenuState()
    {
        super();
    }
    
    @Override
    public int getID()
    {
        return Game.MODALITY_TANGIBLES_MENU_STATE;
    }

    @Override
    public void init( GameContainer container, StateBasedGame game ) throws SlickException
    {
        super.init( container, game );
        
        java.awt.Font font = new java.awt.Font( "Verdana", Font.BOLD, ModalityTangiblesMenuState.TANGIBLE_AREA_FONT_SIZE );
        this.tangibleAreaFont = new UnicodeFont( font );
        this.tangibleAreaFont.addAsciiGlyphs();
        this.tangibleAreaFont.getEffects().add( new ColorEffect( java.awt.Color.WHITE ) );
        this.tangibleAreaFont.loadGlyphs();
     
        // Tangibles areas.
        this.tangibleAreaOne = new TangibleArea( container );
        this.tangibleAreaTwo = new TangibleArea( container );
        this.tangibleAreaThree = new TangibleArea( container );
        this.tangibleAreaFour = new TangibleArea( container );
        
        // Buttons.
        this.buttonStart = new Button( container, ModalityTangiblesMenuState.BUTTON_START, ModalityTangiblesMenuState.BUTTON_START );
        this.buttonStart.setAcceptingInput( Boolean.FALSE );
        this.buttonStart.addClickAndTouchListener( this );
        this.buttonBack = new Button( container, ModalityTangiblesMenuState.BUTTON_BACK, ModalityTangiblesMenuState.BUTTON_BACK );
        this.buttonBack.setAcceptingInput( Boolean.FALSE );
        this.buttonBack.addClickAndTouchListener( this );
        
        // Tangible areas positions.
        Integer tangibleAreaWidth = TangibleArea.WIDTH;
        Integer tangibleAreaHeight = TangibleArea.HEIGHT;
        Integer tangibleAreaPositionX = ( container.getWidth() - tangibleAreaWidth * 4 - ModalityTangiblesMenuState.TANGIBLE_AREA_MARGIN * 3 ) / 2;
        Integer tangibleAreaPositionY = 200;
        
        this.tangibleAreaOne.setLocation( tangibleAreaPositionX + tangibleAreaWidth * 0 + ModalityTangiblesMenuState.TANGIBLE_AREA_MARGIN * 0, tangibleAreaPositionY );
        this.tangibleAreaTwo.setLocation( tangibleAreaPositionX + tangibleAreaWidth * 1 + ModalityTangiblesMenuState.TANGIBLE_AREA_MARGIN * 1, tangibleAreaPositionY );
        this.tangibleAreaThree.setLocation( tangibleAreaPositionX + tangibleAreaWidth * 2 + ModalityTangiblesMenuState.TANGIBLE_AREA_MARGIN * 2, tangibleAreaPositionY );
        this.tangibleAreaFour.setLocation( tangibleAreaPositionX + tangibleAreaWidth * 3 + ModalityTangiblesMenuState.TANGIBLE_AREA_MARGIN * 3, tangibleAreaPositionY );
   
        this.buttonStart.setLocation( ( container.getWidth() - this.buttonStart.getWidth() ) / 2, tangibleAreaPositionY + tangibleAreaHeight + MenuState.BUTTON_MARGIN );
        this.buttonBack.setLocation( ( container.getWidth() - this.buttonBack.getWidth() ) / 2, this.buttonStart.getY() + this.buttonStart.getHeight() + MenuState.BUTTON_MARGIN );
    
        this.tangibleOverlay = new TangibleOverlay( container );
    }
    
    @Override
    public void render( GameContainer container, StateBasedGame game, Graphics g ) throws SlickException
    {
        super.render( container, game, g );
        
        // Draw 'tangible area'-string.
        Integer tangibleAreaStringX = ( container.getWidth() - this.tangibleAreaFont.getWidth( ModalityTangiblesMenuState.TANGIBLE_AREA_STRING ) ) / 2;
        Integer tangibleAreaStringY = 170;
        
        this.tangibleAreaFont.drawString( tangibleAreaStringX, tangibleAreaStringY, ModalityTangiblesMenuState.TANGIBLE_AREA_STRING );
        
        this.tangibleAreaOne.render( container, g );
        this.tangibleAreaTwo.render( container, g );
        this.tangibleAreaThree.render( container, g );
        this.tangibleAreaFour.render( container, g );
        
        this.buttonStart.render( container, g );
        this.buttonBack.render( container, g );
    
        this.tangibleOverlay.render( container, g );
    }
    
    @Override
    public void enter( GameContainer container, StateBasedGame game ) throws SlickException
    {
        super.enter( container, game );
        
        TuioManager tuioManager = TuioManager.getInstance();
        tuioManager.addTuioListener( this.tangibleAreaOne );
        tuioManager.addTuioListener( this.tangibleAreaTwo );
        tuioManager.addTuioListener( this.tangibleAreaThree );
        tuioManager.addTuioListener( this.tangibleAreaFour );
        
        this.buttonStart.setAcceptingInput( Boolean.TRUE );
        this.buttonBack.setAcceptingInput( Boolean.TRUE );
    }
    
    @Override
    public void leave( GameContainer container, StateBasedGame game ) throws SlickException
    {
        super.leave( container, game );
        
        this.buttonStart.setAcceptingInput( Boolean.FALSE );
        this.buttonBack.setAcceptingInput( Boolean.FALSE );
        
        TuioManager tuioManager = TuioManager.getInstance();
        tuioManager.removeTuioListener( this.tangibleAreaOne );
        tuioManager.removeTuioListener( this.tangibleAreaTwo );
        tuioManager.removeTuioListener( this.tangibleAreaThree );
        tuioManager.removeTuioListener( this.tangibleAreaFour );
    }

    @Override
    public void onClickOrTouch( Button button )
    {
        Integer state = -1;
        
        if( button == this.buttonStart )
        {
            /*
            try { 
                int[] playerIDs = {20,2,3,4};//@TODO: add the player ID's
                GameManager.getInstance().startTangibleGame(playerIDs);
            } catch (SlickException ex) {
                Logger.getLogger(ModalityTangiblesMenuState.class.getName()).log(Level.SEVERE, null, ex);
            }
            * */
            //GameManager.getInstance().startGame( 0, GameManager.Mode.MOUSE );
            
            state = Game.GAME_STATE;
        }
        else if( button == this.buttonBack )
        {
            state = Game.MENU_MODALITY_SELECTOR_STATE;
        }
        
        if( state != -1 )
        {
            this.game.enterState( state, new FadeOutTransition(), new FadeInTransition() );
        }
    }
}