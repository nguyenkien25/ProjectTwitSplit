package com.example.kien.projecttwitsplit.ui.adapters.ViewHolders;

import android.view.View;
import android.widget.TextView;

import com.example.kien.projecttwitsplit.R;
import com.example.kien.projecttwitsplit.pojos.ChatObject;

public class ChatResponseVH extends BaseViewHolder {

    private TextView tvResponseText;

    public ChatResponseVH(View itemView) {
        super(itemView);
        tvResponseText = itemView.findViewById(R.id.tv_response_text);
    }

    @Override
    public void onBindView(ChatObject chatObject) {
        tvResponseText.setText(chatObject.getText());
    }
}
