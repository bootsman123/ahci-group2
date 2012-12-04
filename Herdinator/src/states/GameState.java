package states;

import base.Level;
import base.Player;
import java.util.ArrayList;
import java.util.List;
import levels.Level1;
import levels.Level2;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import temp.Game2;

/**
 *
 * @author bootsman
 */
public class GameState extends BasicGameState
{
    public static final int ID = 1;
    
    private Level currentLevel; 
    private List<Level> levels; //@TODO: load levels
    private List<Player> players; //@TODO: add list of players
    
    
    public GameState() throws SlickException
    {
        
    }
    
    
    @Override
    public int getID()
    {
       return GameState.ID;
    }

    @Override
    public void init( GameContainer container, StateBasedGame game ) throws SlickException
    {
        // Initialize levels.
        this.levels = new ArrayList<Level>();
        this.levels.add( new Level1() );
        
        this.currentLevel = this.levels.get( 0 );
        this.currentLevel.init( container, game );  
    }

    @Override
    public void render( GameContainer container, StateBasedGame game, Graphics g ) throws SlickException
    {
         this.currentLevel.render( container, game, g );     
    }

    @Override
    public void update( GameContainer container, StateBasedGame game, int delta ) throws SlickException
    {
        Input input = container.getInput();
        this.currentLevel.update( container, game, delta );  
    }
}
