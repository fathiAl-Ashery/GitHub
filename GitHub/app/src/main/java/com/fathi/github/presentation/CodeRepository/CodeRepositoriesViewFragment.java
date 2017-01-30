package com.fathi.github.presentation.CodeRepository;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.fathi.github.R;
import com.fathi.github.data.model.CodeRepository;
import com.fathi.github.presentation.Constants;
import com.fathi.github.presentation.PresenterCache;
import com.fathi.github.presentation.custom.ScrollChildSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class CodeRepositoriesViewFragment extends Fragment implements CodeRepositoriesContract.View {

    public static final String TAG =  CodeRepositoriesViewFragment.class.getCanonicalName();

    private CodeRepositoriesContract.Presenter presenter;

    private CodeRepositoriesRecyclerViewAdapter listAdapter;
    private CodeRepositoriesItemListener itemListener;

    private View noDataView;
    private LinearLayout dataView;
    private LinearLayoutManager layoutManager;

    private View rootView;
    private ScrollChildSwipeRefreshLayout swipeRefreshLayout;

    public CodeRepositoriesViewFragment() {
        // Requires empty public constructor
    }

    public static CodeRepositoriesViewFragment newInstance() {
        return new CodeRepositoriesViewFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listAdapter = new CodeRepositoriesRecyclerViewAdapter(new ArrayList<CodeRepository>(0), itemListener);

        if (PresenterCache.getInstance().getPresenter(TAG) != null) {
            presenter = (CodeRepositoriesContract.Presenter) PresenterCache.getInstance().getPresenter(TAG);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.list_fragment, container, false);

        layoutManager = new LinearLayoutManager(getActivity());

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.data_list);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(listAdapter);

        // Pagination
        recyclerView.addOnScrollListener(recyclerViewOnScrollListener);

        dataView = (LinearLayout) rootView.findViewById(R.id.dataLLayout);
        noDataView = rootView.findViewById(R.id.noData);

        // Set up progress indicator
        swipeRefreshLayout =
                (ScrollChildSwipeRefreshLayout) rootView.findViewById(R.id.refresh_layout);
        swipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getActivity(), R.color.colorPrimary),
                ContextCompat.getColor(getActivity(), R.color.colorAccent),
                ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark)
        );

        // Set the scrolling view in the custom SwipeRefreshLayout.
        swipeRefreshLayout.setScrollUpChild(recyclerView);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.loadRepositories(true);
            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            itemListener = (CodeRepositoriesItemListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement CodeRepositoriesItemListener");
        }
    }

    @Override
    public void setPresenter(@NonNull CodeRepositoriesContract.Presenter presenter) {
        this.presenter = checkNotNull(presenter);
    }


    @Override
    public void setLoadingIndicator(final boolean active) {

        if (swipeRefreshLayout == null) {
            return;
        }
//        final SwipeRefreshLayout srl = (SwipeRefreshLayout) rootView.findViewById(R.id.refresh_layout);

        // Make sure setRefreshing() is called after the layout is done with everything else.
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(active);
            }
        });
    }

    @Override
    public void showRepositories(List<CodeRepository> codeRepositoryList) {
        listAdapter.replaceData(codeRepositoryList);

        dataView.setVisibility(View.VISIBLE);
        noDataView.setVisibility(View.GONE);
    }

    @Override
    public void showNextRepositoriesPage(List<CodeRepository> repositories) {
        listAdapter.addData(repositories);
    }

    @Override
    public void showLoadingError() {
        setLoadingIndicator(false);
        showMessage(getString(R.string.loading_Data_error));
    }

    @Override
    public void showNoData() {
        dataView.setVisibility(View.GONE);
        noDataView.setVisibility(View.VISIBLE);
    }

    private void showMessage(String message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }


    public interface CodeRepositoriesItemListener {

        void onRepositoryClick(CodeRepository clickedRepository);

    }

    private RecyclerView.OnScrollListener recyclerViewOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            int visibleItemCount = layoutManager.getChildCount();
            int totalItemCount = layoutManager.getItemCount();
            int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

            if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                    && firstVisibleItemPosition >= 0
                    && totalItemCount >= Constants.PAGE_SIZE) {
                presenter.loadNextPage();
            }
        }
    };
}
