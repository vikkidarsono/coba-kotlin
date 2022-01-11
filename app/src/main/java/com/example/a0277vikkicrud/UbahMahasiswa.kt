package com.example.a0277vikkicrud

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class UbahMahasiswa : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ubah_mahasiswa)

        val actionBar = supportActionBar
        if (intent.hasExtra("nama")){
            actionBar?.title = intent.getStringExtra("nama")
        }

        val btnUbah = findViewById<Button>(R.id.btn_edit)

        getIntentData()

        btnUbah.setOnClickListener {
            val dbSaya = MyDBHelper(this)

            val idMahasiswa = intent.getStringExtra("id")
            val nimMahasiswa = findViewById<EditText>(R.id.txt_edit_nim).text.toString()
            val namaMahasiswa = findViewById<EditText>(R.id.txt_edit_nama).text.toString()
            val jurusanMahasiswa = findViewById<EditText>(R.id.txt_edit_jurusan).text.toString()

            dbSaya.ubahMahasiswa(idMahasiswa, nimMahasiswa, namaMahasiswa, jurusanMahasiswa)
        }

        val btnDelete = findViewById<Button>(R.id.btn_hapus)
        btnDelete.setOnClickListener {
            dialogKonfirmasi()
        }
    }

    fun getIntentData() {
        if (
            intent.hasExtra("id")&& intent.hasExtra("nim") && intent.hasExtra("nama") && intent.hasExtra("jurusan")
        ){

            val idMahasiswa = intent.getStringExtra("id")
            val nimMahasiswa = intent.getStringExtra("nim")
            val namaMahasiswa = intent.getStringExtra("nama")
            val jurusanMahasiswa = intent.getStringExtra("jurusan")

            val txtNim = findViewById<EditText>(R.id.txt_edit_nim)
            val txtNama = findViewById<EditText>(R.id.txt_edit_nama)
            val txtJurusan = findViewById<EditText>(R.id.txt_edit_jurusan)

            txtNim.setText(nimMahasiswa)
            txtNama.setText(namaMahasiswa)
            txtJurusan.setText(jurusanMahasiswa)

        }else{
            Toast.makeText(this, "Tidak ada Data !", Toast.LENGTH_SHORT).show()
        }
    }

    fun dialogKonfirmasi() {
        val idMahasiswa = intent.getStringExtra("id")
        val namaMahasiswa = intent.getStringExtra("nama")

        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("Delete " + namaMahasiswa + " ?")
        alertDialog.setMessage("Apakah Anda Yakin Menghapus " + namaMahasiswa + " ?")

        alertDialog.setPositiveButton("Iya",DialogInterface.OnClickListener{dialog, which ->
            val dbSaya = MyDBHelper(this)
            dbSaya.hapusMahasiswa(idMahasiswa)
            startActivity(Intent(this, MainActivity::class.java))
        })

        alertDialog.setNegativeButton("Tidak", DialogInterface.OnClickListener {dialog, which ->  })
        alertDialog.create().show()
    }
}