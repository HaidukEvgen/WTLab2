package by.bsuir.wtlab2.logic.impl;

import by.bsuir.wtlab2.controller.JspPageName;
import by.bsuir.wtlab2.logic.CommandException;
import by.bsuir.wtlab2.logic.ICommand;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Class of not found command
 * @author haidukevgen
 * @version 1.0
 */
public class NoSuchCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        return JspPageName.ERROR_PAGE;
    }
}
