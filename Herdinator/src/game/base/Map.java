package game.base;

import game.actors.Cookie;
import game.actors.Dog;
import game.actors.LoveSheep;
import game.actors.Sheep;
import game.actors.Whistle;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.util.pathfinding.AStarPathFinder;
import org.newdawn.slick.util.pathfinding.Path;
import org.newdawn.slick.util.pathfinding.PathFinder;
import org.newdawn.slick.util.pathfinding.PathFindingContext;
import org.newdawn.slick.util.pathfinding.TileBasedMap;

/**
 * 
 * @author Bas Bootsma
 * @author Roland Meertens
 */
public class Map implements TileBasedMap
{    
    private static final String CONTROLS_LAYER = "Controls";
        
    // Tiled map.
    private TiledMap map;
    
    // Dimensions of the map in pixels.
    private Integer widthInPixels;
    private Integer heightInPixels;
    
    // Coordinates of collision tiles.
    private java.util.Map<Point, Boolean> collisions;
    
    // Coordinates of goal tiles.
    private java.util.Map<Point, Boolean> goals;
    
    // Lists of all the actors.    
    private List<Sheep> sheeps;
    private List<Dog> dogs; 
    private List<LoveSheep> loveSheeps;
    
    private List<Cookie> cookies;
    private List<Whistle> whistles;
    
    
    // Path finder.
    private PathFinder pathFinder;
    
    /**
     * Load a new map.
     * @param filePath
     * @throws SlickException 
     */
    public Map( String filePath ) throws SlickException
    {
        this.map = new TiledMap( filePath );        
        this.widthInPixels = this.map.getWidth() * this.map.getTileWidth();
        this.heightInPixels = this.map.getHeight() * this.map.getTileHeight();
        
        this.sheeps = new ArrayList<Sheep>();
        this.dogs = new ArrayList<Dog>();
        this.loveSheeps = new ArrayList<LoveSheep>();

        this.cookies = new ArrayList<Cookie>();
        this.whistles = new ArrayList<Whistle>();
        
    }
    
    /**
     * Initialize.
     * @param container
     * @param game
     * @throws SlickException 
     */
    public void init( GameContainer container, StateBasedGame game ) throws SlickException
    {
        // Initialize.
        this.collisions = new HashMap<Point, Boolean>();
        this.goals = new HashMap<Point, Boolean>();
        
        // Parse the controls-layer.
        int index = this.map.getLayerIndex( Map.CONTROLS_LAYER );

        if( index == -1 )
        {
            Logger.getLogger( Map.class.getName() ).log( Level.WARNING, "Controls layer unavailable." );
            return;
        }
        
        // Loop over all the tiles.
        for( int x = 0; x < this.map.getWidth(); x++ )
        {
            for( int y = 0; y < this.map.getHeight(); y++ )
            {
                int tileId = this.map.getTileId( x, y, index );
                
                // Check "Collision".
                String collisionString = this.map.getTileProperty( tileId, "Collision", null );
                
                if( collisionString != null )
                {
                    this.collisions.put( new Point( x, y ), true );
                    continue;
                }
                
                // Check "Goal".
                String goalString = this.map.getTileProperty( tileId, "Goal", null );
                
                if( goalString != null )
                {
                    this.goals.put( new Point( x, y ), true );
                    continue;
                }
                
                // Check "Sheep".                
                String sheepString = this.map.getTileProperty( tileId, "Sheep", null );
                
                if( sheepString != null )
                {
                    this.sheeps.add( new Sheep( new Point( x, y ) ) );
                    continue;
                }
                
                // Check "Dog".                
                String dogString = this.map.getTileProperty( tileId, "Dog", null );
                
                if( dogString != null )
                {
                    this.dogs.add( new Dog( new Point( x, y ) ) );
                    continue;
                }
                
                // Check "LoveSheep".
                String loveSheepString = this.map.getTileProperty( tileId, "LoveSheep", null );
                
                if( loveSheepString != null )
                {
                    this.loveSheeps.add( new LoveSheep( new Point( x, y ) ) );
                    continue;
                }
            }
        }
        
        // Initialize actors.
        this.initActors( this.sheeps );
        this.initActors( this.dogs );
        this.initActors( this.loveSheeps );
        //this.initActors( this.cookies );
        //this.initActors( this.whistles );
        
        // Initialize pathfinder.
        this.pathFinder = new AStarPathFinder( this, this.getWidthInTiles() + this.getHeightInTiles(), false );

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
        this.map.render( 0, 0 );

        // Render actors.
        this.renderActors( this.sheeps, g );
        this.renderActors( this.dogs, g );
        this.renderActors( this.loveSheeps, g );
        this.renderActors( this.cookies, g );
        this.renderActors( this.whistles, g );
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
        if( container.isPaused() )
        {
            return;
        }
        
        // Update actors.
        this.updateActors( this.sheeps, delta );
        this.updateActors( this.dogs, delta );
        this.updateActors( this.loveSheeps, delta );
        this.updateActors( this.cookies, delta );
        this.updateActors( this.whistles, delta );
    }
    
    /**
     * Initialize a list of actors.
     * @param actors 
     */
    private void initActors( List<? extends Actor> actors )
    {
        for( Actor actor : actors )
        {
           actor.init();
        }
    }
    
    /**
     * Render a list of renderables.
     * @param renderables
     * @param g 
     */
    private void renderActors( List<? extends Renderable> renderables, Graphics g )
    {
        for( Renderable r : renderables )
        {
            r.render( g );
        }
    }
   
    /**
     * Update a list of actors.
     * @param actors
     * @param delta 
     */
    private void updateActors( List<? extends Actor> actors, int delta )
    {
        for( Actor actor : actors )
        {
            actor.update( delta );
        }
    }
    
    /**
     * Add a new object to the map.
     * @param object 
     */
    public void addUsableActor( UsableActor object )
    {
        if( object != null )
        {
            object.setIsOnMap( Boolean.TRUE );
            
            if( object instanceof Cookie )
            {
                Cookie cookie = (Cookie)object;

                // Add listeners.
                for( LoveSheep loveSheep : this.loveSheeps )
                {
                    cookie.addUseListener( loveSheep );
                }

                this.cookies.add( cookie );
            }
            else if( object instanceof Whistle )
            {
                Whistle whistle = (Whistle)object;

                // Add listeners.
                for( Dog dog : this.dogs )
                {
                    whistle.addUseListener( dog );
                }

                this.whistles.add( whistle );
            }
        }
    }

    /**
     * Remove an object from the map.
     * @param oldObject 
     */
    
    public void removeUsableActor( UsableActor object )
    {
        if( object != null )
        {
            object.setIsOnMap( Boolean.FALSE );
        
            if( object instanceof Cookie )
            {
                Cookie cookie = (Cookie)object;
                
                // Remove listeners.
                for( LoveSheep loveSheep : this.loveSheeps )
                {
                    cookie.removeUseListener( loveSheep );
                }
                
                this.cookies.remove( (Cookie)object );
            }
            else if( object instanceof Whistle )
            {
                Whistle whistle = (Whistle)object;

                // Remove listeners.
                for( Dog dog : this.dogs )
                {
                    whistle.removeUseListener( dog );
                }
                
                this.whistles.remove( (Whistle)object );   
            }
        }
    }
    
    /**
     * Convert from position in pixels to position in tiles
     * @param position
     * @return 
     */
    public Point fromPositionInPixels( Point2D position )
    {
        return new Point( (int)( position.getX() / this.map.getTileWidth() ),
                          (int)( position.getY() / this.map.getTileHeight() ) );
    }
    
    /**
     * Convert from a position tiles to a center position in pixels.
     * @param x
     * @param y
     * @return 
     */
    public Point2D.Double toPositionInPixels( Integer x, Integer y )
    {
        Point2D.Double position =  new Point2D.Double( x * this.map.getTileWidth(),
                                                       y * this.map.getTileHeight() );
        position.x += 0.5 * this.getTileWidth();
        position.y += 0.5 * this.getTileHeight();
        
        return position;
    }

    /**
     * Returns true if the position is within the map.
     * @param position
     * @return 
     */
    private Boolean isValidTile( Point position )
    {
        return ( position.x >= 0 && position.x < this.map.getWidth() &&
                 position.y >= 0 && position.y < this.map.getHeight() );
    }
    
    /**
     * Returns true if the given position is of a collision tile.
     * @param position
     * @return 
     */
    public boolean isCollisionTile( Point position )
    {
        if( !this.isValidTile( position ) )
        {
            // Position outside the map are counted as collisions.
            return true;
        }
        
        return this.collisions.containsKey( position );
    }
    
    /**
     * Returns true if the current position is of a goal tile.
     * @param position
     * @return 
     */
    public boolean isGoalTile( Point position )
    {
       if( !this.isValidTile( position ) )
       {
           return false;
       }
       
       return this.goals.containsKey( position );
    }
    
    /**
     * Returns true if the given position collides with either a collision tile or with an actor.
     * @param position
     * @return 
     */
    public boolean isBlocked( Point position )
    {
        if( this.isCollisionTile( position ) )
        {
            return true;
        }
        
        // Check collisions with actors.
        return ( this.isBlockedByActors( this.sheeps, position ) ||
                 this.isBlockedByActors( this.dogs, position ) ||
                 this.isBlockedByActors( this.loveSheeps, position ) );
    }
    
    /**
     * Returns true 
     * @param actors
     * @param position
     * @return 
     */
    private boolean isBlockedByActors( List<? extends Actor> actors, Point position )
    {
        for( Actor actor : actors )
        {
            if( actor.getPosition().equals( position ) )
            {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Returns the width of a tile.
     * @return 
     */
    public Integer getTileWidth()
    {
        return this.map.getTileWidth();
    }
    
    /**
     * Returns the height of a tile.
     * @return 
     */
    public Integer getTileHeight()
    {
        return this.map.getTileHeight();
    }
    
    /**
     * Returns the width of the map in pixels.
     * @return 
     */
    public Integer getWidthInPixels()
    {
        return this.widthInPixels; 
    }
    
    /**
     * Returns the height of the map in pixels.
     * @return 
     */
    public Integer getHeightInPixels()
    {
        return this.heightInPixels; 
    }
    
    /**
     * Returns the width of the map in tiles.
     * @return 
     */
    @Override
    public int getWidthInTiles()
    {
        return this.map.getWidth();
    }

    /**
     * Returns the height of the map in tiles.
     * @return 
     */
    @Override
    public int getHeightInTiles()
    {
        return this.map.getHeight();
    }

    @Override
    public void pathFinderVisited( int x, int y )
    {
    }

    /**
     * Returns true when the position on (tx, ty) is blocked given a pathfinding context.
     * @param context
     * @param tx
     * @param ty
     * @return 
     */
    @Override
    public boolean blocked( PathFindingContext context, int tx, int ty )
    {
        return this.isBlocked( new Point( tx, ty ) );
    }

    /**
     * Returns the cost of position (tx, ty) given a pathfinding context.
     * @param context
     * @param tx
     * @param ty
     * @return 
     */
    @Override
    public float getCost( PathFindingContext context, int tx, int ty )
    {
        return 1.0f;
    }
    
    /**
     * Returns the path from position p1 to position p2.
     * @param p1
     * @param p2
     * @return 
     */
    public Path pathTo( Point p1, Point p2 )
    {
        return this.pathFinder.findPath( null, p1.x, p1.y, p2.x, p2.y );
    }

    /**
     * Returns a list of all the sheeps.
     * @return 
     */
    public List<Sheep> getSheeps()
    {
        return this.sheeps;
    }
    
    /**
     * Returns a list of all the dogs.
     * @return 
     */
    public List<Dog> getDogs()
    {
        return this.dogs;
    }
    
    /**
     * Returns a list of all the love sheeps.
     * @return 
     */
    public List<LoveSheep> getLoveSheeps()
    {
        return this.loveSheeps;
    }
    
    /**
     * Returns a list of all the whistles.
     * @return 
     */
    public List<Whistle> getWhistles()
    {
        return this.whistles;
    }
    
    /**
     * Returns a list of all the cookies.
     * @return 
     */
    public List<Cookie> getCookies()
    {
        return this.cookies;
    }
}