package com.example.a0277vikkicrud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class TambahMahasiswa : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_mahasiswa)

        val txtNIM = findViewById<EditText>(R.id.txt_nim)
        val txtNama = findViewById<EditText>(R.id.txt_nama)
        val txtJurusan = findViewById<EditText>(R.id.txt_jurusan)
        val btnSimpan = findViewById<Button>(R.id.btn_simpan)

        btnSimpan.setOnClickListener{
            val dbSaya = MyDBHelper (this)
            dbSaya.tambahmhs(
                Integer.valueOf(txtNIM.text.toString().trim()),
                txtNama.text.toString().trim(),
                txtJurusan.text.toString().trim()

            )
        }
    }
}