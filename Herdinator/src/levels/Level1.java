package levels;

import actors.Sheep;
import base.Level;
import base.Map;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author roland
 */
public class Level1 extends Level
{    
    public Level1() throws SlickException
    {
        super() ; 
        this.sheeps.add( new Sheep( 10, 10 ) ); 
        this.sheeps.add( new Sheep( 20, 20 ) ); 
    }
    
    @Override
    public void init( GameContainer container, StateBasedGame game ) throws SlickException
    {
        //@TODO: Abstract more from this.
        this.map = new Map( "../Resources/Maps/level1.tmx" );
    }
}
