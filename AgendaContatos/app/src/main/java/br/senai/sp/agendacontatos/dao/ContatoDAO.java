package br.senai.sp.agendacontatos.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.senai.sp.agendacontatos.modelo.Contato;

public class ContatoDAO extends SQLiteOpenHelper {


    public ContatoDAO(Context context) {

        //ABERTURA DO BANCO DE DADOS

        super(context, "db_contato", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

       String sql = "CREATE TABLE tbl_contato(" +
               "id INTERGER PRIMARY KEY," +
               "nome TEXT NOT NULL, " +
               "endereco TEXT NOT NULL, " +
               "telefone TEXT NOT NULL, " +
               "email TEXT NOT NULL, " +
               "linkedin TEXT NOT NULL)";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void salvar(Contato contato){
        SQLiteDatabase db = getWritableDatabase(); //Escreve dentro do banco


    }
}
