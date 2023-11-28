package by.bsuir.wtlab2.dao;

import by.bsuir.wtlab2.entities.Film;
import by.bsuir.wtlab2.entities.Review;

import java.util.List;

/**
 * Interface of dao with logic related to the films
 * @author haidukevgen
 * @version 1.0
 */
public interface FilmDao {
    /**
     * Method to get all films
     * @return List of all films
     */
    List<Film> getFilms();

    /**
     * Method to add new film
     * @param filmTitle New film title
     * @param imgUrl New film image URL
     * @param filmDescription New film description
     */
    void addFilm(String filmTitle, String imgUrl, String filmDescription);

    /**
     * Method to edit specific film
     * @param filmTitle Edited film title
     * @param filmDescription Edited film description
     * @param filmImgUrl Edited film image URL
     * @param filmId Edited film ID
     */
    void editFilm(String filmTitle, String filmDescription, String filmImgUrl, int filmId);

    /**
     * Method to get specific film
     * @param name Film name
     * @return Film found by name
     */
    Film getFilm(String name);

    /**
     * Method to get reviews for the film
     * @param filmName Film name
     * @param userId ID of current user
     * @return List of reviews
     */
    List<Review> getReviews(String filmName, int userId);

    /**
     * Method to add new review for the specific film
     * @param filmName Film name
     * @param userRating Film rating from user
     * @param reviewText Review text
     * @param userId Current user ID
     */
    void addReview(String filmName, double userRating, String reviewText, int userId);

    /**
     * Method to delete specific review
     * @param reviewId Review ID
     * @param filmName Film name
     */
    void deleteReview(int reviewId, String filmName);
}
