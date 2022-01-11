package com.example.a0277vikkicrud;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaAdapter.viewHolderSaya> {

    private Context context;
    private ArrayList mahasiswa_id, mahasiswa_nim, mahasiswa_nama, mahasiswa_jurusan;

    MahasiswaAdapter(
            Context context,
            ArrayList mahasiswa_id,
            ArrayList mahasiswa_nim,
            ArrayList mahasiswa_nama,
            ArrayList mahasiswa_jurusan

    ){
        this.context = context;
        this.mahasiswa_id = mahasiswa_id;
        this.mahasiswa_nim = mahasiswa_nim;
        this.mahasiswa_nama = mahasiswa_nama;
        this.mahasiswa_jurusan = mahasiswa_jurusan;

    }

    @NonNull
    @Override
    public viewHolderSaya onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflaterKita = LayoutInflater.from(context);
        View viewKita = inflaterKita.inflate(R.layout.vikkidarsono_8020180277, parent, false);
        return new viewHolderSaya(viewKita);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolderSaya holder, @SuppressLint("RecyclerView") int position) {
        holder.txt_mhs_id.setText(String.valueOf(mahasiswa_id.get(position)));
        holder.txt_mhs_nim.setText(String.valueOf(mahasiswa_nim.get(position)));
        holder.txt_mhs_nama.setText(String.valueOf(mahasiswa_nama.get(position)));
        holder.txt_mhs_jurusan.setText(String.valueOf(mahasiswa_jurusan.get(position)));
        holder.layoutUtama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentKita = new Intent(context, UbahMahasiswa.class);
                intentKita.putExtra("id", String.valueOf(mahasiswa_id.get(position)));
                intentKita.putExtra("nim", String.valueOf(mahasiswa_nim.get(position)));
                intentKita.putExtra("nama", String.valueOf(mahasiswa_nama.get(position)));
                intentKita.putExtra("jurusan", String.valueOf(mahasiswa_jurusan.get(position)));

                context.startActivity(intentKita);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mahasiswa_id.size();
    }

    public class viewHolderSaya extends RecyclerView.ViewHolder {

        TextView txt_mhs_id, txt_mhs_nim, txt_mhs_nama, txt_mhs_jurusan;
        LinearLayout layoutUtama;

        public viewHolderSaya(@NonNull View itemView) {
            super(itemView);
            txt_mhs_id = itemView.findViewById(R.id.txt_mhs_id);
            txt_mhs_nim = itemView.findViewById(R.id.txt_mhs_nim);
            txt_mhs_nama = itemView.findViewById(R.id.txt_mhs_nama);
            txt_mhs_jurusan = itemView.findViewById(R.id.txt_mhs_jurusan);
            layoutUtama = itemView.findViewById(R.id.layout_utama);

        }
    }
}
