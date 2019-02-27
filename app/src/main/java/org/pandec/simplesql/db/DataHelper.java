package org.pandec.simplesql.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import org.pandec.simplesql.entity.Data;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;

public class DataHelper {
    private static String TABLE_NAME = DatabaseContract.DataColums.TABLE_NAME;
    private Context context;
    private DatabaseHelper databaseHelper;

    private SQLiteDatabase sqLiteDatabase;

    public DataHelper(Context context) {
        this.context = context;
    }

    public DataHelper open() throws SQLException {
        databaseHelper = new DatabaseHelper(context);
        sqLiteDatabase = databaseHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        databaseHelper.close();
    }

    //Memanggil Semua Data
    public ArrayList<Data> query() {
        ArrayList<Data> arrayList = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.query(DatabaseContract.DataColums.TABLE_NAME, null, null, null,
                null, null, _ID + " DESC", null);
        cursor.moveToFirst();
        Data data;
        if (cursor.getCount() > 0) {
            do {
                data = new Data();
                data.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                data.setNama(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.DataColums.NAMA)));
                data.setStatus(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.DataColums.STATUS)));
                arrayList.add(data);
                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    //Simpan data
    public long insert(Data data) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(DatabaseContract.DataColums.NAMA, data.getNama());
        initialValues.put(DatabaseContract.DataColums.STATUS, data.getStatus());
        return sqLiteDatabase.insert(DatabaseContract.DataColums.TABLE_NAME, null, initialValues);
    }

    //Simpan Data
    public int update(Data data) {
        ContentValues args = new ContentValues();
        args.put(DatabaseContract.DataColums.NAMA, data.getNama());
        args.put(DatabaseContract.DataColums.STATUS, data.getStatus());
        return sqLiteDatabase.update(DatabaseContract.DataColums.TABLE_NAME, args, _ID + "= '" + data.getId() + "'", null);
    }

    //Delete
    public int delete(int id) {
        return sqLiteDatabase.delete(TABLE_NAME, _ID + " = '" + id + "'", null);
    }
}
