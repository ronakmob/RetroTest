package com.rx.retro.services;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by ronakmehta on 9/29/16.
 */
public abstract class ServiceManager<T> {

    public abstract void onSuccess(T response);

    public void onFail() {
    }

    public void onAPIComplete() {
    }

    public enum ServiceType{
        SINGLE,
        DOUBLE;
    }

    CompositeSubscription compositeSubscription = new CompositeSubscription();

    public ServiceManager(Observable<T> login) {
        compositeSubscription.add(callSingleService(login)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Subscriber<T>() {
                @Override
                public void onCompleted() {
                    onAPIComplete();
                }

                @Override
                public void onError(Throwable e) {
                    onFail();

                }

                @Override
                public void onNext(T t) {
                    onSuccess(t);
                }
            }));
    }

    private Observable<T> callSingleService(Observable<T> observable) {
        return observable.concatMap(new Func1<T, Observable<? extends T>>() {
            @Override
            public Observable<? extends T> call(T t) {
                return Observable.just(t);
            }
        });
    }
}
