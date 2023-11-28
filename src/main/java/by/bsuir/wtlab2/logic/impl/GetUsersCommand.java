package by.bsuir.wtlab2.logic.impl;

import by.bsuir.wtlab2.controller.JspPageName;
import by.bsuir.wtlab2.dao.UserDao;
import by.bsuir.wtlab2.dao.impl.JDBCUserDao;
import by.bsuir.wtlab2.entities.User;
import by.bsuir.wtlab2.logic.CommandException;
import by.bsuir.wtlab2.logic.ICommand;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Class of getting users command
 * @author haidukevgen
 * @version 1.0
 */
public class GetUsersCommand implements ICommand {
    private static final Logger log = Logger.getLogger(GetUsersCommand.class);
    private final UserDao userDao = new JDBCUserDao();
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        try {
            List<User> users = userDao.getUsers();
            request.setAttribute("usersList", users);
            return JspPageName.ADMIN_PAGE;
        } catch (RuntimeException e) {
            request.setAttribute("error", e.getMessage());
            log.error(e.getMessage());
            return JspPageName.ERROR_PAGE;
        }
    }
}
