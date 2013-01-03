package game.interfaces;

import game.actors.Cookie;
import game.actors.Whistle;
import game.global.GameManager;
import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import game.players.MousePlayer;
import game.players.Player;
import java.awt.Point;
import java.awt.geom.Point2D;

/**
 *
 * @author roland
 */
public class ObjectPicker
{
    private static final String VERTICAL_PICKER_IMAGE_FILE_PATH = "../Resources/Images/verticalpicker.png";
    private static final String HORIZONTAL_PICKER_IMAGE_FILE_PATH = "../Resources/Images/verticalpicker.png";

    // Dimensions of the picker in pixels.
    private static final int PICKER_START_X = 5;
    private static final int PICKER_START_Y= 20;
    private static final int PICKER_WIDTH = 50;
    private static final int PICKER_HEIGHT = 100;
    private static final int IMAGE_OFFSET = 5;

    private Image horizontalPicker = null;
    private Image verticalPicker = null;
    
    private List<Whistle> whistles;
    private List<Cookie> cookies;

    
   
    public ObjectPicker( ) throws SlickException
    {
       this.whistles = new ArrayList<Whistle>();
       this.cookies = new ArrayList<Cookie>();
    }

    
    public void init( GameContainer container, StateBasedGame game ) throws SlickException
    {
        this.horizontalPicker = new Image(ObjectPicker.HORIZONTAL_PICKER_IMAGE_FILE_PATH);
        this.verticalPicker = new Image(ObjectPicker.VERTICAL_PICKER_IMAGE_FILE_PATH);
        int numberOfInstancesHad = 0;
        int diff = 30;   
        for(Player p : GameManager.getInstance().getPlayers()){
            if(p instanceof MousePlayer){
                Point startingPoint = new Point(4,5); //@TODO: set all the locations right
                
                Cookie cookie = new Cookie(startingPoint);
                this.cookies.add(cookie);
                cookie.init();

                startingPoint = new Point(2,3); //@TODO: set all the locations right
                Whistle whistle = new Whistle(startingPoint);
                this.whistles.add(whistle);
                whistle.init();
            }
        }        
    }
    
    public void render( GameContainer container, StateBasedGame game, Graphics g ) throws SlickException
    {
        this.horizontalPicker.draw(PICKER_START_X, PICKER_START_Y, PICKER_WIDTH, PICKER_HEIGHT);
        
        for( Whistle whistle : this.whistles )
        {
            whistle.render( g );
        }
        for(Cookie cookie : cookies){
            cookie.render(g);
        }
        
    }
    
    public void update( GameContainer container, StateBasedGame game, int delta ) throws SlickException
    {
        if( container.isPaused() )
        {
            return;
        }


        Input input = container.getInput();
        int mouseX = input.getMouseX();
        int mouseY = input.getMouseY();

        Point mousePos = GameManager.getInstance().getMap().fromPositionInPixels(new Point2D.Double(mouseX,mouseY));

        mouseX = (int) mousePos.getX();
        mouseY = (int) mousePos.getY();
        for (Cookie c : cookies){
            
            int cookieWidth = 1;//Cookie.getObjectWidth();
            int cookieHeight = 1;Cookie.getObjectHeight();
            
            if( ( mouseX >= c.getX() && mouseX <= c.getX() + cookieWidth) && ( mouseY >= c.getY() && mouseY <= c.getY() + cookieHeight) ){
                System.out.println("Inside da cookie");
                
                GameManager.getInstance().getPlayers().get(0).setObject(c);
            }
        }
        for(Whistle whistle :whistles){
            int whistleWidth = 1;//Whistle.getObjectWidth();
            int whistleHeight = 1;//Whistle.getObjectHeight();
            if( ( mouseX >= whistle.getX() && mouseX <= whistle.getX()+ whistleWidth) &&
                      ( mouseY >= whistle.getY() && mouseY <= whistle.getY()+ whistleHeight) ){
               System.out.println("Inside da whistle");
                GameManager.getInstance().getPlayers().get(0).setObject(whistle);
            }
        }
    }
}
