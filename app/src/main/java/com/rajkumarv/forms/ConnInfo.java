package com.rajkumarv.forms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ConnInfo extends AppCompatActivity {

    TextView host;
    TextView port;
    TextView path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conn_info);

        host = (TextView) findViewById(R.id.host);
        port = (TextView) findViewById(R.id.port);
        path = (TextView) findViewById(R.id.path);

        host.setText(getIntent().getStringExtra("host"));
        port.setText(getIntent().getStringExtra("port"));
        path.setText(getIntent().getStringExtra("path"));
    }

    public void update(View view) {

        Intent intent = new Intent();
        intent.putExtra("host", host.getText().toString());
        intent.putExtra("port", port.getText().toString());
        intent.putExtra("path", path.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }
}