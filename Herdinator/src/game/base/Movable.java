package game.base;

import java.awt.Point;
import game.util.MathUtil;

/**
 *
 * @author bootsman
 */
public interface Movable
{
    public enum Direction
    {
        UP( new Point( 0, -1 ) ),
        RIGHT( new Point( +1, 0 ) ),
        DOWN( new Point( 0, +1 ) ),
        LEFT( new Point( -1, 0 ) );

        private Point vector;
        private Double angle;
        
        Direction( Point vector )
        {
            this.vector = vector;
            this.angle = MathUtil.angle( new Point( 0, 0 ), this.vector );
        }
        
        public Point getVector()
        {
            return this.vector;
        }
        
        public Double getAngle()
        {
            return this.angle;
        }
        
        public Point toPosition( Point position )
        {
            return new Point( position.x + this.vector.x, position.y + this.vector.y );
        }
    };
    
    public void move( Direction direction );
}