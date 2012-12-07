package actors;

import base.Actor;
import base.Map;
import java.awt.geom.Point2D;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

/**
 *
 * @author roland
 */
public class Bridge extends Actor
{
    public Bridge( Map map )
    {
        super( map, new Point2D.Float( 0.0f, 0.0f ) );
    }

    @Override
    public void render(Graphics g) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(GameContainer container, int delta) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
