package ru.itis.usanov.model;

public class Commodity {

    private int id;
    private String name;
    private String description;
    private String image;
    private double price;
    private int countOfGrade;
    private double rating;

    public int getCountOfGrade() {
        return countOfGrade;
    }

    public void setCountOfGrade(int countOfGrade) {
        this.countOfGrade = countOfGrade;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
