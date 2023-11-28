package by.bsuir.wtlab2.logic.impl;

import by.bsuir.wtlab2.controller.JspPageName;
import by.bsuir.wtlab2.dao.FilmDao;
import by.bsuir.wtlab2.dao.impl.JDBCFilmDao;
import by.bsuir.wtlab2.logic.CommandException;
import by.bsuir.wtlab2.logic.ICommand;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 * Class of editing film command
 * @author haidukevgen
 * @version 1.0
 */
public class EditFilmCommand implements ICommand {
    private static final Logger log = Logger.getLogger(EditFilmCommand.class);
    private final FilmDao filmDao = new JDBCFilmDao();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        int filmId = Integer.parseInt(request.getParameter("filmId"));
        String filmTitle = request.getParameter("filmTitle");
        String filmDescription = request.getParameter("filmDescription");
        String filmImgUrl = request.getParameter("filmImgUrl");

        try {
            filmDao.editFilm(filmTitle, filmDescription, filmImgUrl, filmId);
            return new GetFilmsCommand().execute(request);
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            log.error(e.getMessage());
            return JspPageName.ERROR_PAGE;
        }
    }
}
