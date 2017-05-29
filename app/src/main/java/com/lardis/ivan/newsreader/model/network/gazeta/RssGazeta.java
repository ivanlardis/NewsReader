package com.lardis.ivan.newsreader.model.network.gazeta;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by black-sony on 25.05.17.
 */
@Root(name="rss", strict=false)
public class RssGazeta  {

    public List<ItemGazeta> getItems() {
        return items;
    }

    public void setItems(final List<ItemGazeta> items) {
        this.items = items;
    }

    @ElementList(name="item", inline=true,required=false)
    @Path("channel")
    private List<ItemGazeta> items;

}