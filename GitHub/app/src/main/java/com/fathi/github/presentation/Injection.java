package com.fathi.github.presentation;

import android.content.Context;
import android.support.annotation.NonNull;

import com.fathi.github.data.ReposDataSource;
import com.fathi.github.data.ReposRepository;
import com.fathi.github.data.remote.github.GitHubReposDataSource;
import com.fathi.github.domain.UseCaseHandler;
import com.fathi.github.domain.coderepositoriesusecases.LoadCodeRepositoriesUsecase;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by admin on 1/25/17.
 */

public class Injection {

    public static ReposDataSource provideCodeRepoRepository() {
        return ReposRepository.getInstance(GitHubReposDataSource.getInstance());
    }

    public static LoadCodeRepositoriesUsecase provideCodeRepositoriesUsecase() {
        return new LoadCodeRepositoriesUsecase(provideCodeRepoRepository());
    }

    public static UseCaseHandler provideUseCaseHandler() {
        return UseCaseHandler.getInstance();
    }

}
