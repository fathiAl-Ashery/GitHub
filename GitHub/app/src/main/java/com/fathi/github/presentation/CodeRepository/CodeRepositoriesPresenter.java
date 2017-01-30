package com.fathi.github.presentation.CodeRepository;

import android.support.annotation.NonNull;

import com.fathi.github.data.model.CodeRepository;
import com.fathi.github.domain.UseCase;
import com.fathi.github.domain.UseCaseHandler;
import com.fathi.github.domain.coderepositoriesusecases.LoadCodeRepositoriesUsecase;
import com.fathi.github.presentation.BaseView;
import com.fathi.github.presentation.Constants;

import java.util.List;


/**
 * Created by admin on 1/26/17.
 */

public class CodeRepositoriesPresenter implements CodeRepositoriesContract.Presenter {

    private LoadCodeRepositoriesUsecase loadCodeRepositoriesUsecase;
    private CodeRepositoriesContract.View mView;
    private UseCaseHandler mUsecaseHandler;

    private int mPageIndex = 0;

    private boolean mFirstLoad = true;
    private boolean mIsLastPage = false;
    private boolean mIsLoading = false;

    public CodeRepositoriesPresenter(@NonNull UseCaseHandler useCaseHandler,
                                     @NonNull CodeRepositoriesContract.View view,
                                     @NonNull LoadCodeRepositoriesUsecase interactor){
        this.mUsecaseHandler = useCaseHandler;
        this.mView = view;
        this.loadCodeRepositoriesUsecase = interactor;

        this.mView.setPresenter(this);
    }


    @Override
    public void loadRepositories(boolean forceUpdate) {
        //Reset to the first Page
        mPageIndex = 0;
        mIsLastPage = false;
        loadRepositories(mPageIndex, forceUpdate || mFirstLoad, true);
        mFirstLoad = false;
    }

    private boolean isLastPage() {
        return mIsLastPage;
    }

    private boolean isLoading(){
        return mIsLoading;
    }

    @Override
    public void loadNextPage() {
        if (!isLoading() && !isLastPage()) {
            mPageIndex++;
            loadRepositories(mPageIndex, true, true);
        } else if (isLastPage()){
            //TODO::let View show no more data message
        }
    }

    @Override
    public void start() {
        this.loadRepositories(false);
    }

    @Override
    public void attachToView(BaseView view) {
        this.mView = (CodeRepositoriesContract.View) view;
        mView.setPresenter(this);
    }

    @Override
    public void stop() {
        //Handle Release Resources if Any

    }

    private void loadRepositories(int pageIndex, boolean forceUpdate, final boolean showLoadingUI) {
        if (showLoadingUI && mView.isActive()) {
            mView.setLoadingIndicator(true);
        }

        LoadCodeRepositoriesUsecase.RequestValues requestValue = new LoadCodeRepositoriesUsecase.RequestValues(Constants.ORGANIZATION_NAME, pageIndex, Constants.PAGE_SIZE, forceUpdate);

        mIsLoading = true;
        mUsecaseHandler.execute(loadCodeRepositoriesUsecase, requestValue,
                new UseCase.UseCaseCallback<LoadCodeRepositoriesUsecase.ResponseValue>() {

                    @Override
                    public void onSuccess(LoadCodeRepositoriesUsecase.ResponseValue response) {
                        mIsLoading = false;
                        List<CodeRepository> repositories = response.getRepositories();

                        if (!mView.isActive()) {
                            return;
                        }

                        if (showLoadingUI) {
                            mView.setLoadingIndicator(false);
                        }

                        if (repositories.isEmpty()) {
                            mIsLastPage = true;

                            if(mPageIndex == 0) {
                                mView.showNoData();
                            }

                        } else {
                            if (mPageIndex > 0){
                                mView.showNextRepositoriesPage(repositories);
                            } else {
                                mView.showRepositories(repositories);
                            }
                        }

                    }

                    @Override
                    public void onError() {
                        mIsLoading = false;

                        if (!mView.isActive()) {
                            return;
                        }
                        mView.showLoadingError();
                    }
                });
    }
}
