package game.util;

import java.awt.Point;

/**
 * Generic mathematics functions.
 * @author Bas Bootsma
 */
public final class Math
{
    public static double distanceEuclidian( Point p1, Point p2 )
    {
        return java.lang.Math.sqrt( java.lang.Math.pow( p1.x - p2.x, 2 ) + 
                                    java.lang.Math.pow( p1.y - p2.y, 2 ) );
    }
    
    public static double distanceManhatten( Point p1, Point p2 )
    {
        return java.lang.Math.abs( p1.x - p2.x ) + 
               java.lang.Math.abs( p1.y - p2.y );
    }
    
    public static double clamp( double value, double min, double max )
    {
        return java.lang.Math.max( min, java.lang.Math.min( value, max ) );
    }
    
    public static double lerp( double a, double b, double t )
    {
        return a + ( b - a ) * t;
    }
}