package com.example.kien.projecttwitsplit.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.kien.projecttwitsplit.utils.AppController;
import com.example.kien.projecttwitsplit.R;
import com.example.kien.projecttwitsplit.pojos.ChatInput;
import com.example.kien.projecttwitsplit.pojos.ChatObject;
import com.example.kien.projecttwitsplit.pojos.ChatResponse;
import com.example.kien.projecttwitsplit.pojos.Message;
import com.example.kien.projecttwitsplit.ui.adapters.ChatAdapter;
import com.example.kien.projecttwitsplit.utils.MessageUtils;
import com.example.kien.projecttwitsplit.utils.Utils;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "main";

    @BindView(R.id.listChat)
    RecyclerView listChat;
    @BindView(R.id.edtMessage)
    EditText edtMessage;
    @BindView(R.id.btnSendMsg)
    ImageButton btnSendMsg;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private ChatAdapter chatAdapter;
    private ArrayList<ChatObject> chatObjects;

    private DatabaseReference firebaseDatabase;
    private FirebaseDatabase firebaseInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupUI();
        setupListeners();
    }

    private void setupUI() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.main_chat_title));

        chatObjects = new ArrayList<>();
        getData();
        chatAdapter = new ChatAdapter(chatObjects);
        listChat.setAdapter(chatAdapter);
        listChat.setLayoutManager(new LinearLayoutManager(this));
        listChat.setItemAnimator(new DefaultItemAnimator());
    }

    private void getData() {
        FirebaseDatabase.getInstance().getReference().child(Utils.MESSAGES_CHILD).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                Message message = dataSnapshot.getValue(Message.class);
                ChatObject chatObject = null;
                if (AppController.getInstance().getPrefManager().getUID().equals(message.getId())) {
                    chatObject = new ChatInput();
                } else {
                    chatObject = new ChatResponse();
                }
                chatObject.setText(message.getMassage());
                chatObjects.add(chatObject);
                Log.e(TAG, "message: " + message);
                chatAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, "Failed to read app title value.", databaseError.toException());
            }
        });
    }

    private void setupListeners() {
        btnSendMsg.setOnClickListener(v -> sendMsg());
    }

    private void sendMsg() {
        String msg = edtMessage.getText().toString();
        if (!msg.isEmpty()) {
            String[] msgs = MessageUtils.splitMessage(msg);
            if (msgs.length != 0) {
                for (int i = msgs.length - 1; i >= 0; i--) {
                    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
                    database.child(Utils.MESSAGES_CHILD).push().setValue(new Message(AppController.getInstance().getPrefManager().getUID(), msgs[i]));
                }
            }
            edtMessage.setText("");
        }
    }

    public void scrollToLastPosition() {
        listChat.scrollToPosition(chatObjects.size() - 1);
    }

    @Override
    public void onBackPressed() {
        AppController.getInstance().getPrefManager().clear();
        this.finish();
        super.onBackPressed();
    }
}
