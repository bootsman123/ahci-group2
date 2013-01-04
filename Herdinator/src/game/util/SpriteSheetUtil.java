package game.util;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SpriteSheet;

/**
 * Utilization functions for handling sprite sheets.
 * @author Bas Bootsma
 */
public final class SpriteSheetUtil
{
    public static Animation getAnimation( SpriteSheet spriteSheet, int xFrom, int xTo, int y, int duration )
    {
        Animation animation = new Animation( false );
        
        for( int x = xFrom; x <= xTo; x++ )
        {
            animation.addFrame( spriteSheet.getSprite( x, y ), duration);
        }
        
        return animation;
    }
}
