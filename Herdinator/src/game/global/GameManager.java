package game.global;

import TUIO.TuioClient;
import game.base.Map;
import game.base.UsableActor;
import game.interfaces.MobilePhoneHandler;
import game.players.MobilePhonePlayer;
import game.players.MousePlayer;
import game.players.Player;
import game.players.TouchPlayer;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author roland
 */
public class GameManager
{
    // Instance variable.
    private static final GameManager instance = new GameManager();
    
    // Current map.
    private Map map;
    
    // List of all the players.
    private List<Player> players;
    
    private TuioClient tuioClient;
    private MobilePhoneHandler mobilePhoneHandler;
    
    /**
     * Hidden constructor.
     */
    private GameManager()
    {
        this.map = null;
        this.players = new ArrayList<Player>();
        
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
    
    public void update( GameContainer container, StateBasedGame game, int delta )
    {

        Input input = container.getInput();
        for (Player player : players){

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
                        player.moveObject(map.fromPositionInPixels(new Point2D.Double(input.getMouseX(), input.getMouseY())));
                    }
                    else{
                        
                        Point2D tilePoint = map.fromPositionInPixels(new Point2D.Double(input.getMouseX(), input.getMouseY()));
                        int tileX = (int) tilePoint.getX();
                        int tileY = (int) tilePoint.getY();
                        for (UsableActor actor : map.getCookies()){
                            if(actor.getX()==tileX && actor.getY()==tileY){
                                mousePlayer.setIsDraggingObject(true);
                            }
                        }
                        for (UsableActor actor : map.getWhistles()){
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
    
    

    public void setPlayers(){
        
        this.players = new ArrayList<Player>();
        try{
            this.players.add(new MousePlayer(0, Color.blue));//@TODO: add the right numbers
            this.players.add(new MousePlayer(1, Color.red));
        //    this.players.add(new MobilePhonePlayer(1));//@TODO: add the right numbers
        //    this.players.add(new MobilePhonePlayer(2));//@TODO: add the right numbers
         //   this.players.add(new MobilePhonePlayer(3));//@TODO: add the right numbers
        }
        catch(Exception e){
            System.out.println("GameManager: problem with the initialisation of players");
        }
        
        for( Player player : this.players )
        {
            this.map.addObject( player.getObject() );
        }   
    }


    public void setMap( Map map )
    {
        this.map = map;
    }

    public Map getMap()
    {
        return this.map;
    }

    public List<Player> getPlayers()
    {
        return this.players;
    }
}