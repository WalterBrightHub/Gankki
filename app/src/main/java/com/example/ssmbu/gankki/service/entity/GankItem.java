package com.example.ssmbu.gankki.service.entity;

import java.util.List;

public class GankItem {
    private String _id;
    private String desc;
    private String publishedAt;
    private String type;
    private String url;
    private String who;

    public GankItem(String _id, String desc, String publishedAt, String type, String url, String who) {
        this._id = _id;
        this.desc = desc;
        this.publishedAt = publishedAt;
        this.type = type;
        this.url = url;
        this.who = who;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }
}
