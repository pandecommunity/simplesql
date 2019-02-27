package org.pandec.simplesql.ui;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import org.pandec.simplesql.R;
import org.pandec.simplesql.adapter.DataAdapter;
import org.pandec.simplesql.db.DataHelper;
import org.pandec.simplesql.entity.Data;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DataHelper dataHelper;
    private ArrayList<Data> list;
    private DataAdapter dataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Daftar Data");
        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), TambahDataActivity.class);
                startActivity(intent);
            }
        });

        recyclerView = findViewById(R.id.recyclerView);

        dataHelper = new DataHelper(this);
        dataHelper.open();

        list = new ArrayList<>();

        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        dataAdapter = new DataAdapter(this);
        dataAdapter.setList(list);
        recyclerView.setAdapter(dataAdapter);

        new LoadDataAsync().execute();
    }

    private class LoadDataAsync extends AsyncTask<Void, Void, ArrayList<Data>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            if (list.size() > 0) {
                list.clear();
            }
        }

        @Override
        protected ArrayList<Data> doInBackground(Void... voids) {
            return dataHelper.query();
        }

        @Override
        protected void onPostExecute(ArrayList<Data> data) {
            super.onPostExecute(data);

            list.addAll(data);

            dataAdapter.setList(list);
            dataAdapter.notifyDataSetChanged();

            if (list.size() == 0) {
                Toast.makeText(getBaseContext(), "Tidak Ada Data", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getBaseContext(), "Ada Data", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
