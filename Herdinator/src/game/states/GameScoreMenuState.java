package game.states;

import game.Game;
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
        
        // Buttons.
        this.buttonBackToMenu = new Button( container, GameScoreMenuState.BUTTON_BACK_TO_MENU, GameScoreMenuState.BUTTON_BACK_TO_MENU );
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