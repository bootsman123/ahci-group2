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
    
    public static final Integer TIME_LEFT_TOP_OFFSET = 10;
    public static final Integer TIME_LEFT_FONT_SIZE = 20;
    
    public static final Integer SCORE_LEFT_TOP_OFFSET = 30;
    public static final Integer SCORE_FONT_SIZE = 20;
        
    // Time elapsed in milliseconds.
    private Integer timeElapsed;
   
    private UnicodeFont timeLeftFont;
    private UnicodeFont scoreFont;
    
    private UsableActorContainer overlay;

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

        this.overlay = new UsableActorContainer(container);
        this.overlay.init( container, game );
        
        this.timeElapsed = 0;
        
        java.awt.Font font = new java.awt.Font( "Verdana", Font.PLAIN, GameState.TIME_LEFT_FONT_SIZE );
        this.timeLeftFont = new UnicodeFont( font );
        this.timeLeftFont.addAsciiGlyphs();
        this.timeLeftFont.getEffects().add( new ColorEffect( java.awt.Color.decode( "#db2864" ) ) ); //TODO: andere kleur maken
        this.timeLeftFont.loadGlyphs();
        
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
        
        this.overlay.render(container, g);
        
        // Draw time left.
        Long timeLeft = TimeUnit.SECONDS.toMillis( GameState.TIME_TO_COMPLETE ) - this.timeElapsed;
        
        
        String timeLeftString = String.format( "%s %s", "Time left:", ( new SimpleDateFormat( "mm:ss" ) ).format( new Date( timeLeft ) ) );
        String scoreString = String.format( "%s %s", "Score:", ( new SimpleDateFormat( "mm:ss" ) ).format( new Date( score() ) ) );
        
        this.timeLeftFont.drawString( ( container.getWidth() - this.timeLeftFont.getWidth( timeLeftString ) ) / 2,
                                      GameState.TIME_LEFT_TOP_OFFSET,
                                      timeLeftString );
        
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
        
        this.overlay.update( container, game, delta );
        
        // Update time elapsed.
        this.timeElapsed += delta;
    }
    
    private int score(){
        Map map = GameManager.getInstance().getMap();
        int scoreCounter = 0;
        
        for( Sheep sheep : map.getSheeps() )
        {
            if( sheep.isFinished() )
            {
                scoreCounter +=10;
            }
        }
        return scoreCounter;
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
            if( !map.isGoalTile( sheep.getPosition() ) )
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
}
