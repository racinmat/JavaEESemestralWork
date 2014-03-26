/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package source;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Azathoth
 */
public class AddStudent extends HttpServlet {

    public boolean isAllTrue(boolean[] input){
        boolean output=true;
        for (int i = 0; i < input.length; i++) {
            if (input[i]==false) {
                output=false;
            }
        }
        return output;
    }
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        try {
            response.setContentType("text/html;charset=UTF-8");
            HttpSession session = request.getSession(true);
            UsernameGen generator=new UsernameGen(10);
            String username=generator.getValidatedId();
            String password=generator.getId();
            String[] label=Label.getLabelStudent();
            String[] labelRaw=Label.getLabelStudentRaw();
            Encrypt crypt=new Encrypt();
            password=crypt.encrypt(password, username);
            ArrayList<String> seznamStudentu=(ArrayList<String>) session.getAttribute("newstudent");
            
            Mysql sql=new Mysql();
            String[] input = new String[7];
            boolean rs[]=new boolean[seznamStudentu.size()];
            for (int i = 0; i < 10; i++) {
                input[0]=username;
                input[3]=password;
                input[1]=request.getParameter(labelRaw[1]+"+"+i);
                input[2]=request.getParameter(labelRaw[2]+"+"+i);
                input[4]=request.getParameter(labelRaw[3]+"+"+i);
                input[5]=request.getParameter(labelRaw[4]+"+"+i);
                input[6]=request.getParameter(labelRaw[5]+"+"+i);

                rs[i]=sql.insertNewStudent(input);
            
            }
            
            if (isAllTrue(rs)) {
                session.setAttribute("registered", "success");
            }
            else {
                session.setAttribute("registered", "fail");
            }
            response.sendRedirect("pridaniPedagoga.jsp");
        } catch (IOException ex) {
            Logger.getLogger(AddPedagog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
