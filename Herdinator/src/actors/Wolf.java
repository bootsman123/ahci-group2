package actors;

import base.Map;
import base.MovableActor;
import java.awt.geom.Point2D;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

/**
 *
 * @author bootsman
 */
public class Wolf extends MovableActor
{
    public Wolf( Map map )
    {
        super( map );
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
