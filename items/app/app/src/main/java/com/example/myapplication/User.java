package com.example.myapplication;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private String username;
    private String profileUrl;
    private int balance;
    private boolean status;
    private ArrayList<Game> wishlist;
    private ArrayList<Friend> friends;
    public User(){}

    public User(String username, String profileUrl, int balance, boolean status, ArrayList<Game> wishlist, ArrayList<Friend> friends) {
        this.username = username;
        this.profileUrl = profileUrl;
        this.balance = balance;
        this.status = status;
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
    public boolean getStatus(){ return status;}

    public ArrayList<Game> getWishlist() {
        return wishlist;
    }

    public ArrayList<Friend> getFriends() {
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
    public void setStatus(boolean status){ this.status = status;}

    public void setWishlist(ArrayList<Game> wishlist) {
        this.wishlist = wishlist;
    }

    public void setFriends(ArrayList<Friend> friends) {
        this.friends = friends;
    }
}
