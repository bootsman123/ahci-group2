package game.gui;

import TUIO.TuioCursor;
import TUIO.TuioListener;
import TUIO.TuioObject;
import TUIO.TuioTime;
import game.Game;
import game.base.TouchDot;
import game.global.GameManager;
import game.players.Player;
import game.players.TouchPlayer;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 *
 * @author bootsman
 */
public class TouchHandler implements TuioListener
{
    
    private ArrayList<TuioCursor> currentCursors ;
    
    public TouchHandler()
    {
        currentCursors = new ArrayList<TuioCursor>();
    }
    
    @Override
    public void addTuioObject( TuioObject object )
    {
        System.out.println("TouchHandler: Added tuioobject");
    }

    @Override
    public void updateTuioObject( TuioObject object )
    {
        
    }

    @Override
    public void removeTuioObject( TuioObject object )
    {
        
    }

    @Override
    public void addTuioCursor( TuioCursor cursor )
    {
        
        currentCursors.add(cursor);
        
        
        
        System.out.println("TouchHandler: Added tuiocursor on location " + cursor.getX() + " " + cursor.getY());
        Point2D pixelPoint = new Point2D.Double((cursor.getX()*Game.WIDTH), (cursor.getY()*Game.HEIGHT));
        
        int pixelX = (int) pixelPoint.getX();
        int pixelY = (int) pixelPoint.getY();
        System.out.println("TouchHandler: Added tuiocursor on location after pixelpointed" + pixelPoint.getX() + " " + pixelPoint.getY());
        TouchDot dot = new TouchDot(new Point(pixelX,pixelY), cursor.getCursorID());
        System.out.println("The touchOverlay is existant: " + GameManager.getInstance().getTouchOverlay() ==null);
        GameManager.getInstance().getTouchOverlay().addTouchDot(dot);
        
        /*
        for (UsableActor actor : GameManager.getInstance().getMap().getCookies()){
            
            int actorTileX = actor.getX();
            int actorTileY = actor.getY();
            Point2D.Double positionInPixels = GameManager.getInstance().getMap().toPositionInPixels(actorTileX, actorTileY);
            double actorPixelX = positionInPixels.getX();
            double actorPixelY = positionInPixels.getY();

            int actorWidth = actor.getWidth();
            int actorHeight = actor.getHeight();
            System.out.println("TouchHandler.addTuioCursor: pixelX: " + pixelX + " actorPixelX: " + actorPixelX + " pixelY: " + pixelY + " actorPixelY: " + actorPixelY );
            if (( pixelX >= actorPixelX && pixelX <= actorPixelX + actorWidth) && ( pixelY >= actorPixelY && pixelY <= actorPixelY + actorHeight) ){
                System.out.println("TouchHandler.addTuioCursor: Player is now dragging the object");
                for (Player player : GameManager.getInstance().getPlayers()){
                    if (player instanceof TouchPlayer){
                        TouchPlayer touchPlayer = (TouchPlayer)player;
                        touchPlayer.setHasFingerOnTable(true);
                        touchPlayer.setAssignedBlobID(cursor.getCursorID());
                        touchPlayer.setFingerLocation(new Point2D.Double(cursor.getX(), cursor.getY()));
                        touchPlayer.setObject(actor);
                    }
                }
            }

        }
       for (UsableActor actor : GameManager.getInstance().getMap().getWhistles()){

            int actorTileX = actor.getX();
            int actorTileY = actor.getY();
            Point2D.Double positionInPixels = GameManager.getInstance().getMap().toPositionInPixels(actorTileX, actorTileY);
            double actorPixelX = positionInPixels.getX();
            double actorPixelY = positionInPixels.getY();
            int actorWidth = actor.getWidth();
            int actorHeight = actor.getHeight();
            System.out.println("GameManager.update: pixelX: " + pixelX + " actorPixelX: " + actorPixelX + " pixelY: " + pixelY + " actorPixelY: " + actorPixelY );
            if (( pixelX >= actorPixelX && pixelX <= actorPixelX + actorWidth) && ( pixelY >= actorPixelY && pixelY <= actorPixelY + actorHeight) ){
                System.out.println("GameManager.update: Player is now dragging the object");
                for (Player player : GameManager.getInstance().getPlayers()){
                    if (player instanceof TouchPlayer){
                        TouchPlayer touchPlayer = (TouchPlayer)player;
                        touchPlayer.setHasFingerOnTable(true);
                        touchPlayer.setAssignedBlobID(cursor.getCursorID());
                        touchPlayer.setFingerLocation(new Point2D.Double(cursor.getX(), cursor.getY()));
                        touchPlayer.setObject(actor);
                    }
                }
            }
       }
       */
    }

    @Override
    public void updateTuioCursor( TuioCursor cursor )
    {
        Point point = new Point((int)(cursor.getX()*Game.WIDTH), (int)(cursor.getY()*Game.HEIGHT));
        System.out.println("TouchHandler: moved tuiocursor on location " + point.getX() + " " + point.getY());
        GameManager.getInstance().getTouchOverlay().moveTouchDot(cursor.getCursorID(), point);
        
        for (int x = 0 ; x < currentCursors.size(); x++){
            TuioCursor oldCursor = currentCursors.get(x);
            if (oldCursor.getCursorID() == cursor.getCursorID()){
                currentCursors.remove(oldCursor);
                break;
            }
        }
        currentCursors.add(cursor);
        for (Player player : GameManager.getInstance().getPlayers()){
            if (player instanceof TouchPlayer){
                TouchPlayer touchPlayer = (TouchPlayer)player;
                if (touchPlayer.hasFingerOnTable() && touchPlayer.getAssignedBlobID() == cursor.getCursorID()){
                    touchPlayer.setFingerLocation(new Point2D.Double(point.getX(), point.getY()));
                }
            }
        }
        //System.out.println("Updated cursor: " + );
    }

    @Override
    public void removeTuioCursor( TuioCursor cursor )
    {
        System.out.println("Removed TuioCursor: " + + cursor.getCursorID());
        currentCursors.remove(cursor);
        GameManager.getInstance().getTouchOverlay().removeTouchDot(cursor.getCursorID());
        for (Player player : GameManager.getInstance().getPlayers()){
            if (player instanceof TouchPlayer){
                TouchPlayer touchPlayer = (TouchPlayer)player;
                if (touchPlayer.hasFingerOnTable() && touchPlayer.getAssignedBlobID() == cursor.getCursorID()){
                    touchPlayer.setHasFingerOnTable(false);
                }
            }
        }
        
        
    }

    @Override
    public void refresh( TuioTime time )
    {
        
    }

    public ArrayList<TuioCursor> getTuioCursors(){
        return this.currentCursors;
    }
    
}