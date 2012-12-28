package game.base;

import game.actors.Cookie;
import game.actors.Dog;
import game.actors.LoveSheep;
import game.actors.Sheep;
import game.actors.Whistle;
import game.actors.Wolf;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

/**
 * 
 * @author Bas Bootsma
 * @author Roland Meertens
 */
public class Map
{
    private static final String CONTROLS_LAYER = "Controls";
    private static final String FARM_AMBIANCE_SOUND_PATH = "../Resources/Sounds/farmambiance.wav";
    
    private Sound sound;
    
    // Tiled map.
    private TiledMap map;
    
    // Dimensions of the map in pixels.
    private Integer widthInPixels;
    private Integer heightInPixels;
    
    // Coordinates of collision tiles.
    private java.util.Map<Point, Boolean> collisions;
    
    // Coordinates of goal tiles.
    private java.util.Map<Point, Boolean> goals;
    
    private List<Sheep> sheeps;
    private List<Dog> dogs; 
    private List<Wolf> wolves;
    private List<LoveSheep> loveSheeps;
    
    private List<Cookie> cookies;
    private List<Whistle> whistles;
    
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
        this.wolves = new ArrayList<Wolf>();
        this.loveSheeps = new ArrayList<LoveSheep>();

        this.cookies = new ArrayList<Cookie>();
        this.whistles = new ArrayList<Whistle>();
    }
    
    public void init( GameContainer container, StateBasedGame game ) throws SlickException
    {
        // Initialize sound.
        this.sound = new Sound( Map.FARM_AMBIANCE_SOUND_PATH );
        this.sound.play();
        
        // Initialize.
        this.collisions = new HashMap<Point, Boolean>();
        this.goals = new HashMap<Point, Boolean>();
        
        // Parse the controls-layer.
        int index = this.map.getLayerIndex( Map.CONTROLS_LAYER );

        if( index == -1 )
        {
            Logger.getLogger( Map.class.getName(), "Controls layer unavailable." );
            return;
        }
        
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
                    this.collisions.put( new Point( x, y ), true );
                    continue;
                }
                
                // Check "Goal".
                String goal = this.map.getTileProperty( tileId, "Goal", null );
                
                if( goal != null )
                {
                    this.goals.put( new Point( x, y ), true );
                    continue;
                }
                
                // Check "Sheep".                
                String sheep = this.map.getTileProperty( tileId, "Sheep", null );
                
                if( sheep != null )
                {
                    this.sheeps.add( new Sheep( this.toPositionInPixels( x, y ) ) );
                    continue;
                }
                
                // Check "Dog".                
                String dog = this.map.getTileProperty( tileId, "Dog", null );
                
                if( dog != null )
                {
                    this.dogs.add( new Dog( this.toPositionInPixels( x, y ) ) );
                    continue;
                }
                
                // Check "Wolf".
                String wolf = this.map.getTileProperty( tileId, "Wolf", null );
                
                if( wolf != null )
                {
                    this.wolves.add( new Wolf( this.toPositionInPixels( x, y ) ) );
                    continue;
                }
                
                // Check "LoveSheep".
                String loveSheep = this.map.getTileProperty( tileId, "LoveSheep", null );
                
                if( loveSheep != null )
                {
                    this.loveSheeps.add( new LoveSheep( this.toPositionInPixels( x, y ) ) );
                    continue;
                }
            }
        }
        
        // Initialize sheeps.
        for( Sheep sheep : this.sheeps )
        {
            sheep.init();
        }
        
        // Initialize dogs.
        for( Dog dog : this.dogs )
        {
            dog.init();
        }
        
        // Initialize wolves.
        for( Wolf wolf : this.wolves )
        {
            wolf.init();
        }
        
        // Initialize love sheeps.
        for( LoveSheep loveSheep : this.loveSheeps )
        {
            loveSheep.init();
        }
        
        // Initialize cookies.
        for( Cookie cookie : this.cookies )
        {
            cookie.init();
        }
        
        // Initialize whistles.
        for( Whistle whistle : this.whistles )
        {
            whistle.init();
        }
    }
    
    public void render( GameContainer container, StateBasedGame game, Graphics g ) throws SlickException
    {
        this.map.render( 0, 0 );
        
        // Render sheeps.
        for( Sheep sheep : this.sheeps )
        {
            sheep.render( g );
        }
        
        // Render dogs.
        for( Dog dog : this.dogs )
        {
            dog.render( g );
        }
        
        // Render wolves.
        for( Wolf wolf : this.wolves )
        {
            wolf.render( g );
        }
        
        // Render love sheeps.
        for( LoveSheep loveSheep : this.loveSheeps )
        {
            loveSheep.render( g );
        }
        
        // Render cookies.
        for( Cookie cookie : this.cookies )
        {
            cookie.render( g );
        }
        
        // Render whistles.
        for( Whistle whistle : this.whistles )
        {
            whistle.render( g );
        }
    }
    
    public void update( GameContainer container, StateBasedGame game, int delta ) throws SlickException
    {
        if( container.isPaused() )
        {
            return;
        }
        
        // Update sheeps.
        for( Sheep sheep : this.sheeps )
        {
            sheep.update( delta );
        }
        
        // Update dogs.
        for( Dog dog : this.dogs )
        {
            dog.update( delta );
        }
        
        // Update wolves.
        for( Wolf wolf : this.wolves )
        {
            wolf.update( delta );
        }
        
        // Update love sheeps.
        for( LoveSheep loveSheep : this.loveSheeps )
        {
            loveSheep.update( delta );
        }
        
        // Update cookies.
        for( Cookie cookie : this.cookies )
        {
            cookie.update( delta );
        }
        
        // Update whistles.
        for( Whistle whistle : this.whistles )
        {
            whistle.update( delta );
        }
    }
    
    /*
    @TODO: Nice trick.
    private void render( List<? extends Renderable> renderables, Graphics g )
    {
        for( Renderable r : renderables )
        {
            r.render( g );
        }
    }
   
    private void update( List<? extends Actor> actors, int delta )
    
    
    */

    
/*
    public void addObject(MovableActor newObject) {
        if(newObject instanceof Cookie){
            this.cookies.add((Cookie)newObject);
        }
        else if(newObject instanceof Whistle){
            this.whistles.add((Whistle)newObject);
        }
    }

    public void removeObject(MovableActor oldObject) {
        if(oldObject instanceof Cookie){
            this.cookies.remove((Cookie)oldObject);
        }
        else if(oldObject instanceof Whistle){
            this.whistles.remove((Whistle)oldObject);
        }
    }
    */

    
/*
    public void setActingPosition(int x, int y, int playerID) {
        System.out.println("Updating the object to pos " + x + " " + y);
        for(Cookie cookie : cookies){
            if (cookie.getOwnerID() == playerID){
                cookie.setPosition(new Point2D.Double(x,y));
            }
        }
        for(Whistle cookie : whistles){
            if (cookie.getOwnerID() == playerID){
                cookie.setPosition(new Point2D.Double(x,y));
            }
        }
    }
    * */

    
    /**
     * Convert from position in pixels to position in tiles
     * @param position
     * @return 
     */
    private Point fromPositionInPixels( Point2D.Double position )
    {
        return new Point( (int)( position.x / this.map.getTileWidth() ),
                          (int)( position.y / this.map.getTileHeight() ) );
    }
    
    /**
     * Convert from a position tiles to a position in pixels.
     * @param x
     * @param y
     * @return 
     */
    private Point2D.Double toPositionInPixels( int x, int y )
    {
        return new Point2D.Double( x * this.map.getTileWidth(),
                                   y * this.map.getTileHeight() );
    }
    
    /**
     * Returns true if the position is within the map.
     * @param position
     * @return 
     */
    private boolean isValidTile( Point position )
    {
        return ( position.x >= 0 && position.x <= this.map.getWidth() &&
                 position.y >= 0 && position.y <= this.map.getHeight() );
    }
    
    /**
     * Returns true if the current position is of a collision tile.
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
     * Returns a list of all the wolves.
     * @return 
     */
    public List<Wolf> getWolves()
    {
        return this.wolves;
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
