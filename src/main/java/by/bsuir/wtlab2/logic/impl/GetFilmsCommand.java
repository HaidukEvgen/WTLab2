package by.bsuir.wtlab2.logic.impl;

import by.bsuir.wtlab2.controller.JspPageName;
import by.bsuir.wtlab2.dao.FilmDao;
import by.bsuir.wtlab2.dao.impl.JDBCFilmDao;
import by.bsuir.wtlab2.entities.Film;
import by.bsuir.wtlab2.logic.CommandException;
import by.bsuir.wtlab2.logic.ICommand;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Class of getting films command
 * @author haidukevgen
 * @version 1.0
 */
public class GetFilmsCommand implements ICommand {
    private static final Logger log = Logger.getLogger(GetFilmsCommand.class);
    private final FilmDao filmDao = new JDBCFilmDao();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        try {
            List<Film> films = filmDao.getFilms();
            request.setAttribute("filmsList", films);
            return JspPageName.FILMS_PAGE;
        } catch (RuntimeException e) {
            request.setAttribute("error", e.getMessage());
            log.error(e.getMessage());
            return JspPageName.ERROR_PAGE;
        }
    }
}
