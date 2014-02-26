



import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        try {
            password=crypt.encrypt(password, username);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        String rightsString=null;
        try
        {
            Connection conn = null;
            String id=null;
            Statement stmt;
            String url = "jdbc:mysql://localhost:3306/";
            String dbName ="mysql"; 
            String uname = "root";
            String pwd = "";
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url+dbName,uname,pwd);
            String sql = "SELECT * FROM login where username='"+username+"' and password='"+password+"'";
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
    
            int size=0;
            int rights=Integer.MAX_VALUE;                                       //číselná práva jsou od nuly výš, max value tedy nepřidělí platná práva
            int rightsTemp=Integer.MAX_VALUE;
            String name="";
            String lastname="";
            String nameTemp="";
            String lastnameTemp="";
            while(rs.next()){
                nameTemp=rs.getString("name");
                lastnameTemp=rs.getString("lastname");
                rightsTemp=rs.getInt("rights");
                size++;
            }
            response.getWriter().print(size);
            if(size==1){                                                        //podmínkou pro úspěšné přihlášení se je právě jedna shoda
                //rs.beforeFirst();
                name=nameTemp;
                lastname=lastnameTemp;
                rights=rightsTemp;
                response.getWriter().print("Login Success");
                session.setAttribute("name", name);                             
                session.setAttribute("lastname", lastname);                     
                session.setAttribute("logged", "success");  
                session.setAttribute("rights", rights);
                switch(rights){ 
                    case 0:
                        session.setAttribute("rightsString", "hlavní admin");
                        response.sendRedirect(URL);
                        break;                    //main admin
                    case 1:
                        session.setAttribute("rightsString", "administrativa");
                        response.sendRedirect("proAdministrativu.jsp");
                        break;                    //administrativa
                    case 2:
                        session.setAttribute("rightsString", "pedagog");
                        response.sendRedirect("proPedagogy.jsp");
                        break;                    //pedagog
                    case 3:
                        session.setAttribute("rightsString", "student");
                        response.sendRedirect("proStudenty.jsp");
                        break;                    //student
                    case 4:
                        session.setAttribute("rightsString", "uchazeč");
                        response.sendRedirect("proUchazece.jsp");
                        break;                    //uchazeč
                    
                }
            }
            else{
                session.setAttribute("logged", "fail");                         //je zapotřebí pro výpis chybové hlášky ve footeru
                response.sendRedirect(URL);
            }        
        }
        catch(ClassNotFoundException e)
        {
            response.getWriter().print(e);
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            Throwable t = ex.getCause();
            response.getWriter().print(t);
        }
    }
}