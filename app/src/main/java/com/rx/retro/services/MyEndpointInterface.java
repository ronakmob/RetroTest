package com.rx.retro.services;


import com.rx.retro.model.Comments;
import com.rx.retro.model.Post;

import org.w3c.dom.Comment;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface MyEndpointInterface {

    @GET("/topstories.json")
    Observable<List<Long>> getTopStories();

    @GET("/comments")
    Observable<List<Comments>> getComments();

    /**
     * Return a list of a users post IDs.
     */
    @GET("/posts/{id}")
    Observable<Post> getPostByNumber(@Path("id") String id);

    /**
     * Return story item.
     */
    @GET("/v0/item/{itemId}.json")
    Observable<Post> getStoryItem(@Path("itemId") String itemId, @Query("print") String print);

    /**
     * Returns a comment item.
     */
    @GET("/item/{itemId}.json")
    Observable<Comment> getCommentItem(@Path("itemId") String itemId);
}
