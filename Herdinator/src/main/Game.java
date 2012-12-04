package main;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author bootsman
 */
public class Game extends StateBasedGame
{
    public static final String NAME = "Herdinator";
    
    public Game()
    {
        super( Game.NAME );
    }

    @Override
    public void initStatesList( GameContainer container ) throws SlickException
    {
        
    }
    
}
