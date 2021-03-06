package com.rx.retro.viewModel.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.rx.retro.application.RetroApp;
import com.rx.retro.sample.R;
import com.rx.retro.services.MyEndpointInterface;
import com.rx.retro.viewModel.fragment.BaseFragment;
import com.rx.retro.viewModel.fragment.MainFragment;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity implements BaseFragment.BaseCallBack {
    @Bind(R.id.toolBar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((RetroApp) getApplication()).getServiceComponent().inject(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        addFragment(MainActivity.this, new MainFragment(), false);

        //nestedCall();
        setUpToolBar();
        // getUserResponseThroughRXConcat();
        // getUserResponseThroughAPIManager();
        // RXDependentCall();
    }

    private void setUpToolBar() {
        setSupportActionBar(toolbar);
    }


    public void nestedCall() {
       /* new RXNestedCallManager<User, Post>() {
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
        }.RXDependentCall(myEndpointInterface.getUser("dhouston", "pretty"), myEndpointInterface.getStoryItem("2488", "pretty")); */
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
    public void setRootFragment(BaseFragment baseFragment) {
    }

    @Override
    public void addFragment(BaseFragment baseFragment, boolean addToStack) {
        addFragment(MainActivity.this, baseFragment, true);
    }

    @Override
    public void replaceFragment(BaseFragment baseFragment, boolean addToStack) {
        replaceFragment(MainActivity.this, baseFragment, true);
    }

    @Override
    public void restoreFragment(BaseFragment baseFragment, boolean addToStack) {

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
    public MyEndpointInterface getAppService() {
        return getService();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        FragmentManager fm = getSupportFragmentManager();
        int count = fm.getBackStackEntryCount();
        if (count == 0) { // only 1 view on it, lets clean it
            finish();
        } else {
            // lets roll back to previous fragment
            FragmentManager.BackStackEntry entry = fm.getBackStackEntryAt(count - 1);
            // Must use 0 here, since we want to respect the order strictly
            fm.popBackStack(entry.getName(), 0);
        }
    }
}
