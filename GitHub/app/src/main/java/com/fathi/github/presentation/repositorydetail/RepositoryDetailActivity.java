package com.fathi.github.presentation.repositorydetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.fathi.github.R;
import com.fathi.github.data.model.CodeRepository;
import com.fathi.github.presentation.CodeRepository.CodeRepositoriesActivity;
import com.fathi.github.presentation.PresenterCache;
import com.squareup.picasso.Picasso;

public class RepositoryDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        AppCompatImageView imageView  = (AppCompatImageView) findViewById(R.id.owner_avatar_image);

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if (savedInstanceState == null) {
            Bundle arguments = new Bundle();
            arguments.putParcelable(RepositoryDetailFragment.REPO_ITEM,
                    getIntent().getParcelableExtra(RepositoryDetailFragment.REPO_ITEM));

            RepositoryDetailFragment repositoryDetailFragment = new RepositoryDetailFragment();
            repositoryDetailFragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.item_detail_container, repositoryDetailFragment)
                    .commit();

            CodeRepository repository = getIntent().getParcelableExtra(RepositoryDetailFragment.REPO_ITEM);

            Picasso.with(this).load(repository.getOwnerAvatarImage()).into(imageView);
            RepositoryDetailPresenter repositoryDetailPresenter = (RepositoryDetailPresenter) PresenterCache.getInstance().getPresenter(RepositoryDetailFragment.TAG);

            if(repositoryDetailPresenter == null) {
                // Create the presenter
                PresenterCache.getInstance().addPresenter(RepositoryDetailFragment.TAG,
                        new RepositoryDetailPresenter(repositoryDetailFragment));

            } else{
                repositoryDetailPresenter.attachToView(repositoryDetailFragment);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            navigateUpTo(new Intent(this, CodeRepositoriesActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
