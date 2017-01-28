package com.fathi.github.data;

/**
 * Created by admin on 1/28/17.
 */

import android.support.annotation.NonNull;

import com.fathi.github.data.model.CodeRepository;

import java.util.List;


public interface ReposDataSource {

    interface LoadCallback<T> {

        void onDataLoaded(List<T> itemsList);

        void onDataNotAvailable();
    }


    void getRepositories(@NonNull String organizationName, @NonNull int pageIndex, @NonNull int pageSize, @NonNull LoadCallback<CodeRepository> callback);

    void refreshData();

}
