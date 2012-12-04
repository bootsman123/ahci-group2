package main;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author bootsman
 */
public class ServletHandler extends HttpServlet
{
    private int test;
    
    public ServletHandler()
    {
        super();
        
        this.test = 0;
    }
    
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServletHandler</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServletHandler at " + request.getContextPath () + "</h1>");
            out.println("<p>" + String.valueOf( this.test ) + "</p>");
            out.println("</body>");
            out.println("</html>");
        } finally {            
            out.close();
        }
        
        this.test++;
    }

    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {        
        // Write response.
        PrintWriter writer = response.getWriter();
        
        try
        {
            writer.printf( "Context path: %s\n", request.getContextPath() );
            writer.printf( "Request URI: %s\n", request.getRequestURI() );
            writer.printf( "Path info: %s\n", request.getPathInfo() );
        }
        finally
        {
            writer.close();
        }
        //processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
