package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import enums.Label;
import enums.SQLTable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import source.MyLogger;
import source.Mysql;
import source.SecurityCheck;

/**
 * Servlet for processing data from form for adding new Student.
 * Puts data to SQL table.
 * @author Azathoth
 */
public class AddStudent extends HttpServlet {

    /**
     *
     * @param input boolean array you want to test
     * @return true only when each element of boolean array is true, otherwise
     * return false
     */
    public boolean isAllTrue(boolean[] input) {
        boolean output = true;
        for (int i = 0; i < input.length; i++) {
            if (input[i] == false) {
                output = false;
            }
        }
        return output;
    }

    /**
     * Processes requests for HTTP <code>GET</code> method. Only disables direct
     * access.
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        SecurityCheck security = new SecurityCheck(request);
        security.noDirectAccess(response);
    }

    /**
     * Processes requests for HTTP <code>POST</code> method. Adds data about new
     * students to sql and then redirects user back to jsp page from which ke
     * came.
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("text/html;charset=UTF-8");
            HttpSession session = request.getSession(true);
            ArrayList<LinkedHashMap<Label, String>> seznamStudentu = (ArrayList<LinkedHashMap<Label, String>>) session.getAttribute("newstudent");
            Mysql sql = new Mysql();
            HashMap<Label, String> input = new HashMap<>();
            boolean rs[] = new boolean[seznamStudentu.size()];
            ArrayList<HashMap<Label, String>> listOfStudents = new ArrayList<>();
            if (session.getAttribute("newstudent") != null) {
                listOfStudents = (ArrayList<HashMap<Label, String>>) session.getAttribute("newstudent");
            }
            for (int i = 0; i < seznamStudentu.size(); i++) {
                input.put(Label.USERNAME, listOfStudents.get(i).get(Label.USERNAME));
                for (Label label : Label.values()) {
                    if (label.isInTable(SQLTable.STUDENTS) && !label.isAutoFill()) {
                        if (label.isPhonenumber()) {
                            input.put(label, request.getParameter("predvolba" + label.getNameRaw() + "+" + i) + request.getParameter(label.getNameRaw() + "+" + i));
                        } else {
                            input.put(label, request.getParameter(label.getNameRaw() + "+" + i));
                        }
                    }
                }
                rs[i] = sql.insertNewStudent(input);
                input.clear();
            }

            if (isAllTrue(rs)) {
                session.setAttribute("registered", "success");
            } else {
                session.setAttribute("registered", "fail");
            }
            response.sendRedirect("pridaniStudenta.jsp");
        } catch (SQLException | ClassNotFoundException | IOException ex) {
            try {
                MyLogger.getLogger().logp(Level.SEVERE, this.getClass().getName(), "doPost method", "Error in mysql. " + ex.getMessage(), ex);
                response.sendRedirect("chyba.jsp?error=0");
            } catch (IOException ex1) {
                MyLogger.getLogger().logp(Level.SEVERE, this.getClass().getName(), "doPost method", "Error in redirecting to chyba.jsp?error=0. " + ex1.getMessage(), ex1);
            }
        }
    }
}
