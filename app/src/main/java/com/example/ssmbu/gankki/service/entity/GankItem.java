package com.example.ssmbu.gankki.service.entity;

import android.os.Parcel;
import android.os.Parcelable;


import com.example.ssmbu.gankki.database.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.annotation.Unique;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.List;

@Table(database = AppDatabase.class)
public class GankItem extends BaseModel implements Parcelable {

    @PrimaryKey(autoincrement = true)
    public long id;
    @Column
    @Unique
    private String _id;
    @Column
    private String desc;
    @Column
    private String publishedAt;
    @Column
    private String type;
    @Column
    private String url;
    @Column
    private String who;

    public GankItem(){}

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_id);
        dest.writeString(desc);
        dest.writeString(publishedAt);
        dest.writeString(type);
        dest.writeString(url);
        dest.writeString(who);
    }
    public static final Parcelable.Creator<GankItem> CREATOR=new Parcelable.Creator<GankItem>(){
        @Override
        public GankItem createFromParcel(Parcel source) {
            GankItem gankItem=new GankItem(source.readString(),source.readString(),source.readString(),source.readString(),source.readString(),source.readString());
            return gankItem;
        }

        @Override
        public GankItem[] newArray(int size) {
            return new GankItem[size];
        }
    };
}
