package com.rx.retro.viewModel.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.rx.retro.sample.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ronakmehta on 10/6/16.
 */

public class SecondFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        getBaseFragment().showBackButton(true);
        getBaseFragment().setToolBarTitle("Second Fragment");
        Toast.makeText(getActivity(), "Second screen called", Toast.LENGTH_SHORT).show();
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.btnGo)
    void setBtnGo() {
        getBaseFragment().replaceFragment(new ThirdFragment(), false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
