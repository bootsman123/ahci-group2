package base;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

/**
 *
 * @author roland
 */
public class Map
{
    public static final String COLLISION_LAYER = "Collisions";
    
    private TiledMap map;
    private int mapWidth;
    private int mapHeight;
    
    public Map( String filePath ) throws SlickException
    {
       this.map = new TiledMap( filePath );
       this.mapWidth = this.map.getWidth() * this.map.getTileWidth();
       this.mapHeight = this.map.getHeight() * this.map.getTileHeight();
    }
    
    public void render( GameContainer container, Graphics g ) throws SlickException
    {
        this.map.render( 0, 0 );   
    }
    
    public void update( GameContainer container, int delta ) throws SlickException
    {
        //@TODO: Validate whether map needs an update function or not.
    }

    public boolean doesCollide( int x, int y )
    {
        int index = this.map.getLayerIndex( Map.COLLISION_LAYER );
        
        // Check if there is a collision layer.
        if( index == -1 )
        {
            return false;
        }
        
        int tileId = this.map.getTileId( x, y, index );
        
        //@TODO: Update further collision detection.
        System.out.printf( "TileId( %d, %d, %d ): %d", x, y, index, tileId ); 
        
        return false;
    }
    
    public int getMapWidth()
    {
        return this.mapWidth; 
    }
    
    public int getMapHeight()
    {
        return this.mapHeight; 
    }
}
