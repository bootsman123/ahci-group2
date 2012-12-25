package game.util;

import java.awt.geom.Point2D;

/**
 *
 * @author bootsman
 */
public final class Distance
{
    public static double euclidian( Point2D.Double p1, Point2D.Double p2 )
    {
        return Math.sqrt( Math.pow( p1.x - p2.x, 2 ) + Math.pow( p1.y - p2.y, 2 ) );
    }
}