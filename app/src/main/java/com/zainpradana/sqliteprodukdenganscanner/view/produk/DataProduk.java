package com.zainpradana.sqliteprodukdenganscanner.view.produk;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.zainpradana.sqliteprodukdenganscanner.R;
import com.zainpradana.sqliteprodukdenganscanner.database.DatabaseHelper;

public class DataProduk extends AppCompatActivity {
    public static DataProduk dp;
    protected Cursor cursor;
    String[] daftarProduk, daftarNomorProduk;
    ListView listViewProduk;
    Button btnTambahProduk;
    DatabaseHelper dbCenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_produk);

        btnTambahProduk = findViewById(R.id.bt_tambah_produk);
        btnTambahProduk.setOnClickListener(view -> {
            Intent goToTambahProduk = new Intent(DataProduk.this, TambahProduk.class);
            startActivity(goToTambahProduk);
        });

        dp = this;
        dbCenter = new DatabaseHelper(this);
        RefreshList();
    }

    public void RefreshList() {
        SQLiteDatabase db = dbCenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM produk ORDER BY nama_produk DESC", null);
        daftarProduk = new String[cursor.getCount()];

        for (int cc = 0; cc < cursor.getCount(); cc++){
            cursor.moveToPosition(cc);
            daftarProduk[cc] = cursor.getString(1).toString();
        }

        listViewProduk = findViewById(R.id.listview_data_produk);
        listViewProduk.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, daftarProduk));
        listViewProduk.setSelected(true);
        listViewProduk.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                final String selection = daftarNomorProduk[arg2];
                final CharSequence[] dialogItem = { "Lihat Produk", "Update Produk", "Hapus Produk"};
                AlertDialog.Builder builder = new AlertDialog.Builder(DataProduk.this);
                builder.setTitle("Pilihan");
                builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        switch (item){
                            case 0:
//                                Intent goToLihatProduk = new Intent(DataProduk.this, LihatProduk.class);
//                                goToLihatProduk.putExtra("noproduk", selection);
//                                startActivity(goToLihatProduk);
                                break;

                            case 1:
//                                Intent goToUpdateProduk = new Intent(DataProduk.this, UpdateProduk.class);
//                                goToUpdateProduk.putExtra("noproduk", selection);
//                                Toast.makeText(getApplicationContext(), selection.toString(), Toast.LENGTH_SHORT).show();
//                                startActivity(goToUpdateProduk);
                                break;

                            case 2:
                                SQLiteDatabase db = dbCenter.getWritableDatabase();
                                db.execSQL("DELETE FROM produk WHERE noproduk = '"+ selection + "'");
                                RefreshList();
                                break;
                        }
                    }
                });
                builder.create().show();
            }
        });
        ((ArrayAdapter)listViewProduk.getAdapter()).notifyDataSetInvalidated();
    }
}