package org.pandec.simplesql.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.pandec.simplesql.R;

public class TambahDataActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etNama, etStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data);
        setTitle("Tambah Data");

        etNama = findViewById(R.id.et_nama);
        etStatus = findViewById(R.id.et_status);
        Button btnSimpan = findViewById(R.id.btn_simpan);

        btnSimpan.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_simpan :
                if (etNama.length() == 0 || etStatus.length() == 0) {
                    Toast.makeText(getBaseContext(), "Data Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getBaseContext(), "Oke Lanjut", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}
