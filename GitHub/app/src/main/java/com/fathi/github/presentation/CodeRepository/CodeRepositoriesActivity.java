package com.fathi.github.presentation.CodeRepository;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;


import com.fathi.github.R;
import com.fathi.github.data.model.CodeRepository;
import com.fathi.github.presentation.Injection;
import com.fathi.github.presentation.PresenterCache;
import com.fathi.github.presentation.repositorydetail.RepositoryDetailActivity;
import com.fathi.github.presentation.repositorydetail.RepositoryDetailFragment;
import com.fathi.github.presentation.repositorydetail.RepositoryDetailPresenter;
import com.fathi.github.util.ActivityUtils;

public class CodeRepositoriesActivity extends AppCompatActivity implements CodeRepositoriesViewFragment.CodeRepositoriesItemListener {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_repository);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        CodeRepositoriesViewFragment codeReposViewFragment = (CodeRepositoriesViewFragment) getSupportFragmentManager().findFragmentById(R.id.list_fragment);
        CodeRepositoriesPresenter codeRepositoriesPresenter = (CodeRepositoriesPresenter) PresenterCache.getInstance().getPresenter(CodeRepositoriesViewFragment.TAG);

        if(codeRepositoriesPresenter == null) {
            // Create the presenter
            PresenterCache.getInstance().addPresenter(CodeRepositoriesViewFragment.TAG,
                    new CodeRepositoriesPresenter(Injection.provideUseCaseHandler(),
                            codeReposViewFragment,
                            Injection.provideCodeRepositoriesUsecase()));

        } else{
            codeRepositoriesPresenter.attachToView(codeReposViewFragment);
        }

        if (findViewById(R.id.details_container) != null ) {
            RepositoryDetailFragment repositoryDetailFragment = RepositoryDetailFragment.newInstance(null);

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), repositoryDetailFragment, R.id.details_container, true, RepositoryDetailFragment.TAG);
                mTwoPane = true;

                RepositoryDetailPresenter repositoryDetailPresenter = (RepositoryDetailPresenter) PresenterCache.getInstance().getPresenter(RepositoryDetailFragment.TAG);

                if (repositoryDetailPresenter == null) {
                    // Create the presenter
                    PresenterCache.getInstance().addPresenter(RepositoryDetailFragment.TAG,
                            new RepositoryDetailPresenter(repositoryDetailFragment));

                } else {
                    repositoryDetailPresenter.attachToView(repositoryDetailFragment);
                }

            }

    }


    @Override
    public void onRepositoryClick(CodeRepository clickedRepository) {

        if (mTwoPane) {
            Bundle arguments = new Bundle();
            arguments.putParcelable(RepositoryDetailFragment.REPO_ITEM, clickedRepository);
            RepositoryDetailFragment fragment = new RepositoryDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.item_detail_container, fragment)
                    .commit();
        } else {
            Intent intent = new Intent(this, RepositoryDetailActivity.class);
            intent.putExtra(RepositoryDetailFragment.REPO_ITEM, clickedRepository);

            startActivity(intent);
        }
    }

}
