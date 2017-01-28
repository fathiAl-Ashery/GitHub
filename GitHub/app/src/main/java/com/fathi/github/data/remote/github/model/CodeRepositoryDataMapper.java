package com.fathi.github.data.remote.github.model;

import android.graphics.CornerPathEffect;

import com.fathi.github.data.model.CodeRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 1/28/17.
 *
 */

public class CodeRepositoryDataMapper {

    private static CodeRepositoryDataMapper INSTANCE;

    public static CodeRepositoryDataMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CodeRepositoryDataMapper();
        }
        return INSTANCE;
    }

    // Prevent direct instantiation.
    private CodeRepositoryDataMapper() {

    }

    public List<CodeRepository> transform(Collection<RepoEntity> gitHubRepositories){
        if (gitHubRepositories == null)
            return null;

        List<CodeRepository> codeRepositories = new ArrayList();
        for (RepoEntity entry : gitHubRepositories)
        {
            final CodeRepository repository = transform(entry);
            if (repository != null) {
                codeRepositories.add(repository);
            }
        }

        return codeRepositories;
    }

    private CodeRepository transform(RepoEntity item) {
        CodeRepository repository = new CodeRepository();
        if (item != null) {
            repository.setDescription(item.description);
            repository.setName(item.name);
            repository.setRepositoryFullName(item.full_name);
            repository.setHasFork(item.fork);
            repository.setCreationDate(item.created_at);
            repository.setOwnerAvatarImage(item.owner.avatar_url);
            repository.setOwnerLogin(item.owner.login);
            repository.setRepositoriesHTMLUrl(item.html_url);
        }
        return repository;
    }


}
