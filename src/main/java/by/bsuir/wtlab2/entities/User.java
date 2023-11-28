package by.bsuir.wtlab2.entities;

public class User {
    private final int id;
    private final String name;
    private final String email;
    private final int rating;
    private final String role;
    private final boolean isBanned;

    public User(int id, String name, String email, int rating, String role, boolean isBanned) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.rating = rating;
        this.role = role;
        this.isBanned = isBanned;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getRating() {
        return rating;
    }

    public String getRole() {
        return role;
    }

    public boolean getIsBanned() {
        return isBanned;
    }
}
