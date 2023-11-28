package by.bsuir.wtlab2.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import by.bsuir.wtlab2.logic.CommandException;
import by.bsuir.wtlab2.logic.CommandHelper;
import by.bsuir.wtlab2.logic.CommandName;
import by.bsuir.wtlab2.logic.ICommand;
import by.bsuir.wtlab2.logic.impl.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Main servlet controller class
 * @author haidukevgen
 * @version 1.0
 */
public class Controller extends HttpServlet {
    /**
     * Serial version
     */
    private static final long serialVersionUID = 1L;
    /**
     * Path of jsp files
     */
    private static final String JSP_PATH = "/WEB-INF/jsp/%s";

    /**
     * Constructor
     */
    public Controller() {
        super();
    }

    /**
     * Get HTTP method handler
     * @param request Servlet request object
     * @param response Servlet response object
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = request.getParameter("page");
        if (page != null) {
            request.getRequestDispatcher(String.format(JSP_PATH, page)).forward(request, response);
        } else {
            executeCommand(request, response);
        }
    }

    /**
     * Post HTTP method handler
     * @param request Servlet request object
     * @param response Servlet response object
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        executeCommand(request, response);
    }

    /**
     * Method of executing a specific command
     * @param request Servlet request object
     * @param response Servlet response object
     * @throws ServletException
     * @throws IOException
     */
    private void executeCommand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandName = request.getParameter(RequestParameterName.COMMAND_NAME);
        ICommand command = CommandHelper.getInstance().getCommand(commandName);
        String page;
        try {
            page = command.execute(request);
        } catch (Exception e) {
            page = JspPageName.ERROR_PAGE;
        }
        if (command instanceof ChangeLanguageCommand) {
            response.sendRedirect(page);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher(page);
            if (dispatcher != null) {
                dispatcher.forward(request, response);
            } else {
                errorMessageDirectlyFromResponse(response);
            }
        }
    }

    /**
     * Method of displaying error page
     * @param response Servlet response object
     * @throws IOException
     */
    private void errorMessageDirectlyFromResponse(HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.getWriter().println("E R R O R");
    }
}