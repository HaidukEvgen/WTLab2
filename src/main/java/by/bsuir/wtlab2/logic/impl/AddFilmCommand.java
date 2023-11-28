package by.bsuir.wtlab2.logic.impl;

import by.bsuir.wtlab2.controller.JspPageName;
import by.bsuir.wtlab2.dao.FilmDao;
import by.bsuir.wtlab2.dao.impl.JDBCFilmDao;
import by.bsuir.wtlab2.logic.CommandException;
import by.bsuir.wtlab2.logic.ICommand;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 * Class of adding film command
 * @author haidukevgen
 * @version 1.0
 */
public class AddFilmCommand implements ICommand {
    private static final Logger log = Logger.getLogger(AddFilmCommand.class);
    private final FilmDao filmDao = new JDBCFilmDao();

    /**
     * Method of adding new film
     * @param request Servlet request object
     * @return Page to redirect
     * @throws CommandException
     */
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String filmTitle = request.getParameter("filmTitle");
        String filmDescription = request.getParameter("filmDescription");
        String imgUrl = request.getParameter("imgUrl");
        try {
            filmDao.addFilm(filmTitle, imgUrl, filmDescription);
            return new GetFilmsCommand().execute(request);
        } catch (RuntimeException e) {
            request.setAttribute("error", e.getMessage());
            log.error(e.getMessage());
            return JspPageName.ERROR_PAGE;
        }
    }
}
