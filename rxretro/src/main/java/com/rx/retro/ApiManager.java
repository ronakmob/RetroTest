package com.rx.retro;

import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ronakmehta on 9/6/16.
 */
public abstract class ApiManager<S> {
    public abstract void onAPINext(S s);

    public abstract void onAPIError(RetrofitException error);

    public ApiManager(final FragmentActivity activity, Observable<S> user) {
        user.subscribeOn(Schedulers.newThread()).
            observeOn(AndroidSchedulers.mainThread()).
            subscribe(new Observer<S>() {
                @Override
                public void onCompleted() {
                    Toast.makeText(activity, "onCompleted", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onError(Throwable e) {
                    RetrofitException error = (RetrofitException) e;
                    if (error != null) {
                        onAPIError(error);
                    }
                }

                @Override
                public void onNext(S s) {
                    Toast.makeText(activity, "Success", Toast.LENGTH_SHORT).show();
                    onAPINext(s);
                }
            });
    }
}
