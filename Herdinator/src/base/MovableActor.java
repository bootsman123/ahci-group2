package base;

import java.awt.geom.Point2D;

/**
 *
 * @author bootsman
 */
public abstract class MovableActor extends Actor implements Movable
{
    private Float speed;
    
    public MovableActor( Map map, Point2D.Float position, Float speed )
    {
        super( map, position );
        
        this.speed = speed;
    }
    
    public MovableActor( Map map, Point2D.Float position )
    {
        this( map, position, 0.0f );
    }
    
    public MovableActor( Map map )
    {
        this( map, new Point2D.Float( 0.0f, 0.0f ) );
    }
    
    public void setSpeed( Float speed )
    {
        this.speed = speed;
    }
    
    public Float getSpeed()
    {
        return this.speed;
    }
    
    @Override
    public void moveUp( int delta )
    {
        this.setY( this.getY() - delta * this.getSpeed() );
    }
    
    @Override
    public void moveRight( int delta )
    {
        this.setX( this.getX() + delta * this.getSpeed() );
    }
    
    @Override
    public void moveDown( int delta )
    {
        this.setY( this.getY() + delta * this.getSpeed() );
    }
    
    @Override
    public void moveLeft( int delta )
    {
        this.setX( this.getX() - delta * this.getSpeed() );
    }
    
    public void moveTo( int delta, Point2D.Float position, Point2D.Float target ){
        if(Math.abs(position.x-target.x) < Math.abs(position.y-target.y)){
            if(position.x > target.x)
                moveLeft( delta);
            else
                moveRight( delta );
        }
        else
             if(position.y > target.y)
                moveUp( delta);
            else
                moveDown( delta );  
    }
    
    public void moveAwayFrom( int delta, Point2D.Float position, Point2D.Float target ){
        if(Math.abs(position.x-target.x) < Math.abs(position.y-target.y)){
            if(position.x > target.x)
                moveRight( delta);
            else
                moveLeft( delta );
        }
        else
             if(position.y > target.y)
                moveDown( delta);
            else
                moveUp( delta ); 
    }
    
    public double euclideanDistance(Point2D.Float a, Point2D.Float b){
        return Math.sqrt(Math.pow((a.x - b.x),2)+Math.pow((a.x - b.x),2));
    }
}
