package com.teamhaddy.app.chaterest;


import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;



/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TechFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TechFragment extends Fragment {

    private FirebaseListAdapter<ChatMessage> adapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TechFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TechFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TechFragment newInstance(String param1, String param2) {
        TechFragment fragment = new TechFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_tech, null);

        ImageView fab = (ImageView) root.findViewById(R.id.send_image);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText input = (EditText) root.findViewById(R.id.user_message);

                if(input.getText().toString().trim().length()!=0) {

                    // Read the input field and push a new instance
                    // of ChatMessage to the Firebase database
                    FirebaseDatabase.getInstance()
                            .getReference()
                            .child("Tech")
                            .push()
                            .setValue(new ChatMessage(input.getText().toString(),
                                    FirebaseAuth.getInstance()
                                            .getCurrentUser()
                                            .getDisplayName())
                            );

                    // Clear the input
                    input.setText("");

                }

            }
        });


        ListView listOfMessages = (ListView) root.findViewById(R.id.list_message);

        FirebaseListOptions<ChatMessage> options =
                new FirebaseListOptions.Builder<ChatMessage>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Tech").limitToLast(100), ChatMessage.class)
                        .setLayout(R.layout.messages)
                        .build();

        adapter = new FirebaseListAdapter<ChatMessage>(options) {
            @Override
            protected void populateView(View v, ChatMessage model, int position) {
                // Get references to the views of message.xml
                TextView messageText = (TextView)v.findViewById(R.id.message_text);
                TextView messageUser = (TextView)v.findViewById(R.id.message_user);
                TextView messageTime = (TextView)v.findViewById(R.id.message_time);

                // Set their text
                messageText.setText(model.getMessageText());
                messageUser.setText(model.getMessageUser());

                // Format the date before showing it
                messageTime.setText(DateFormat.format("HH:mm:ss  dd/MM/yyyy",
                        model.getMessageTime()));
            }
        };

        listOfMessages.setAdapter(adapter);

        ProgressBar pb = (ProgressBar) root.findViewById(R.id.progressbar_loading);
        pb.setVisibility(View.GONE);

        listOfMessages.setEmptyView(pb);


        return root;

    }

}