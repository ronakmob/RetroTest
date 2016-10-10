package com.rx.retro;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ronakmehta on 9/29/16.
 * AndroidSchedulers.mainThread() /// run some code on the UI thread
 */
public abstract class RXRetroManager<T> {

    protected abstract void onSuccess(T response);

    protected void onFailure(RetrofitException retrofitException) {
    }

    protected void onComplete() {
    }

    public void RXSingleCall(Observable<T> observable) {
        Observable.just(observable
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getSubscription()));
    }

    private Subscriber<T> getSubscription() {
        return new Subscriber<T>() {
            @Override
            public void onCompleted() {
                onComplete();
            }

            @Override
            public void onError(Throwable e) {
                RetrofitException retrofitException = (RetrofitException) e;
                if (retrofitException != null) {
                    if (retrofitException.getKind() == RetrofitException.Kind.NETWORK) {
                    } else if (retrofitException.getKind() == RetrofitException.Kind.HTTP) {

                    }else if (retrofitException.getKind() == RetrofitException.Kind.TIMEOUT) {

                    }

                }
                onFailure(retrofitException);
            }

            @Override
            public void onNext(T t) {
                onSuccess(t);
            }
        };
    }
}
