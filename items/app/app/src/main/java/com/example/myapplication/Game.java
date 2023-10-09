package com.example.myapplication;

import java.io.Serializable;
import java.util.Objects;

public class Game implements Serializable {
    private String name;
    private int year;
    private String producer;
    private int review;

    private int price;
    private String type;
    private String description;
    private String url;
    private String url_l;
    public Game(String name, int year, String producer, int review,int price){
        this.name=name;
        this.year=year;
        this.producer=producer;
        this.review=review;
        this.price=price;
    }
    public Game(){}


    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public String getProducer() {
        return producer;
    }

    public int getReview() {
        return review;
    }

    public int getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public void setReview(int review) {
        this.review = review;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return year == game.year && review == game.review && price == game.price && Objects.equals(name, game.name) && Objects.equals(producer, game.producer) && Objects.equals(type, game.type) && Objects.equals(description, game.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, year, producer, review, price, type, description);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public String getUrl_l() {
        return url_l;
    }
    public void setUrl_l(String url_l) {
        this.url_l = url_l;
    }
}

