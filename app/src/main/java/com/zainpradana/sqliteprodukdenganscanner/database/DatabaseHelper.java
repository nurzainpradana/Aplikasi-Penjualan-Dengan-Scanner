package com.zainpradana.sqliteprodukdenganscanner.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "dbproduk.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // MEMBUAT TABEL & RECORD untuk PASIEN
        db.execSQL("CREATE TABLE produk(kode_produk string primary key, nama_produk text, merek text, harga int)");
        db.execSQL("INSERT INTO produk(kode_produk, nama_produk, merek, harga) VALUES (8886008101053, 'Air Mineral 250ml', 'Aqua', 3000)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE produk");
    }
}
