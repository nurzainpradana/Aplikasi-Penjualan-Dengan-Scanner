package com.zainpradana.sqliteprodukdenganscanner.view.scanbarcode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import com.google.zxing.Result;
import com.zainpradana.sqliteprodukdenganscanner.view.halamanutama.MainActivity;
import com.zainpradana.sqliteprodukdenganscanner.R;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScanBarcode extends AppCompatActivity implements ZXingScannerView.ResultHandler  {
    private ZXingScannerView zXingScannerView;
    String hasilPemindaian;

    public static int CAMERA_PERMISSION_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_barcode);

        zXingScannerView = findViewById(R.id.zx_scanner);

        checkPermission(Manifest.permission.CAMERA, CAMERA_PERMISSION_CODE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        zXingScannerView.setResultHandler(this);
        zXingScannerView.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        zXingScannerView.setResultHandler(this);
        zXingScannerView.stopCamera();
    }

    private void checkPermission(String camera, int cameraPermissionCode) {
        if (ContextCompat.checkSelfPermission(ScanBarcode.this, camera) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(ScanBarcode.this, new String[] {camera}, cameraPermissionCode);
        }
        else {
            Toast.makeText(ScanBarcode.this, "Permission already Granted", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void handleResult(Result result) {
        hasilPemindaian = result.getText();

        if (hasilPemindaian != null){
            Toast.makeText(this, "Berhasil Memindai", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(ScanBarcode.this, MainActivity.class);
            i.putExtra("hasil_key", hasilPemindaian);
            startActivity(i);
            finish();
        } else {
            zXingScannerView.resumeCameraPreview(this);
        }
    }
}