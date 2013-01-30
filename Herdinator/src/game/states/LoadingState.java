package game.states;

import game.Game;
import game.global.ResourceManager;
import java.awt.Font;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.loading.DeferredResource;
import org.newdawn.slick.loading.LoadingList;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

/**
 *
 * @author Bas Bootsma
 */
public class LoadingState extends BasicGameState
{
    public static final Integer LOADING_FONT_SIZE = 20;
    
    private UnicodeFont loadingFont;    
    
    private DeferredResource nextResource;
    
    /**
     * Constructor.
     */
    public LoadingState()
    {   
    }

    @Override
    public int getID()
    {
        return Game.LOADING_STATE;
    }

    @Override
    public void init( GameContainer container, StateBasedGame game ) throws SlickException
    {
        // Font.
        //this.loadingFont = ResourceManager.getInstance().getFont( "" );
        
        this.nextResource = null;
    }

    @Override
    public void render( GameContainer container, StateBasedGame game, Graphics g ) throws SlickException
    {
        // Font.
        String loadingString = "Loading...";
        Integer loadingStringX = 20;//@TODO: fix this //( container.getWidth() - this.loadingFont.getWidth( loadingString ) ) / 2;
        Integer loadingStringY = 20;//@TODO: fix this //( container.getHeight() - this.loadingFont.getHeight( loadingString ) ) / 2;
        
       // this.loadingFont.drawString( loadingStringX, loadingStringY, loadingString );
    }

    @Override
    public void update( GameContainer container, StateBasedGame game, int delta ) throws SlickException
    {        
        if( this.nextResource != null )
        {
            try
            {
                this.nextResource.load();                
            }
            catch( IOException e )
            {
                Logger.getLogger( LoadingState.class.getName() ).log( Level.SEVERE, null, e );
            }
        }
        
        if( LoadingList.get().getRemainingResources() > 0 )
        {
            this.nextResource = LoadingList.get().getNext();
        }
        else
        {
            game.enterState( Game.MENU_MODALITY_SELECTOR_STATE, new FadeOutTransition(), new FadeInTransition() );
        }
    }
}