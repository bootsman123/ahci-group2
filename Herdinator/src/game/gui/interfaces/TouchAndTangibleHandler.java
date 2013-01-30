package game.gui.interfaces;

import TUIO.TuioCursor;
import TUIO.TuioListener;
import TUIO.TuioObject;
import TUIO.TuioTime;
import game.Game;
import game.base.TouchDot;
import game.global.GameManager;
import game.players.Player;
import game.players.TangiblePlayer;
import game.players.TouchPlayer;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 *
 * @author bootsman
 */
public class TouchAndTangibleHandler implements TuioListener
{
    private ArrayList<TuioCursor> currentCursors;
    
    public TouchAndTangibleHandler()
    {
        currentCursors = new ArrayList<TuioCursor>();
    }
    
    @Override
    public void addTuioObject( TuioObject object )
    {
        
    }

    @Override
    public void updateTuioObject( TuioObject object )
    {
        
        for (Player p : GameManager.getInstance().getPlayers()){
            if (p instanceof TangiblePlayer){
                TangiblePlayer player = (TangiblePlayer) p;
                if (player.getMarkId() == object.getSymbolID()){
                    System.out.println("Updating the TUIO object: " + object.getSymbolID());
                    player.setTangibleLocation(new Point2D.Double(object.getX()*Game.WIDTH, object.getY()*Game.HEIGHT));
                }
            }

        }
    }

    @Override
    public void removeTuioObject( TuioObject object )
    {
        
    }

    @Override
    public void addTuioCursor( TuioCursor cursor )
    {
        //Add the cursor to the list
        currentCursors.add(cursor);
        Point2D pixelPoint = new Point2D.Double((cursor.getX()*Game.WIDTH), (cursor.getY()*Game.HEIGHT));
        int pixelX = (int) pixelPoint.getX();
        int pixelY = (int) pixelPoint.getY();
        TouchDot dot = new TouchDot(new Point(pixelX,pixelY), cursor.getCursorID());
        //Add the cursor to the overlay
        System.out.println("Touchoverlay: " + GameManager.getInstance().getTouchOverlay() == null);
        GameManager.getInstance().getTouchOverlay().addTouchDot(dot);
    }

    @Override
    public void updateTuioCursor( TuioCursor cursor )
    {
        Point point = new Point((int)(cursor.getX()*Game.WIDTH), (int)(cursor.getY()*Game.HEIGHT));
        System.out.println("Touchoverlay: " + GameManager.getInstance().getTouchOverlay() == null);
        GameManager.getInstance().getTouchOverlay().moveTouchDot(cursor.getCursorID(), point);
        
        //Remove the old cursor with the same idea
        for (int x = 0 ; x < currentCursors.size(); x++){
            TuioCursor oldCursor = currentCursors.get(x);
            if (oldCursor.getCursorID() == cursor.getCursorID()){
                currentCursors.remove(oldCursor);
                break;
            }
        }
        
        //Add the new cursor
        currentCursors.add(cursor);
        //Change the finger location of the player
        for (Player player : GameManager.getInstance().getPlayers()){
            if (player instanceof TouchPlayer){
                TouchPlayer touchPlayer = (TouchPlayer)player;
                if (touchPlayer.hasFingerOnTable() && touchPlayer.getAssignedBlobID() == cursor.getCursorID()){
                    touchPlayer.setFingerLocation(new Point2D.Double(point.getX(), point.getY()));
                }
            }
        }
    }

    @Override
    public void removeTuioCursor( TuioCursor cursor )
    {
        //Remove the cursor from the list
        currentCursors.remove(cursor);
        //Remove the cursor from the overlay
        GameManager.getInstance().getTouchOverlay().removeTouchDot(cursor.getCursorID());
        //Make sure that the player is not dragging anymore
        for (Player player : GameManager.getInstance().getPlayers()){
            if (player instanceof TouchPlayer){
                TouchPlayer touchPlayer = (TouchPlayer)player;
                if (touchPlayer.hasFingerOnTable() && touchPlayer.getAssignedBlobID() == cursor.getCursorID()){
                    touchPlayer.getCurrentObject().use();
                    touchPlayer.setHasFingerOnTable(false);
                }
            }
        }
        
        
    }

    @Override
    public void refresh( TuioTime time )
    {
        
    }

    /**
     * Get the list of cursors. 
     * @return 
     */
    public ArrayList<TuioCursor> getTuioCursors(){
        return this.currentCursors;
    }
    
}