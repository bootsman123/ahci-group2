package game.gui;

import TUIO.TuioCursor;
import TUIO.TuioListener;
import TUIO.TuioObject;
import TUIO.TuioTime;
import game.base.TouchDot;
import game.global.GameManager;
import java.awt.Point;
import game.Game;
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
        
        
        
        
        Point2D pixelPoint = new Point2D.Double(cursor.getX(), cursor.getY());
        
        int pixelX = (int) (pixelPoint.getX()*Game.WIDTH);
        int pixelY = (int) (pixelPoint.getY()*Game.HEIGHT);

        System.out.println("TouchHandler.addTuioCursor: pixelx " + pixelX + " pixely " + pixelY );
        TouchDot dot = new TouchDot(new Point(pixelX,pixelY), cursor.getCursorID());
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
            System.out.println("TouchHandler.update: pixelX: " + pixelX + " actorPixelX: " + actorPixelX + " pixelY: " + pixelY + " actorPixelY: " + actorPixelY );
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
       * */
    }

    @Override
    public void updateTuioCursor( TuioCursor cursor )
    {
        
        
        Point2D pixelPoint = new Point2D.Double(cursor.getX(), cursor.getY());
        
        int pixelX = (int) (pixelPoint.getX()*Game.WIDTH);
        int pixelY = (int) (pixelPoint.getY()*Game.HEIGHT);

        System.out.println("TouchHandler.updateTuioCursor: pixelx " + pixelX + " pixely " + pixelY );
        TouchDot dot = new TouchDot(new Point(pixelX,pixelY), cursor.getCursorID());
        
        
        GameManager.getInstance().getTouchOverlay().moveTouchDot(cursor.getCursorID(), new Point(pixelX, pixelY));
        /*
        for (TuioCursor oldCursor : currentCursors ){
            if (oldCursor.getCursorID() == cursor.getCursorID()){
                currentCursors.remove(oldCursor);
            }
        }
        currentCursors.add(cursor);
        for (Player player : GameManager.getInstance().getPlayers()){
            if (player instanceof TouchPlayer){
                TouchPlayer touchPlayer = (TouchPlayer)player;
                if (touchPlayer.hasFingerOnTable() && touchPlayer.getAssignedBlobID() == cursor.getCursorID()){
                    touchPlayer.setFingerLocation(new Point2D.Double(cursor.getX(), cursor.getY()));
                }
            }
        }
        */
        //System.out.println("Updated cursor: " + );
    }

    @Override
    public void removeTuioCursor( TuioCursor cursor )
    {
        System.out.println("Removed TuioCursor: " + + cursor.getCursorID());
        currentCursors.remove(cursor);
        
        GameManager.getInstance().getTouchOverlay().removeTouchDot(cursor.getCursorID());
        /*
        for (Player player : GameManager.getInstance().getPlayers()){
            if (player instanceof TouchPlayer){
                TouchPlayer touchPlayer = (TouchPlayer)player;
                if (touchPlayer.hasFingerOnTable() && touchPlayer.getAssignedBlobID() == cursor.getCursorID()){
                    touchPlayer.setHasFingerOnTable(false);
                }
            }
        }
        * */
        
        
    }

    @Override
    public void refresh( TuioTime time )
    {
        
    }

    public ArrayList<TuioCursor> getTuioCursors(){
        return this.currentCursors;
    }
    
}