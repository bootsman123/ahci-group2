package game.states;

import game.Game;
import game.global.GameManager;
import game.global.ResourceManager;
import game.global.TuioManager;
import game.gui.Button;
import game.gui.listeners.ClickAndTouchListener;
import java.awt.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author bootsman
 */
public class GameScoreMenuState extends MenuState implements ClickAndTouchListener
{
    public static final Integer SCORE_FONT_SIZE = 32;
    
    //public static final String BUTTON_BACK_TO_MENU = "../Resources/Images/Menu/buttonBackToMenu.png";
    public static final String BUTTON_EXIT = "../Resources/Images/Menu/buttonExit.png";  
    
    private UnicodeFont scoreFont;

    //private Button buttonBackToMenu;
    private Button buttonExit;

    /**
     * Constructor.
     */
    public GameScoreMenuState()
    {
        super();
    }
    
    @Override
    public int getID()
    {
        return Game.GAME_SCORE_MENU_STATE;
    }
    
    @Override
    public void init( GameContainer container, StateBasedGame game ) throws SlickException
    {
        super.init( container, game );
        
        ResourceManager resourceManager = ResourceManager.getInstance();
        
        // Font.
        java.awt.Font font = new java.awt.Font( "Verdana", Font.BOLD, GameScoreMenuState.SCORE_FONT_SIZE );
        this.scoreFont = new UnicodeFont( font );
        this.scoreFont.addAsciiGlyphs();
        this.scoreFont.getEffects().add( new ColorEffect( java.awt.Color.WHITE ) );
        this.scoreFont.loadGlyphs();
        
        // Buttons.
        /*
        this.buttonBackToMenu = new Button( container,
                                            resourceManager.getImage( GameScoreMenuState.BUTTON_BACK_TO_MENU ),
                                            resourceManager.getImage( GameScoreMenuState.BUTTON_BACK_TO_MENU ) );
        this.buttonBackToMenu.setAcceptingInput( Boolean.FALSE );
        this.buttonBackToMenu.addClickAndTouchListener( this );
        
        // Buttons locations.
        this.buttonBackToMenu.setLocation( ( container.getWidth() - this.buttonBackToMenu.getWidth() ) / 2, 260 );
        */
    
        this.buttonExit = new Button( container,
                                      resourceManager.getImage( GameScoreMenuState.BUTTON_EXIT ),
                                      resourceManager.getImage( GameScoreMenuState.BUTTON_EXIT ) );
        this.buttonExit.setAcceptingInput( Boolean.FALSE );
        this.buttonExit.addClickAndTouchListener( this );
        
        // Button locations.
        this.buttonExit.setLocation( ( container.getWidth() - this.buttonExit.getWidth() ) / 2, 260 );
    }
    
    @Override
    public void render( GameContainer container, StateBasedGame game, Graphics g ) throws SlickException
    {
        super.render( container, game, g );
        
        // Draw score.
        String scoreString = String.format( "Total score: %d", GameManager.getInstance().getLastScore() );
        Integer scoreStringX = ( container.getWidth() - this.scoreFont.getWidth( scoreString ) ) / 2;
        Integer scoreStringY = 200;
        
        this.scoreFont.drawString( scoreStringX, scoreStringY, scoreString );
        
        //this.buttonBackToMenu.render( container, g );
        this.buttonExit.render( container, g );
    }
    
    @Override
    public void enter( GameContainer container, StateBasedGame game ) throws SlickException
    {
        super.enter( container, game );
        
        TuioManager tuioManager = TuioManager.getInstance();
        
        //this.buttonBackToMenu.setAcceptingInput( Boolean.TRUE );
        //tuioManager.addTuioListener( this.buttonBackToMenu );
    
        this.buttonExit.setAcceptingInput( Boolean.TRUE );
        tuioManager.addTuioListener( this.buttonExit );
    }
    
    @Override
    public void leave( GameContainer container, StateBasedGame game ) throws SlickException
    {
        super.leave( container, game );
        
        TuioManager tuioManager = TuioManager.getInstance();
        
        //this.buttonBackToMenu.setAcceptingInput( Boolean.FALSE );
        //tuioManager.removeTuioListener( this.buttonBackToMenu );
    
        this.buttonExit.setAcceptingInput( Boolean.FALSE );
        tuioManager.removeTuioListener( this.buttonExit );
    }

    @Override
    public void onClickOrTouch( Button button )
    {
        Integer state = -1;
        
        /*
        if( button == this.buttonBackToMenu )
        {         
            state = Game.MENU_MODALITY_SELECTOR_STATE;
        }
        */
        
        if( button == this.buttonExit )
        {
            this.container.exit();
        }
        
        /*
        if( state != -1 )
        {
            this.game.enterState( state, new FadeOutTransition(), new FadeInTransition() );
        }
        */
    }
}