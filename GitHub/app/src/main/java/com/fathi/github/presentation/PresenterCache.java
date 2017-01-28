package com.fathi.github.presentation;

import android.support.v4.util.SimpleArrayMap;
import android.util.Log;


/**
 * Created by admin on 1/27/17.
 */

public class PresenterCache {
    private static PresenterCache instance = null;

    private SimpleArrayMap<String, BasePresenter> presenters;

    private PresenterCache() {}

    public static PresenterCache getInstance() {
        if (instance == null) {
            instance = new PresenterCache();
        }
        return instance;
    }

    public BasePresenter getPresenter(String who) {
        if (presenters == null) {
            presenters = new SimpleArrayMap<>();
        }

        BasePresenter p = null;

        try {
            p = presenters.get(who);
        } catch (ClassCastException e) {
            Log.w("PresenterActivity", "Duplicate Presenter " +
                    "tag identified: " + who + ". This could " +
                    "cause issues with state.");
        }

        return p;
    }

    public void addPresenter(String who, BasePresenter presenter) {
        if (presenters == null) {
            presenters = new SimpleArrayMap<>();
        }

        try {
            presenters.put(who, presenter);
        } catch (ClassCastException e) {
            Log.w("PresenterActivity", "Duplicate Presenter " +
                    "tag identified: " + who + ". This could " +
                    "cause issues with state.");
        }


    }

    /**
     * Remove the presenter associated with the given tag
     *
     * @param who A unique tag to identify the presenter
     */
    public final void removePresenter(String who) {
        if (presenters != null) {
            presenters.remove(who);
        }
    }

}
