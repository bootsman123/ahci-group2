package game.global;

import TUIO.TuioCursor;
import game.Game;
import game.base.Map;
import game.base.UsableActor;
import game.gui.interfaces.TouchAndTangibleHandler;
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
        MOUSE, TOUCH, PHONE;
    }
    
    private static final Integer MAXIMUM_NUMBER_OF_PLAYERS = 4;
    
    // Singleton instance variable.
    private static final GameManager INSTANCE = new GameManager();
    
    // List of all the maps.
    private Map map;
    private List<Map> maps;
    
    // List of all the players.
    private List<Player> players;
    
    private TouchAndTangibleHandler touchHandler;
    private TouchOverlay touchOverlay;
    private UsableActorContainer overlay;
    private Mode gameMode; 
    
    /**
     * Hidden constructor.
     */
    private GameManager()
    {
        this.touchHandler = new TouchAndTangibleHandler();
        TuioManager.getInstance().addTuioListener( this.touchHandler );
        
        this.players = new ArrayList<Player>();
    }

    /**
     * Returns the instance.
     * @return 
     */
    public static GameManager getInstance()
    {
        return GameManager.INSTANCE;
    }
    
    /**
     * Returns the touch overlay.
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
     * Starts a new touch or mouse game.
     * @param numberOfPlayers
     * @param mode
     */
    public void startGame( int numberOfPlayers, Mode mode ) throws SlickException
    {
        this.gameMode = mode;
        this.players = new ArrayList<Player>();
        // Initialize players.                        
        for( Integer i = 0; i < numberOfPlayers; i++ )
        {
            Player player;
            System.out.println("GameManager, amount of players now: " + GameManager.getInstance().getNumberOfPlayers()); 
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
        
        //System.out.println("Starting to connect the tuioclient in the gamemanager");
        //this.tuioClient.connect();
        //System.out.println("Finished connecting the tuioclient in the gamemanager");
        this.overlay.startGame();
    }
    
    /**
     * Called by a tangible game.
     * @throws SlickException 
     */
    public void startGame() throws SlickException
    {
        // Initialize players.
        for( Player player : this.players )
        {
            player.init();
        }
        
        this.overlay.startGame();
    }
    
    /**
     * Starts a new interactive tangible objects game.
     * @param numberOfPlayers
     * @param mode
     *
    public void startTangibleGame( int[] playerIDs ) throws SlickException
    {
        this.gameMode = GameManager.Mode.PHONE;
        System.out.println("Total amount of players: " + playerIDs.length);
        // Initialize players.                        
        for( Integer i = 0; i < playerIDs.length; i++ )
        {
            System.out.println("Amount of players now: " + this.getPlayers().size());
            Player player = new TangiblePlayer(playerIDs[i]);
            player.setObject(new Cookie(new Point(0,0), player, true));
            this.addPlayer( player );
        } 
        
        System.out.println("Starting to connect the tuioclient in the gamemanager");
        this.tuioClient.connect();
        System.out.println("Finished connecting the tuioclient in the gamemanager");
        
        //this.overlay.startGame();
    }*/
    
    
    /**
     * End the current game.
     * @param score 
     */
    public void endGame( Integer score )
    {
        System.out.println("GameManager.endGame: Ended game, going to the score or something");
        //this.score = score;
        
        // Remove all players from the game.
        this.players.clear();
        this.map.removeAllActors();
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
                
                TangiblePlayer currentPlayer = (TangiblePlayer) player;
                if(currentPlayer.getTangibleLocation() != null && currentPlayer.isTangibleOnTable()){
                    currentPlayer.getObject().setPosition(this.map.fromPositionInPixels(new Point2D.Double((int)currentPlayer.getTangibleLocation().getX(), (int)currentPlayer.getTangibleLocation().getY())));//this.map.setActingPosition((int)currentPlayer.getTangibleLocation().getX(), (int)currentPlayer.getTangibleLocation().getY(), currentPlayer.getMarkId());
                }
            }
            else if (player instanceof MousePlayer){
                updateForMousePlayer(player,input);   
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
    public TouchAndTangibleHandler getTouchHandler()
    {
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
        
        this.touchOverlay.render( container, g );
        
        if( this.gameMode != GameManager.Mode.PHONE )
        {
            this.overlay.render(container, g);
        }
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
            if( id.equals( player.getId() ) )
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
            
            actorPixelX -= actorWidth/2;
            actorPixelY -= actorWidth/2;
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
                        player.selectObject(actor);
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
                ArrayList<TuioCursor> cursors = this.touchHandler.getTuioCursors();
                for(int y = 0; y < cursors.size(); y++){
                    Point2D pixelPoint = new Point2D.Double(cursors.get(y).getX()*Game.WIDTH, this.touchHandler.getTuioCursors().get(y).getY()*Game.HEIGHT);
                    List<UsableActor> combinedList = getAllUsableObjects();
                    //Check if an object is touched
                    for ( int x = 0 ; x < combinedList.size(); x++){
                        UsableActor actor = combinedList.get(x);
                        if(checkIfTouched(actor,player,pixelPoint))
                        {
                            ((TouchPlayer)player).setHasFingerOnTable(true);
                            ((TouchPlayer)player).setAssignedBlobID(cursors.get(y).getCursorID());
                            player.selectObject(actor);
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