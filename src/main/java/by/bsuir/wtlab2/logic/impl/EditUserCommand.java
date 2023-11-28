package by.bsuir.wtlab2.logic.impl;

import by.bsuir.wtlab2.controller.JspPageName;
import by.bsuir.wtlab2.dao.UserDao;
import by.bsuir.wtlab2.dao.impl.JDBCUserDao;
import by.bsuir.wtlab2.logic.CommandException;
import by.bsuir.wtlab2.logic.ICommand;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 * Class of editing user for admin command
 * @author haidukevgen
 * @version 1.0
 */
public class EditUserCommand implements ICommand {
    private static final Logger log = Logger.getLogger(EditUserCommand.class);
    private final UserDao usersDao = new JDBCUserDao();
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        int id = Integer.parseInt(request.getParameter("id"));
        int rating = Integer.parseInt(request.getParameter("rating"));
        boolean isBanned = Boolean.parseBoolean(request.getParameter("ban"));
        try {
            usersDao.updateUser(id, rating, isBanned);
            return new GetUsersCommand().execute(request);
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            log.error(e.getMessage());
            return JspPageName.ERROR_PAGE;
        }
    }
}
