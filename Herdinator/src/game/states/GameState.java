package game.states;

import game.Game;
import game.actors.Sheep;
import game.base.Map;
import game.global.GameManager;
import game.global.ResourceManager;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
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
    public static final String AMBIANCE_SOUND_FILE_PATH = "../Resources/Sounds/farmambiance.wav";
    
    // Time to complete a level (in seconds).
    private static final Integer TIME_TO_COMPLETE = 120;
    
    private static final Integer SCORE_LEFT_TOP_OFFSET = 10;
    private static final Integer SCORE_FONT_SIZE = 20;
    
    // Score.
    private static final Integer SCORE_PER_SHEEP = 10;
    private static final Integer SCORE_PER_SECOND = 1;
    
    private Sound sound;
    
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
        
        // Initialize sound.
        this.sound = ResourceManager.getInstance().getSound( GameState.AMBIANCE_SOUND_FILE_PATH );
               
        java.awt.Font font = new java.awt.Font( "Verdana", Font.PLAIN, GameState.SCORE_FONT_SIZE );
        this.scoreFont = new UnicodeFont( font );
        this.scoreFont.addAsciiGlyphs();
        this.scoreFont.getEffects().add( new ColorEffect( java.awt.Color.decode( "#db2864" ) ) );
        this.scoreFont.loadGlyphs();        
        
        this.timeElapsed = 0;
    }

    @Override
    public void render( GameContainer container, StateBasedGame game, Graphics g ) throws SlickException
    {
        // @TODO: Ugly fix to stop rendering when there is no time left or the game if finished.
        if( !this.isTimeLeft() || this.isFinished() )
        {
            return;
        }
        
        GameManager.getInstance().render( container, game, g );
        
        // Draw time left.
        Long timeLeft = TimeUnit.SECONDS.toMillis( GameState.TIME_TO_COMPLETE ) - this.timeElapsed;
        
        
        String scoreString = String.format( "%s: %s   %s: %d",
                                            "Time left",
                                            ( new SimpleDateFormat( "mm:ss" ) ).format( new Date( timeLeft ) ),
                                            "Score",
                                            this.getScoreForSheep() );

        this.scoreFont.drawString( ( container.getWidth() - this.scoreFont.getWidth( scoreString ) ) / 2,
                                     GameState.SCORE_LEFT_TOP_OFFSET,
                                     scoreString );   
    }
    
    @Override
    public void enter( GameContainer container, StateBasedGame game ) throws SlickException
    {
        super.enter( container, game );
        
        this.sound.play();
    }
    
    @Override
    public void leave( GameContainer container, StateBasedGame game ) throws SlickException
    {
        super.enter( container, game );
        
        this.sound.stop();        
        this.timeElapsed = 0;
    }

    @Override
    public void update( GameContainer container, StateBasedGame game, int delta ) throws SlickException
    {
        // Check if there is no time left or the game if finished.
        if( !this.isTimeLeft() || this.isFinished() )
        {
            GameManager.getInstance().endGame( this.getScoreForSheep() + this.getScoreForTime() );
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
        return ( TimeUnit.SECONDS.toMillis( GameState.TIME_TO_COMPLETE ) - this.timeElapsed >= 0 );
    }
    
    /**
     * Returns the current score of the game.
     * @return 
     */
    private Integer getScoreForSheep()
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
    
    /**
     * Returns the score for the time.
     * @return 
     */
    private Integer getScoreForTime()
    {
        return (int)( Math.max( 0, GameState.TIME_TO_COMPLETE - TimeUnit.MILLISECONDS.toSeconds( this.timeElapsed ) ) ) * GameState.SCORE_PER_SECOND;
    }
}
