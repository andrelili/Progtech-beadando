package com.example.sorozatok.observer;

import java.util.List;
import java.util.ArrayList;

public abstract class Observable<T> {
    private List<T> observers = new ArrayList<>();

    public void addObserver(T observer) {
        observers.add(observer);
    }

    public void removeObserver(T observer) {
        observers.remove(observer);
    }

    protected void notifyObservers(java.util.function.Consumer<T> notifier) {
        for (T observer : observers) {
            notifier.accept(observer);
        }
    }
}
