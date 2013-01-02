package game.actors;

import game.base.Actor;
import game.base.ImmovableActor;
import java.awt.geom.Point2D;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import game.util.SpriteSheetUtil;
import java.awt.Point;

/**
 *
 * @author roland
 */
public class Whistle extends ImmovableActor
{
    private static final String SPRITE_SHEET_FILE_PATH = "../Resources/Images/whistle.png";
    private static final Integer SPRITE_SHEET_SPRITE_WIDTH = 32;
    private static final Integer SPRITE_SHEET_SPRITE_HEIGHT = 32;
    private static final Color SPRITE_SHEET_BACKGROUND_COLOR = new Color( 123, 198, 132 );

    /**
     * Constructor.
     * @param position
     * @throws SlickException 
     */
    public Whistle( Point position ) throws SlickException
    {
        super( position );
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
        }
        catch( SlickException e )
        {
            Logger.getLogger( Whistle.class.getName() ).log( Level.SEVERE, e.getLocalizedMessage() );
        }
    }
}
