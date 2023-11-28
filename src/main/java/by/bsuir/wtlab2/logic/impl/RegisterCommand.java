package by.bsuir.wtlab2.logic.impl;

import by.bsuir.wtlab2.controller.JspPageName;
import by.bsuir.wtlab2.dao.AuthDao;
import by.bsuir.wtlab2.dao.impl.JDBCAuthDao;
import by.bsuir.wtlab2.logic.CommandException;
import by.bsuir.wtlab2.logic.ICommand;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Objects;

/**
 * Class of registration command
 * @author haidukevgen
 * @version 1.0
 */
public class RegisterCommand implements ICommand {
    private static final Logger log = Logger.getLogger(RegisterCommand.class);
    private final AuthDao authDao = new JDBCAuthDao();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String repeatPassword = request.getParameter("re-password");
        if (!Objects.equals(password, repeatPassword)) {
            request.setAttribute("error", "Passwords should match");
            return JspPageName.REGISTRATION_PAGE;
        }
        if (password.length() < 8) {
            request.setAttribute("error", "Password should be at least 8 characters");
            return JspPageName.REGISTRATION_PAGE;
        }
        try {
            int userId = authDao.registerUser(name, email, hashPassword(password));
            request.getSession().setAttribute("id", userId);
            request.getSession().setAttribute("name", name);
            request.getSession().setAttribute("role", "client");
            request.setAttribute("message", "Successfully registered, you can login now");
            return JspPageName.REGISTRATION_PAGE;
        } catch (RuntimeException e) {
            request.setAttribute("error", e.getMessage());
            log.error(email + " got error " + e.getMessage().toLowerCase());
            return JspPageName.REGISTRATION_PAGE;
        }
    }

    public static String hashPassword(String password) {
        String salt = BCrypt.gensalt(12);
        return BCrypt.hashpw(password, salt);
    }
}
