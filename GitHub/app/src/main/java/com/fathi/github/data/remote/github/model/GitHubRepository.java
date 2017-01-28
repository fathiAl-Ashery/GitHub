package com.fathi.github.data.remote.github.model;

import java.util.List;

/**
 * Created by admin on 1/28/17.
 */

public class GitHubRepository {
    public List<RepositoryEntry> getRepositoryEntries() {
        return repositoryEntries;
    }

    public void setRepositoryEntries(List<RepositoryEntry> repositoryEntries) {
        this.repositoryEntries = repositoryEntries;
    }

    private List<RepositoryEntry> repositoryEntries;


}
