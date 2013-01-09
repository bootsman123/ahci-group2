package game.global;

import TUIO.TuioClient;
import game.base.Map;
import game.base.UsableActor;
import game.gui.MobilePhoneHandler;
import game.players.MobilePhonePlayer;
import game.players.MousePlayer;
import game.players.Player;
import game.players.TouchPlayer;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.pathfinding.AStarPathFinder;
import org.newdawn.slick.util.pathfinding.Path;
import org.newdawn.slick.util.pathfinding.PathFinder;

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
    private Integer numberOfPlayers;
    private List<Player> players;
    
    private TuioClient tuioClient;
    private MobilePhoneHandler mobilePhoneHandler;
    
    /**
     * Hidden constructor.
     */
    private GameManager()
    {
        this.tuioClient = new TuioClient();
        this.mobilePhoneHandler = new MobilePhoneHandler();
        
        this.tuioClient.addTuioListener( this.mobilePhoneHandler );
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
                
        // Initialize players.
        this.players = new ArrayList<Player>();
        
        for( Integer i = 0; i < this.numberOfPlayers; i++ )
        {
            this.players.add( new MousePlayer( i, Color.blue ) );
            this.map.addUsableActor( this.players.get( i ).getObject() );
        }
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
                if(input.isMouseButtonDown(input.MOUSE_LEFT_BUTTON)){
                    MousePlayer mousePlayer = (MousePlayer) player;
                    if(mousePlayer.isDraggingObject()){
                        player.moveObject( this.map.fromPositionInPixels(new Point2D.Double(input.getMouseX(), input.getMouseY())));
                    }
                    else{
                        
                        Point2D tilePoint = this.map.fromPositionInPixels(new Point2D.Double(input.getMouseX(), input.getMouseY()));
                        int tileX = (int) tilePoint.getX();
                        int tileY = (int) tilePoint.getY();
                        for (UsableActor actor : this.map.getCookies()){
                            if(actor.getX()==tileX && actor.getY()==tileY){
                                mousePlayer.setIsDraggingObject(true);
                            }
                        }
                        for (UsableActor actor : this.map.getWhistles()){
                            if(actor.getX()==tileX && actor.getY()==tileY){
                                mousePlayer.setIsDraggingObject(true);
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
                System.out.println("Touch player not yet supported");
            }
            else{
                System.out.println("Player not supported");
            }
        }

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
}