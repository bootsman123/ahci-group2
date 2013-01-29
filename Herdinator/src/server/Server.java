package server;

import java.io.File;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

/**
 * Basic server class.
 * @author Bas Bootsma
 */
public class Server
{
    public static final int PORT = 8080;
    
    private Tomcat tomcat;
    
    public Server() throws LifecycleException
    {
        this.tomcat = new Tomcat();
        this.tomcat.setPort( Server.PORT );
        
        Context context = this.tomcat.addContext( "/", ( new File( "." ) ).getAbsolutePath() );
        context.setReloadable( true );
        
        Tomcat.addServlet( context, "ServerServlet", new ServerServlet() );
        context.addServletMapping( "/*", "ServerServlet" );
    }
    
    /**
     * Start server.
     */
    public void start() throws LifecycleException
    {
        this.tomcat.start();
        //this.tomcat.getServer().await();
    }
    
    /**
     * Stop server.
     */
    public void stop() throws LifecycleException
    {
        this.tomcat.stop();
    }
}