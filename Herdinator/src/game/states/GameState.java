package game.states;

import game.Game;
import game.base.Map;
import game.global.GameManager;
import game.interfaces.ObjectPicker;
import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


/**
 *
 * @author bootsman
 */
public class GameState extends BasicGameState
{
    //private ObjectPicker overlay = null;

    private List<Map> maps;
    private Map currentMap;
    
    public GameState() throws SlickException
    {
    }
    
    @Override
    public int getID()
    {
       return Game.GAME_STATE_GAME;
    }

    @Override
    public void init( GameContainer container, StateBasedGame game ) throws SlickException
    {
        // Initialize levels.
        this.maps = new ArrayList<Map>();

        this.maps.add( new Map( "../Resources/Maps/level1.tmx" ) );
        this.maps.add( new Map( "../Resources/Maps/level2.tmx" ) );
       
        // Set map.
        GameManager.getInstance().setMap( this.maps.get( 0 ) );
        
        // Initialize maps.
        // @TODO: Could use a for-loop.
        this.maps.get( 0 ).init( container, game );
        this.maps.get( 1 ).init( container, game );

        
        //GameManager.getInstance().setPlayers();

        //overlay = new ObjectPicker();
        //overlay.init(container, game);
    }

    @Override
    public void render( GameContainer container, StateBasedGame game, Graphics g ) throws SlickException
    {
        GameManager.getInstance().getMap().render( container, game, g );
        //overlay.render(container, game, g);
    }

    @Override
    public void update( GameContainer container, StateBasedGame game, int delta ) throws SlickException
    {   
        GameManager.getInstance().getMap().update( container, game, delta );
        //GameManager.getInstance().update( container, game, delta );
        //overlay.update(container, game, delta);
    }
}
