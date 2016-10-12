package com.rx.retro.viewModel.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.rx.retro.application.RetroApp;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by ronakmehta on 10/5/16.
 */
public abstract class BaseFragment extends Fragment {

    protected BaseCallBack baseFragment;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        baseFragment = (BaseCallBack) context;
    }

    public interface BaseCallBack {

        void showProgress();

        void loadFragment(BaseFragment baseFragment, boolean addToStack);

        void setRootFragment(BaseFragment baseFragment);

        void replaceFragment(BaseFragment baseFragment, boolean addToStack);

        void hideProgress();

        void showBackButton(boolean show);

        void setToolBarTitle(String title);
    }

    public BaseCallBack getBaseFragment() {
        return baseFragment;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = RetroApp.getRefWatcher(getActivity());
        refWatcher.watch(this);
    }
}
