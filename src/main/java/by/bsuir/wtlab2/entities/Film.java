package by.bsuir.wtlab2.entities;

public class Film {
    private final int id;
    private final String name;
    private final String description;
    private final double rating;
    private final String imgUrl;

    public Film(int id, String name, String description, double rating, String imgUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.imgUrl = imgUrl;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getRating() {
        return rating;
    }

    public String getImgUrl() {
        return imgUrl;
    }
}
