package game.states;

import game.Game;
import game.actors.Sheep;
import game.base.Map;
import game.global.GameManager;
import game.gui.interfaces.UsableActorContainer;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

/**
 *
 * @author bootsman
 */
public class GameState extends BasicGameState
{
    // Time to complete a level in seconds.
    public static final Integer TIME_TO_COMPLETE = 120;
    
    public static final Integer SCORE_LEFT_TOP_OFFSET = 10;
    public static final Integer SCORE_FONT_SIZE = 20;
    
    // Score.
    public static final Integer SCORE_PER_SHEEP = 10;
        
    // Time elapsed in milliseconds.
    private Integer timeElapsed;
   
    private UnicodeFont scoreFont;
    
    

    /**
     * Constructor.
     * @throws SlickException 
     */
    public GameState() throws SlickException
    {
    }
    
    @Override
    public int getID()
    {
       return Game.GAME_STATE;
    }

    @Override
    public void init( GameContainer container, StateBasedGame game ) throws SlickException
    {        
        GameManager.getInstance().init( container, game );

       
        this.timeElapsed = 0;
        
        java.awt.Font font = new java.awt.Font( "Verdana", Font.PLAIN, GameState.SCORE_FONT_SIZE );
        this.scoreFont = new UnicodeFont( font );
        this.scoreFont.addAsciiGlyphs();
        this.scoreFont.getEffects().add( new ColorEffect( java.awt.Color.decode( "#db2864" ) ) );
        this.scoreFont.loadGlyphs();
    }
    
    /*
    @Override
    public void enter( GameContainer container, StateBasedGame game ) throws SlickException
    {
    }
    */

    @Override
    public void render( GameContainer container, StateBasedGame game, Graphics g ) throws SlickException
    {
        GameManager.getInstance().render( container, game, g );
        
       
        
        // Draw time left.
        Long timeLeft = TimeUnit.SECONDS.toMillis( GameState.TIME_TO_COMPLETE ) - this.timeElapsed;
        
        
        String scoreString = String.format( "%s: %s   %s: %d",
                                            "Time left",
                                            ( new SimpleDateFormat( "mm:ss" ) ).format( new Date( timeLeft ) ),
                                            "Score",
                                            this.getScore() );

        this.scoreFont.drawString( ( container.getWidth() - this.scoreFont.getWidth( scoreString ) ) / 2,
                                     GameState.SCORE_LEFT_TOP_OFFSET,
                                     scoreString );   
    }

    @Override
    public void update( GameContainer container, StateBasedGame game, int delta ) throws SlickException
    {
        // Check if there is no time left or the game if finished.
        if( !this.isTimeLeft() || this.isFinished() )
        {
            game.enterState( Game.GAME_SCORE_MENU_STATE, new FadeOutTransition(), new FadeInTransition() );
            return;
        }
        
        GameManager.getInstance().update( container, game, delta );  
        
        
        
        // Update time elapsed.
        this.timeElapsed += delta;
    }
    
    /**
     * Check whether the game is finished.
     * @return 
     */
    private Boolean isFinished()
    {
        Map map = GameManager.getInstance().getMap();
        
        // Check if all the sheep are in the goal area.
        for( Sheep sheep : map.getSheeps() )
        {
            if( !sheep.isInGoalTile() )
            {
                return Boolean.FALSE;
            }
        }
        
        return Boolean.TRUE;
    }
    
    /**
     * Returns whether there is time left.
     * @return 
     */
    private Boolean isTimeLeft()
    {
        return ( this.timeElapsed >= 0 );
    }
    

    
    /**
     * Returns the current score of the game.
     * @return 
     */
    private Integer getScore()
    {
        Map map = GameManager.getInstance().getMap();
        Integer score = 0;
        
        for( Sheep sheep : map.getSheeps() )
        {
            if( sheep.isInGoalTile() )
            {
                score += GameState.SCORE_PER_SHEEP;
            }
        }
        return score;
    }
}
