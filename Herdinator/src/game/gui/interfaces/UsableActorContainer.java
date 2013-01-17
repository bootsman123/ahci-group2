package game.gui.interfaces;

import game.actors.Cookie;
import game.actors.Whistle;
import game.base.UsableActor;
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

   /**
    * Initialise all usable objects for all player
    * @throws SlickException 
    */
    public void startGame() throws SlickException{
        for(Player player : GameManager.getInstance().getPlayers()){
            if(player instanceof MousePlayer || player instanceof TouchPlayer){
                initObjectsForPlayer(player);
            }
        }  
    }
    
    /**
     * Initialise the object picker images
     * @param container
     * @param game
     * @throws SlickException 
     */
    public void init( GameContainer container, StateBasedGame game ) throws SlickException
    {
        this.horizontalPicker = new Image(UsableActorContainer.HORIZONTAL_PICKER_IMAGE_FILE_PATH);
        this.verticalPicker = new Image(UsableActorContainer.VERTICAL_PICKER_IMAGE_FILE_PATH);   
    }
    
    public void update( GameContainer container, StateBasedGame game, int delta ) throws SlickException
    {
        if( container.isPaused() )
        {
            return;
        }

        Input mouseInput = container.getInput();
        
        Point2D pixelPoint = new Point2D.Double(input.getMouseX(), input.getMouseY());
        int pixelX = (int) pixelPoint.getX();
        int pixelY = (int) pixelPoint.getY();

        for (UsableActor actor : cookies){
            
            int actorTileX = actor.getX();
            int actorTileY = actor.getY();
            Point2D.Double positionInPixels = GameManager.getInstance().getMap().toPositionInPixels(actorTileX, actorTileY);
            double actorPixelX = positionInPixels.getX();
            double actorPixelY = positionInPixels.getY();

            int actorWidth = actor.getWidth();
            int actorHeight = actor.getHeight();
           //System.out.println("UsableActorContainer.update: pixelX: " + pixelX + " actorPixelX: " + actorPixelX + " pixelY: " + pixelY + " actorPixelY: " + actorPixelY );
            
            //Check if the pixels are inside the actor
            if (( pixelX >= actorPixelX && pixelX <= actorPixelX + actorWidth) && ( pixelY >= actorPixelY && pixelY <= actorPixelY + actorHeight) ){
                System.out.println("UsableActorContainer.update: Player is now dragging the object");

                
                actor.getOwner().setObject(actor);
                if (actor.getOwner() instanceof MousePlayer){
                    ((MousePlayer) actor.getOwner()).setIsDraggingObject(true);
                }
                GameManager.getInstance().getMap().addUsableActor(actor);
                cookies.remove(actor);
                break;
            }
        }
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

    /**
     * Add all usable objects to the arraylists and assign them to a player
     * @param player
     * @throws SlickException 
     */
    private void initObjectsForPlayer(Player player) throws SlickException {
        Point startingPoint = new Point(0,0);
        
        //Add cookie
        Cookie cookie = new Cookie(startingPoint, player, false);
        Point2D.Double locationInsideContainer = new Point2D.Double((this.pickerStartX+UsableActorContainer.IMAGE_OFFSET)*player.getId()*3, this.pickerStartY + UsableActorContainer.IMAGE_OFFSET);
        cookie.setLocationInsideActorContainer(locationInsideContainer);
        this.cookies.add(cookie);
        cookie.init();

        //Add whistle
        Whistle whistle = new Whistle(startingPoint, player, false);
        locationInsideContainer = new Point2D.Double((this.pickerStartX+UsableActorContainer.IMAGE_OFFSET)*player.getId()*3, this.pickerStartY + UsableActorContainer.IMAGE_OFFSET + PIXEL_DIFFERENCE_NEXT_OBJECT_Y);
        whistle.setLocationInsideActorContainer(locationInsideContainer);
        this.whistles.add(whistle);
        whistle.init();
    }
}
