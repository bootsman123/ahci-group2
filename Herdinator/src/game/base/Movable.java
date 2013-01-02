package game.base;

import java.awt.Point;

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
        
        Direction( Point vector )
        {
            this.vector = vector;
        }
        
        public Point getVector()
        {
            return this.vector;
        }
        
        public Point toPosition( Point position )
        {
            return new Point( position.x + this.vector.x, position.y + this.vector.y );
        }
    };
    
    public void move( Direction direction );
}