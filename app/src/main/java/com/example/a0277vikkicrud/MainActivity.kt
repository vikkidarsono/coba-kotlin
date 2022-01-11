package com.example.a0277vikkicrud

import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    val mahasiswa_id: ArrayList<String> = arrayListOf()
    val mahasiswa_nim: ArrayList<String> = arrayListOf()
    val mahasiswa_nama: ArrayList<String> = arrayListOf()
    val mahasiswa_jurusan: ArrayList<String> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnTambah = findViewById<FloatingActionButton>(R.id.flo_add)
        btnTambah.setOnClickListener{
            val intenKita = Intent(this, TambahMahasiswa::class.java)
            startActivity(intenKita)
        }
        simpanDataDiArray()

        val rv_mahasiswa = findViewById<RecyclerView>(R.id.rv_biodata)
        val mahasiswaAdapter =MahasiswaAdapter(this, mahasiswa_id, mahasiswa_nim, mahasiswa_nama, mahasiswa_jurusan)

        rv_mahasiswa.adapter = mahasiswaAdapter
        rv_mahasiswa.layoutManager = LinearLayoutManager(this)
    }

    fun simpanDataDiArray(){
        val dbSaya = MyDBHelper(this)
        val dataSaya: Cursor = dbSaya.BacaSemuaData()

        if (dataSaya.count == 0) {
            Toast.makeText(this, "Data Tidak Ada !", Toast.LENGTH_SHORT).show()
        }else{
            while (dataSaya.moveToNext()) {
                mahasiswa_id.add(dataSaya.getString(0))
                mahasiswa_nim.add(dataSaya.getString(1))
                mahasiswa_nama.add(dataSaya.getString(2))
                mahasiswa_jurusan.add(dataSaya.getString(3))
            }
        }
    }
}