package game.states;

import game.Game;

/**
 *
 * @author bootsman
 */
public class GameScoreMenuState extends MenuState
{
    public GameScoreMenuState()
    {
        super();
    }
    
    @Override
    public int getID()
    {
        return Game.GAME_SCORE_MENU_STATE;
    }
    
}