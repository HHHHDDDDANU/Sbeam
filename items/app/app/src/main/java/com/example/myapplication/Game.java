package com.example.myapplication;

import java.io.Serializable;

public class Game implements Serializable {
    private String name;
    private int year;
    private String producer;
    private int review;

    private int price;
    String url;
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
}

