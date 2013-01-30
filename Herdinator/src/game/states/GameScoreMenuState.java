package game.states;

import game.Game;
import game.global.ResourceManager;
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
public class GameScoreMenuState extends MenuState implements ClickAndTouchListener
{
    public static final String BUTTON_BACK_TO_MENU = "../Resources/Images/Menu/buttonBackToMenu.png";
    
    private Button buttonBackToMenu;
    
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
        
        // Buttons.
        this.buttonBackToMenu = new Button( container,
                                            resourceManager.getImage( GameScoreMenuState.BUTTON_BACK_TO_MENU ),
                                            resourceManager.getImage( GameScoreMenuState.BUTTON_BACK_TO_MENU ) );
        this.buttonBackToMenu.setAcceptingInput( Boolean.FALSE );
        this.buttonBackToMenu.addClickAndTouchListener( this );
        
        // Buttons locations.
        this.buttonBackToMenu.setLocation( ( container.getWidth() - this.buttonBackToMenu.getWidth() ) / 2, 260 );
    }
    
    @Override
    public void render( GameContainer container, StateBasedGame game, Graphics g ) throws SlickException
    {
        super.render( container, game, g );
        
        this.buttonBackToMenu.render( container, g );
    }
    
    @Override
    public void enter( GameContainer container, StateBasedGame game ) throws SlickException
    {
        super.enter( container, game );
        
        TuioManager tuioManager = TuioManager.getInstance();
        
        this.buttonBackToMenu.setAcceptingInput( Boolean.TRUE );
        tuioManager.addTuioListener( this.buttonBackToMenu );
    }
    
    @Override
    public void leave( GameContainer container, StateBasedGame game ) throws SlickException
    {
        super.leave( container, game );
        
        TuioManager tuioManager = TuioManager.getInstance();
        
        this.buttonBackToMenu.setAcceptingInput( Boolean.FALSE );
        tuioManager.removeTuioListener( this.buttonBackToMenu );
    }

    @Override
    public void onClickOrTouch( Button button )
    {
        Integer state = -1;
        
        if( button == this.buttonBackToMenu )
        {         
            state = Game.MENU_MODALITY_SELECTOR_STATE;
        }
        
        if( state != -1 )
        {
            this.game.enterState( state, new FadeOutTransition(), new FadeInTransition() );
        }
    }
}