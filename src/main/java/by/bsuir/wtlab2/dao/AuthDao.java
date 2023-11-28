package by.bsuir.wtlab2.dao;

import by.bsuir.wtlab2.entities.User;

/**
 * Interface of auth logic dao
 * @author haidukevgen
 * @version 1.0
 */
public interface AuthDao {
    /**
     * Method to get specific user
     * @param email User email
     * @param password User password
     * @return User found by email and password
     */
    User getUser(String email, String password);
    /**
     * Method to get register new user
     * @param name User name
     * @param email User email
     * @param password User password
     * @return Registered user ID
     */
    int registerUser(String name, String email, String password);
}
