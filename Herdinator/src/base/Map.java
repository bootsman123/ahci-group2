package base;

import actors.Dog;
import actors.LoveSheep;
import actors.Sheep;
import actors.Wolf;
import util.Pair;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

/**
 *
 * @author roland
 */
public class Map
{
    public static final String CONTROLS_LAYER = "Controls";
    
    private TiledMap map;
    
    // Dimensions of the map in pixels.
    private int mapWidth;
    private int mapHeight;
    
    // Coordinates of collision tiles.
    private java.util.Map<Pair<Integer, Integer>, Boolean> collisions;
    
    // Coordinates of goal tiles.
    private java.util.Map<Pair<Integer, Integer>, Boolean> goals;
    
    private List<Sheep> sheeps;
    private Dog dog; 
    private Wolf wolf;
    private LoveSheep loveSheep;
    
    /**
     * Load a new map.
     * @param filePath
     * @throws SlickException 
     */
    public Map( String filePath ) throws SlickException
    {
       this.map = new TiledMap( filePath );
       this.mapWidth = this.map.getWidth() * this.map.getTileWidth();
       this.mapHeight = this.map.getHeight() * this.map.getTileHeight();

       
       this.sheeps = new ArrayList<Sheep>();

    }
    
    public void init( GameContainer container, StateBasedGame game ) throws SlickException
    {
        // Initialize.
        this.collisions = new HashMap<Pair<Integer, Integer>, Boolean>();
        this.goals = new HashMap<Pair<Integer, Integer>, Boolean>();
        
        // Parse the controls-layer.
        int index = this.map.getLayerIndex( Map.CONTROLS_LAYER );

        if( index == -1 )
        {
            return;
        }
        Point2D.Float startingPoint = new Point2D.Float(0,0);
        this.loveSheep = new LoveSheep(this, startingPoint);
        
        // Loop over all the tiles.
        for( int x = 0; x < this.map.getWidth(); x++ )
        {
            for( int y = 0; y < this.map.getHeight(); y++ )
            {
                int tileId = this.map.getTileId( x, y, index );
                
                // Check "Collision".
                String collision = this.map.getTileProperty( tileId, "Collision", null );
                
                if( collision != null )
                {
                    this.collisions.put( Pair.of( x, y ), true );
                    continue;
                }
                
                // Check "Goal".
                String goal = this.map.getTileProperty( tileId, "Goal", null );
                
                if( goal != null )
                {
                    this.goals.put( Pair.of( x, y ), true );
                    continue;
                }
                
                // Check "Sheep".                
                String sheep = this.map.getTileProperty( tileId, "Sheep", null );
                
                if( sheep != null )
                {
                    //@TODO: Ugly hack for now, see if it is possible to pass map in a different way.
                    this.sheeps.add( new Sheep( this, this.toPosition( x, y ) ) );
                    continue;
                }
                
                // Check "Dog".                
                String dog = this.map.getTileProperty( tileId, "Dog", null );
                
                if( dog != null )
                {
                    //@TODO: Implement Dog.
                    //this.dog = new Dog( this.toPosition( x, y ) );
                    continue;
                }
            }
        }
    }
    
    public void render( GameContainer container, StateBasedGame game, Graphics g ) throws SlickException
    {
        this.map.render( 0, 0 );  
        
        for( Sheep sheep : this.sheeps )
        {
            sheep.render( g );
        }
        loveSheep.render(g);
    }
    
    public void update( GameContainer container, StateBasedGame game, int delta ) throws SlickException
    {
        if( container.isPaused() )
        {
            return;
        }
                
        for( Sheep sheep : this.sheeps )
        {
            sheep.setLoveSheepLocation(loveSheep.getPosition());
            sheep.update( container, delta );
        }

        
        //this.dog.update( container, delta );
        //this.wolf.update( container, celta );
        this.loveSheep.update( container, delta );
    }
    
    private Pair<Integer, Integer> fromPosition( Point2D.Float position )
    {
        return Pair.of( (int)( position.x / this.map.getTileWidth() ),
                        (int)( position.y / this.map.getTileHeight() ) );
    }
    
    private Point2D.Float toPosition( int x, int y )
    {
        return new Point2D.Float( x * this.map.getTileWidth(),
                                  y * this.map.getTileHeight() );
    }
    
    /**
     * Returns true if the position is within the map.
     * @param x
     * @param y
     * @return 
     */
    public boolean isValidTile( Point2D.Float position )
    {
        return ( position.x >= 0 && position.x <= this.map.getWidth() &&
                 position.y >= 0 && position.y <= this.map.getHeight() );
    }
    
    /**
     * Returns true if the current position is of a collision tile.
     * @param x
     * @param y
     * @return 
     */
    public boolean isCollisionTile( Point2D.Float position )
    {
        if( !this.isValidTile( position ) )
        {
            // Position outside the map are counted as collisions.
            return true;
        }
        
        return this.collisions.containsKey( this.fromPosition( position ) );
    }
    
    /**
     * Returns true if the current position is of a goal tile.
     * @param x
     * @param y
     * @return 
     */
    public boolean isGoalTile( Point2D.Float position )
    {
      /*
       if( !this.isValidTile( position ) )
       {
           System.out.println("Not asking for a valid tile");
           return false;
       }*/
       
       return this.goals.containsKey( this.fromPosition( position ) );
    }
    
    /**
     * Returns the width of the map in pixels.
     * @return 
     */
    public int getMapWidth()
    {
        return this.mapWidth; 
    }
    
    /**
     * Returns the height of the map in pixels.
     * @return 
     */
    public int getMapHeight()
    {
        return this.mapHeight; 
    }

    /**
     * Function that does something with the x and y position of the mouse.
     * @param x
     * @param y
     */
    public void setMousePosition(int x, int y){
        this.loveSheep.setPosition(new Point2D.Float(x,y));
    }
}
