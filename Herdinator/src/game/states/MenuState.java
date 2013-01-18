package game.states;

import game.util.FontUtil;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.HieroSettings;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Abstract menu with a background, a title and a subtitle.
 * Top color: #fa0d70.
 * Bottom color: #db2864.
 * @author Bas Bootsma
 */
public abstract class MenuState extends BasicGameState
{
    public static final Integer BUTTON_MARGIN = 20;
    
    private static final String BACKGROUND_FILE_PATH = "../Resources/Images/Menu/background.jpg";

    // Titles.
    private static final String TITLE_STRING = "Herdinator";
    private static final String TITLE_FONT_FILE_PATH = "../Resources/Fonts/Harabara.ttf";
    private static final Integer TITLE_FONT_SIZE = 60;
   
    //private static final String SUB_TITLE_STRING = "Catching sheep has never been more fun! YOHO, you only herd once.";
    private static final String[] SUB_TITLE_STRINGS = {
        "Catching sheep has never been more fun!",
        "YOHO, you only herd once!",
        "It's all fun and games until someone gets herd!",
        "You may have herd of our game!",
        "My jokes are sheeeeppppp."
    };
    private static final String SUB_TITLE_FONT_FILE_PATH = "../Resources/Fonts/simplicity.ttf";
    private static final Integer SUB_TITLE_FONT_SIZE = 30;
    
    private UnicodeFont titleFont;
    private UnicodeFont subTitleFont;
    
    private String subTitleString;
    
    private Image background;
    
    protected GameContainer container;
    protected StateBasedGame game;
    
    /**
     * Constructor.
     */
    public MenuState()
    {
        super();
    }

    @Override
    public void init( GameContainer container, StateBasedGame game ) throws SlickException
    {        
        this.background = new Image( MenuState.BACKGROUND_FILE_PATH );
        
        // Fonts.
        HieroSettings titleSettings = new HieroSettings();
        titleSettings.setFontSize( MenuState.TITLE_FONT_SIZE );
        titleSettings.setBold( Boolean.FALSE );
        titleSettings.setItalic( Boolean.FALSE );
        titleSettings.getEffects().add( new ColorEffect( java.awt.Color.WHITE ) );
        
        this.titleFont = FontUtil.load( MenuState.TITLE_FONT_FILE_PATH, titleSettings );
        this.titleFont.addAsciiGlyphs();
        this.titleFont.loadGlyphs();
        
        HieroSettings subTitleSettings = new HieroSettings();
        subTitleSettings.setFontSize( MenuState.SUB_TITLE_FONT_SIZE );
        subTitleSettings.setBold( Boolean.FALSE );
        subTitleSettings.setItalic( Boolean.FALSE );
        subTitleSettings.getEffects().add( new ColorEffect( java.awt.Color.WHITE ) );
        
        this.subTitleFont = FontUtil.load( MenuState.SUB_TITLE_FONT_FILE_PATH, subTitleSettings );
        this.subTitleFont.addAsciiGlyphs();
        this.subTitleFont.loadGlyphs();
        
        // Random subtitle.
        this.subTitleString = MenuState.SUB_TITLE_STRINGS[ (int)( Math.random() * MenuState.SUB_TITLE_STRINGS.length ) ];
    }

    @Override
    public void render( GameContainer container, StateBasedGame game, Graphics g ) throws SlickException
    {
        this.background.draw( 0, 0, container.getWidth(), container.getHeight() );
        
        // Draw title.
        Integer titleStringX = ( container.getWidth() - this.titleFont.getWidth( MenuState.TITLE_STRING ) ) / 2;
        Integer titleStringY = 20;
        
        this.titleFont.drawString( titleStringX, titleStringY, MenuState.TITLE_STRING );
        
        // Draw sub title.
        Integer subTitleStringX = ( container.getWidth() - this.subTitleFont.getWidth( this.subTitleString ) ) / 2;
        Integer subTitleStringY = 80;
        
        this.subTitleFont.drawString( subTitleStringX, subTitleStringY, this.subTitleString );
    }
    
    @Override
    public void update( GameContainer container, StateBasedGame game, int delta ) throws SlickException
    {
    }
    
    @Override
    public void enter( GameContainer container, StateBasedGame game ) throws SlickException
    {
        this.container = container;
        this.game = game;
    }
}
