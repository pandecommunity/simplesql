package org.pandec.simplesql.db;

import android.provider.BaseColumns;

public class DatabaseContract {
    static final class DataColums implements BaseColumns {
        //Nama table di dalam database
        static String TABLE_NAME = "data";
        //Nama field
        static String NAMA = "nama";
        //Nama field
        static String STATUS = "status";
    }
}
