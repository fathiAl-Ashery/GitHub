package com.fathi.github.data.model;

/**
 * Created by admin on 1/28/17.
 */

public class CodeRepository {
    private String name;
    private String description;
    private String ownerLogin;

    private boolean hasFork;

    private String repositoryFullName;
    private String ownerAvatarImage;
    private String creationDate;
    private String repositoriesHTMLUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOwnerLogin() {
        return ownerLogin;
    }

    public void setOwnerLogin(String ownerLogin) {
        this.ownerLogin = ownerLogin;
    }

    public boolean isHasFork() {
        return hasFork;
    }

    public void setHasFork(boolean hasFork) {
        this.hasFork = hasFork;
    }

    public String getRepositoryFullName() {
        return repositoryFullName;
    }

    public void setRepositoryFullName(String repositoryFullName) {
        this.repositoryFullName = repositoryFullName;
    }

    public String getOwnerAvatarImage() {
        return ownerAvatarImage;
    }

    public void setOwnerAvatarImage(String ownerAvatarImage) {
        this.ownerAvatarImage = ownerAvatarImage;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getRepositoriesHTMLUrl() {
        return repositoriesHTMLUrl;
    }

    public void setRepositoriesHTMLUrl(String repositoriesHTMLUrl) {
        this.repositoriesHTMLUrl = repositoriesHTMLUrl;
    }
}
