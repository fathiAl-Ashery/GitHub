package com.fathi.github.presentation.repositorydetail;

import com.fathi.github.data.model.CodeRepository;
import com.fathi.github.presentation.BasePresenter;
import com.fathi.github.presentation.BaseView;

import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
public interface RepositoryDetailContract {

    interface View extends BaseView<Presenter> {
        void setLoadingIndicator(boolean active);

        void showLoadingError();
        void showNoData();
        boolean isActive();

    }

    interface Presenter extends BasePresenter {

    }
}
