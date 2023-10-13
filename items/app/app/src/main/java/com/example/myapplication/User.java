package com.example.myapplication;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author u7574421 Simon Fu
 * This class defines a user, and each instance of this class represents a unique user.
 */
public class User implements Serializable {
    private String username;
    private String profileUrl;
    private int balance;
    private int status;
    private ArrayList<Game> wishlist;
    private ArrayList<Game> library;
    private ArrayList<User> friends;
    private String emailAddress;
    public User(){}

    public User(String username, String profileUrl, int balance, int status,ArrayList<Game> wishlist, ArrayList<User> friends,ArrayList<Game> library) {
        this.username = username;
        this.profileUrl = profileUrl;
        this.balance = balance;
        this.status = status;
        this.wishlist = wishlist;
        this.friends = friends;
        this.library=library;
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
    public int getStatus(){ return status;}

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
    public void setStatus(int status){ this.status = status;}

    public void setWishlist(ArrayList<Game> wishlist) {
        this.wishlist = wishlist;
    }

    public void setFriends(ArrayList<User> friends) {
        this.friends = friends;
    }

    public ArrayList<Game> getLibrary() {
        return library;
    }
    public void setLibrary(ArrayList<Game> library) {
        this.library = library;
    }
    public void setEmailAddress(String emailAddress){ this.emailAddress = emailAddress;}
    public String getEmailAddress(){ return emailAddress;}
}
