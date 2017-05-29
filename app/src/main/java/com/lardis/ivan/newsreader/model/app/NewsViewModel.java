package com.lardis.ivan.newsreader.model.app;

import android.support.annotation.NonNull;

/**
 * Created by black-sony on 27.05.17.
 */

public class NewsViewModel implements Comparable<NewsViewModel> {

    private String origin;

    private String title;

    private String description;

    private Long dateLong;

    private String guid;

    private String urlPhoto;
    private boolean showDescription;

    public NewsViewModel(final String origin,
            final String title,
            final String description,
            final Long dateLong,
            final String guid,
            final String urlPhoto) {
        this.origin = origin;
        this.title = title;
        this.description = description;
        this.dateLong = dateLong;
        this.guid = guid;
        this.urlPhoto = urlPhoto;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public Long getDateLong() {
        return dateLong;
    }

    public void setDateLong(final Long dateLong) {
        this.dateLong = dateLong;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(final String guid) {
        this.guid = guid;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(final String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }


    public String getOrigin() {
        return origin;
    }

    public void setOrigin(final String origin) {
        this.origin = origin;
    }

    @Override
    public int compareTo(@NonNull final NewsViewModel o) {

        return (int) (o.getDateLong()-getDateLong());
    }

    public boolean isShowDescription() {
        return showDescription;
    }

    public void setShowDescription(final boolean showDescription) {
        this.showDescription = showDescription;
    }
}
