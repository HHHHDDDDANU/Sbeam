package com.example.myapplication;

/**
 * @author u7618768 Connor Li
 * Chat Message.
 */

public class ChatMessage {
    //private int index;
    private String message;
    private String receive_user_name;
    private String send_user_name;
    public ChatMessage(String message, String send_user_name, String receive_user_name){
        //this.index = index;
        this.message = message;
        this.receive_user_name = receive_user_name;
        this.send_user_name = send_user_name;
    }
    public ChatMessage(){}
    public String getMessage(){
        return message;
    }
    public void setMessage(String message){
        this.message = message;
    }
    public String getSend_user_name(){
        return send_user_name;
    }
    public void setSend_user_name(String send_user_name){
        this.send_user_name = send_user_name;
    }
    public String getReceive_user_name(){
        return receive_user_name;
    }
    public void setReceive_user_name(String receive_user_name){
        this.receive_user_name = receive_user_name;
    }
    public void printMessage(){
        System.out.println("send: " + send_user_name + "receive: " + receive_user_name + "message: " + message);
    }
//    public int getIndex(){
//        return index;
//    }
//    public void setIndex(int index){
//        this.index = index;
//    }

}
