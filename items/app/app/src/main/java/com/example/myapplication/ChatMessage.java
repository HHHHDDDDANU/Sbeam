package com.example.myapplication;

public class ChatMessage {
    private String message;
    private User send_user;
    private User recieve_user;
    public ChatMessage(String message, User send_user, User recieve_user){
        this.message = message;
        this.send_user = send_user;
        this.recieve_user = recieve_user;
    }
    public ChatMessage(){}
    public String getMessage(){
        return message;
    }
    public void setMessage(String message){
        this.message = message;
    }
    public User getSend_user(){
        return send_user;
    }
    public User getRecieve_user(){
        return recieve_user;
    }

}
