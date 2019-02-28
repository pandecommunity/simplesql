package org.pandec.simplesql.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.pandec.simplesql.R;
import org.pandec.simplesql.db.DataHelper;
import org.pandec.simplesql.entity.Data;

public class PerubahanActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etNama, etStatus;
    private Integer id;
    private DataHelper dataHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perubahan);

        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        String nama = intent.getStringExtra("nama");
        String status = intent.getStringExtra("status");

        etNama = findViewById(R.id.et_nama);
        etStatus = findViewById(R.id.et_status);

        etNama.setText(nama);
        etStatus.setText(status);

        dataHelper = new DataHelper(this);
        dataHelper.open();

        Button btnUpdate = findViewById(R.id.btn_update);
        Button btnHapus = findViewById(R.id.btn_hapus);

        btnUpdate.setOnClickListener(this);
        btnHapus.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_update :
                if (etNama.length() == 0 || etStatus.length() == 0) {
                    Toast.makeText(getBaseContext(), "Data Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
                } else {
                    String nama = etNama.getText().toString();
                    String status = etStatus.getText().toString();

                    Data data = new Data();
                    data.setId(id);
                    data.setNama(nama);
                    data.setStatus(status);

                    dataHelper.update(data);

                    Toast.makeText(getBaseContext(), "Data Sudah Berubah", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(getBaseContext(), MainActivity.class);
                    startActivity(intent);
                }
                break;

            case R.id.btn_hapus :
                dataHelper.delete(id);

                Toast.makeText(getBaseContext(), "Data Sudah Terhapus", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
                break;
        }
    }
}
