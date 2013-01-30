package game.actors;

import game.base.UsableActor;
import game.global.ResourceManager;
import game.players.Player;
import game.util.SpriteSheetUtil;
import java.awt.Point;
import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;

/**
 *
 * @author roland
 */
public class Cookie extends UsableActor 
{
    public static final String SPRITE_SHEET_FILE_PATH = "../Resources/Images/cookie.png";
    public static final Integer SPRITE_SHEET_SPRITE_WIDTH = 32;
    public static final Integer SPRITE_SHEET_SPRITE_HEIGHT = 32;
    public static final Color SPRITE_SHEET_BACKGROUND_COLOR = new Color( 123, 198, 132 );
    
    public static final String SOUND_ON_USE_FILE_PATH = "../Resources/Sounds/cookie_s.wav";
    
    /**
     * Constructor.
     * @param position
     * @param owner
     * @param isOnMap
     * @throws SlickException 
     */
    public Cookie( Point position, Player owner, Boolean isOnMap ) throws SlickException
    {
        super( position, owner, isOnMap );
    }
    
    /**
     * Constructor.
     * @param owner
     * @param isOnMap
     * @throws SlickException 
     */
    public Cookie( Player owner, Boolean isOnMap ) throws SlickException
    {
        this( new Point( 0, 0 ), owner, isOnMap );
    }

    @Override
    public void init()
    {
        // Setup animations.
        this.animation = SpriteSheetUtil.getAnimation( ResourceManager.getInstance().getSpriteSheet( Cookie.SPRITE_SHEET_FILE_PATH ), 0, 0, 0, 150 );
        this.sound = ResourceManager.getInstance().getSound( Cookie.SOUND_ON_USE_FILE_PATH );
    }

    @Override
    public String toString()
    {
        return "Cookie";
    }
}
