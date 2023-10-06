package com.example.myapplication;
import java.util.ArrayList;
public class Friend extends User{
    private boolean blocked;

    public Friend() {

    }

    public Friend(String username, String profileUrl, int balance, boolean status, ArrayList<Game> wishlist, ArrayList<Friend> friends, boolean blocked) {
        super(username, profileUrl, balance, status, wishlist, friends);
        this.blocked = blocked;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }
}
