package com.lardis.ivan.newsreader.model.network.gazeta;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by black-sony on 25.05.17.
 */

@Root(name = "item", strict = false)
public class ItemGazeta {

    @Element(name = "guid")
    private String guid;

    @Element(name = "pubDate")
    private String pubDate;

    @Element(name = "title")
    private String title;

    @Element(name = "author")
    private String author;


    @Element(name = "description")
    private String description;

    @Element(name = "link")
    private String link;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(final String author) {
        this.author = author;
    }
}


