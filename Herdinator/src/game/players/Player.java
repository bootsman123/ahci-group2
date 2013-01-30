package game.players;

import game.actors.Cookie;
import game.actors.Whistle;
import game.base.UsableActor;
import game.global.GameManager;
import java.util.EnumMap;
import java.util.Map;
import java.util.Random;
import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;

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
    };
    
    public enum PlayerObject
    {
        WHISTLE, COOKIE;
    };
    
    // Id of the player.
    private Integer id;
    
    // Color of the player.
    private Color color;
    
    // Objects of the player.
    private Map<PlayerObject, UsableActor> objects;
    private UsableActor currentObject;
    
    /**
     * Constructor.
     * @param color 
     */
    public Player()
    {
        System.out.println("amount of players now: " + GameManager.getInstance().getNumberOfPlayers() );
        this.color = PlayerColor.values()[ GameManager.getInstance().getNumberOfPlayers() ].getColor();
        this.id = ( new Random() ).nextInt( Integer.MAX_VALUE );
        
        this.objects = new EnumMap<PlayerObject, UsableActor>( PlayerObject.class );
        this.currentObject = null;
    }
    
    
    /**
     * Set the object to be used by this player
     * @param newObject 
     */
    /*public void setObject(UsableActor newObject) {
        if (!newObject.equals(this.getObject())){
            GameManager.getInstance().getMap().removeUsableActor(this.object);//@TODO: do not let the player object remove the current object from the map?
            this.object = newObject;
            GameManager.getInstance().getMap().addUsableActor(this.object);
        }
    }*/
    
    /**
     * Initialize.
     * @param container
     * @param game
     * @throws SlickException 
     */
    public void init(/* GameContainer container, StateBasedGame game */) throws SlickException
    {
        // Add objects.
        Cookie cookie = new Cookie( this, Boolean.FALSE );
        cookie.init();
        
        Whistle whistle = new Whistle( this, Boolean.FALSE );
        whistle.init();
        
        this.objects.put( PlayerObject.COOKIE, cookie );
        this.objects.put( PlayerObject.WHISTLE, whistle );
    }
    
    /**
     * Select an object.
     * @param object 
     */
    public void selectObject( PlayerObject object )
    {
        UsableActor newObject = this.objects.get( object );
        
        if( !newObject.equals( this.currentObject ) )
        {
            // Remove old object.
            GameManager.getInstance().getMap().removeUsableActor( this.currentObject );
            
            // Add new object.
            this.currentObject = newObject;
            GameManager.getInstance().getMap().addUsableActor( this.currentObject );
        }
    }
    
    /**
     * Select an object.
     * @deprecated 
     * @param actor 
     */
    public void selectObject( UsableActor actor )
    {        
        if( !actor.equals( this.currentObject ) )
        {
            // Remove old object.
            GameManager.getInstance().getMap().removeUsableActor( this.currentObject );
            
            // Add new object.
            this.currentObject = actor;
            GameManager.getInstance().getMap().addUsableActor( this.currentObject );
        }
    }
    
    /**
     * Return the object.
     * @return 
     */
    public UsableActor getObject()
    {
        return this.currentObject;
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
}