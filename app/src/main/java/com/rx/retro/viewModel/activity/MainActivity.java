package com.rx.retro.viewModel.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.rx.retro.ApiManager;
import com.rx.retro.application.RetroApp;
import com.rx.retro.request.LoginRequest;
import com.rx.retro.response.UserResponse;
import com.rx.retro.sample.R;
import com.rx.retro.services.MyEndpointInterface;
import com.rx.retro.services.ServiceManager;

import javax.inject.Inject;

import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    private TextView txtView;

    @Inject
    MyEndpointInterface myEndpointInterface;

    LoginRequest loginRequestDto =
        new LoginRequest("rmehta", "Qwerty123");
    private Object userResponseThroughRX;
    private Object userResponseThroughAPIManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((RetroApp) getApplication()).getServiceComponent().inject(this);

        setContentView(R.layout.activity_main);

        txtView = (TextView) findViewById(R.id.txtView);

        getUserResponseThroughRX();
        getUserResponseThroughAPIManager();
    }

    public void getUserResponseThroughRX() {
        new ServiceManager<UserResponse>(myEndpointInterface.loginwithRX(loginRequestDto)) {
            @Override
            public void onSuccess(UserResponse response) {
                Toast.makeText(MainActivity.this, "UserResponse", Toast.LENGTH_SHORT).show();
                txtView.setText(response.getData().getEmail());
            }

            @Override
            public void onFail() {
                super.onFail();
                Toast.makeText(MainActivity.this, "onFail", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAPIComplete() {
                super.onAPIComplete();
                Toast.makeText(MainActivity.this, "onAPIComplete", Toast.LENGTH_SHORT).show();
            }
        };
    }

    public void getUserResponseThroughAPIManager() {
        new ApiManager<UserResponse>(myEndpointInterface.loginWithRetrofti(loginRequestDto)) {
            @Override
            public void onSuccess(Response<UserResponse> response) {
                txtView.append("\n"+response.body().getData().getFirstName());
            }
        };
    }
}
