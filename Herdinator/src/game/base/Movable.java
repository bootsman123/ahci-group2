package game.base;

/**
 *
 * @author bootsman
 */
public interface Movable
{
    public enum Direction
    {
        UP, RIGHT, DOWN, LEFT;
    };
    
    public void move( Direction direction );
}