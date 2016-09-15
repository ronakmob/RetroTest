package com.rx.retro.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.rx.retro.ApiManager;
import com.rx.retro.RetrofitException;

import java.io.IOException;

import okhttp3.ResponseBody;

public class MainActivity extends AppCompatActivity {
    private TextView txtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtView = (TextView) findViewById(R.id.txtView);

        LoginRequest loginRequestDto =
            new LoginRequest("rmehta@mobiquityinc.com", "Qwerty123");

        new ApiManager<ResponseBody>(MainActivity.this, ((TestApp) getApplication()).getMyEndpointInterface().login(loginRequestDto)) {
            @Override
            public void onAPINext(ResponseBody responseBody) {
                try {
                    txtView.setText(responseBody.string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onAPIError(RetrofitException error) {
                Toast.makeText(MainActivity.this, error.getKind().toString(), Toast.LENGTH_SHORT).show();
            }
        };
    }
}
