package com.zainpradana.sqliteprodukdenganscanner.view.halamanutama;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.zainpradana.sqliteprodukdenganscanner.R;
import com.zainpradana.sqliteprodukdenganscanner.view.produk.DataProduk;
import com.zainpradana.sqliteprodukdenganscanner.view.scanbarcode.ScanBarcode;

public class MainActivity extends AppCompatActivity {
    public EditText etHasilScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etHasilScan = findViewById(R.id.et_hasil_scan);

        String hasilScan = getIntent().getStringExtra("hasil_key");
        if (hasilScan != null){
            etHasilScan.setText(hasilScan);
        }
    }

    public void keScanBarcode(View view) {
        Intent i = new Intent(MainActivity.this, ScanBarcode.class);
        startActivity(i);
    }

    public void keDaftarProduk(View view) {
        Intent i = new Intent(MainActivity.this, DataProduk.class);
        startActivity(i);
    }
}