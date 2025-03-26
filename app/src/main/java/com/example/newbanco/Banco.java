package com.example.newbanco;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Banco extends SQLiteOpenHelper {

    private static final String NOME_BANCO ="AppPersist";

    public Banco(@Nullable Context context) {
        super(context, NOME_BANCO, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS filmes( "+
                " id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT , "+
                " nome TEXT NOT NULL, " +
                " categoria TEXT,"+
                " idioma TEXT,"+
                " legenda TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
    }
}
