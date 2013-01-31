package game.gui.interfaces;

import TUIO.TuioCursor;
import game.Game;
import game.actors.Cookie;
import game.actors.Whistle;
import game.base.UsableActor;
import game.global.GameManager;
import game.global.ResourceManager;
import game.players.MousePlayer;
import game.players.Player;
import game.players.Player.PlayerObject;
import game.players.TouchPlayer;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author roland
 */
public class UsableActorContainer extends AbstractComponent
{
    public static final String VERTICAL_PICKER_IMAGE_FILE_PATH = "../Resources/Images/verticalpicker.png";
    public static final String HORIZONTAL_PICKER_IMAGE_FILE_PATH = "../Resources/Images/verticalpicker.png";

    private static final int IMAGE_OFFSET = 25;
    
    private static final int HORIZONTAL_PICKER_PIXEL_DIFFERENCE_NEXT_OBJECT_X = 0;
    private static final int HORIZONTAL_PICKER_PIXEL_DIFFERENCE_NEXT_OBJECT_Y = 170;
    private static final int VERTICAL_PICKER_PIXEL_DIFFERENCE_NEXT_OBJECT_X = 170;
    private static final int VERTICAL_PICKER_PIXEL_DIFFERENCE_NEXT_OBJECT_Y = 0;
    
    private static final int HORIZONTAL_PICKER_HEIGHT = 220;
    private static final int HORIZONTAL_PICKER_WIDTH = 50;
    private static final int VERTICAL_PICKER_HEIGHT = 50;
    private static final int VERTICAL_PICKER_WIDTH = 230;
    
    private static final int[] PICKER_START_PIXELS_X = {10, 60, 700, 500};
    private static final int[] PICKER_START_PIXELS_Y = {110, 580, 110, 580};
    private static final boolean[] PICKER_DRAWN_HORIZONTAL = {true,false,true,false};
        
    private Image horizontalPicker = null;
    private Image verticalPicker = null;
    
    private List<Whistle> whistles;
    private List<Cookie> cookies;
    
    private int pickerStartX = 0;
    private int pickerStartY = 0;
    private int pickerWidth = 0;
    private int pickerHeight = 0;
    
    /**
     * Constructor.
     * @param container 
     */
    public UsableActorContainer( GUIContext container )
    {
       super( container );
       
       this.whistles = new ArrayList<Whistle>();
       this.cookies = new ArrayList<Cookie>();
    }

    /**
     * Tell the usableActor container that the game started and that the objects for every player have to be added.
     * @throws SlickException 
     */
    public void startGame() throws SlickException
    {
        //Add the object pickers to the screen.        
        GameManager gameManager = GameManager.getInstance();
        ResourceManager resourceManager = ResourceManager.getInstance();
        
        this.horizontalPicker = resourceManager.getImage(UsableActorContainer.HORIZONTAL_PICKER_IMAGE_FILE_PATH);
        this.verticalPicker = resourceManager.getImage(UsableActorContainer.VERTICAL_PICKER_IMAGE_FILE_PATH);
        
        // Add the objects for every player.
        int playerCount = 0; 
        
        for( Player player : gameManager.getPlayers() )
        {
            if( player instanceof MousePlayer || player instanceof TouchPlayer )
            {
                UsableActor actor;
                Point2D.Double locationInsideContainer;
                
                // Initialize cookie.                      
                if( UsableActorContainer.PICKER_DRAWN_HORIZONTAL[ playerCount ] )
                {
                    locationInsideContainer = new Point2D.Double((UsableActorContainer.PICKER_START_PIXELS_X[playerCount]+UsableActorContainer.IMAGE_OFFSET), UsableActorContainer.PICKER_START_PIXELS_Y[playerCount] + UsableActorContainer.IMAGE_OFFSET);
                }
                else
                {
                    locationInsideContainer = new Point2D.Double((UsableActorContainer.PICKER_START_PIXELS_X[playerCount]+UsableActorContainer.IMAGE_OFFSET), UsableActorContainer.PICKER_START_PIXELS_Y[playerCount] + UsableActorContainer.IMAGE_OFFSET);
                }
                 
                /*
                actor = player.getObject( PlayerObject.COOKIE );
                actor.setIsOnMap( Boolean.FALSE );
                actor.setLocationInsideActorContainer( locationInsideContainer );
                gameManager.getMap().addUsableActor( actor );
                */
                
                Cookie cookie = new Cookie( player, Boolean.FALSE );
                cookie.init();
                cookie.setLocationInsideActorContainer( locationInsideContainer );
                this.cookies.add( cookie );
 
                // Initialize whistle.
                if(UsableActorContainer.PICKER_DRAWN_HORIZONTAL[playerCount])
                {
                    locationInsideContainer = new Point2D.Double((UsableActorContainer.PICKER_START_PIXELS_X[playerCount]+UsableActorContainer.IMAGE_OFFSET + UsableActorContainer.HORIZONTAL_PICKER_PIXEL_DIFFERENCE_NEXT_OBJECT_X), UsableActorContainer.PICKER_START_PIXELS_Y[playerCount] + UsableActorContainer.IMAGE_OFFSET + UsableActorContainer.HORIZONTAL_PICKER_PIXEL_DIFFERENCE_NEXT_OBJECT_Y);
                }
                else
                {
                    locationInsideContainer = new Point2D.Double((UsableActorContainer.PICKER_START_PIXELS_X[playerCount]+UsableActorContainer.IMAGE_OFFSET + UsableActorContainer.VERTICAL_PICKER_PIXEL_DIFFERENCE_NEXT_OBJECT_X), UsableActorContainer.PICKER_START_PIXELS_Y[playerCount] + UsableActorContainer.IMAGE_OFFSET + UsableActorContainer.VERTICAL_PICKER_PIXEL_DIFFERENCE_NEXT_OBJECT_Y);
                }
                
                /*
                actor = player.getObject( PlayerObject.WHISTLE );
                actor.setIsOnMap( Boolean.FALSE );
                actor.setLocationInsideActorContainer( locationInsideContainer );
                gameManager.getMap().addUsableActor( actor );
                */
                
                Whistle whistle = new Whistle( player, Boolean.FALSE );
                whistle.init();
                whistle.setLocationInsideActorContainer( locationInsideContainer );
                this.whistles.add( whistle );
            }
            
            playerCount++;
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
         for (int x = 0 ; x < GameManager.getInstance().getPlayers().size(); x++){
             if (UsableActorContainer.PICKER_DRAWN_HORIZONTAL[x])
             {
                this.horizontalPicker.draw(UsableActorContainer.PICKER_START_PIXELS_X[x], UsableActorContainer.PICKER_START_PIXELS_Y[x], UsableActorContainer.HORIZONTAL_PICKER_WIDTH, UsableActorContainer.HORIZONTAL_PICKER_HEIGHT);
             }
             else{
                 this.verticalPicker.draw(UsableActorContainer.PICKER_START_PIXELS_X[x], UsableActorContainer.PICKER_START_PIXELS_Y[x], UsableActorContainer.VERTICAL_PICKER_WIDTH, UsableActorContainer.VERTICAL_PICKER_HEIGHT);
             }
         }
         
         
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
    public int getX()
    {
        return this.pickerStartX;
    }

    @Override
    public int getY()
    {
        return this.pickerStartY;
    }

    @Override
    public int getWidth()
    {
        return this.pickerWidth;
    }

    @Override
    public int getHeight()
    {
        return this.pickerHeight;
    }

    /**
     * Lets the owner of the given object select this object for use
     * @param actor 
     */
    private void pickObject(UsableActor actor) {
        
        //Reset the object that the actor currently is using
        UsableActor currentObject = actor.getOwner().getCurrentObject();
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
        actor.getOwner().selectObject(actor);
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
        List<UsableActor> combinedList = getAllUsableObjects();
        
        //Iterate all actors to check if it is touched
        for (UsableActor actor : combinedList){
            double actorPixelX = actor.getLocationInsideActorContainer().getX();
            double actorPixelY = actor.getLocationInsideActorContainer().getY();
            
            int actorWidth = actor.getWidth();
            int actorHeight = actor.getHeight();
            
            actorPixelX -= actorWidth/2;
            actorPixelY -= actorWidth/2;
            
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
        ArrayList<TuioCursor> cursors = GameManager.getInstance().getTouchHandler().getTuioCursors();
        for (int z = 0; z < cursors.size(); z++){
            try{
                Point2D pixelPoint = new Point2D.Double(cursors.get(z).getX()*Game.WIDTH, cursors.get(z).getY()*Game.HEIGHT);
                int pixelX = (int) pixelPoint.getX();
                int pixelY = (int) pixelPoint.getY();

                //Combine the lists with actors to one big list
                List<UsableActor> combinedList = getAllUsableObjects();

                //Check for every actor if this touchpoint touches this actor
                for (UsableActor actor : combinedList){
                    double actorPixelX = actor.getLocationInsideActorContainer().getX();
                    double actorPixelY = actor.getLocationInsideActorContainer().getY();

                    int actorWidth = actor.getWidth();
                    int actorHeight = actor.getHeight();

                    actorPixelX -= actorWidth/2; 
                    actorPixelY -= actorWidth/2;

                    if (( pixelX >= actorPixelX && pixelX <= actorPixelX + actorWidth) && ( pixelY >= actorPixelY && pixelY <= actorPixelY + actorHeight) ){
                        pickObject(actor);
                        break;
                    }
                }
            }
            catch(Exception e)
            {
            
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
    
    private List<UsableActor> getAllUsableObjects() {
        List<UsableActor> combinedList = new ArrayList<UsableActor>();
        combinedList.addAll(this.cookies);
        combinedList.addAll(this.whistles);
        return combinedList;
    }
}
