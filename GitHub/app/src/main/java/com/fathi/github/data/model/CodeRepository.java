package com.fathi.github.data.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by admin on 1/28/17.
 */

public class CodeRepository implements Parcelable {
    private String name;
    private String description;
    private String ownerLogin;
    private String repositoryFullName;
    private String ownerAvatarImage;
    private String creationDate;
    private String repositoriesHTMLUrl;
    private boolean hasFork;

    public static final Parcelable.Creator<CodeRepository> CREATOR
            = new Parcelable.Creator<CodeRepository>() {
        public CodeRepository createFromParcel(Parcel in) {
            return new CodeRepository(in);
        }

        public CodeRepository[] newArray(int size) {
            return new CodeRepository[size];
        }
    };

    public CodeRepository(){

    }

    private CodeRepository(Parcel parcel) {

        name = parcel.readString();
        description = parcel.readString();
        ownerLogin = parcel.readString();
        repositoryFullName = parcel.readString();
        ownerAvatarImage = parcel.readString();
        creationDate = parcel.readString();
        repositoriesHTMLUrl = parcel.readString();
        hasFork = (boolean) parcel.readValue(null);
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeString(ownerLogin);
        parcel.writeString(repositoryFullName);
        parcel.writeString(ownerAvatarImage);
        parcel.writeString(creationDate);
        parcel.writeString(repositoriesHTMLUrl);
        parcel.writeValue(hasFork);
    }

}
