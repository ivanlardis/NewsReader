package com.lardis.ivan.newsreader.business.model.app;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;



public class LTechModel implements Comparable ,Parcelable   {

    private int id;

    private String title;

    private String text;

    private String imageUrl;

    private int sort;

    private long timestump;

    public LTechModel(final int id, final String title, final String text, final String imageUrl,
            final int sort,
            final long timestump) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.imageUrl = imageUrl;
        this.sort = sort;
        this.timestump = timestump;
    }


    protected LTechModel(Parcel in) {
        title = in.readString();
        text = in.readString();
        imageUrl = in.readString();
    }


    public static final Creator<LTechModel> CREATOR = new Creator<LTechModel>() {
        @Override
        public LTechModel createFromParcel(Parcel in) {
            return new LTechModel(in);
        }

        @Override
        public LTechModel[] newArray(int size) {
            return new LTechModel[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(final String text) {
        this.text = text;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(final String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(final Integer sort) {
        this.sort = sort;
    }

    public Long getTimestump() {
        return timestump;
    }

    public void setTimestump(final Long timestump) {
        this.timestump = timestump;
    }

    @Override
    public int compareTo(@NonNull final Object o) {
        LTechModel lTechModel= (LTechModel) o;
        return this.sort-lTechModel.sort;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(text);
        dest.writeString(imageUrl);
        dest.writeInt(sort);
        dest.writeLong(timestump);
    }
}
