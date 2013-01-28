package herdinatorserver;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

/**
 *
 * @author bootsman
 */
public class ServerHandler
{
    public static final int PORT = 8080;
    
    private Tomcat tomcat;
    
    public ServerHandler() throws LifecycleException
    {
        this.tomcat = new Tomcat();
        this.tomcat.setPort( ServerHandler.PORT );
        
        Context context = this.tomcat.addContext( "/", ( new File( "." ) ).getAbsolutePath() );
        context.setReloadable( true );
        
        Tomcat.addServlet( context, "ServerHandlerServlet", ServerHandlerServlet.getInstance() );
        context.addServletMapping( "/*", "ServerHandlerServlet" );
        
        this.tomcat.start();
        this.tomcat.getServer().await();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main( String[] args )
    {
        try
        {
            new ServerHandler();
        }
        catch( LifecycleException e )
        {
            Logger.getLogger( ServerHandler.class.getName() ).log( Level.SEVERE, null, e );
        }
    }

}
