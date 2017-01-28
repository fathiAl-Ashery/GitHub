
package com.fathi.github.data.remote.github;

import android.support.annotation.NonNull;

import com.fathi.github.data.ReposDataSource;
import com.fathi.github.data.model.CodeRepository;
import com.fathi.github.data.remote.github.model.CodeRepositoryDataMapper;
import com.fathi.github.data.remote.github.model.RepoEntity;
import com.fathi.github.data.remote.github.networking.GitHubEndPoints;

import java.io.IOException;
import java.util.List;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Implementation of the data source For GitHub Service
 */
public class GitHubReposDataSource implements ReposDataSource {

    private static GitHubReposDataSource INSTANCE;
    private final static String BASE_URL = "https://api.github.com/";


    public static GitHubReposDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GitHubReposDataSource();
        }
        return INSTANCE;
    }

    // Prevent direct instantiation.
    private GitHubReposDataSource() {

    }

    @Override
    public void getRepositories(@NonNull String organizationName, @NonNull int pageIndex, @NonNull int pageSize, @NonNull LoadCallback<CodeRepository> callback){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GitHubEndPoints service = retrofit.create(GitHubEndPoints.class);

        try {
            Response<List<RepoEntity>> response = service.getRepository(organizationName, pageIndex, pageSize).execute();
            callback.onDataLoaded( CodeRepositoryDataMapper.getInstance().transform((List<RepoEntity>) response.body() ) );
        } catch (IOException e) {
            e.printStackTrace();
            /* TODO handle different Exceptions to show different message to User */
            callback.onDataNotAvailable();
        }

    }

    @Override
    public void refreshData() {
        //remove in-memory cache if any
    }
}
