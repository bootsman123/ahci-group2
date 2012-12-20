package actors;

import base.GameManager;
import base.Map;
import base.MovableActor;
import java.awt.geom.Point2D;

import java.util.Random;

import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import util.SpriteSheetUtil;





/**
 *
 * @author bootsman
 */
public class Wolf extends MovableActor
{
    private static final Float SPEED = 0.1f;




    private static final String SPRITE_SHEET_FILE_PATH = "../Resources/Images/wolves_animation.png";
    private static final int SPRITE_SHEET_SPRITE_WIDTH = 32;
    private static final int SPRITE_SHEET_SPRITE_HEIGHT = 32;
    private static final Color SPRITE_SHEET_BACKGROUND_COLOR = new Color( 123, 198, 132 );


    private static final Float GOAL_DISTANCE = 100.0f;
    private static final Float GOAL_MOVEMENT = 0.8f;
    private static final Float MAX_DISTANCE_TO_LOVE_SHEEP = 100.0f ;

    SpriteSheet spriteSheet;
    private Animation animation, animationUp, animationRight, animationDown, animationLeft;
    
   private Point2D.Float dogLocation;
   private Point2D.Float goalPosition; 
    
   private ArrayList<Point2D.Float>sheepLocations = new ArrayList<Point2D.Float>();
    
    
    public Wolf(Point2D.Float position ) throws SlickException
    {
        super(position,Wolf.SPEED );

        spriteSheet = new SpriteSheet( Wolf.SPRITE_SHEET_FILE_PATH,
                                                   Wolf.SPRITE_SHEET_SPRITE_WIDTH,
                                                   Wolf.SPRITE_SHEET_SPRITE_HEIGHT,
                                                   Wolf.SPRITE_SHEET_BACKGROUND_COLOR );

        this.animation = SpriteSheetUtil.getAnimation( spriteSheet, 0, 2, 0, 150 );



        this.animationUp = SpriteSheetUtil.getAnimation( spriteSheet, 0, 2, 3, 150 );
        this.animationRight = SpriteSheetUtil.getAnimation( spriteSheet, 0, 2, 2, 150 );
        this.animationDown = SpriteSheetUtil.getAnimation( spriteSheet, 0, 2, 0, 150 );
        this.animationLeft = SpriteSheetUtil.getAnimation( spriteSheet, 0, 2, 1, 150 );

        this.animation = this.animationDown;


       // this.goalPosition = new Point2D.Float( this.getX(), this.getY() );
//        this.determineRandomPosition();
    }

   
    private void determineRandomPosition() //why?
    {


        Random randomGenerator = new Random();

        this.goalPosition.x = this.getPosition().x + Wolf.GOAL_DISTANCE * ( randomGenerator.nextInt(3) - 1 );
        this.goalPosition.y = this.getPosition().y + Wolf.GOAL_DISTANCE * ( randomGenerator.nextInt(3) - 1 );


        //@TODO: Fugly.
        this.goalPosition.x = Math.max( 0, Math.min( this.goalPosition.x, GameManager.getInstance().getMap().getMapWidth()-Wolf.SPRITE_SHEET_SPRITE_WIDTH ) );
        this.goalPosition.y = Math.max( 0, Math.min( this.goalPosition.y, GameManager.getInstance().getMap().getMapHeight()-Wolf.SPRITE_SHEET_SPRITE_HEIGHT ) );

        this.animation = SpriteSheetUtil.getAnimation( spriteSheet, 6, 8, 0, 150 );

    }

    @Override
    public void render(Graphics g) {
       this.animation.draw( this.getX(), this.getY() );
    }

    @Override
    public void update(GameContainer container, int delta) {
        this.move(delta);
        this.animation.update( delta );
    }


          /**     * Sets the location of the dog
     * @param dogLocation
     */
    

     public void setDogLocation(Point2D.Float dogLocation){
        this.dogLocation = dogLocation;
    }
     
     public void setSheepLocation(Point2D.Float sheepLocation, int i){
         //sheepLocations.set(i, sheepLocation); //het spel sluit meteen af als dit aan staat. TODO: fix dit            }
     }
    
     //Moet naar het ditschbijzijnde schaap lopen maar weg van de hond
     
    private void move( int delta ){
        if(euclideanDistance(this.getPosition(),dogLocation) < 100){ //TODO: denk wat dieper na over deze variabele, dit is alleen voor testen
            moveAwayFrom( delta, this.getPosition(), dogLocation);
        }
        else{
            moveTo(delta, this.getPosition(), locationClosestSheep());
        }
    }   

    /**
     * Returns the location of the closest sheep
     * @return
     */
    private Point2D.Float locationClosestSheep(){
        Point2D.Float locationClosestSheep = new Point2D.Float(0,0); //to make Netbeans happy
        double distanceToNearestSheep = Math.pow(10, 9); //should start higher than anything
        for(Point2D.Float sheepLocation:sheepLocations){
            if(euclideanDistance(sheepLocation, this.getPosition()) < distanceToNearestSheep){
                locationClosestSheep = sheepLocation;
                distanceToNearestSheep = euclideanDistance(sheepLocation, this.getPosition());        
            }     
        }
        return locationClosestSheep;

    }
    
}

    


