package com.example.kien.projecttwitsplit.ui.adapters.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.kien.projecttwitsplit.pojos.ChatObject;

public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void onBindView(ChatObject chatObject);
}
