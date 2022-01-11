package com.example.a0277vikkicrud;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDBHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "Mahasiswa.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME       = "BioMhs";
    private static final String COLUMN_ID        = "_id";
    private static final String COLUMN_NIM       = "mhs_nim";
    private static final String COLUMN_NAMA      = "mhs_nama";
    private static final String COLUMN_JURUSAN   = "mhs_jurusan";

    public MyDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NIM + " INTEGER, " +
                COLUMN_NAMA + " TEXT, " +
                COLUMN_JURUSAN + " TEXT" +
                ") ;";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    void tambahmhs(int nim, String nama, String jurusan) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NIM, nim);
        cv.put(COLUMN_NAMA, nama);
        cv.put(COLUMN_JURUSAN, jurusan);

        long result = db.insert(TABLE_NAME, null, cv);

        if (result == -1) {
            Toast.makeText(context, "Gagal !", Toast.LENGTH_SHORT).show();
        } else  {
            Toast.makeText(context, "Data Ditambahkan !", Toast.LENGTH_SHORT).show();
        }
    }
    Cursor BacaSemuaData(){
        String queryKita = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase dbSaya = this.getReadableDatabase();

        Cursor dataSaya = null;
        if(dbSaya != null){
            dataSaya = dbSaya.rawQuery(queryKita, null);
        }
        return dataSaya;
    }

    void ubahMahasiswa(String baris_id, String nim, String nama, String jurusan){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues dataSaya = new ContentValues();
        dataSaya.put(COLUMN_NIM, nim);
        dataSaya.put(COLUMN_NAMA, nama);
        dataSaya.put(COLUMN_JURUSAN, jurusan);

        long hasil = db.update(TABLE_NAME, dataSaya, "_id=?", new String[]{baris_id});

        if (hasil == -1) {
            Toast.makeText(context, "Ada gangguan !", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Berhasil di Ubah !", Toast.LENGTH_SHORT).show();
        }
    }

    void hapusMahasiswa(String row_id){
        SQLiteDatabase dbSaya = this.getReadableDatabase();
        long result = dbSaya.delete(TABLE_NAME, "_id=?", new String[]{row_id});

        if (result == -1) {
            Toast.makeText(context, "Gagal Delete !", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Berhasil Delete !", Toast.LENGTH_SHORT).show();
        }
    }
}
