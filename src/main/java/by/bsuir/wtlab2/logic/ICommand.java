package by.bsuir.wtlab2.logic;

import jakarta.servlet.http.HttpServletRequest;

/**
 * Interface of servlet command
 * @author haidukevgen
 * @version 1.0
 */
public interface ICommand {
    /**
     * Method to execute command
     * @param request Servlet request object
     * @return Page to redirect
     * @throws CommandException
     */
    String execute(HttpServletRequest request) throws CommandException;
}
