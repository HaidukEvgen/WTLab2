package by.bsuir.wtlab2.logic.impl;

import by.bsuir.wtlab2.controller.JspPageName;
import by.bsuir.wtlab2.dao.FilmDao;
import by.bsuir.wtlab2.dao.impl.JDBCFilmDao;
import by.bsuir.wtlab2.entities.Film;
import by.bsuir.wtlab2.logic.CommandException;
import by.bsuir.wtlab2.logic.ICommand;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 * Class of getting film command
 * @author haidukevgen
 * @version 1.0
 */
public class GetFilmCommand implements ICommand {
    private static final Logger log = Logger.getLogger(GetFilmCommand.class);
    private final FilmDao filmDao = new JDBCFilmDao();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String filmName = request.getParameter("filmName");
        try {
            Film film = filmDao.getFilm(filmName);
            if (film == null) {
                throw new RuntimeException("Film is null");
            }
            request.setAttribute("film", film);
            return new GetReviewsCommand().execute(request);
        } catch (RuntimeException e) {
            request.setAttribute("error", e.getMessage());
            log.error(e.getMessage());
            return JspPageName.ERROR_PAGE;
        }
    }
}
