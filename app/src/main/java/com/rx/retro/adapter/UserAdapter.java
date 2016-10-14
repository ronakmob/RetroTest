package com.rx.retro.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.rx.retro.model.Comments;
import com.rx.retro.sample.R;
import com.rx.retro.sample.databinding.ItemPostBinding;

import java.util.List;

/**
 * Created by ronakmehta on 10/14/16.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<Comments> mCommentsList;
    private Context mContext;
    public UserAdapter(Context activity, List<Comments> mCommentsList) {
        this.mCommentsList = mCommentsList;
        mContext = activity;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemPostBinding itemPostBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_post, parent, false);

        return new UserViewHolder(itemPostBinding);
    }


    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        ItemPostBinding itemPostBinding = holder.itemPostBinding;
        itemPostBinding.setComments(mCommentsList.get(position));
    }

    @Override
    public int getItemCount() {
        return mCommentsList.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {

        private ItemPostBinding itemPostBinding;

        UserViewHolder(ItemPostBinding itemPostBinding) {
            super(itemPostBinding.getRoot());
            this.itemPostBinding = itemPostBinding;

        }
    }
}
