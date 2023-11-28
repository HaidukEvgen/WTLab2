package by.bsuir.wtlab2.logic.impl;

import by.bsuir.wtlab2.controller.JspPageName;
import by.bsuir.wtlab2.dao.FilmDao;
import by.bsuir.wtlab2.dao.impl.JDBCFilmDao;
import by.bsuir.wtlab2.logic.CommandException;
import by.bsuir.wtlab2.logic.ICommand;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 * Class of deleting review command
 * @author haidukevgen
 * @version 1.0
 */
public class DeleteReviewCommand implements ICommand {
    private static final Logger log = Logger.getLogger(DeleteReviewCommand.class);
    private final FilmDao filmDao = new JDBCFilmDao();

    /**
     * Method delete review from the specific film
     * @param request Servlet request object
     * @return Page to redirect
     * @throws CommandException
     */
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        int reviewId = Integer.parseInt(request.getParameter("reviewId"));
        String filmName = request.getParameter("filmName");
        try {
            filmDao.deleteReview(reviewId, filmName);
            return new GetFilmCommand().execute(request);
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            log.error(e.getMessage());
            return JspPageName.ERROR_PAGE;
        }
    }
}
