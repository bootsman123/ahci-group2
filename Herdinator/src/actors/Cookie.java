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
public class Cookie extends MovableActor
{
    private static final String SPRITE_SHEET_FILE_PATH = "../Resources/Images/temporarycookie.png";
    public static final int SPRITE_SHEET_SPRITE_WIDTH = 32; //@TODO: is public ok?
    public static final int SPRITE_SHEET_SPRITE_HEIGHT = 32;
    private static final Color SPRITE_SHEET_BACKGROUND_COLOR = new Color( 123, 198, 132 );

    SpriteSheet spriteSheet;

    private Animation animation;
    private int ownerID;

    
    public Cookie( Map map, Point2D.Float position, int ownerID ) throws SlickException
    {
        super( map, position );
        spriteSheet = new SpriteSheet( Cookie.SPRITE_SHEET_FILE_PATH,
                                                   Cookie.SPRITE_SHEET_SPRITE_WIDTH,
                                                   Cookie.SPRITE_SHEET_SPRITE_HEIGHT,
                                                   Cookie.SPRITE_SHEET_BACKGROUND_COLOR );
        
        
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
