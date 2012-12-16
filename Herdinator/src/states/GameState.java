package states;

import base.GameManager;
import base.Map;
import base.ObjectPicker;
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
    public static final int ID = 1;
    
    ObjectPicker overlay = null;

    private List<Map> maps; //@TODO: load levels
    
    
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
        this.maps = new ArrayList<Map>();
        this.maps.add( new Map( "../Resources/Maps/level1.tmx" ) );
        this.maps.add( new Map( "../Resources/Maps/level2.tmx" ) );

        Map currentMap;
        
        currentMap = this.maps.get( 0 );
        currentMap.init( container, game );

        GameManager.getInstance().setMap(currentMap);

        overlay = new ObjectPicker();
        overlay.init(container, game);

    }

    @Override
    public void render( GameContainer container, StateBasedGame game, Graphics g ) throws SlickException
    {
         GameManager.getInstance().getMap().render( container, game, g );
         // render the overlay
        overlay.render(container, game, g);
 
    }

    @Override
    public void update( GameContainer container, StateBasedGame game, int delta ) throws SlickException
    {
        
        GameManager.getInstance().update(container, game, delta);
        overlay.update(container, game, delta);
    }
}
