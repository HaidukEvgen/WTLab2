package by.bsuir.wtlab2.logic.impl;

import by.bsuir.wtlab2.controller.JspPageName;
import by.bsuir.wtlab2.dao.FilmDao;
import by.bsuir.wtlab2.dao.impl.JDBCFilmDao;
import by.bsuir.wtlab2.entities.User;
import by.bsuir.wtlab2.logic.CommandException;
import by.bsuir.wtlab2.logic.ICommand;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 * Class of adding review command
 * @author haidukevgen
 * @version 1.0
 */
public class AddReviewCommand implements ICommand {
    private static final Logger log = Logger.getLogger(AddReviewCommand.class);
    private final FilmDao filmDao = new JDBCFilmDao();

    /**
     * Method to add new review to the specific film
     * @param request Servlet request object
     * @return Page to redirect
     * @throws CommandException
     */
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        User user = (User)request.getSession().getAttribute("user");
        if (user == null) {
            return JspPageName.LOGIN_PAGE;
        }
        int userId = user.getId();
        String filmName = request.getParameter("filmName");
        double userRating = Double.parseDouble(request.getParameter("userRating"));
        String reviewText = request.getParameter("reviewText");
        try {
            filmDao.addReview(filmName, userRating, reviewText, userId);
            return new GetFilmCommand().execute(request);
        } catch (RuntimeException e) {
            request.setAttribute("error", e.getMessage());
            log.error(e.getMessage());
            return JspPageName.ERROR_PAGE;
        }
    }
}
