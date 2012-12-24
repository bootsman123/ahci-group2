package global;

import actors.Cookie;
import actors.Whistle;
import global.GameManager;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import players.MousePlayer;
import players.Player;

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
        this.horizontalPicker = new Image(ObjectPicker.HORIZONTAL_PICKER_IMAGE_FILE_PATH);
        this.verticalPicker = new Image(ObjectPicker.VERTICAL_PICKER_IMAGE_FILE_PATH);
        int numberOfInstancesHad = 0;
        int diff = 30;   
        for(Player p : GameManager.getInstance().getPlayers()){
            if(p instanceof MousePlayer){
                Point2D.Float startingPoint = new Point2D.Float(PICKER_START_X+IMAGE_OFFSET,numberOfInstancesHad+=diff);
                Cookie cookie = new Cookie(GameManager.getInstance().getMap(), startingPoint, p.getPlayerID()); //@TODO: change the ownerID
                this.cookies.add(cookie);

                startingPoint = new Point2D.Float(PICKER_START_X+IMAGE_OFFSET,numberOfInstancesHad+=diff);
                Whistle whistle = new Whistle(GameManager.getInstance().getMap(), startingPoint, p.getPlayerID()); //@TODO: change the ownerID
                this.whistles.add(whistle);
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


        for (Cookie c : cookies){
            
            int cookieWidth = Cookie.SPRITE_SHEET_SPRITE_WIDTH;
            int cookieHeight = Cookie.SPRITE_SHEET_SPRITE_HEIGHT;

            if( ( mouseX >= c.getX() && mouseX <= c.getX() + cookieWidth) && ( mouseY >= c.getY() && mouseY <= c.getY() + cookieHeight) ){
                System.out.println("Inside da cookie");
                GameManager.getInstance().changeObjectOfPlayer(c);
            }
        }
        for(Whistle whistle :whistles){
            if( ( mouseX >= whistle.getX() && mouseX <= whistle.getX()+ Whistle.SPRITE_SHEET_SPRITE_WIDTH) &&
                      ( mouseY >= whistle.getY() && mouseY <= whistle.getY()+ Whistle.SPRITE_SHEET_SPRITE_HEIGHT) ){
               System.out.println("Inside da whistle");
                GameManager.getInstance().changeObjectOfPlayer(whistle);
            }
        }
    }
}
