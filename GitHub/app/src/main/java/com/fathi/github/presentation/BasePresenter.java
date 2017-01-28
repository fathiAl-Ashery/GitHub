package com.fathi.github.presentation;

public interface BasePresenter {
    void start();
    void attachToView(BaseView view);
    void stop();
}
