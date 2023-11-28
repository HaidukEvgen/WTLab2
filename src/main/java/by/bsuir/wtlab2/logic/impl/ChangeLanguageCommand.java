package by.bsuir.wtlab2.logic.impl;

import by.bsuir.wtlab2.logic.CommandException;
import by.bsuir.wtlab2.logic.ICommand;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Class of changing language command
 * @author haidukevgen
 * @version 1.0
 */
public class ChangeLanguageCommand implements ICommand {
    /**
     * Method to change language
     * @param request Servlet request object
     * @return Page to redirect
     * @throws CommandException
     */
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String newLang = request.getParameter("language");
        request.getSession().setAttribute("lang", newLang);
        return request.getHeader("Referer");
    }
}
