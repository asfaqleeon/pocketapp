package com.app.message;

/**
 * Created by user1 on 10/06/2016.
 */
public class LinkUrl {
    private String url;
    private String category;

    public LinkUrl() {
    }

    public LinkUrl(String url, String category) {
        this.url = url;
        this.category = category;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
