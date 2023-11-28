package by.bsuir.wtlab2.logic.impl;

import by.bsuir.wtlab2.controller.JspPageName;
import by.bsuir.wtlab2.dao.AuthDao;
import by.bsuir.wtlab2.dao.impl.JDBCAuthDao;
import by.bsuir.wtlab2.entities.User;
import by.bsuir.wtlab2.logic.CommandException;
import by.bsuir.wtlab2.logic.ICommand;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 * Class of login command
 * @author haidukevgen
 * @version 1.0
 */
public class LoginCommand implements ICommand {
    private static final Logger log = Logger.getLogger(LoginCommand.class);
    private final AuthDao authDao = new JDBCAuthDao();
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if (password.length() < 8) {
            request.setAttribute("error", "Password should be at least 8 characters");
            return JspPageName.LOGIN_PAGE;
        }
        try {
            User user = authDao.getUser(email, password);
            if (user.getIsBanned()) {
                throw new RuntimeException("Sorry, your account has been banned");
            }
            request.getSession().setAttribute("user", user);
            request.setAttribute("loginFlag", true);
            return JspPageName.LOGIN_PAGE;
        } catch (RuntimeException e) {
            request.setAttribute("error", e.getMessage());
            log.error(email + " got error " + e.getMessage().toLowerCase());
            return JspPageName.LOGIN_PAGE;
        }
    }
}
