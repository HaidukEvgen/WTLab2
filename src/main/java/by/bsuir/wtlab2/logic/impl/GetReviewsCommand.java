package by.bsuir.wtlab2.logic.impl;

import by.bsuir.wtlab2.controller.JspPageName;
import by.bsuir.wtlab2.dao.FilmDao;
import by.bsuir.wtlab2.dao.impl.JDBCFilmDao;
import by.bsuir.wtlab2.entities.Review;
import by.bsuir.wtlab2.entities.User;
import by.bsuir.wtlab2.logic.CommandException;
import by.bsuir.wtlab2.logic.ICommand;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Class of getting reviews for the film command
 * @author haidukevgen
 * @version 1.0
 */
public class GetReviewsCommand implements ICommand {
    private static final Logger log = Logger.getLogger(GetReviewsCommand.class);
    private final FilmDao filmDao = new JDBCFilmDao();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        User user = (User)request.getSession().getAttribute("user");
        if (user == null) {
            return JspPageName.LOGIN_PAGE;
        }
        String filmName = request.getParameter("filmName");
        int userId = user.getId();
        try {
            List<Review> reviews = filmDao.getReviews(filmName, userId);
            request.setAttribute("reviews", reviews);
            if (!reviews.isEmpty() && reviews.get(0).getAuthorId() == userId) {
                request.setAttribute("isButtonDisabled", true);
            }
            return JspPageName.FILM_DETAILS_PAGE;
        } catch (RuntimeException e) {
            request.setAttribute("error", e.getMessage());
            log.error(e.getMessage());
            return JspPageName.ERROR_PAGE;
        }
    }
}
