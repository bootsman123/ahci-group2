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
    public static final int WIDTH = 800;
    public static final int HEIGHT = 640;
    
    public Game()
    {
        super( Game.NAME );
    }

    @Override
    public void initStatesList( GameContainer container ) throws SlickException
    {
        
    }
}
