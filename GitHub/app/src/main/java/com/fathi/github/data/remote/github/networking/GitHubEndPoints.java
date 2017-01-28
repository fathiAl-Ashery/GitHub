package com.fathi.github.data.remote.github.networking;

import com.fathi.github.data.remote.github.model.GitHubRepository;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface GitHubEndPoints {

    @GET("orgs/{organization}/repos")
    Call<GitHubRepository> getRepository(@Path("organization") String organizationName, @Query("page") int pageIndex, @Query("per_page") int pageSize);


}
