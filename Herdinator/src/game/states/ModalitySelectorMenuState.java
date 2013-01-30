package game.states;

import game.Game;
import game.global.TuioManager;
import game.gui.Button;
import game.gui.listeners.ClickAndTouchListener;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

/**
 *
 * @author bootsman
 */
public class ModalitySelectorMenuState extends MenuState implements ClickAndTouchListener
{
    private static final String BUTTON_MODALITY_MOUSE_AND_TOUCH = "../Resources/Images/Menu/buttonModalityMouseAndTouch.png";
    private static final String BUTTON_MODALITY_TANGIBLES = "../Resources/Images/Menu/buttonModalityTangibles.png";
    private static final String BUTTON_EXIT = "../Resources/Images/Menu/buttonExit.png";
    
    private Button buttonModalityMouseAndTouch;
    private Button buttonModalityTangibles;
    private Button buttonExit;
    
    /**
     * Constructor.
     */
    public ModalitySelectorMenuState()
    {
        super();
    }

    @Override
    public int getID()
    {
        return Game.MENU_MODALITY_SELECTOR_STATE;
    }

    @Override
    public void init( GameContainer container, StateBasedGame game ) throws SlickException
    {
        super.init( container, game );
        
        // Buttons.
        this.buttonModalityMouseAndTouch = new Button( container, ModalitySelectorMenuState.BUTTON_MODALITY_MOUSE_AND_TOUCH, ModalitySelectorMenuState.BUTTON_MODALITY_MOUSE_AND_TOUCH );
        this.buttonModalityMouseAndTouch.setAcceptingInput( Boolean.FALSE );
        this.buttonModalityMouseAndTouch.addClickAndTouchListener( this );
        
        this.buttonModalityTangibles = new Button( container, ModalitySelectorMenuState.BUTTON_MODALITY_TANGIBLES, ModalitySelectorMenuState.BUTTON_MODALITY_TANGIBLES );
        this.buttonModalityTangibles.setAcceptingInput( Boolean.FALSE );
        this.buttonModalityTangibles.addClickAndTouchListener( this );
        
        this.buttonExit = new Button( container, ModalitySelectorMenuState.BUTTON_EXIT, ModalitySelectorMenuState.BUTTON_EXIT );
        this.buttonExit.setAcceptingInput( Boolean.FALSE );
        this.buttonExit.addClickAndTouchListener( this );
        
        // Button positions.
        Integer buttonModalityMouseAndTouchY = 200;
        
        this.buttonModalityMouseAndTouch.setLocation( ( container.getWidth() - this.buttonModalityMouseAndTouch.getWidth() ) / 2, buttonModalityMouseAndTouchY );
        this.buttonModalityTangibles.setLocation( ( container.getWidth() - this.buttonModalityTangibles.getWidth() ) / 2, this.buttonModalityMouseAndTouch.getY() + this.buttonModalityMouseAndTouch.getHeight() + MenuState.BUTTON_MARGIN );
        this.buttonExit.setLocation( ( container.getWidth() - this.buttonExit.getWidth() ) / 2, this.buttonModalityTangibles.getY() + this.buttonModalityTangibles.getHeight() + MenuState.BUTTON_MARGIN );       
    }

    @Override
    public void render( GameContainer container, StateBasedGame game, Graphics g ) throws SlickException
    {
        super.render( container, game, g );
                       
        this.buttonModalityMouseAndTouch.render( container, g );
        this.buttonModalityTangibles.render( container, g );
        this.buttonExit.render( container, g );
    }
    
    @Override
    public void enter( GameContainer container, StateBasedGame game ) throws SlickException
    {
        super.enter( container, game );
        
        TuioManager tuioManager = TuioManager.getInstance();
        
        this.buttonModalityMouseAndTouch.setAcceptingInput( Boolean.TRUE );
        tuioManager.addTuioListener( this.buttonModalityMouseAndTouch );
        this.buttonModalityTangibles.setAcceptingInput( Boolean.TRUE );
        tuioManager.addTuioListener( this.buttonModalityTangibles );
        this.buttonExit.setAcceptingInput( Boolean.TRUE );
        tuioManager.addTuioListener( this.buttonExit );
    }
    
    @Override
    public void leave( GameContainer container, StateBasedGame game ) throws SlickException
    {
        super.leave( container, game );
        
        TuioManager tuioManager = TuioManager.getInstance();
        
        this.buttonModalityMouseAndTouch.setAcceptingInput( Boolean.FALSE );
        tuioManager.removeTuioListener( this.buttonModalityMouseAndTouch );
        this.buttonModalityTangibles.setAcceptingInput( Boolean.FALSE );
        tuioManager.removeTuioListener( this.buttonModalityTangibles );
        this.buttonExit.setAcceptingInput( Boolean.FALSE );
        tuioManager.removeTuioListener( this.buttonExit );
    }

    @Override
    public void onClickOrTouch( Button button )
    {
        Integer state = -1;
        
        if( button == this.buttonModalityMouseAndTouch )
        {
            state = Game.MODALITY_MOUSE_AND_TOUCH_MENU_STATE;
        }
        else if( button == this.buttonModalityTangibles )
        {
            state = Game.MODALITY_TANGIBLES_MENU_STATE;
        }
        else if( button == this.buttonExit )
        {
            this.container.exit();
        }
        
        // Switch state.
        if( state != -1 )
        {
            this.game.enterState( state, new FadeOutTransition(), new FadeInTransition() );
        }
    }
}