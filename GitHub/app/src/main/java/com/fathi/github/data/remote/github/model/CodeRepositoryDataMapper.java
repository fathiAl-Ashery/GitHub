package com.fathi.github.data.remote.github.model;

import com.fathi.github.data.model.CodeRepository;

import java.util.ArrayList;
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

    /**
     * Transform a {@link Map<String, String>} into an {@link List<CodeRepository>}.
     *
     * @param gitHubRepositories of data to be transformed.
     * @return {@link List<CodeRepository>} if valid {@link Map} otherwise null.
     */
    public List<CodeRepository> transform(List<RepositoryEntry> gitHubRepositories) {

        if (gitHubRepositories == null)
            return null;

        List<CodeRepository> codeRepositories = new ArrayList();
        for (RepositoryEntry entry : gitHubRepositories)
        {
            codeRepositories.add(new CodeRepository());

        }

        return codeRepositories;
    }


}
