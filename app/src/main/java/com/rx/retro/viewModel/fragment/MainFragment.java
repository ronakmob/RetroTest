package com.rx.retro.viewModel.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.rx.retro.sample.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ronakmehta on 10/5/16.
 */
public class MainFragment extends BaseFragment {

    @Bind(R.id.btnSecondFragment)
    Button btnSecondFragment;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        getBaseFragment().showBackButton(false);
        getBaseFragment().setToolBarTitle("Main Fragment");
        Toast.makeText(getActivity(), "Main screen called", Toast.LENGTH_SHORT).show();
        return view;
    }


    @OnClick(R.id.btnSecondFragment)
    void goToSecondFragment() {
        getBaseFragment().loadFragment(new SecondFragment(), true);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
