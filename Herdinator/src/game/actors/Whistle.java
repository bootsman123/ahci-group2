package game.actors;

import game.base.UsableActor;
import game.base.listeners.UseListener;
import game.players.Player;
import game.util.SpriteSheetUtil;
import java.awt.Point;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author roland
 */
public class Whistle extends UsableActor
{
    private static final String SPRITE_SHEET_FILE_PATH = "../Resources/Images/whistle.png";
    private static final Integer SPRITE_SHEET_SPRITE_WIDTH = 32;
    private static final Integer SPRITE_SHEET_SPRITE_HEIGHT = 32;
    private static final Color SPRITE_SHEET_BACKGROUND_COLOR = new Color( 123, 198, 132 );
    private static final String SOUND_ON_USE = "../Resources/Sounds/whistle_s.wav";
    
    
    
    /**
     * Constructor.
     * @param position
     * @throws SlickException 
     */
    public Whistle( Point position, Player owner, boolean isOnMap ) throws SlickException
    {
        super( position, owner, isOnMap );
    }
    
    @Override
    public void init()
    {
        
        try
        {
            // Setup animations.
            SpriteSheet spriteSheet = new SpriteSheet( Whistle.SPRITE_SHEET_FILE_PATH,
                                                       Whistle.SPRITE_SHEET_SPRITE_WIDTH,
                                                       Whistle.SPRITE_SHEET_SPRITE_HEIGHT,
                                                       Whistle.SPRITE_SHEET_BACKGROUND_COLOR );
                                                       
            this.animation = SpriteSheetUtil.getAnimation( spriteSheet, 0, 0, 0, 150 );
            this.sound = new Sound( Whistle.SOUND_ON_USE );
        }
        catch( SlickException e )
        {
            Logger.getLogger( Whistle.class.getName() ).log( Level.SEVERE, e.getLocalizedMessage() );
        }
    }

    

    
}
