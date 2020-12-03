package com.zainpradana.sqliteprodukdenganscanner.view.produk;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zainpradana.sqliteprodukdenganscanner.R;
import com.zainpradana.sqliteprodukdenganscanner.database.DatabaseHelper;

public class TambahProduk extends AppCompatActivity {

    protected Cursor cursor;
    DatabaseHelper dbHelper;
    Button buttonSimpanProduk, buttonKembali;
    EditText etKodeProduk, etNamaProduk, etMerek, etHarga;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_produk);

        dbHelper = new DatabaseHelper(this);

        etKodeProduk = findViewById(R.id.et_kode_produk);
        etNamaProduk = findViewById(R.id.et_nama_produk);
        etMerek = findViewById(R.id.et_merek);
        etHarga = findViewById(R.id.et_harga);

        buttonKembali = findViewById(R.id.button_kembali);
        buttonSimpanProduk = findViewById(R.id.button_simpan_produk);

        buttonKembali.setOnClickListener(view -> {
            finish();
        });

        buttonSimpanProduk.setOnClickListener(view -> {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            db.execSQL("insert into produk(kode_produk, nama_produk, merek, harga) values('" +
                    etKodeProduk.getText().toString() + "','" +
                    etNamaProduk.getText().toString() + "','" +
                    etMerek.getText().toString() + "','" +
                    etHarga.getText().toString() + "')");
            Toast.makeText(getApplicationContext(), "Berhasil" ,
                    Toast.LENGTH_LONG).show();
            DataProduk.dp.RefreshList();
            finish();
        });
    }
}