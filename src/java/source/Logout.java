package source;


import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Azathoth
 */
public class Logout extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
    HttpSession session = request.getSession(true);
    session.setAttribute("logged", null); 
    session.setAttribute("username", null); 
    session.setAttribute("name", null); 
    session.setAttribute("lastname", null); 
    session.setAttribute("rightsString", null);  
    session.setAttribute("rights", null);
    String URL=(String) session.getAttribute("loggingURL");
    session.setAttribute("loggingURL", null);
    response.sendRedirect(URL);
    
    }    
}
