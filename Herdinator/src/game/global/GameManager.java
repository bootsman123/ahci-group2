package game.global;

import TUIO.TuioClient;
import game.Game;
import game.base.Map;
import game.base.UsableActor;
import game.gui.TouchHandler;
import game.gui.interfaces.TouchOverlay;
import game.gui.interfaces.UsableActorContainer;
import game.players.MousePlayer;
import game.players.Player;
import game.players.TangiblePlayer;
import game.players.TouchPlayer;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author roland
 */
public class GameManager
{
    public enum Mode
    {
        MOUSE, TOUCH;
    }
    
    private static final Integer MAXIMUM_NUMBER_OF_PLAYERS = 4;
    
    // Instance variable.
    private static final GameManager instance = new GameManager();
    
    // List of all the maps.
    private Map map;
    private List<Map> maps;
    
    // List of all the players.
    private List<Player> players;
    
    private TuioClient tuioClient;
    private TouchHandler touchHandler;
    private TouchOverlay touchOverlay;
    private UsableActorContainer overlay;
    
    /**
     * Hidden constructor.
     */
    private GameManager()
    {
        this.tuioClient = new TuioClient();
        
        
        this.touchHandler = new TouchHandler();
        this.tuioClient.addTuioListener(touchHandler);
        this.tuioClient.connect();
        
    }

    /**
     * Returns the instance.
     * @return 
     */
    public static GameManager getInstance()
    {
        return GameManager.instance;
    }
    
    /**
     * Returns the touchoverlay
     * @return 
     */
    
    public TouchOverlay getTouchOverlay()
    {
        return this.touchOverlay;
    }
    
    /**
     * Initialize.
     * @param container
     * @param game
     * @throws SlickException 
     */
    public void init( GameContainer container, StateBasedGame game ) throws SlickException
    {
        // Initialize maps.
        this.maps = new ArrayList<Map>();

        // Level 1.
        this.maps.add( new Map( "../Resources/Maps/Level1.tmx" ) );
        
        this.map = this.maps.get( 0 ); // @TODO: Fugly solution.
        
        this.maps.get( 0 ).init( container, game );
    
        this.touchOverlay = new TouchOverlay(container);    
        this.overlay = new UsableActorContainer(container);
        this.overlay.init( container, game );
        
    }
    
    /**
     * Starts a new game
     * @param numberOfPlayers 
     */
    public void startGame( int numberOfPlayers, Mode mode ) throws SlickException
    {
        // Initialize players.
        this.players = new ArrayList<Player>();
                        
        for( Integer i = 0; i < numberOfPlayers; i++ )
        {
            Player player;
            
            switch( mode )
            {
                case TOUCH:
                    player = new TouchPlayer();
                    break;
                    
                case MOUSE:
                default:
                    player = new MousePlayer();
                    break;
            }
            
            this.addPlayer( player );
        } 

        this.overlay.startGame();
    }
    /**
     * Update.
     * @param container
     * @param game
     * @param delta
     * @throws SlickException 
     */
    public void update( GameContainer container, StateBasedGame game, int delta ) throws SlickException
    {
        // Update map.
        this.map.update( container, game, delta );
        this.overlay.update( container, game, delta );
        
        // Update players.
        Input input = container.getInput();
        
        for( Player player : this.getPlayers() )
        {
            if (player instanceof TangiblePlayer){
                /*
                TangiblePlayer currentPlayer = (TangiblePlayer) player;
                if(currentPlayer.locationTelephone != null && currentPlayer.hasTelephoneOnTable){
                    System.out.println("First location: " + (int)currentPlayer.locationTelephone.getX() + " second location: " + (int)currentPlayer.locationTelephone.getY());
                    this.map.setActingPosition((int)currentPlayer.locationTelephone.getX(), (int)currentPlayer.locationTelephone.getY(), currentPlayer.getPlayerID());
                }
                 */
            }
            else if (player instanceof MousePlayer){
                //System.out.println("GameManager.update: " + " updated mouseplayer");
                
                if(input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)){
                    MousePlayer mousePlayer = (MousePlayer) player;
                    if(mousePlayer.isDraggingObject()){
                        player.getObject().setPosition(this.map.fromPositionInPixels(new Point2D.Double(input.getMouseX(), input.getMouseY())));
                    }
                    else{   
                        Point2D pixelPoint = new Point2D.Double(input.getMouseX(), input.getMouseY());
                        for (int x = 0 ; x < this.map.getCookies().size(); x++){
                            UsableActor actor = this.map.getCookies().get(x);
                            checkIfTouched(actor, player, pixelPoint);
                        }
                        for(int x = 0 ; x < this.map.getWhistles().size(); x++){
                            UsableActor actor = this.map.getWhistles().get(x);
                            checkIfTouched(actor,player,pixelPoint);
                        }
                    }
                }
                else{
                    MousePlayer mousePlayer = ( MousePlayer ) player;
                    mousePlayer.setIsDraggingObject( false );
                }
            }
            else if (player instanceof TouchPlayer){
                TouchPlayer touchPlayer = (TouchPlayer) player;
                if(touchPlayer.hasFingerOnTable()){
                    //@TODO: make sure to select the right object when dragging
                    System.out.println("GameManager.update: player is now dragging this object");
                    Point2D.Double touchPoint = touchPlayer.getFingerLocation();
                    player.getObject().setPosition( this.map.fromPositionInPixels(touchPoint));
                }
                else{

                }

                updateForMousePlayer(player, input);
            }
            else if (player instanceof TouchPlayer){
                updateForTouchPlayer(player);
            }
            else{
                System.out.println("Player not supported");
            }
        }

    }
    
    /**
     * Returns the touchHandler
     * @return 
     */
    public TouchHandler getTouchHandler(){
        return this.touchHandler;
    }
    /**
     * Render.
     * @param container
     * @param game
     * @param g
     * @throws SlickException 
     */
    public void render( GameContainer container, StateBasedGame game, Graphics g ) throws SlickException
    {
        this.map.render( container, game, g );
        
        this.touchOverlay.render(container, g );
                this.overlay.render(container, g);

    }
    
    /**
     * Returns the current map.
     * @return 
     */
    public Map getMap()
    {
        return this.map;
    }

    /**
     * Return a list of players.
     * @return 
     */
    public List<Player> getPlayers()
    {
        return this.players;
    }
    
    /**
     * Returns a player by id.
     * @param id
     * @return 
     */
    public Player getPlayer( Integer id )
    {
        for( Player player : this.players )
        {
            if( id == player.getId() )
            {
                return player;
            }
        }
        
        return null;
    }
    
    /**
     * Add a player.
     * @param player 
     */
    public void addPlayer( Player player )
    {
        this.players.add( player );
    }
    
    /**
     * Remove a player.
     * @param player 
     */
    public void removePlayer( Player player )
    {
        this.players.remove( player );
    }
        
    /**
     * Return the number of players.
     * @return 
     */
    public Integer getNumberOfPlayers()
    {
        return this.players.size();
    }
    
    /**
     * Returns true if the player limit has been reached, false otherwise.
     * @return 
     */
    public Boolean hasReachedPlayerLimit()
    {
        return this.getNumberOfPlayers() == GameManager.MAXIMUM_NUMBER_OF_PLAYERS;
    }

    /**
     * Returns whether the given point in pixels touches the given actor. It only works when the owner of the actor is equal to the given player. @TODO: remove this dumb constraint
     * @param actor
     * @param player
     * @param pixelPoint
     * @return 
     */
    private boolean checkIfTouched(UsableActor actor, Player player, Point2D pixelPoint) {
        int pixelX = (int) pixelPoint.getX();
        int pixelY = (int) pixelPoint.getY();

        if (actor.getOwner().equals(player)){
            Point2D.Double positionInPixels = this.map.toPositionInPixels(actor.getX(), actor.getY());
            double actorPixelX = positionInPixels.getX();
            double actorPixelY = positionInPixels.getY();

            int actorWidth = actor.getWidth();
            int actorHeight = actor.getHeight();
            if (( pixelX >= actorPixelX && pixelX <= actorPixelX + actorWidth) && ( pixelY >= actorPixelY && pixelY <= actorPixelY + actorHeight) ){
                return true;
            }
        }
        return false;
    }

    private List<UsableActor> getAllUsableObjects() {
        List<UsableActor> combinedList = new ArrayList<UsableActor>();
        combinedList.addAll(this.map.getCookies());
        combinedList.addAll(this.map.getWhistles());
        return combinedList;
    }

    /**
     * Checks the input for the mouse player
     * @param player
     * @param container 
     */
    private void updateForMousePlayer(Player player, Input input) {
        
        //When the mouse button is down, start dragging objects
        if(input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)){
            MousePlayer mousePlayer = (MousePlayer) player;
            //When the player is already dragging an object, move this object
            if(mousePlayer.isDraggingObject()){
                // @TODO: Roland
                player.getObject().setPosition(this.map.fromPositionInPixels(new Point2D.Double(input.getMouseX(), input.getMouseY())));
            }
            else{   
                Point2D pixelPoint = new Point2D.Double(input.getMouseX(), input.getMouseY());

                //Check if the mouse is touching an object
                List<UsableActor> combinedList = getAllUsableObjects();
                for (int x = 0 ; x < combinedList.size(); x++){
                    UsableActor actor = combinedList.get(x);
                    if(checkIfTouched(actor,player,pixelPoint))
                    {
                        ((MousePlayer)player).setIsDraggingObject(true);
                        player.setObject(actor);
                    }
                }
            }
        }
        else{
            //Make sure that when there is no mouse press, this player is not dragging
            MousePlayer mousePlayer = ( MousePlayer ) player;
            if(mousePlayer.isDraggingObject())
            {
                mousePlayer.getObject().use();
            }
            mousePlayer.setIsDraggingObject( false );
            
        }
    }

    private void updateForTouchPlayer(Player player) {
        if(this.touchHandler.getTuioCursors().size() > 0){
            TouchPlayer touchPlayer = (TouchPlayer) player;
            //Check if the player is already dragging
            if(touchPlayer.hasFingerOnTable()){
                //Select the blob that the player is using
                for(int y = 0; y < this.touchHandler.getTuioCursors().size(); y++){
                    if (this.touchHandler.getTuioCursors().get(y).getCursorID() == touchPlayer.getAssignedBlobID()){
                        //Move the object of the player to this position
                        Point2D.Double pixelPoint = new Point2D.Double(this.touchHandler.getTuioCursors().get(y).getX()*Game.WIDTH, this.touchHandler.getTuioCursors().get(y).getY()*Game.HEIGHT);
                        player.getObject().setPosition( this.map.fromPositionInPixels(pixelPoint));
                    }
                }
            }
            else{ 
                //Check if there is a touchpoint that touches an object
                for(int y = 0; y < this.touchHandler.getTuioCursors().size(); y++){
                    Point2D pixelPoint = new Point2D.Double(this.touchHandler.getTuioCursors().get(y).getX()*Game.WIDTH, this.touchHandler.getTuioCursors().get(y).getY()*Game.HEIGHT);
                    List<UsableActor> combinedList = getAllUsableObjects();
                    //Check if an object is touched
                    for ( int x = 0 ; x < combinedList.size(); x++){
                        UsableActor actor = combinedList.get(x);
                        if(checkIfTouched(actor,player,pixelPoint))
                        {
                            ((TouchPlayer)player).setHasFingerOnTable(true);
                            ((TouchPlayer)player).setAssignedBlobID(this.touchHandler.getTuioCursors().get(y).getCursorID());
                            player.setObject(actor);
                        }
                    }
                }
            }   
        }
        else{
            //reset all players
            TouchPlayer touchPlayer = ( TouchPlayer ) player;
            //touchPlayer.getObject().use();
            touchPlayer.setHasFingerOnTable( false );

        }
    }
}