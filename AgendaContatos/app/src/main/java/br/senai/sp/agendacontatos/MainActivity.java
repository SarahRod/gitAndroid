package br.senai.sp.agendacontatos;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.VisibleForTesting;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.List;

import br.senai.sp.agendacontatos.dao.ContatoDAO;
import br.senai.sp.agendacontatos.modelo.Contato;

public class MainActivity extends AppCompatActivity {
 //*****   ESTA É a MAINACTIVITY      ****************/

    private ListView listaContatos;
    private Button btnNovoContato;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

    btnNovoContato = findViewById(R.id.btn_novo_contato);

    listaContatos = findViewById(R.id.lista_contato);




    // O BOTÃO CHAMA A ACTIVITY CADASTRAR CONTATO
    btnNovoContato.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Toast.makeText(MainActivity.this, "Contato Salvo", Toast.LENGTH_LONG).show();
            Intent cadastroContato = new Intent(MainActivity.this, CadastroContato.class);
            startActivity(cadastroContato);

        }
    });

    registerForContextMenu(listaContatos);

    listaContatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contato contato = (Contato) listaContatos.getItemAtPosition(position);

                Intent cadastro = new Intent(MainActivity.this, CadastroContato.class);
                cadastro.putExtra("contato", contato);
                startActivity(cadastro);
            }
        });

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_contexto_contato, menu);


        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        final ContatoDAO dao = new ContatoDAO(this);

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        final Contato contato = (Contato)listaContatos.getItemAtPosition(info.position);
        Toast.makeText(this, contato.getNome(),Toast.LENGTH_LONG).show();


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Excluir Contato");
        builder.setMessage("Confirmar a exclusão do " + contato.getNome() + " ?");

        builder.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dao.excluir(contato);

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

        return super.onContextItemSelected(item);
    }

    @Override
    protected void onResume() {
        carregarLista();

        super.onResume();
    }

    private void carregarLista(){


        ContatoDAO dao = new ContatoDAO(this);
        List<Contato> contatos = dao.getContatos();
        dao.close();


        /*String[] ArrayListaContatos = {"Como Treinar o seu Dragão 3",
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
                "Minha Vida em Marte"};
        */

        ArrayAdapter<Contato> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, contatos);
        listaContatos.setAdapter(adapter);

    }

}
