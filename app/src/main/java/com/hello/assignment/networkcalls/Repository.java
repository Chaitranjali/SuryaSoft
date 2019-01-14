package com.hello.assignment.networkcalls;


public interface Repository<T> {

    void onCompleted();
    void onError(Throwable e);
    void onNext(T t);

}
