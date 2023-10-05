package com.example.myapplication;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private String username;
    private String profileUrl;
    private int balance;
    private ArrayList<Game> wishlist;
    private ArrayList<User> friends;
    public User(){}

    public User(String username, String profileUrl, int balance, ArrayList<Game> wishlist, ArrayList<User> friends) {
        this.username = username;
        this.profileUrl = profileUrl;
        this.balance = balance;
        this.wishlist = wishlist;
        this.friends = friends;
    }

    public String getUsername() {
        return username;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public int getBalance() {
        return balance;
    }

    public ArrayList<Game> getWishlist() {
        return wishlist;
    }

    public ArrayList<User> getFriends() {
        return friends;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setWishlist(ArrayList<Game> wishlist) {
        this.wishlist = wishlist;
    }

    public void setFriends(ArrayList<User> friends) {
        this.friends = friends;
    }
}
