package game.players;

import game.base.UsableActor;
import game.global.GameManager;
import java.util.Random;
import org.newdawn.slick.Color;

/**
 *
 * @author roland
 */
public abstract class Player
{    
    //@TODO: Star Craft colors:
    //Red, Blue, Teal, Purple, Yellow, Orange, Green, Pink, Grey, Light Blue, Dark Green, Brown
    public enum PlayerColor
    {
        RED( Color.red ),
        BLUE( Color.blue ),
        YELLOW( Color.yellow ),
        PINK( Color.pink ),
        GRAY( Color.gray );
        
        private Color color;
                
        /**
         * Constructor.
         * @param color 
         */
        PlayerColor( Color color )
        {
            this.color = color;
        }
        
        public Color getColor()
        {
            return this.color;
        }
    }
    
    // Id of the player.
    private Integer id;
    
    // Color of the player.
    private Color color;
    
    // Current object of the player.
    private UsableActor object;
    
    /**
     * Constructor.
     * @param color 
     */
    public Player()
    {
        this.color = PlayerColor.values()[ GameManager.getInstance().getNumberOfPlayers() ].getColor();
        this.id = ( new Random() ).nextInt( Integer.MAX_VALUE );        
        this.object = null;
    }

    /**
     * Set the object to be used by this player
     * @param newObject 
     */
    public void setObject(UsableActor newObject) {
        if (!newObject.equals(this.getObject())){
            GameManager.getInstance().getMap().removeUsableActor(this.object);//@TODO: do not let the player object remove the current object from the map?
            this.object = newObject;
            GameManager.getInstance().getMap().addUsableActor(this.object);
        }
    }
    
    /**
     * Return the id.
     * @return 
     */
    public Integer getId()
    {
        return this.id;
    }
    
    /**
     * Returns the color.
     * @return 
     */
    public Color getColor()
    {
        return this.color;
    }
    
    /**
     * Return the object.
     * @return 
     */
    public UsableActor getObject()
    {
        return this.object;
    }
}
