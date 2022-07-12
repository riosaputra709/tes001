package com.example.androidchatapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.example.androidchatapp.Common.Common;
import com.example.androidchatapp.Listener.IFirebaseLoadFailed;
import com.example.androidchatapp.Listener.ILoadTimeFromFirebaseListener;
import com.example.androidchatapp.Model.ChatMessageModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatActivity extends AppCompatActivity {

    private static final int MY_CAMERA_REQUEST_CODE = 7171;
    private static final int MY_RESULT_LOAD_IMAGE = 7172;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.img_camera)
    ImageView img_camera;
    @BindView(R.id.img_image)
    ImageView img_image;
    @BindView(R.id.edt_chat)
    AppCompatEditText edt_chat;
    @BindView(R.id.img_send)
    ImageView img_send;
    @BindView(R.id.recycler_chat)
    RecyclerView recycler_chat;
    @BindView(R.id.img_preview)
    ImageView img_preview;
    @BindView(R.id.img_avatar)
    ImageView img_avatar;
    @BindView(R.id.txt_name)
    TextView txt_name;

    FirebaseDatabase database;
    DatabaseReference chatRef, offsetRef;
    ILoadTimeFromFirebaseListener listener;
    IFirebaseLoadFailed errorListener;

    FirebaseRecyclerAdapter<ChatMessageModel, RecyclerView.ViewHolder> adapter;
    FirebaseRecyclerOptions<ChatMessageModel> options;

    Uri fileUri;

    LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        initView();
        loadChatContent();
    }

    private void initView() {
        listener = this;
        errorListener = this;
        database = FirebaseDatabase.getInstance();
        chatRef = database.getReference(Common.CHAT_REFERENCE);

        offsetRef = database.getReference(".info/serverTimeOffset");

        Query query = chatRef.child(Common.generateChatRoomId(
                Common.chatUser.getUid(),
                FirebaseAuth.getInstance().getCurrentUser().getUid()
        )).child(Common.CHAT_DETAIL_REFERENCE);

        options = new FirebaseRecyclerOptions.Builder<ChatMessageModel>()
                .setQuery(query,ChatMessageModel.class).build();
        ButterKnife.bind(this);
        layoutManager = new LinearLayoutManager(this);
        recycler_chat.setLayoutManager(layoutManager);

        ColorGenerator generator = ColorGenerator.MATERIAL;
        int color = generator.getColor(Common.chatUser.getUid());
        TextDrawable.IBuilder builder = TextDrawable.builder()
                .beginConfig()
                .withBorder(4)
                .endConfig()
                .round();
        TextDrawable drawable = builder.build(Common.chatUser.getFirstName().substring(0,1),color);
        img_avatar.setImageDrawable(drawable);
        txt_name.setText(Common.getName(Common.chatUser));

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(v -> {
            finish();
        });
    }
}