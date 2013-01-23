package game.global;

import TUIO.TuioClient;
import game.Game;
import game.base.Map;
import game.base.UsableActor;
import game.gui.TouchHandler;
import game.gui.interfaces.TouchOverlay;
import game.gui.interfaces.UsableActorContainer;
import game.players.MobilePhonePlayer;
import game.players.MousePlayer;
import game.players.Player;
import game.players.TouchPlayer;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.Color;
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
    private Integer numberOfPlayers;
    
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
    public void startGame( int numberOfPlayers ) throws SlickException
    {
        this.numberOfPlayers = numberOfPlayers;
        // Initialize players.
        this.players = new ArrayList<Player>();
        
        // Add colours for the players
        Color[] colorsForPlayers = new Color[4];
        colorsForPlayers[0] = Color.blue;
        colorsForPlayers[1] = Color.pink;
        colorsForPlayers[2] = Color.red;
        colorsForPlayers[3] = Color.green;
        
        
        //Add all players
        // @TODO: Need to find a place for this. 
        System.out.println("GameManager.startGame: numberOfPlayers; " + numberOfPlayers);
        
        for( Integer i = 0; i < numberOfPlayers; i++ )
        {
            this.players.add( new TouchPlayer( i, colorsForPlayers[i] ) );
            //this.map.addUsableActor( this.players.get( i ).getObject() );
        } 
        //this.players.add(new MousePlayer(numberOfPlayers,colorsForPlayers[numberOfPlayers]));
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
            if (player instanceof MobilePhonePlayer){
                /*
                MobilePhonePlayer currentPlayer = (MobilePhonePlayer) player;
                if(currentPlayer.locationTelephone != null && currentPlayer.hasTelephoneOnTable){
                    System.out.println("First location: " + (int)currentPlayer.locationTelephone.getX() + " second location: " + (int)currentPlayer.locationTelephone.getY());
                    this.map.setActingPosition((int)currentPlayer.locationTelephone.getX(), (int)currentPlayer.locationTelephone.getY(), currentPlayer.getPlayerID());
                }
                 */
            }
            else if (player instanceof MousePlayer){
                //System.out.println("GameManager.update: " + " updated mouseplayer");
                
                if(input.isMouseButtonDown(input.MOUSE_LEFT_BUTTON)){
                    MousePlayer mousePlayer = (MousePlayer) player;
                    if(mousePlayer.isDraggingObject()){
                        player.moveObject( this.map.fromPositionInPixels(new Point2D.Double(input.getMouseX(), input.getMouseY())));
                    }
                    else{   
                        Point2D pixelPoint = new Point2D.Double(input.getMouseX(), input.getMouseY());
                        for (int x = 0 ; x < this.map.getCookies().size(); x++){
                            UsableActor actor = this.map.getCookies().get(x);
                            if(checkIfTouched(actor,player,pixelPoint))
                            {
                                ((MousePlayer)player).setIsDraggingObject(true);
                                player.setObject(actor);
                            }
                        }
                        for(int x = 0 ; x < this.map.getWhistles().size(); x++){
                            UsableActor actor = this.map.getWhistles().get(x);
                            if(checkIfTouched(actor,player,pixelPoint))
                            {
                                ((MousePlayer)player).setIsDraggingObject(true);
                                player.setObject(actor);
                            }
                        }
                    }
                }
                else{
                    MousePlayer mousePlayer = ( MousePlayer ) player;
                    mousePlayer.setIsDraggingObject( false );
                }
            }
            else if (player instanceof TouchPlayer){
                if(this.touchHandler.getTuioCursors().size() > 0){
                    TouchPlayer touchPlayer = (TouchPlayer) player;
                    if(touchPlayer.hasFingerOnTable()){
                     //   System.out.println("We are dragging!!!!");
                        for(int y = 0; y < this.touchHandler.getTuioCursors().size(); y++){
                            if (this.touchHandler.getTuioCursors().get(y).getCursorID() == touchPlayer.getAssignedBlobID()){
                                Point2D.Double pixelPoint = new Point2D.Double(this.touchHandler.getTuioCursors().get(y).getX()*Game.WIDTH, this.touchHandler.getTuioCursors().get(y).getY()*Game.HEIGHT);
                                player.moveObject( this.map.fromPositionInPixels(pixelPoint));
                            }
                        }
                        
                    }
                    else{   
                        
                        for(int y = 0; y < this.touchHandler.getTuioCursors().size(); y++){
                            Point2D pixelPoint = new Point2D.Double(this.touchHandler.getTuioCursors().get(y).getX()*Game.WIDTH, this.touchHandler.getTuioCursors().get(y).getY()*Game.HEIGHT);
                            //System.out.println("Touchpoint x: " + this.touchHandler.getTuioCursors().get(y).getX() + " and y: " + this.touchHandler.getTuioCursors().get(y).getY());
                            for (int x = 0 ; x < this.map.getCookies().size(); x++){
                                
                                UsableActor actor = this.map.getCookies().get(x);
                                if(checkIfTouched(actor,player,pixelPoint))
                                {
                                    ((TouchPlayer)player).setHasFingerOnTable(true);
                                    ((TouchPlayer)player).setAssignedBlobID(this.touchHandler.getTuioCursors().get(y).getCursorID());
                                    
                                    player.setObject(actor);
                                    System.out.println("GameManager.update: We are starting with dragging!");
                                }
                            }
                            for(int x = 0 ; x < this.map.getWhistles().size(); x++){
                                UsableActor actor = this.map.getWhistles().get(x);
                                if(checkIfTouched(actor,player,pixelPoint))
                                {
                                    ((TouchPlayer)player).setHasFingerOnTable(true);
                                    ((TouchPlayer)player).setAssignedBlobID(this.touchHandler.getTuioCursors().get(y).getCursorID());
                                    
                                    player.setObject(actor);
                                    System.out.println("GameManager.update: We are starting with dragging!");
                                }
                            }
                        }
                    }   
                }
                else{
                    //reset all players
                    TouchPlayer touchPlayer = ( TouchPlayer ) player;
                    touchPlayer.setHasFingerOnTable( false );

                }
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
     * Set the number of players playing the game.
     * @param numberOfPlayers 
     */
    public void setNumberOfPlayers( Integer numberOfPlayers )
    {
        System.out.println("GameManager.setNumberOfPlayers: amount of players: " + numberOfPlayers);
        this.numberOfPlayers = numberOfPlayers;
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
     * Returns the list of players.
     * @return 
     */
    public List<Player> getPlayers()
    {
        return this.players;
    }

    private boolean checkIfTouched(UsableActor actor, Player player, Point2D pixelPoint) {
        int pixelX = (int) pixelPoint.getX();
        int pixelY = (int) pixelPoint.getY();

        if (actor.getOwner().equals(player)){
            int actorTileX = actor.getX();
            int actorTileY = actor.getY();
            Point2D.Double positionInPixels = map.toPositionInPixels(actorTileX, actorTileY);
            double actorPixelX = positionInPixels.getX();
            double actorPixelY = positionInPixels.getY();

            int actorWidth = actor.getWidth();
            int actorHeight = actor.getHeight();
           // System.out.println("GameManager.checkIfTouched: pixelX: " + pixelX + " actorPixelX: " + actorPixelX + " pixelY: " + pixelY + " actorPixelY: " + actorPixelY + " actor width: " + actorWidth + " actor height: " + actorHeight);
            if (( pixelX >= actorPixelX && pixelX <= actorPixelX + actorWidth) && ( pixelY >= actorPixelY && pixelY <= actorPixelY + actorHeight) ){
                return true;
            }
        }
        return false;
    }
}