package com.example.kien.projecttwitsplit.pojos;

public class Message {
    private String id;
    private String massage;

    public Message() {
    }

    public Message(String id, String massage) {
        this.id = id;
        this.massage = massage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }
}
