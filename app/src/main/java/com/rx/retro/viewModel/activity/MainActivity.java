package com.rx.retro.viewModel.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.rx.retro.RXNestedCallManager;
import com.rx.retro.RXRetroManager;
import com.rx.retro.RetrofitException;
import com.rx.retro.application.RetroApp;
import com.rx.retro.model.Post;
import com.rx.retro.model.User;
import com.rx.retro.sample.R;
import com.rx.retro.services.MyEndpointInterface;
import com.rx.retro.viewModel.fragment.BaseFragment;
import com.rx.retro.viewModel.fragment.MainFragment;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity implements BaseFragment.BaseCallBack {
    @Bind(R.id.toolBar)
    Toolbar toolbar;

    @Inject
    MyEndpointInterface myEndpointInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((RetroApp) getApplication()).getServiceComponent().inject(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        navigateTo(MainActivity.this, new MainFragment(), true, false);

        //nestedCall();
        setUpToolBar();
        // simpleCall();
        // getUserResponseThroughRXConcat();
        // getUserResponseThroughAPIManager();
        // RXDependentCall();
    }

    private void setUpToolBar() {
        setSupportActionBar(toolbar);
    }


    public void nestedCall() {
        new RXNestedCallManager<User, Post>() {
            @Override
            protected void onSuccess(User response) {
                // txtView.append("\n Name " + response.id);
            }

            @Override
            protected void onSuccessObj(Object response) {
                super.onSuccessObj(response);
                // txtView.append("\n Name " + ((Post) response).text);
            }

            @Override
            protected void onFailure(RetrofitException retrofitException) {
                super.onFailure(retrofitException);
                Toast.makeText(MainActivity.this, "error +" + retrofitException.getResponse().errorBody(), Toast.LENGTH_SHORT).show();
            }
        }.RXDependentCall(myEndpointInterface.getUser("dhouston", "pretty"), myEndpointInterface.getStoryItem("2488", "pretty"));
    }

    public void simpleCall() {
        new RXRetroManager<User>() {
            @Override
            protected void onFailure(RetrofitException retrofitException) {
                super.onFailure(retrofitException);
                Toast.makeText(MainActivity.this, "error +" + retrofitException.getResponse().errorBody(), Toast.LENGTH_SHORT).show();
            }

            @Override
            protected void onSuccess(User response) {
                Log.e("error", response.about);
                // txtView.append("\n Name " + response.id);
            }
        }.RXSingleCall(myEndpointInterface.getUser("dhouston", "pretty"));
    }

    public void getUserResponseThroughAPIManager() {
      /*   new ApiManager<UserResponse>(myEndpointInterface.loginWithRetrofit(loginRequestDto)) {
            @Override
            public void onSuccess(Response<UserResponse> response) {
                txtView.append("\n" + response.body().getData().getFirstName());
            }

            @Override
            public void onFail(Response<UserResponse> response) {
                super.onFail(response);
            }
        }; */
    }

    public void getUserResponseThroughRXConcat() {
      /*   new RXRetroManager<UserResponse>(myEndpointInterface.loginwithRX(loginRequestDto), myEndpointInterface.loginwithRX(loginRequestDto)) {
            @Override
            public void onSuccess(UserResponse response) {
                Log.e("", "onSuccess: " + response);
            }
        }; */
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void loadFragment(BaseFragment baseFragment, boolean addToStack) {
        navigateTo(MainActivity.this, baseFragment, addToStack, false);
    }


    @Override
    public void setRootFragment(BaseFragment baseFragment) {
        navigateToAsRoot(MainActivity.this, baseFragment);
    }

    @Override
    public void replaceFragment(BaseFragment baseFragment, boolean addToStack) {
        replaceWith(MainActivity.this, baseFragment);
    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showBackButton(boolean show) {
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(show);
    }

    @Override
    public void setToolBarTitle(String title) {
        if (toolbar != null) {
            toolbar.setTitle(title);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
