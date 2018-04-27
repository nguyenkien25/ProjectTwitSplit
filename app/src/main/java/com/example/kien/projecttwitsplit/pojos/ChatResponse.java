package com.example.kien.projecttwitsplit.pojos;

public class ChatResponse extends ChatObject {
    public ChatResponse() {
    }

    @Override
    public int getType() {
        return ChatObject.RESPONSE_OBJECT;
    }
}
