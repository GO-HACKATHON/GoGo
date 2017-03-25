package com.gogo.migi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import com.gogo.migi.model.Chat;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private ListView listView;
    private EditText editText;
    private ChatAdapter chatAdapter;
    private ArrayList<Chat> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.lvChat);
        textView = (TextView)findViewById(R.id.textViewTest);
        editText = (EditText)findViewById(R.id.etMessage);

        list = new ArrayList<Chat>();
    }

    public void sendClicked(View view) throws UnsupportedEncodingException {
//        sendQuery(URLEncoder.encode(Config.URL + "/query?message=hai", "UTF-8"));
        updateAdapter();
    }

    public void updateAdapter() {
        list.add(new Chat(this.editText.getText().toString()));
        this.chatAdapter = new ChatAdapter(this.list, MainActivity.this);

        listView.setAdapter(this.chatAdapter);
    }
}
