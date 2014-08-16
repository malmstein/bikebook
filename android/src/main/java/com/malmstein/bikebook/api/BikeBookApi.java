package com.malmstein.bikebook.api;

import com.malmstein.bikebook.model.Index;

import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscriber;
import rx.schedulers.Schedulers;

import static com.google.common.base.Preconditions.checkNotNull;

public class BikeBookApi {

    private final BikeBook backend;
    private final Scheduler mainThreadScheduler;
    private final String baseUrl;

    public BikeBookApi(final BikeBook bikeBook, final Scheduler mainThreadScheduler, final String baseUrl) {
        this.baseUrl = baseUrl;
        this.backend = checkNotNull(bikeBook);
        this.mainThreadScheduler = checkNotNull(mainThreadScheduler);
    }

    public void getIndex(final Observer<Index> observer) {
        observeIndex(baseUrl).observeOn(mainThreadScheduler).subscribe(observer, Schedulers.io());
    }

    private Observable<Index> observeIndex(final String requestUrl) {
        return Observable.create(new Observable.OnSubscribe<Index>() {
            @Override
            public void call(final Subscriber<? super Index> subscriber) {
                try {
                    Index index = Index.from(backend.getIndex(requestUrl));
                    if (index != null) {
                        subscriber.onNext(index);
                        subscriber.onCompleted();
                    } else {
                        subscriber.onError(new RuntimeException("Index failed"));
                    }
                } catch (final Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }
}
