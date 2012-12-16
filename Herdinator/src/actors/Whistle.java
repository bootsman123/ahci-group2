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
public class Whistle extends MovableActor
{
    private static final String SPRITE_SHEET_FILE_PATH = "../Resources/Images/birdzilla_cow-recolors.png";
    private static final int SPRITE_SHEET_SPRITE_WIDTH = 32;
    private static final int SPRITE_SHEET_SPRITE_HEIGHT = 32;
    private static final Color SPRITE_SHEET_BACKGROUND_COLOR = new Color( 123, 198, 132 );

    SpriteSheet spriteSheet;

    private Animation animation;
    private int ownerID;


    public Whistle( Map map, Point2D.Float position, int ownerID ) throws SlickException
    {
        super( map, position );
        spriteSheet = new SpriteSheet( Whistle.SPRITE_SHEET_FILE_PATH,
                                                   Whistle.SPRITE_SHEET_SPRITE_WIDTH,
                                                   Whistle.SPRITE_SHEET_SPRITE_HEIGHT,
                                                   Whistle.SPRITE_SHEET_BACKGROUND_COLOR );


        //this.animation = SpriteSheetUtil.getAnimation( spriteSheet, ownerID%2, ownerID%2+2, (ownerID+2)%2, 150 );
        this.animation = SpriteSheetUtil.getAnimation( spriteSheet, (ownerID%4*3), (ownerID%4*3)+2, 0, 150 );


        this.ownerID = ownerID;

    }

    public int getOwnerID(){
        return ownerID;
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
