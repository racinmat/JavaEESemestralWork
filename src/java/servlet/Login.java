package servlet;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import source.Encrypt;
import source.LoggedUser;
import source.Mysql;
import java.sql.SQLException;
import java.util.logging.Level;
import source.MyLogger;
import source.SecurityCheck;

/**
 * Used for processing login. Encrypts password and checks if username and
 * password are correct.
 *
 * @author Azathoth
 */
public class Login extends HttpServlet {

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
     * Processes requests for HTTP <code>POST</code> method. Checks if user
     * provided correct data, fills sesstion variable user with data about user
     * in case of successful login and otherwise fills variable user with
     * instance of UserFailingInLogin and redirects user to page which is
     * default for his rights.
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession(true);
            String URL = (String) session.getAttribute("loggingURL");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            Encrypt crypt = new Encrypt();
            password = crypt.encrypt(password, username);
            Mysql sql = new Mysql();
            LoggedUser user = sql.login(username, password);
            session.setAttribute("user", user);
            if (user.failedInLogin()) {
                response.sendRedirect(URL);
            } else {
                response.sendRedirect(user.getRights().getInitialRedirect());
            }
        } catch (SQLException | ClassNotFoundException | NullPointerException | IOException ex) {
            try {
                MyLogger.getLogger().logp(Level.SEVERE, Login.class.getName(), "doPost method", "Error in mysql or loging in. " + ex.getMessage(), ex);
                response.sendRedirect("chyba.jsp?error=0");
            } catch (IOException ex1) {
                MyLogger.getLogger().logp(Level.SEVERE, AddAdministrativa.class.getName(), "doPost method", "Error in redirecting to chyba.jsp?error=0. " + ex1.getMessage(), ex1);
            }
        }
    }
}
