package com.rx.retro.viewModel.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.rx.retro.RXRetroManager;
import com.rx.retro.RetrofitException;
import com.rx.retro.model.Post;
import com.rx.retro.sample.R;
import com.rx.retro.sample.databinding.FragmentMainBinding;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.rx.retro.sample.R.layout.fragment_main;

/**
 * Created by ronakmehta on 10/5/16.
 */
public class MainFragment extends BaseFragment {

    @Bind(R.id.btnSecondFragment)
    Button btnSecondFragment;

    FragmentMainBinding fragmentMainBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentMainBinding = DataBindingUtil.inflate(inflater, fragment_main, container, false);
        View view = fragmentMainBinding.getRoot();

        ButterKnife.bind(this, view);
        getBaseFragment().showBackButton(true);
        getBaseFragment().setToolBarTitle("Main Fragment");

        simpleCall();

        return view;
    }

    public void simpleCall() {
        new RXRetroManager<Post>() {
            @Override
            protected void onFailure(RetrofitException retrofitException) {
                super.onFailure(retrofitException);
                Toast.makeText(getActivity(), "error +" + retrofitException.getResponse().errorBody(), Toast.LENGTH_SHORT).show();
            }

            @Override
            protected void onSuccess(Post response) {
                fragmentMainBinding.setPost(response);
                Toast.makeText(getActivity(), response.getTitle(), Toast.LENGTH_SHORT).show();
            }
        }.RXSingleCall(getBaseFragment().getAppService().getPostByNumber("1"));
    }

    @OnClick(R.id.btnSecondFragment)
    void goToSecondFragment() {
        getBaseFragment().replaceFragment(new SecondFragment(), true);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
