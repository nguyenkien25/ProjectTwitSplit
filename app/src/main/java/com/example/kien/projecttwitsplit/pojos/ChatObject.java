package com.example.kien.projecttwitsplit.pojos;

import android.support.annotation.NonNull;

public abstract class ChatObject {

    public static final int INPUT_OBJECT = 0;
    public static final int RESPONSE_OBJECT = 1;

    private String text;

    public ChatObject() {
    }

    @NonNull
    public String getText() {
        return text;
    }

    public void setText(@NonNull String text) {
        this.text = text;
    }

    public abstract int getType();
}
