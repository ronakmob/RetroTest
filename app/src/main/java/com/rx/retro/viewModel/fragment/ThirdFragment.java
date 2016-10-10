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
 * Created by ronakmehta on 10/6/16.
 */

public class ThirdFragment extends BaseFragment {

    @Bind(R.id.btnGo)
    Button btnGo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_third, container, false);
        ButterKnife.bind(this, view);
        getBaseFragment().showBackButton(true);
        getBaseFragment().setToolBarTitle("Third Fragment");
        Toast.makeText(getActivity(), "Third screen called", Toast.LENGTH_SHORT).show();
        return view;
    }

    @OnClick(R.id.btnGo)
    void setBtnGo() {
        getBaseFragment().loadFragment(new MainFragment(), true);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
