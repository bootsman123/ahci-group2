/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package game.util;

import java.util.HashMap;
import java.util.Map;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.HieroSettings;

/**
 *
 * @author bootsman
 */
public class FontUtil
{
    /**
     * Private class used for indexing Unicode fonts.
     */
    private static class FontCacheIndex
    {
        private String filePath;
        private HieroSettings settings;
        
        /**
         * Constructor.
         * @param filePath
         * @param settings 
         */
        public FontCacheIndex( String filePath, HieroSettings settings )
        {
            this.filePath = filePath;
            this.settings = settings;
        }
        
        public String getFilePath()
        {
            return this.filePath;
        }
        
        public HieroSettings getSettings()
        {
            return this.settings;
        }
        
        @Override
        public boolean equals( Object o )
        {
            if( this == o )
            {
                return true;
            }
            else if( !( o instanceof FontCacheIndex ) )
            {
                return false;
            }
            else
            {
                FontCacheIndex index = (FontCacheIndex)o;
                
                return this.getFilePath().equals( index.getFilePath() ); //&&
                       //this.getSettings().equals( index.getSettings() );
            }
        }

        @Override
        public int hashCode()
        {
            int hash = 7;
            
            hash = 97 * hash + ( this.filePath != null ? this.filePath.hashCode() : 0 );            
            //hash = 97 * hash + ( this.settings != null ? this.settings.hashCode() : 0 );
                        
            return hash;
        }

    };
    
    // Font cache.
    private static Map<FontCacheIndex, UnicodeFont> fontCache;
    
    /**
     * Load a font.
     * @param filePath
     * @param settings
     * @return 
     */
    public static UnicodeFont load( String filePath, HieroSettings settings ) throws SlickException
    {
        if( FontUtil.fontCache == null )
        {
            FontUtil.fontCache = new HashMap<FontCacheIndex, UnicodeFont>();
        }
        
        // Check if the font is already cached.
        FontUtil.FontCacheIndex index = new FontUtil.FontCacheIndex( filePath, settings );
        UnicodeFont font = FontUtil.fontCache.get( index );
        
        if( font == null )
        {
            font = new UnicodeFont( filePath, settings );
            FontUtil.fontCache.put( index, font );
        }
        

        return font;
    }
}