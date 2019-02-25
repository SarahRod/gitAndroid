package br.senai.sp.agendacontatos;

import android.content.Intent;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import br.senai.sp.agendacontatos.dao.ContatoDAO;

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

    }



    @Override
    protected void onResume() {
        carregarLista();
        super.onResume();
    }

    private void carregarLista(){

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

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, ArrayListaContatos);
        listaContatos.setAdapter(adapter);*/





    }


}
