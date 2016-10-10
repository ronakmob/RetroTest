package com.rx.retro;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by ronakmehta on 9/29/16.
 * AndroidSchedulers.mainThread() /// run some code on the UI thread
 */
public abstract class RXNestedCallManager<T, E> {

    protected abstract void onSuccess(T response);

    protected void onSuccessObj(Object response) {
    }


    protected void onFailure(RetrofitException retrofitException) {
    }

    protected void onComplete() {
    }

    public void RXDependentCall(Observable<T> observable1, final Observable<E> observable2) {
        observable1.subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap(new Func1<T, Observable<?>>() {
                @Override
                public Observable<?> call(T t) {
                    return observable2.subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread());
                }
            }).subscribe(new Subscriber<Object>() {
            @Override
            public void onCompleted() {
                onComplete();

            }

            @Override
            public void onError(Throwable e) {
                RetrofitException retrofitException = (RetrofitException) e;
                onFailure(retrofitException);
            }

            @Override
            public void onNext(Object o) {
                onSuccessObj(o);
            }
        });
    }
}
