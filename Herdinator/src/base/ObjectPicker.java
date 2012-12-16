package base;

import actors.Cookie;
import actors.Dog;
import actors.Whistle;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author roland
 */
public class ObjectPicker
{
    // Dimensions of the picker in pixels.
    private static final int PICKER_WIDTH = 50;
    private static final int PICKER_HEIGHT = 100;
    
    private List<Whistle> whistles;
    private List<Cookie> cookies;

    
    /**
     * Load a new ObjectPicker
     * @param filePath
     * @throws SlickException 
     */
    public ObjectPicker( ) throws SlickException
    {
       
       this.whistles = new ArrayList<Whistle>();
       this.cookies = new ArrayList<Cookie>();
    }

    
    
    public void init( GameContainer container, StateBasedGame game ) throws SlickException
    {

        System.out.println("Initialising objectpicker");
        //Point2D.Float startingPointDog = new Point2D.Float(10,10);
        int numberOfInstancesHad = 0;
        int diff = 30;
        System.out.println("Amount of players: " +GameManager.getInstance().getPlayers().size() );
        for(Player p : GameManager.getInstance().getPlayers()){
                    Point2D.Float startingPoint = new Point2D.Float(0,numberOfInstancesHad+=diff);

          Cookie cookie = new Cookie(GameManager.getInstance().getMap(), startingPoint, p.getPlayerID()); //@TODO: change the ownerID
          this.cookies.add(cookie);
        }
        for(Player p : GameManager.getInstance().getPlayers()){
                    Point2D.Float startingPoint = new Point2D.Float(0,numberOfInstancesHad+=diff);

          Whistle whistle = new Whistle(GameManager.getInstance().getMap(), startingPoint, p.getPlayerID()); //@TODO: change the ownerID
          this.whistles.add(whistle);
        }
       
        
    }
    
    public void render( GameContainer container, StateBasedGame game, Graphics g ) throws SlickException
    {
        //System.out.println("Rendering object picker");
        g.drawRect(0, 0, PICKER_WIDTH, PICKER_HEIGHT);
        
        for( Whistle whistle : this.whistles )
        {


            whistle.render( g );
        }
        for(Cookie cookie : cookies){
            System.out.println("Rendering object picker whistles");

            cookie.render(g);
        }
        
    }
    
    public void update( GameContainer container, StateBasedGame game, int delta ) throws SlickException
    {
        
        if( container.isPaused() )
        {
            return;
        }
        
    }
}
