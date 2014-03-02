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
public class Login extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        HttpSession session = request.getSession(true);
        String URL=(String) session.getAttribute("loggingURL");
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        Encrypt crypt=new Encrypt();
        password=crypt.encrypt(password, username);
        String[] login=new String[5];
        Mysql sql=new Mysql();
        login=sql.login(username, password);
        if (login[0]==null) {                                                   //ochrana proti nullpointer exception
            login[0]="fail";
        }
        session.setAttribute("logged", login[0]);
        if(login[0].equals("fail")){
            response.sendRedirect(URL);
        }
        else{
            session.setAttribute("name", login[3]);                             
            session.setAttribute("lastname", login[4]);                     
            int rights=Integer.parseInt(login[1]);
            session.setAttribute("rights", rights);
            session.setAttribute("rightsString",login[2]);
            switch(rights){ 
            case 0:
                response.sendRedirect(URL);
                break;                    //main admin
            case 1:
                response.sendRedirect("proAdministrativu.jsp");
                break;                    //administrativa
            case 2:
                response.sendRedirect("proPedagogy.jsp");
                break;                    //pedagog
            case 3:
                response.sendRedirect("proStudenty.jsp");
                break;                    //student
            case 4:
                response.sendRedirect("proUchazece.jsp");
                break;                    //uchazeč
            }
        }
        
    }
}