package game.util;

import java.awt.Point;

/**
 * Generic mathematics functions.
 * @author Bas Bootsma
 */
public final class MathUtil
{
    /**
     * Euclidian distance between point p1 and point p2.
     * @param p1
     * @param p2
     * @return 
     */
    public static double distanceEuclidian( Point p1, Point p2 )
    {
        return java.lang.Math.sqrt( java.lang.Math.pow( p1.x - p2.x, 2 ) + 
                                    java.lang.Math.pow( p1.y - p2.y, 2 ) );
    }
    
    /**
     * Manhattan distance between point p1 and point p2.
     * @param p1
     * @param p2
     * @return 
     */
    public static double distanceManhattan( Point p1, Point p2 )
    {
        return java.lang.Math.abs( p1.x - p2.x ) + 
               java.lang.Math.abs( p1.y - p2.y );
    }
    
    /**
     * Returns the angle between two points.
     * @param p1
     * @param p2
     * @return 
     */
    public static double angle( Point p1, Point p2 )
    {
        Double angle = java.lang.Math.atan2( p2.y - p1.y, p2.x - p1.x );
        //angle += java.lang.MathUtil.PI;
        
        return angle;
    }
    
    /**
     * Clamps a value between a minimum and maximum.
     * @param value
     * @param min
     * @param max
     * @return 
     */
    public static double clamp( double value, double min, double max )
    {
        return java.lang.Math.max( min, java.lang.Math.min( value, max ) );
    }
    
    /**
     * Linear interpolation of a value between a and b.
     * @param a
     * @param b
     * @param t
     * @return 
     */
    public static double lerp( double a, double b, double t )
    {
        return a + ( b - a ) * t;
    }
}