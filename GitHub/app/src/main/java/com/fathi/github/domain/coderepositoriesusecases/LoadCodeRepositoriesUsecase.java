package com.fathi.github.domain.coderepositoriesusecases;

import android.support.annotation.NonNull;

import com.fathi.github.data.ReposDataSource;
import com.fathi.github.data.model.CodeRepository;
import com.fathi.github.domain.UseCase;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by admin on 1/28/17.
 */

public class LoadCodeRepositoriesUsecase extends UseCase<LoadCodeRepositoriesUsecase.RequestValues, LoadCodeRepositoriesUsecase.ResponseValue> {
    private final ReposDataSource codeRespositoryDataSource;

    public LoadCodeRepositoriesUsecase(@NonNull ReposDataSource codeRespositoryDataSource) {
        this.codeRespositoryDataSource = checkNotNull(codeRespositoryDataSource, "Respository cannot be null!");
    }

    @Override
    protected void executeUseCase(final RequestValues values) {
        if (values.isForceUpdate()) {
            codeRespositoryDataSource.refreshData();
        }


        codeRespositoryDataSource.getRepositories(values.getOrganization(), values.getPage(), values.getPageSize(), new ReposDataSource.LoadCallback<CodeRepository>() {
            @Override
            public void onDataLoaded(List<CodeRepository> repositories) {
                ResponseValue responseValue = new ResponseValue(repositories);
                getUseCaseCallback().onSuccess(responseValue);
            }

            @Override
            public void onDataNotAvailable() {
                getUseCaseCallback().onError();

            }

        });

    }

    public static final class RequestValues implements UseCase.RequestValues {

        private final boolean forceUpdate;
        private final int page;
        private final int pageSize;
        private final String organization;

        public boolean isForceUpdate() {
            return forceUpdate;
        }

        public int getPage() {
            return page;
        }

        public int getPageSize() {
            return pageSize;
        }

        public String getOrganization() {
            return organization;
        }

        public RequestValues(String organizationName, int page, int pageSize, boolean forceUpdate) {
            this.forceUpdate = forceUpdate;
            this.page = page;
            this.pageSize = pageSize;
            this.organization = organizationName;
        }

    }

    public static final class ResponseValue implements UseCase.ResponseValue {

        private final List<CodeRepository> codeRepositories;

        public ResponseValue(@NonNull List<CodeRepository> codeRepositories) {
            this.codeRepositories = checkNotNull(codeRepositories, "data cannot be null!");
        }

        public List<CodeRepository> getRepositories() {
            return codeRepositories;
        }
    }
}
