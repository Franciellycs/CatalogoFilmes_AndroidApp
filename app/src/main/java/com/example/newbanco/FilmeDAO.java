package com.example.newbanco;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class FilmeDAO {

    public static void inserir(Context context,Filme filme){

        //Classe que armazena os dados no banco
        ContentValues values = new ContentValues();
        values.put("nome",filme.getNome());
        values.put("categoria",filme.getCategoria());
        values.put("idioma", filme.getIdioma());
        values.put("legenda", filme.getLegenda());

        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getWritableDatabase();

        db.insert("filmes",null,values);
    }

    public static void editar(Context context,Filme filme){
        ContentValues values = new ContentValues();
        values.put("nome",filme.getNome());
        values.put("categoria",filme.getCategoria());
        values.put("idioma",filme.getIdioma());
        values.put("legenda",filme.getLegenda());

        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getWritableDatabase();

        db.update("filmes",values," id = " + filme.getId(),null);

    }

    public static void excluir(Context context,int idFilme){
        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getWritableDatabase();

        db.delete("filmes"," id = " + idFilme,null);
    }

    public static List<Filme> getFilme(Context context){
        List<Filme> lista = new ArrayList<>();
        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getWritableDatabase();

        //Classe que percorre os registros do banco
        Cursor cursor = db.rawQuery("SELECT * FROM filmes ORDER BY nome",null );
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            do{
                Filme film = new Filme();
                film.setId(cursor.getInt(0));
                film.setNome(cursor.getString(1));
                film.setCategoria(cursor.getString(2));
                film.setIdioma(cursor.getString(3));
                film.setLegenda(cursor.getString(4));
                lista.add(film);
            }while(cursor.moveToNext());
        }
        return lista;
    }

    public static Filme getFilmebyId(Context context, int idFilme){
        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM filmes WHERE id = "+idFilme,null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            Filme film = new Filme();
            film.setId(cursor.getInt(0));
            film.setNome(cursor.getString(1));
            film.setCategoria(cursor.getString(2));
            film.setIdioma(cursor.getString(3));
            film.setLegenda(cursor.getString(4));
            return film;
            }
        else
        {
            return null;
        }
    }
}
