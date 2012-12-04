package base;

import actors.Dog;
import actors.LoveSheep;
import actors.Sheep;
import actors.Wolf;
import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author roland
 */
public abstract class Level
{
    protected Map map;
    
    protected List<Sheep> sheeps; 
    private Dog dog; 
    private Wolf wolf;
    private LoveSheep loveSheep;
    
    public Level() throws SlickException
    {

    }
    
    public void init( GameContainer container, StateBasedGame game ) throws SlickException
    {
        //this.map = new Map(); 
        this.sheeps = new ArrayList<Sheep>();   
    }

    public void render( GameContainer container, StateBasedGame game, Graphics g ) throws SlickException
    {
         this.map.render( container, g );
         
         for( Sheep sheep : this.getSheeps() )
         {
             sheep.render( container, g );
         }
    }

    public void update( GameContainer container, StateBasedGame game, int delta ) throws SlickException
    {
        this.map.update( container, delta );
        
        for( Sheep sheep : this.getSheeps() )
        {
            sheep.update( container, delta );
        }
         
    }
    
    
    
    
/*
    public void update(Input input, int delta) {
         if( input.isKeyDown( Input.KEY_UP ) )
        {
            for (Sheep sheep : sheeps){
                sheep.moveUp(delta); 
            }
        }
        else if( input.isKeyDown( Input.KEY_RIGHT ) )
        {
            for (Sheep sheep : sheeps){
                sheep.moveRight(delta); 
            }
        }
        else if( input.isKeyDown( Input.KEY_DOWN ) )
        {
            for (Sheep sheep : sheeps){
                sheep.moveDown(delta); 
            }
        }
        else if( input.isKeyDown( Input.KEY_LEFT ) )
        {
            for (Sheep sheep : sheeps){
                sheep.moveLeft(delta); 
            }
        }
       for (Sheep sheep : sheeps){
           sheep.update(map.getMapWidth(), map.getMapHeight());; 
       }
       
    }
     */
    
    public Map getMap()
    {
        return this.map; 
    }
    
    public List<Sheep> getSheeps()
    {
        return this.sheeps;
    }
}
