package com.example.kien.projecttwitsplit.ui.adapters.ViewHolders;

import android.view.View;
import android.widget.TextView;

import com.example.kien.projecttwitsplit.R;
import com.example.kien.projecttwitsplit.pojos.ChatObject;

public class ChatInputVH extends BaseViewHolder {
    private TextView tvInputText;

    public ChatInputVH(View itemView) {
        super(itemView);
        tvInputText = itemView.findViewById(R.id.tv_input_text);
    }

    @Override
    public void onBindView(ChatObject chatObject) {
        tvInputText.setText(chatObject.getText());
    }
}
