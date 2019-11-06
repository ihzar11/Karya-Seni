package org.android.jplas.projectsuperman;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataHelper extends SQLiteOpenHelper {
    private static String DATABASE_NAME = "projeksatu";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_BIODATA = "datadiri";
    private static final String KEY_ID = "id";
    private static final String KEY_NAMA = "nama";
    private static final String KEY_UMUR = "umur";
    private static final String KEYKELAMIN = "jeniskelamin";

    private static final String CREATE_TABLE_USER = "create table "+ TABLE_BIODATA +" ("+KEY_ID+" INTEGER primary key autoincrement,"+ KEY_NAMA +" TEXT,"+KEY_UMUR+" TEXT,"+KEYKELAMIN+" TEXT);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_BIODATA + "'");
        onCreate(db);
    }

    public void addBiodata(String nama, String umur, String kelamin){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAMA,nama);
        values.put(KEY_UMUR,umur);
        values.put(KEYKELAMIN,kelamin);
        db.insert(TABLE_BIODATA,null,values);
    }

    public ArrayList<KegiatanModel> getKegiatan() {
        ArrayList<KegiatanModel> list = new ArrayList<KegiatanModel>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM "+ TABLE_KEGIATAN;
        Cursor c = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                KegiatanModel k = new KegiatanModel();
                k.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                k.setNama(c.getString(c.getColumnIndex(KEY_NAMA)));
                k.setKeterangan(c.getString(c.getColumnIndex(KEY_KETERANGAN)));
                k.setWaktu(c.getString(c.getColumnIndex(KEY_WAKTU)));

                list.add(k);
            }while(c.moveToNext());
        }
        return list;
    }

    public void updateKegiatan(int id,String nama, String keterangan, String waktu){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAMA,nama);
        values.put(KEY_KETERANGAN,keterangan);
        values.put(KEY_WAKTU,waktu);
        db.update(TABLE_KEGIATAN,values, KEY_ID+" = ?", new String[]{String.valueOf(id)});
    }

    public void deleteKegiatan(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_KEGIATAN, KEY_ID+" = ?", new String[]{String.valueOf(id)});
    }
}
