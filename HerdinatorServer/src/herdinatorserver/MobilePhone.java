package herdinatorserver;

import java.util.UUID;

/**
 *
 * @author bootsman
 */
public class MobilePhone
{
    public enum MobilePhoneObject
    {
        FENCE( "fence" ),
        BRIDGE( "bridge" ),
        WHISTLE( "whistle" ),
        COOKIE( "cookie" );
        
        private String name;
        
        MobilePhoneObject( String name )
        {
            this.name = name;
        }
        
        public String getName()
        {
            return this.name;
        }
        
        public MobilePhoneObject fromName( String name )
        {
            return MobilePhoneObject.valueOf( name.toUpperCase() );
        }
    };
    
    
    private UUID id;
    private Integer markId;
    private MobilePhoneObject object;
    
    public MobilePhone( Integer markId )
    {
        this.id = UUID.randomUUID();
        this.markId = markId;
        this.object = null;
    }
    
    public UUID getId()
    {
        return this.id;
    }
    
    public Integer getMarkId()
    {
        return this.markId;
    }
    
    public MobilePhoneObject
}