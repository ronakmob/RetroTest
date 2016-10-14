package com.rx.retro.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rx.retro.model.Comments;
import com.rx.retro.sample.R;

import java.util.List;

/**
 * Created by ronakmehta on 10/14/16.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<Comments> mCommentsList;

    public UserAdapter(List<Comments> posts) {
        mCommentsList = posts;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        holder.txtPost.setText(mCommentsList.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return mCommentsList.size();
    }

     class UserViewHolder extends RecyclerView.ViewHolder {
        TextView txtPost;

         UserViewHolder(View itemView) {
            super(itemView);
            txtPost = (TextView) itemView.findViewById(R.id.txtPost);
        }
    }
}
