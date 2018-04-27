package com.example.kien.projecttwitsplit.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kien.projecttwitsplit.R;
import com.example.kien.projecttwitsplit.pojos.ChatObject;
import com.example.kien.projecttwitsplit.ui.adapters.ViewHolders.BaseViewHolder;
import com.example.kien.projecttwitsplit.ui.adapters.ViewHolders.ChatInputVH;
import com.example.kien.projecttwitsplit.ui.adapters.ViewHolders.ChatResponseVH;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private static final String TAG = "adapter";
    private ArrayList<ChatObject> chatObjects;

    public ChatAdapter(ArrayList<ChatObject> chatObjects) {
        this.chatObjects = chatObjects;
        Log.d(TAG, "ChatAdapter: ");
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View itemView;
        switch (viewType) {
            case ChatObject.INPUT_OBJECT:
                Log.d(TAG, "onCreateViewHolder: input");
                itemView = inflater.inflate(R.layout.chat_input_layout, parent, false);
                return new ChatInputVH(itemView);
            case ChatObject.RESPONSE_OBJECT:
                Log.d(TAG, "onCreateViewHolder: response");
                itemView = inflater.inflate(R.layout.chat_response_layout, parent, false);
                return new ChatResponseVH(itemView);
            default:
                Log.d(TAG, "onCreateViewHolder: input");
                itemView = inflater.inflate(R.layout.chat_response_layout, parent, false);
                return new ChatResponseVH(itemView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBindView(chatObjects.get(position));
    }

    @Override
    public int getItemCount() {
        return chatObjects.size();
    }

    @Override
    public int getItemViewType(int position) {
        return chatObjects.get(position).getType();
    }

    public void clear() {
        chatObjects.clear();
        notifyDataSetChanged();
    }

    public void add(ChatObject chatObject) {
        chatObjects.add(chatObject);
        notifyItemInserted(chatObjects.size() - 1);
    }

    public void addAll(ArrayList<ChatObject> chatObjects) {
        for (ChatObject chatObject : chatObjects) {
            add(chatObject);
        }
    }

    public void remove(ChatObject chatObject) {
        int pos = chatObjects.indexOf(chatObject);
        if (pos > -1) {
            chatObjects.remove(pos);
            notifyItemRemoved(pos);
        }
    }

}
