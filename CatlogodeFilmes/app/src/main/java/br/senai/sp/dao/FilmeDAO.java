package br.senai.sp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import br.senai.sp.modelo.Filme;

public class FilmeDAO extends SQLiteOpenHelper {
    public FilmeDAO(Context context) {///quem chamr tem que pedir, mas qualquer activity pode usa-la

        //Aqui se abre o banco de dados

        super(context, "db_filme",null, 2); //o contexro que ele vai passar. o nome do  banco de dados. versão. O super é SQLiteOpenHelper(classe mãe)
    }//importar para consgir ter acesso ao banco de dados
    @Override
    public void onCreate(SQLiteDatabase db) {//entrega o banco, não precisa se identificar

        String sql = "CREATE TABLE tbl_filme (" +
                "id INTEGER PRIMARY KEY, " +
                "titulo TEXT NOT NULL, " +
                "diretor TEXT NOT NULL, " +
                "genero TEXT NOT NULL, " +
                "data_lancamento TEXT NOT NULL, " +
                "duracao TEXT NOT NULL, " +
                "nota INTEGER NOT NULL)";

         db.execSQL(sql);


    }

    @Override// o metodo esta sendo sobre escrito a classe mãe. Exxiste na classe mãe mas está escrevendo dnv. E para usar esse
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //mudar a versão
        /*Strig sql = "DROP TABLE IF EXISTS tbl_filme";
        * db.execSQL(sql)
        * onCreate(db)*/
        String sql = "ALTER TABLE tbl_filme ADD COLUMN foto BLOB";
        db.execSQL(sql);
    }


    public void salvar(Filme filme) {

        SQLiteDatabase db = getWritableDatabase(); //banco de dados com permissão de escrevr dentro // ou gravavel no banco de dados.

        //Cria um conjunto vazio de valores usando o tamanho inicial padrão
        ContentValues dados = getContentValues(filme);

        db.insert("tbl_filme", null, dados); //nome da tabela
    }

    @NonNull
    private ContentValues getContentValues(Filme filme) {
        ContentValues dados = new ContentValues();

        dados.put("titulo", filme.getTitulo());
        dados.put("diretor", filme.getDiretor());
        dados.put("genero", filme.getGenero());
        dados.put ("data_lancamento", filme.getDataLancamento());
        dados.put("duracao", filme.getDuracao());
        dados.put("nota", filme.getNota());
        dados.put("foto", filme.getFoto());
        return dados;
    }

    public List<Filme> getFilmes() {

        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM tbl_filme";

        Cursor c = db.rawQuery(sql, null); // os filmes estão no cursor

        List<Filme> filmes = new ArrayList<>();
        //db.execSQL(sql); //esse não retorna um resultado então usa outro

        while(c.moveToNext())//Enquanto conseguri mover para a prox linha,... tem conteudo cria um objeto filme... cria e joga dento da lista... quando não tiver sai do loop
        {
            Filme filme = new Filme();

            filme.setId(c.getInt(c.getColumnIndex("id"))); //setar o cursor para pegar o inteiro que esta na coluna id
            filme.setTitulo(c.getString(c.getColumnIndex("titulo")));
            filme.setDiretor(c.getString(c.getColumnIndex("diretor")));
            filme.setGenero(c.getString(c.getColumnIndex("genero")));
            filme.setDataLancamento(c.getString(c.getColumnIndex("data_lancamento")));
            filme.setDuracao(c.getString(c.getColumnIndex("duracao")));
            filme.setNota(c.getInt(c.getColumnIndex("nota")));
            filme.setFoto(c.getBlob(c.getColumnIndex("foto")));

            filmes.add(filme); //adiciona no array list

        }

        return filmes;
    }


    public void excluir (Filme filme){

        //metodo ue vai pegar o banco de dados
        SQLiteDatabase db = getWritableDatabase();

        String[] params = {String.valueOf(filme.getId())};

        db.delete("tbl_filme", "id = ?", params);
    }


    public void atualizar(Filme filme) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = getContentValues(filme);

        String [] params = {String.valueOf(filme.getId())};
        db.update("tbl_filme", dados, "id = ?", params);
    }
}
