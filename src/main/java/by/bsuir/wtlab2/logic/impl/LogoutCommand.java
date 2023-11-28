package by.bsuir.wtlab2.logic.impl;

import by.bsuir.wtlab2.controller.JspPageName;
import by.bsuir.wtlab2.logic.CommandException;
import by.bsuir.wtlab2.logic.ICommand;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Class of logout command
 * @author haidukevgen
 * @version 1.0
 */
public class LogoutCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        request.getSession().invalidate();
        return JspPageName.LOGIN_PAGE;
    }
}
