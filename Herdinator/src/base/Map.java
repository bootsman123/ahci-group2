package base;

import actors.Cookie;
import actors.Dog;
import actors.LoveSheep;
import actors.Sheep;
import actors.Whistle;
import actors.Wolf;
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
import util.Pair;

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
    private int mapWidth;
    private int mapHeight;
    
    // Coordinates of collision tiles.
    private java.util.Map<Pair<Integer, Integer>, Boolean> collisions;
    
    // Coordinates of goal tiles.
    private java.util.Map<Pair<Integer, Integer>, Boolean> goals;
    
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
       this.mapWidth = this.map.getWidth() * this.map.getTileWidth();
       this.mapHeight = this.map.getHeight() * this.map.getTileHeight();

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
        this.collisions = new HashMap<Pair<Integer, Integer>, Boolean>();
        this.goals = new HashMap<Pair<Integer, Integer>, Boolean>();
        
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
                    this.sheeps.add( new Sheep( this.toPosition( x, y ) ) );
                    continue;
                }
                
                // Check "Dog".                
                String dog = this.map.getTileProperty( tileId, "Dog", null );
                
                if( dog != null )
                {
                    this.dogs.add( new Dog( this.toPosition( x, y ) ) );
                    continue;
                    
                }
                
                // Check "Wolf".
                String wolf = this.map.getTileProperty( tileId, "Wolf", null );
                
                if( wolf != null )
                {
                    this.wolves.add( new Wolf( this.toPosition( x, y ) ) );
                    continue;
                }
                
                // Check "LoveSheep".
                String loveSheep = this.map.getTileProperty( tileId, "LoveSheep", null );
                
                if( loveSheep != null )
                {
                    this.loveSheeps.add( new LoveSheep( this.toPosition( x, y ) ) );
                    continue;
                }
            }
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
            whistle.render(g);
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
                cookie.setPosition(new Point2D.Float(x,y));
            }
        }
        for(Whistle cookie : whistles){
            if (cookie.getOwnerID() == playerID){
                cookie.setPosition(new Point2D.Float(x,y));
            }
        }
    }
    * */

    
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
