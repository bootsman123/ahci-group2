package game.global;

import game.players.Player;
import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.Color;

/**
 *
 * @author bootsman
 */
public class PlayerManager
{
    public static final Integer MAXIMUM_PLAYERS = 4;
    
    public enum PlayerColor
    {
        ONE( Color.red ),
        TWO( Color.blue ),
        THREE( Color.yellow ),
        FOUR( Color.gray );
        
        private Color color;
        
        /**
         * Constructor.
         * @param color 
         */
        PlayerColor( Color color )
        {
            this.color = color;
        }
        
        /**
         * Return the color.
         * @return 
         */
        public Color getColor()
        {
            return this.color;
        }
    }
    
    // Singleton instance.
    private static final PlayerManager INSTANCE = new PlayerManager();
    
    private List<Player> players;
   
    /**
     * Constructor (private).
     */
    private PlayerManager()
    {
        this.players = new ArrayList<Player>();
    }
    
    /**
     * Return the player manager instance.
     * @return 
     */
    public static PlayerManager getInstance()
    {
        return PlayerManager.INSTANCE;
    }
    
    /**
     * Return a list of players.
     * @return 
     */
    public List<Player> getPlayers()
    {
        return this.players;
    }
    
    /**
     * Returns a player by id.
     * @param id
     * @return 
     */
    public Player getPlayer( Integer id )
    {
        for( Player player : this.players )
        {
            if( id == player.getId() )
            {
                return player;
            }
        }
        
        return null;
    }
    
    /**
     * Add a player.
     * @param player 
     */
    public void addPlayer( Player player )
    {
        this.players.add( player );
    }
    
    /**
     * Remove a player.
     * @param player 
     */
    public void removePlayer( Player player )
    {
        this.players.remove( player );
    }
        
    /**
     * Return the number of players.
     * @return 
     */
    public Integer numberOfPlayers()
    {
        return this.players.size();
    }
    
    /**
     * Returns true if the player limit has been reached, false otherwise.
     * @return 
     */
    public Boolean hasReachedPlayerLimit()
    {
        return this.numberOfPlayers() == PlayerManager.MAXIMUM_PLAYERS;
    }
}