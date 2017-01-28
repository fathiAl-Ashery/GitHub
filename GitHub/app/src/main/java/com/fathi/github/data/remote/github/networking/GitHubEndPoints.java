package com.fathi.github.data.remote.github.networking;

import com.fathi.github.data.remote.github.model.RepoEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface GitHubEndPoints {

    @GET("orgs/{organization}/repos")
    Call<List<RepoEntity>> getRepository(@Path("organization") String organizationName, @Query("page") int pageIndex, @Query("per_page") int pageSize);


}
