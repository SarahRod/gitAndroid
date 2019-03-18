package br.senai.sp.catlogodefilmes;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.senai.sp.adapter.FilmesAdapter;
import br.senai.sp.dao.FilmeDAO;
import br.senai.sp.modelo.Filme;

public class MainActivity extends AppCompatActivity {

    private ListView listaFilmes; //declaração da lista
    private Button btnNovoFilme;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //opter a intenção



        //Associo o objeto ListView do Java à View do layout xml
        listaFilmes = findViewById(R.id.list_filmes); //assossiação do listaFilmes com o  que esta no list_filmes
        //coloca no começ pois assim vai enchergar o listaFilmes



        btnNovoFilme = findViewById(R.id.bt_novo_filme);




        //******AÇÃO DO BOTÃO NOVO

        btnNovoFilme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //Toast.makeText(MainActivity.this,"Filme Salvo", Toast.LENGTH_SHORT).show();  // aonde vc quer colocar pois está em outro contexto(contexto)

                Intent cadastroFilme = new Intent(MainActivity.this, CadastroFilmeActivity.class); //passar o contexto e activity que irá ser aberta
                startActivity(cadastroFilme); // para começar a aplicação

            }

        });
       // Toast.makeText(this, "Estou no método ON-CREATE", Toast.LENGTH_LONG).show();

        //****DEFINIÇÃO DE UM MENU DE CONTEXTO PARA A LISTVIEW(lista de filmes)



        //criaç]ão de um menu de contexto, disparando um vento prar o prx metdo
        registerForContextMenu(listaFilmes);
        //vai receber um listview


        listaFilmes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Filme filme = (Filme) listaFilmes.getItemAtPosition(position);

                Intent cadastro = new Intent(MainActivity.this, CadastroFilmeActivity.class);
                cadastro.putExtra("filme", filme);
                startActivity(cadastro);
            }
        });

    }

    //criação do menu e contexto


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_contexto_listar_filmes, menu); // vc vai inflar esse menu no menu


        /*MenuItem deletar = menu.add("excluir");
        MenuItem editar = menu.add("Editar");
        MenuItem vizualizar = menu.add("Visualizar");

        //criar o ouvinte do item do menu
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(MainActivity.this, "deletar,", Toast.LENGTH_LONG).show();
                return false;
            }
        });*/

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        final FilmeDAO dao = new FilmeDAO (this);

         AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

         final Filme filme = (Filme)listaFilmes.getItemAtPosition(info.position);
         Toast.makeText(this, filme.getTitulo(),Toast.LENGTH_LONG).show();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder. setTitle("Excluir Filme");
        builder.setMessage("Confirmar a exclusão do " + filme.getTitulo() + " ?");

        builder.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dao.excluir(filme);
                dao.close();
                carregarLista();
            }
        });

        builder.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();



        //dao.excluir();


         //Toast.makeText(this, item.getTitle(), Toast.LENGTH_LONG).show();
         return super.onContextItemSelected(item);
    }




    @Override
    protected void onResume() {

        carregarLista();

        //Adaptador do adapter


        super.onResume();
    }

    private void carregarLista(){
        //******MATRIZ DE FILMES QUE SERÃO EXIBIDOS NO LISTVIEW
        //Abrir o banco de dados
        //Rodar uma query de consulta
        //Retornar um Array List

        FilmeDAO dao = new FilmeDAO(this);
        List<Filme> filmes = dao.getFilmes(); //Precisa criar um dao que vai retornar um array list
        dao.close();



        /*String [] arrayListaFilmes = {"Como Treinar o seu Dragão 3",
                            "Ralph Breaks the Internet",
                            "Homem-aranha: no Aranhaverso",
                            "A Menino que Queria ser Rei",
                            "Minha Vida em Marte", "Como Treinar o seu Dragão 3",
                            "Ralph Breaks the Internet",
                            "Homem-aranha: no Aranhaverso",
                            "A Menino que Queria ser Rei",
                            "Minha Vida em Marte", "Como Treinar o seu Dragão 3",
                            "Ralph Breaks the Internet",
                            "Homem-aranha: no Aranhaverso",
                            "A Menino que Queria ser Rei",
                            "Minha Vida em Marte"};*/ //criação de uma matiz para filmes


        //***Definimos um adapter para carregar os dados do array List na ListView
        //**Utilizamos  um layout pronto (simple_list_item_1)
        //o array adapter é agora de filme
        //ArrayAdapter<Filme> listaFilmesAdapter = new ArrayAdapter<Filme>(this, android.R.layout.simple_list_item_1,filmes);//pegar o vetor para que se transforme em uma listView -- vai ser um adpter com string --   new ArrayAdapter<String>(em qual contexto vai rodar - aonde esta e qual array vai colocar)
        //Carregar o layout qye criamos
        FilmesAdapter adapter = new FilmesAdapter(this, filmes);
        listaFilmes.setAdapter(adapter);

        //Injetamos o adpter no objeto listView
        //listaFilmes.setAdapter(listaFilmesAdapter);  // chama o lista filmes adpter
    }


}
