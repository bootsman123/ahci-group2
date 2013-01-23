package game.gui.interfaces;

import game.Game;
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
     * Tell the usableActor container that the game started and that the objects for every player have to be added.
     * @throws SlickException 
     */
    public void startGame() throws SlickException{
        //Add the object pickers to the screen
        this.horizontalPicker = new Image(UsableActorContainer.HORIZONTAL_PICKER_IMAGE_FILE_PATH);
        this.verticalPicker = new Image(UsableActorContainer.VERTICAL_PICKER_IMAGE_FILE_PATH);
        
        //Add the objects for every player
        for(Player p : GameManager.getInstance().getPlayers()){
            if(p instanceof MousePlayer || p instanceof TouchPlayer){
                Point startingPoint = new Point(0,0);
                
                //Add a cookie for this player to the list of cookies
                Cookie cookie = new Cookie(startingPoint, p, false);
                Point2D.Double locationInsideContainer = new Point2D.Double((this.pickerStartX+UsableActorContainer.IMAGE_OFFSET)*p.getId()*3, this.pickerStartY + UsableActorContainer.IMAGE_OFFSET);
                cookie.setLocationInsideActorContainer(locationInsideContainer);
                this.cookies.add(cookie);
                cookie.init();

                //Add a whistle for this player
                Whistle whistle = new Whistle(startingPoint, p, false);
                locationInsideContainer = new Point2D.Double((this.pickerStartX+UsableActorContainer.IMAGE_OFFSET)*p.getId()*3, this.pickerStartY + UsableActorContainer.IMAGE_OFFSET + PIXEL_DIFFERENCE_NEXT_OBJECT_Y);
                whistle.setLocationInsideActorContainer(locationInsideContainer);
                this.whistles.add(whistle);
                whistle.init();
                
            }
        }  
    }
    
    /**
     * Initialise the usable actor container.
     * @param container
     * @param game
     * @throws SlickException 
     */
    public void init( GameContainer container, StateBasedGame game ) throws SlickException
    {
       
        
        
    }
    
    /**
     * Updates the actor container by checking the touch possibilities. 
     * @param container
     * @param game
     * @param delta
     * @throws SlickException 
     */
    public void update( GameContainer container, StateBasedGame game, int delta ) throws SlickException
    {
        if( container.isPaused() )
        {
            return;
        }

        
        //Check mouse touch
        checkMouseTouch();
        
        //Check touch touch
        checkTouchTouch();
    }
    

    @Override
    public void render(GUIContext container, Graphics g) throws SlickException {
        //Draw the pickers at their respective places
         this.horizontalPicker.draw(this.pickerStartX, this.pickerStartY, this.pickerWidth, this.pickerHeight);
         
        //Draw the objects inside the pickers
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
     * Lets the owner of the given object select this object for use
     * @param actor 
     */
    private void pickObject(UsableActor actor) {
        
        //Reset the object that the actor currently is using
        UsableActor currentObject = actor.getOwner().getObject();
        if (currentObject != null){
            currentObject.resetPosition();
            if (currentObject instanceof Cookie){
                this.cookies.add((Cookie) currentObject);
            }
            else if (currentObject instanceof Whistle){
               this.whistles.add((Whistle) currentObject);
            }
        }
        
        //Give the given actor to the owner
        actor.resetPosition();
        actor.getOwner().setObject(actor);
        if (actor.getOwner() instanceof MousePlayer){
            ((MousePlayer) actor.getOwner()).setIsDraggingObject(true);
        }
        
        //Remove the object from the usable actor container
        removeObjectFromContainer(actor);
    }

    /**
     * Checks if the mouse touches (and thus selects) a given object
     */
    private void checkMouseTouch() {
        //Get the location of the mouse (in pixels)
        Input mouseInput = container.getInput();
        Point2D pixelPoint = new Point2D.Double(mouseInput.getMouseX(), mouseInput.getMouseY());
        int pixelX = (int) pixelPoint.getX();
        int pixelY = (int) pixelPoint.getY();
        
        //Combine the lists with actors to one big list
        List<UsableActor> combinedList = new ArrayList<UsableActor>();
        combinedList.addAll(this.cookies);
        combinedList.addAll(this.whistles);
        
        //Iterate all actors to check if it is touched
        for (UsableActor actor : combinedList){
            double actorPixelX = actor.getLocationInsideActorContainer().getX();
            double actorPixelY = actor.getLocationInsideActorContainer().getY();
            
            int actorWidth = actor.getWidth();
            int actorHeight = actor.getHeight();
            if (( pixelX >= actorPixelX && pixelX <= actorPixelX + actorWidth) && ( pixelY >= actorPixelY && pixelY <= actorPixelY + actorHeight) ){
                pickObject(actor);
                break;
            }
        }
    }

    /**
     * Checks if a touch point touches (and thus selects) a given object
     */
    private void checkTouchTouch() {
        for (int z = 0; z < GameManager.getInstance().getTouchHandler().getTuioCursors().size(); z++){
            Point2D pixelPoint = new Point2D.Double(GameManager.getInstance().getTouchHandler().getTuioCursors().get(z).getX()*Game.WIDTH, GameManager.getInstance().getTouchHandler().getTuioCursors().get(z).getY()*Game.HEIGHT);
            int pixelX = (int) pixelPoint.getX();
            int pixelY = (int) pixelPoint.getY();

            //Combine the lists with actors to one big list
            List<UsableActor> combinedList = new ArrayList<UsableActor>();
            combinedList.addAll(this.cookies);
            combinedList.addAll(this.whistles);
            
            //Check for every actor if this touchpoint touches this actor
            for (UsableActor actor : combinedList){
                double actorPixelX = actor.getLocationInsideActorContainer().getX();
                double actorPixelY = actor.getLocationInsideActorContainer().getY();

                int actorWidth = actor.getWidth();
                int actorHeight = actor.getHeight();
                if (( pixelX >= actorPixelX && pixelX <= actorPixelX + actorWidth) && ( pixelY >= actorPixelY && pixelY <= actorPixelY + actorHeight) ){
                    pickObject(actor);
                    break;
                }
            }
        }
    }

    /**
     * Removes the given object from the lists of objects. 
     * @param actor 
     */
    private void removeObjectFromContainer(UsableActor actor) {
        if (actor instanceof Cookie){
            for (int x = 0 ; x < this.cookies.size(); x++){
                if (this.cookies.get(x).equals(actor)){
                    this.cookies.remove(x);
                    break;
                }
            }   
        }
        else if (actor instanceof Whistle){
           for (int x = 0 ; x < this.whistles.size(); x++){
                if (this.whistles.get(x).equals(actor)){
                    this.whistles.remove(x);
                    break;
                }
            }
        }
    }
}
