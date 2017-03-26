package com.gogo.migi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import com.gogo.migi.config.Config;
import com.gogo.migi.model.Chat;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import cz.msebera.android.httpclient.Header;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private EditText editText;
    private ChatAdapter chatAdapter;
    private ArrayList<Chat> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.lvChat);
        editText = (EditText)findViewById(R.id.etMessage);

        list = new ArrayList<Chat>();
    }

    public void sendClicked(View view) throws UnsupportedEncodingException {
        String message = this.editText.getText().toString();
        this.editText.setText("");

        sendQuery(Config.URL + "/query?message=" + message);

        updateAdapter(message);
    }

    public void updateAdapter(String message) {
        list.add(new Chat(message));
        this.chatAdapter = new ChatAdapter(this.list, MainActivity.this);

        listView.setAdapter(this.chatAdapter);
    }

    public void sendQuery(String url){
        RequestParams params = new RequestParams();

        //params.put("message", message);

        AsyncHttpClient client = new AsyncHttpClient();

        client.get(url, params, new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, final JSONObject response) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        updateAdapter(response.toString());
                        editText.setText("");
                    }
                });
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Toast.makeText(
                        getApplicationContext(),
                        "Something went wrong :(",
                        Toast.LENGTH_LONG
                ).show();
            }
        });
    }
}
