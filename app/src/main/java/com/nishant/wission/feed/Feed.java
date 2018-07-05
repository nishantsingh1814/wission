package com.nishant.wission.feed;

public class Feed {
    private String thumbnail;
    private String likes;
    private String title;

    public String getUrlKey() {
        return urlKey;
    }

    public void setUrlKey(String urlKey) {
        this.urlKey = urlKey;
    }

    private String urlKey;

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Feed(String thumbnail, String likes, String title, String urlKey) {
        this.thumbnail = thumbnail;
        this.likes = likes;
        this.title = title;
        this.urlKey = urlKey;
    }
}
