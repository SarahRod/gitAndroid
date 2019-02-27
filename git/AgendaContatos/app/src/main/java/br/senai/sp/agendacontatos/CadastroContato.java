package br.senai.sp.agendacontatos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import br.senai.sp.agendacontatos.dao.ContatoDAO;
import br.senai.sp.agendacontatos.helper.CadastroContatoHelper;
import br.senai.sp.agendacontatos.modelo.Contato;

public class CadastroContato extends AppCompatActivity {

    private CadastroContatoHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_contato);

        helper = new CadastroContatoHelper(this);
        Intent intent = getIntent();
        Contato contato = (Contato) intent.getSerializableExtra("contato");

        if(contato != null){
            helper.preencherContato(contato);
        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_cadastro_contato,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.menu_salvar:

                Contato contato = helper.getContato();
                ContatoDAO dao = new ContatoDAO(this);

                if(contato.getId() == 0){
                    dao.salvar(contato);
                } else{

                }

                dao.close();
                Toast.makeText(this, contato.getNome() + " gravado com Sucesso", Toast.LENGTH_LONG).show();
                finish();


            break;

            case R.id.menu_excluir:
                Toast.makeText(CadastroContato.this,"Filme Excluido", Toast.LENGTH_SHORT).show();

                break;
            default:
                Toast.makeText(CadastroContato.this,"Nada", Toast.LENGTH_SHORT).show();
                break;


        }




        /*if (item.getItemId() == R.id.menu_salvar) {
            Toast.makeText(CadastroContato.this, "Filme Salvo", Toast.LENGTH_SHORT).show();

            item.getItemId();
        }*/

        return super.onOptionsItemSelected(item);
    }
}
