package com.rajkumarv.forms;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    String host = "";
    String port = "";
    String path = "";
    TextView connInfo;
    TextView response;

    NotificationChannel channel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connInfo = (TextView) findViewById(R.id.connInfo);
        connInfo.setText("NULL");
        response = (TextView) findViewById(R.id.response);
    }

    public void updateConnInfo(View view) {
        Intent intent = new Intent(getApplicationContext(), ConnInfo.class);
        intent.putExtra("host", host);
        intent.putExtra("port", port);
        intent.putExtra("path", path);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 1) {
            host = data.getStringExtra("host");
            port = data.getStringExtra("port");
            path = data.getStringExtra("path");
            String connectionString = "http://" + host +  ":" + port + "/" + path;
            connInfo.setText(connectionString);
        }
    }

    public void fire(View view) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String connectionString = "http://" + host +  ":" + port + "/" + path;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, connectionString,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String resp) {
                        response.setText("resp: " + resp);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                response.setText("err: " + error.toString());
            }
        });

        queue.add(stringRequest);
    }
}