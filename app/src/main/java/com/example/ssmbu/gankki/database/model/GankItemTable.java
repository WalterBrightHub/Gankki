package com.example.ssmbu.gankki.database.model;

import com.example.ssmbu.gankki.database.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.annotation.Unique;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = AppDatabase.class)
public class GankItemTable extends BaseModel{
    @PrimaryKey(autoincrement = true)
    public long id;
    @Column
    @Unique
    public String _id;
    @Column
    public String desc;
    @Column
    public String publishedAt;
    @Column
    public String type;
    @Column
    public String url;
    @Column
    public String who;



    public void insertData(String _id, String desc, String publishedAt, String type, String url, String who) {
        this._id=_id;
        this.desc = desc;
        this.publishedAt = publishedAt;
        this.type = type;
        this.url = url;
        this.who = who;
    }
}
