package org.pandec.simplesql.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.pandec.simplesql.R;

public class PerubahanActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etNama, etStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perubahan);

        etNama = findViewById(R.id.et_nama);
        etStatus = findViewById(R.id.et_status);

        Button btnUpdate = findViewById(R.id.btn_update);
        Button btnHapus = findViewById(R.id.btn_hapus);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_update :
                if (etNama.length() == 0 || etStatus.length() == 0) {
                    Toast.makeText(getBaseContext(), "Data Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getBaseContext(), "Update", Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.btn_hapus :
                Toast.makeText(getBaseContext(), "Hapus", Toast.LENGTH_LONG).show();
                break;
        }
    }
}
