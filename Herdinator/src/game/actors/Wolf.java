package game.actors;

import game.base.MovableActor;
import game.util.SpriteSheetUtil;
import java.awt.geom.Point2D;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author bootsman
 */
public class Wolf extends MovableActor
{
    private static final String SPRITE_SHEET_FILE_PATH = "../Resources/Images/wolves_animation.png";
    private static final Integer SPRITE_SHEET_SPRITE_WIDTH = 32;
    private static final Integer SPRITE_SHEET_SPRITE_HEIGHT = 32;
    private static final Color SPRITE_SHEET_BACKGROUND_COLOR = new Color( 123, 198, 132 );
    
    private static final Double SPEED = 0.1;

    /**
     * Constructor.
     * @param position
     * @throws SlickException 
     */
    public Wolf( Point2D.Double position ) throws SlickException
    {
        super( position,Wolf.SPEED );
    }

    @Override
    public void init()
    {
        super.init();
               
        try
        {
            // Setup animations.
            SpriteSheet spriteSheet = new SpriteSheet( Wolf.SPRITE_SHEET_FILE_PATH,
                                                       Wolf.SPRITE_SHEET_SPRITE_WIDTH,
                                                       Wolf.SPRITE_SHEET_SPRITE_HEIGHT,
                                                       Wolf.SPRITE_SHEET_BACKGROUND_COLOR );

            this.animations.put( Direction.UP, SpriteSheetUtil.getAnimation( spriteSheet, 0, 2, 3, 150 ) );
            this.animations.put( Direction.RIGHT, SpriteSheetUtil.getAnimation( spriteSheet, 0, 2, 2, 150 ) );
            this.animations.put( Direction.DOWN, SpriteSheetUtil.getAnimation( spriteSheet, 0, 2, 0, 150 ) );
            this.animations.put( Direction.LEFT, SpriteSheetUtil.getAnimation( spriteSheet, 0, 2, 1, 150 ) );
            this.setAnimation( this.animations.get( Direction.DOWN ) );
        }
        catch( SlickException e )
        {
            Logger.getLogger( Dog.class.getName() ).log( Level.SEVERE, e.getLocalizedMessage() );
        }
    }  

    /**
     * Returns the location of the closest sheep
     * @return
     */
    /*
    private Point2D.Double locationClosestSheep(){
        Point2D.Double locationClosestSheep = new Point2D.Double(0,0); //to make Netbeans happy
        double distanceToNearestSheep = Math.pow(10, 9); //should start higher than anything
        for(Point2D.Double sheepLocation:sheepLocations){
            if(euclideanDistance(sheepLocation, this.getPosition()) < distanceToNearestSheep){
                locationClosestSheep = sheepLocation;
                distanceToNearestSheep = euclideanDistance(sheepLocation, this.getPosition());        
            }     
        }
        return locationClosestSheep;

    }
    * */
    
}

    


