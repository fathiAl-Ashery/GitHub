package com.fathi.github.data;

/**
 * Created by admin on 1/28/17.
 */

import android.support.annotation.NonNull;

import com.fathi.github.data.model.CodeRepository;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;


public class ReposRepository implements ReposDataSource {

    private static ReposRepository INSTANCE = null;

    private final ReposDataSource mRemoteReposDataSource;

    /**
     * This variable has package local visibility so it can be accessed from tests.
     */
    List<CodeRepository> cachedRepositories = null;


    /**
     * Marks the cache as invalid, to force an update the next time data is requested. This variable
     * has package local visibility so it can be accessed from tests.
     */
    boolean invalidCache = false;

    // Prevent direct instantiation.
    private ReposRepository(@NonNull ReposDataSource carsRemoteDataSource) {
        mRemoteReposDataSource = checkNotNull(carsRemoteDataSource);
    }

    /**
     * Returns the single instance of this class, creating it if necessary.
     *
     * @param carsRemoteDataSource the Remote data source for the Album Data
     * @return the {@link ReposRepository} instance
     */
    public static ReposRepository getInstance(ReposDataSource carsRemoteDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new ReposRepository(carsRemoteDataSource);
        }
        return INSTANCE;
    }

    /**
     * Used to force {@link #getInstance(ReposDataSource)} to create a new instance
     * next time it's called.
     */
    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public void getRepositories(@NonNull String organizationName, @NonNull int pageIndex, @NonNull int pageSize, @NonNull final LoadCallback<CodeRepository> callback){
        checkNotNull(callback);

        // Respond immediately with cache if available and not dirty
        if (cachedRepositories != null && !invalidCache) {
            callback.onDataLoaded(cachedRepositories);
            return;
        }

        mRemoteReposDataSource.getRepositories(organizationName, pageIndex, pageSize, new LoadCallback<CodeRepository>() {
            @Override
            public void onDataLoaded(List<CodeRepository> repositories) {
                refreshCachedCodeRepositories(repositories);
                callback.onDataLoaded(repositories);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });

    }



    @Override
    public void refreshData() {
        invalidCache = true;
    }

    private void refreshCachedCodeRepositories(List<CodeRepository> repositories) {
        cachedRepositories = repositories;

        invalidCache = false;
    }

}
