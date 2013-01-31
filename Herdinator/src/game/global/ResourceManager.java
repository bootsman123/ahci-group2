package game.global;

import game.actors.Cookie;
import game.actors.Dog;
import game.actors.LoveSheep;
import game.actors.Sheep;
import game.actors.Whistle;
import game.gui.interfaces.UsableActorContainer;
import game.states.GameScoreMenuState;
import game.states.GameState;
import game.states.LoadingState;
import game.states.MenuState;
import game.states.ModalityMouseAndTouchMenuState;
import game.states.ModalitySelectorMenuState;
import game.states.ModalityTangiblesMenuState;
import java.awt.Font;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

/**
 *
 * @author bootsman
 */
public class ResourceManager
{
    // Singleton instance.
    private static ResourceManager INSTANCE = new ResourceManager();
    
    public enum ResourceType
    {
        Image, SpriteSheet, Sound, Font
    }
    
    private HashMap<String, Image> images;
    private HashMap<String, SpriteSheet> spriteSheets;
    private HashMap<String, Sound> sounds;
    private HashMap<String, UnicodeFont> fonts;
    
    private Boolean isLoaded;
    
    /**
     * Constructor.
     */
    private ResourceManager()
    {
        this.images = new HashMap<String, Image>();
        this.spriteSheets = new HashMap<String, SpriteSheet>();
        this.sounds = new HashMap<String, Sound>();
        this.fonts = new HashMap<String, UnicodeFont>();
                    
        this.isLoaded = Boolean.FALSE;
    }
    
    /**
     * Return instance.
     * @return 
     */
    public static ResourceManager getInstance()
    {
        return ResourceManager.INSTANCE;
    }
    
    /**
     * Helper function to load all resources.
     */
    public void load() throws SlickException
    {
        if( this.isLoaded )
        {
            return;
        }
                
        // Load images.
        this.images.put( MenuState.BACKGROUND_FILE_PATH, new Image( MenuState.BACKGROUND_FILE_PATH ) );
        
        // ModalitySelectorMenuState.
        this.images.put( ModalitySelectorMenuState.BUTTON_MODALITY_MOUSE_AND_TOUCH, new Image( ModalitySelectorMenuState.BUTTON_MODALITY_MOUSE_AND_TOUCH ) );
        this.images.put( ModalitySelectorMenuState.BUTTON_MODALITY_TANGIBLES, new Image( ModalitySelectorMenuState.BUTTON_MODALITY_TANGIBLES ) );
        this.images.put( ModalitySelectorMenuState.BUTTON_EXIT, new Image( ModalitySelectorMenuState.BUTTON_EXIT ) );
        
        // ModalityMouseAndTouchMenuState.
        this.images.put( ModalityMouseAndTouchMenuState.BUTTON_NUMBER_OF_PLAYERS_ONE, new Image( ModalityMouseAndTouchMenuState.BUTTON_NUMBER_OF_PLAYERS_ONE ) );
        this.images.put( ModalityMouseAndTouchMenuState.BUTTON_NUMBER_OF_PLAYERS_TWO, new Image( ModalityMouseAndTouchMenuState.BUTTON_NUMBER_OF_PLAYERS_TWO ) );
        this.images.put( ModalityMouseAndTouchMenuState.BUTTON_NUMBER_OF_PLAYERS_THREE, new Image( ModalityMouseAndTouchMenuState.BUTTON_NUMBER_OF_PLAYERS_THREE ) );
        this.images.put( ModalityMouseAndTouchMenuState.BUTTON_NUMBER_OF_PLAYERS_FOUR, new Image( ModalityMouseAndTouchMenuState.BUTTON_NUMBER_OF_PLAYERS_FOUR ) );
       
        this.images.put( ModalityMouseAndTouchMenuState.BUTTON_ACTIVE_NUMBER_OF_PLAYERS_ONE, new Image( ModalityMouseAndTouchMenuState.BUTTON_ACTIVE_NUMBER_OF_PLAYERS_ONE ) );
        this.images.put( ModalityMouseAndTouchMenuState.BUTTON_ACTIVE_NUMBER_OF_PLAYERS_TWO, new Image( ModalityMouseAndTouchMenuState.BUTTON_ACTIVE_NUMBER_OF_PLAYERS_TWO ) );
        this.images.put( ModalityMouseAndTouchMenuState.BUTTON_ACTIVE_NUMBER_OF_PLAYERS_THREE, new Image( ModalityMouseAndTouchMenuState.BUTTON_ACTIVE_NUMBER_OF_PLAYERS_THREE ) );
        this.images.put( ModalityMouseAndTouchMenuState.BUTTON_ACTIVE_NUMBER_OF_PLAYERS_FOUR, new Image( ModalityMouseAndTouchMenuState.BUTTON_ACTIVE_NUMBER_OF_PLAYERS_FOUR ) );
        
        this.images.put( ModalityMouseAndTouchMenuState.BUTTON_START, new Image( ModalityMouseAndTouchMenuState.BUTTON_START ) );
        this.images.put( ModalityMouseAndTouchMenuState.BUTTON_BACK, new Image( ModalityMouseAndTouchMenuState.BUTTON_BACK ) );
        this.images.put( ModalityMouseAndTouchMenuState.BUTTON_DEBUG, new Image( ModalityMouseAndTouchMenuState.BUTTON_DEBUG ) );
        this.images.put( ModalityMouseAndTouchMenuState.ACTIVE_BUTTON_DEBUG, new Image( ModalityMouseAndTouchMenuState.ACTIVE_BUTTON_DEBUG ) );
        
        // ModalityTangiblesMenuState.
        this.images.put( ModalityTangiblesMenuState.BUTTON_START, new Image( ModalityTangiblesMenuState.BUTTON_START ) );
        this.images.put( ModalityTangiblesMenuState.BUTTON_BACK, new Image( ModalityTangiblesMenuState.BUTTON_BACK ) );
        
        // GameScoreMenuState.
        //this.images.put( GameScoreMenuState.BUTTON_BACK_TO_MENU, new Image( GameScoreMenuState.BUTTON_BACK_TO_MENU ) );
        this.images.put( GameScoreMenuState.BUTTON_EXIT, new Image( GameScoreMenuState.BUTTON_EXIT ) );
        
        // UsableActorContainer.
        this.images.put( UsableActorContainer.HORIZONTAL_PICKER_IMAGE_FILE_PATH, new Image( UsableActorContainer.HORIZONTAL_PICKER_IMAGE_FILE_PATH ) );
        this.images.put( UsableActorContainer.VERTICAL_PICKER_IMAGE_FILE_PATH, new Image( UsableActorContainer.VERTICAL_PICKER_IMAGE_FILE_PATH ) );
        
        // Load spritesheets.
        this.spriteSheets.put( Cookie.SPRITE_SHEET_FILE_PATH, new SpriteSheet( Cookie.SPRITE_SHEET_FILE_PATH,
                                                                         Cookie.SPRITE_SHEET_SPRITE_WIDTH,
                                                                         Cookie.SPRITE_SHEET_SPRITE_HEIGHT,
                                                                         Cookie.SPRITE_SHEET_BACKGROUND_COLOR ) );
        
        this.spriteSheets.put( Dog.SPRITE_SHEET_FILE_PATH, new SpriteSheet( Dog.SPRITE_SHEET_FILE_PATH,
                                                                      Dog.SPRITE_SHEET_SPRITE_WIDTH,
                                                                      Dog.SPRITE_SHEET_SPRITE_HEIGHT,
                                                                      Dog.SPRITE_SHEET_BACKGROUND_COLOR ) );
        
        this.spriteSheets.put( LoveSheep.SPRITE_SHEET_FILE_PATH, new SpriteSheet( LoveSheep.SPRITE_SHEET_FILE_PATH,
                                                                            LoveSheep.SPRITE_SHEET_SPRITE_WIDTH,
                                                                            LoveSheep.SPRITE_SHEET_SPRITE_HEIGHT,
                                                                            LoveSheep.SPRITE_SHEET_BACKGROUND_COLOR ) );
        
        this.spriteSheets.put( Sheep.SPRITE_SHEET_FILE_PATH, new SpriteSheet( Sheep.SPRITE_SHEET_FILE_PATH,
                                                                        Sheep.SPRITE_SHEET_SPRITE_WIDTH,
                                                                        Sheep.SPRITE_SHEET_SPRITE_HEIGHT,
                                                                        Sheep.SPRITE_SHEET_BACKGROUND_COLOR ) );
        
        this.spriteSheets.put( Whistle.SPRITE_SHEET_FILE_PATH, new SpriteSheet( Whistle.SPRITE_SHEET_FILE_PATH,
                                                                          Whistle.SPRITE_SHEET_SPRITE_WIDTH,
                                                                          Whistle.SPRITE_SHEET_SPRITE_HEIGHT,
                                                                          Whistle.SPRITE_SHEET_BACKGROUND_COLOR ) );
        
        // Load sounds.
        this.sounds.put( Cookie.SOUND_ON_USE_FILE_PATH, new Sound( Cookie.SOUND_ON_USE_FILE_PATH ) );
        this.sounds.put( Whistle.SOUND_ON_USE_FILE_PATH, new Sound( Whistle.SOUND_ON_USE_FILE_PATH ) );
        this.sounds.put( GameState.AMBIANCE_SOUND_FILE_PATH, new Sound( GameState.AMBIANCE_SOUND_FILE_PATH ) );
        
        // Load fonts.
        // @TODO
        
        this.isLoaded = Boolean.TRUE;
    }
    
    /**
     * Return the cached image.
     * @param key
     * @return 
     */
    public Image getImage( String key )
    {
        return this.images.get( key );
    }
    
    /**
     * Return the cached sprite sheet.
     * @param key
     * @return 
     */
    public SpriteSheet getSpriteSheet( String key )
    {
        return this.spriteSheets.get( key );
    }
    
    /**
     * Return the cached sound.
     * @param key
     * @return 
     */
    public Sound getSound( String key )
    {
        return this.sounds.get( key );
    }
    
    /**
     * Return the cached Unicode font.
     * @param key
     * @return 
     */
    public UnicodeFont getFont( String key )
    {
        return this.fonts.get( key );
    }
}