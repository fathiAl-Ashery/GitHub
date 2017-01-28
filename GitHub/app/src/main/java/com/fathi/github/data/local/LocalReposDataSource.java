
package com.fathi.github.data.local;

import android.support.annotation.NonNull;

import com.fathi.github.data.ReposDataSource;
import com.fathi.github.data.model.CodeRepository;

/**
 * Implementation of the data source For GitHub Service
 */
public class LocalReposDataSource implements ReposDataSource {

    private static LocalReposDataSource INSTANCE;

    public static LocalReposDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LocalReposDataSource();
        }
        return INSTANCE;
    }

    // Prevent direct instantiation.
    private LocalReposDataSource() {
    }

    @Override
    public void getRepositories(@NonNull String organizationName, @NonNull int pageIndex, @NonNull int pageSize, @NonNull LoadCallback<CodeRepository> callback){
    }

    @Override
    public void refreshData() {
        //remove in-memory cache if any
    }
}
