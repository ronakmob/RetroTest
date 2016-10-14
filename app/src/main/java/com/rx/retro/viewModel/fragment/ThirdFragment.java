package com.rx.retro.viewModel.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.rx.retro.RXRetroManager;
import com.rx.retro.adapter.UserAdapter;
import com.rx.retro.model.Comments;
import com.rx.retro.sample.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ronakmehta on 10/6/16.
 */

public class ThirdFragment extends BaseFragment {

    @Bind(R.id.btnGo)
    Button btnGo;

    @Bind(R.id.recycleView)
    RecyclerView recycleView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_third, container, false);

        ButterKnife.bind(this, view);
        getBaseFragment().showBackButton(true);
        getBaseFragment().setToolBarTitle("Third Fragment");

        new RXRetroManager<List<Comments>>() {
            @Override
            protected void onSuccess(List<Comments> response) {
                UserAdapter userAdapter = new UserAdapter(response);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                recycleView.setLayoutManager(layoutManager);
                recycleView.setAdapter(userAdapter);
            }
        }.RXSingleCall(getBaseFragment().getAppService().getComments());

        return view;
    }

    @OnClick(R.id.btnGo)
    void setBtnGo() {
        getBaseFragment().replaceFragment(new FourFragment(), true);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
