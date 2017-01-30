package com.fathi.github.presentation.repositorydetail;

import android.app.Activity;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fathi.github.R;
import com.fathi.github.data.model.CodeRepository;

public class RepositoryDetailFragment extends Fragment implements RepositoryDetailContract.View {
    public static final String REPO_ITEM = "code_repo_item";

    public static final String TAG =  RepositoryDetailFragment.class.getCanonicalName();

    private RepositoryDetailContract.Presenter presenter;

    private CodeRepository repoItem;

    public RepositoryDetailFragment() {
    }

    public static RepositoryDetailFragment newInstance(CodeRepository repoItem) {
        RepositoryDetailFragment fragment = new RepositoryDetailFragment();
        // Supply index input as an argument.
        Bundle args = new Bundle();
        args.putParcelable(REPO_ITEM, repoItem);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(REPO_ITEM)) {
            repoItem =  getArguments().getParcelable(REPO_ITEM);

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(repoItem.getName());
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.repository_detail_fragment, container, false);

        if (repoItem != null) {
            ((TextView) rootView.findViewById(R.id.item_detail)).setText(repoItem.getDescription());
        }

        return rootView;
    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    public void showLoadingError() {

    }

    @Override
    public void showNoData() {

    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void setPresenter(RepositoryDetailContract.Presenter presenter) {

    }
}
