package com.example.ssmbu.gankki.database;

import com.raizlabs.android.dbflow.annotation.Database;

@Database(name= AppDatabase.name,version = AppDatabase.VERSION)
public class AppDatabase {
    public static final String name = "dbflowDataBase";

    public static final int VERSION = 1;
}
