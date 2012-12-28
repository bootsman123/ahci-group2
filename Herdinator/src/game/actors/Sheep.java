package game.actors;

import game.base.Map;
import game.base.MovableActor;
import game.global.GameManager;
import game.util.Math;
import game.util.SpriteSheetUtil;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
    
/**
 *
 * @author bootsman
 */
public class Sheep extends MovableActor
{
    private static final String SPRITE_SHEET_FILE_PATH = "../Resources/Images/sheeps_animation.png";
    private static final Integer SPRITE_SHEET_SPRITE_WIDTH = 32;
    private static final Integer SPRITE_SHEET_SPRITE_HEIGHT = 32;
    private static final Color SPRITE_SHEET_BACKGROUND_COLOR = new Color( 123, 198, 132 );
    
    private static final Double SPEED = 0.01;
    private static final Double DISTANCE_TO_GOAL = 2.0;

    private Point2D.Double positionTarget;

    /**
     * Constructor.
     * @param position
     * @throws SlickException 
     */
    public Sheep( Point2D.Double position ) throws SlickException
    {
        super( position, Sheep.SPEED );
    }
    
    @Override
    public void init()
    {
        super.init();
               
        try
        {
            // Setup animations.
            SpriteSheet spriteSheet = new SpriteSheet( Sheep.SPRITE_SHEET_FILE_PATH,
                                                       Sheep.SPRITE_SHEET_SPRITE_WIDTH,
                                                       Sheep.SPRITE_SHEET_SPRITE_HEIGHT,
                                                       Sheep.SPRITE_SHEET_BACKGROUND_COLOR );

            this.animations.put( Direction.UP, SpriteSheetUtil.getAnimation( spriteSheet, 0, 2, 3, 150 ) );
            this.animations.put( Direction.RIGHT, SpriteSheetUtil.getAnimation( spriteSheet, 0, 2, 2, 150 ) );
            this.animations.put( Direction.DOWN, SpriteSheetUtil.getAnimation( spriteSheet, 0, 2, 0, 150 ) );
            this.animations.put( Direction.LEFT, SpriteSheetUtil.getAnimation( spriteSheet, 0, 2, 1, 150 ) );
            this.setAnimation( this.animations.get( Direction.DOWN ) );
        }
        catch( SlickException e )
        {
            Logger.getLogger( Sheep.class.getName() ).log( Level.SEVERE, e.getLocalizedMessage() );
        }
        
        this.positionTarget = this.determineRandomPosition( this.getPosition() );
    }
    
    @Override
    public void update( int delta )
    {
        super.update( delta );
        
        //System.out.printf( "%f\n", Math.distanceEuclidian( this.getPosition(), this.positionTarget ) );

        // Check if the sheep is close to its goal.
        if( Math.distanceEuclidian( this.getPosition(), this.positionTarget ) < Sheep.DISTANCE_TO_GOAL )
        {
            this.positionTarget = this.determineRandomPosition( this.getPosition() );
        }
        
        this.moveTo( this.positionTarget );
    }
    
    private Point2D.Double determineRandomPosition( Point2D.Double position )
    {
        // Current map.
        Map map = GameManager.getInstance().getMap();
        
        // Get the tile for the given position.
        Integer x = (int)( position.getX() / map.getTileWidth() );
        Integer y = (int)( position.getY() / map.getTileHeight() );
                
        // Fill a list with possible positions.
        List<Point> positionsNew = new ArrayList<Point>();
        
        for( Direction direction : Direction.values() )
        {
            Integer xNew = x + direction.getVector().x;
            Integer yNew = y + direction.getVector().y;
            Point positionNew = new Point( xNew, yNew );
            
            if( !map.isCollisionTile( positionNew ) &&
                !map.isGoalTile( positionNew ) )
            {
                positionsNew.add( positionNew );
            }
        }
        
        // Check if it possible to move.
        Integer positionsNewSize = positionsNew.size();
        
        if( positionsNewSize == 0 )
        {
            return position;
        }
        
        // Select a random new position.
        int r = (int)( java.lang.Math.random() * positionsNewSize );
        Point positionNew = positionsNew.get( r );
                 
        return new Point2D.Double( positionNew.x * map.getTileWidth(), positionNew.y * map.getTileHeight() );
    }
    
    /**

     * Vlucht voor de hond of de wolf als in de buurt, of loopt naar het love sheep als die in de buurt is
     * @param delta 
     *
    public void move( int delta ){ 
        if(euclideanDistance( this.getPosition(), wolfLocation) < 100) //TODO: fix de parameters
            moveAwayFrom( delta, this.getPosition(), wolfLocation);
        else if(euclideanDistance( this.getPosition(), dogLocation) < 100) //TODO: fix de parameters
            moveAwayFrom( delta, this.getPosition(), dogLocation);
        else if(euclideanDistance( this.getPosition(), loveSheepLocation) < 100) //TODO: fix de parameters
            moveTo( this.getPosition(), loveSheepLocation, delta);
        else
            moveRandom( delta);
     }
     * */
}