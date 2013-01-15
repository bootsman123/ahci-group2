package game.gui.interfaces;

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
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.state.StateBasedGame;
import game.players.MousePlayer;
import game.players.Player;
import game.players.TouchPlayer;
import java.awt.Point;
import java.awt.geom.Point2D;
import org.newdawn.slick.gui.AbstractComponent;

/**
 *
 * @author roland
 */
public class UsableActorContainer extends AbstractComponent
{
    private static final String VERTICAL_PICKER_IMAGE_FILE_PATH = "../Resources/Images/verticalpicker.png";
    private static final String HORIZONTAL_PICKER_IMAGE_FILE_PATH = "../Resources/Images/verticalpicker.png";

    private static final int IMAGE_OFFSET = 25;
    private static final int PIXEL_DIFFERENCE_NEXT_OBJECT_Y = 45;
    private static final int MAP_POSITION_X = 1;
    private static final int MAP_POSITION_Y = 1;
    
    // Dimensions of the picker in pixels.
    private int pickerStartX = 5;
    private int pickerStartY = 20;
    private int pickerWidth = 50;
    private int pickerHeight = 100;
    
    private Image horizontalPicker = null;
    private Image verticalPicker = null;
    
    private List<Whistle> whistles;
    private List<Cookie> cookies;


    
   
   public UsableActorContainer(GUIContext container)
    {
       super(container);
       this.whistles = new ArrayList<Whistle>();
       this.cookies = new ArrayList<Cookie>();
    }

    
    public void init( GameContainer container, StateBasedGame game ) throws SlickException
    {
       
        this.horizontalPicker = new Image(UsableActorContainer.HORIZONTAL_PICKER_IMAGE_FILE_PATH);
        this.verticalPicker = new Image(UsableActorContainer.VERTICAL_PICKER_IMAGE_FILE_PATH);
        System.out.println("UsableActorContainer.init: amount of players: " + GameManager.getInstance().getPlayers().size());
        for(Player p : GameManager.getInstance().getPlayers()){
            if(p instanceof MousePlayer || p instanceof TouchPlayer){
                System.out.println("UsableActorContainer.init: adding object for player!");
                //Point startingPoint = new Point(UsableActorContainer.MAP_POSITION_X,UsableActorContainer.MAP_POSITION_Y+(p.getId()*NEXT_OBJECT_DIFFERENCE)); //@TODO: set all the locations right
                Point startingPoint = new Point(0,0);
                
                Cookie cookie = new Cookie(startingPoint, p, false);
                Point2D.Double locationInsideContainer = new Point2D.Double((this.pickerStartX+UsableActorContainer.IMAGE_OFFSET)*p.getId()*3, this.pickerStartY + UsableActorContainer.IMAGE_OFFSET);
                cookie.setLocationInsideActorContainer(locationInsideContainer);
                this.cookies.add(cookie);
                cookie.init();

                //startingPoint = new Point(UsableActorContainer.MAP_POSITION_X,UsableActorContainer.MAP_POSITION_Y+(p.getId()*NEXT_OBJECT_DIFFERENCE)+3); //@TODO: set all the locations right
                Whistle whistle = new Whistle(startingPoint, p, false);

                locationInsideContainer = new Point2D.Double((this.pickerStartX+UsableActorContainer.IMAGE_OFFSET)*p.getId()*3, this.pickerStartY + UsableActorContainer.IMAGE_OFFSET + PIXEL_DIFFERENCE_NEXT_OBJECT_Y);
                whistle.setLocationInsideActorContainer(locationInsideContainer);
                this.whistles.add(whistle);
                whistle.init();
            }
        }        
    }
    
    public void update( GameContainer container, StateBasedGame game, int delta ) throws SlickException
    {
        if( container.isPaused() )
        {
            return;
        }

        Input mouseInput = container.getInput();
        int mouseX = mouseInput.getMouseX();
        int mouseY = mouseInput.getMouseY();

        //Point mousePos = GameManager.getInstance().getMap().fromPositionInPixels(new Point2D.Double(mouseX,mouseY));

        //mouseX = (int) mousePos.getX();
       // mouseY = (int) mousePos.getY();
       
        
    }

    @Override
    public void render(GUIContext container, Graphics g) throws SlickException {
         this.horizontalPicker.draw(this.pickerStartX, this.pickerStartY, this.pickerWidth, this.pickerHeight);

        for( Whistle whistle : this.whistles )
        {
            whistle.render( g );
        }
        for(Cookie cookie : this.cookies){
            cookie.render(g);
        }
    }

    @Override
    public void setLocation(int x, int y) {
        this.pickerStartX = x;
        this.pickerStartY = y;
    }

    @Override
    public int getX() {
        return this.pickerStartX;
    }

    @Override
    public int getY() {
        return this.pickerStartY;
    }

    @Override
    public int getWidth() {
        return this.pickerWidth;
    }

    @Override
    public int getHeight() {
        return this.pickerHeight;
    }
}
