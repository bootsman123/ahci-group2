package actors;

import base.Map;
import base.MovableActor;
import java.awt.geom.Point2D;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import util.SpriteSheetUtil;

/**
 *
 * @author roland
 */
public class LoveSheep extends MovableActor
{
    private static final String SPRITE_SHEET_FILE_PATH = "../Resources/Images/sheeps_animation.png";
    private static final int SPRITE_SHEET_SPRITE_WIDTH = 32;
    private static final int SPRITE_SHEET_SPRITE_HEIGHT = 32;
    private static final Color SPRITE_SHEET_BACKGROUND_COLOR = new Color( 123, 198, 132 );
    
    SpriteSheet spriteSheet;

    private Animation animation;

    public LoveSheep( Map map, Point2D.Float position ) throws SlickException
    {
        super( map, position );
        spriteSheet = new SpriteSheet( LoveSheep.SPRITE_SHEET_FILE_PATH,
                                                   LoveSheep.SPRITE_SHEET_SPRITE_WIDTH,
                                                   LoveSheep.SPRITE_SHEET_SPRITE_HEIGHT,
                                                   LoveSheep.SPRITE_SHEET_BACKGROUND_COLOR );
        this.animation = SpriteSheetUtil.getAnimation( spriteSheet, 6, 8, 0, 150 );
    }
    
    @Override
    public void render(Graphics g) {
       this.animation.draw( this.getX(), this.getY() );
    }

    @Override
    public void update(GameContainer container, int delta) {
        this.animation.update( delta );
    }
    
}
