package base;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

/**
 * Any object which can be rendered onto the screen should implement this.
 * @author Bas Bootsma
 */
public interface Animatable
{
    public void render( Graphics g );
    public void update( GameContainer container, int delta );
}
