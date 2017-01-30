package com.fathi.github.presentation.repositorydetail;

import android.support.annotation.NonNull;

import com.fathi.github.data.model.CodeRepository;
import com.fathi.github.domain.UseCase;
import com.fathi.github.domain.coderepositoriesusecases.LoadCodeRepositoriesUsecase;
import com.fathi.github.presentation.BaseView;
import com.fathi.github.presentation.CodeRepository.CodeRepositoriesContract;
import com.fathi.github.presentation.Constants;

import java.util.List;


/**
 * Created by admin on 1/26/17.
 */

public class RepositoryDetailPresenter implements RepositoryDetailContract.Presenter {

    private RepositoryDetailContract.View view;

    public RepositoryDetailPresenter(@NonNull RepositoryDetailContract.View view){
        this.view = view;
        this.view.setPresenter(this);
    }



    @Override
    public void start() {

    }

    @Override
    public void attachToView(BaseView view) {
        this.view = (RepositoryDetailContract.View) view;
        this.view.setPresenter(this);
    }

    @Override
    public void stop() {
        //Handle Release Resources if Any

    }

}
