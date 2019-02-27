package org.pandec.simplesql.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.pandec.simplesql.R;
import org.pandec.simplesql.entity.Data;
import org.pandec.simplesql.ui.PerubahanActivity;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private ArrayList<Data> list;
    private Context context;

    public ArrayList<Data> getList() {
        return list;
    }

    public void setList(ArrayList<Data> list) {
        this.list = list;
    }

    public DataAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_list_data, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.tvNama.setText(list.get(i).getNama());
        viewHolder.tvStatus.setText(list.get(i).getStatus());
        viewHolder.perubahan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PerubahanActivity.class);
                intent.putExtra("id", list.get(i).getId());
                intent.putExtra("nama", list.get(i).getNama());
                intent.putExtra("status", list.get(i).getStatus());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return getList().size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNama, tvStatus;
        LinearLayout perubahan;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNama = itemView.findViewById(R.id.tv_nama);
            tvStatus = itemView.findViewById(R.id.tv_status);
            perubahan = itemView.findViewById(R.id.perubahan);
        }
    }
}
