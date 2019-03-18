package br.senai.sp.catlogodefilmes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import br.senai.sp.dao.FilmeDAO;
import br.senai.sp.modelo.Filme;

public class CadastroFilmeActivity extends AppCompatActivity {

    private CadastroFilmeHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_filme);

        helper = new CadastroFilmeHelper(this); // quer passar uma activity que será está// no construtor recebe a actuvity e fazer a ligação
        Intent intent = getIntent();
        Filme filme = (Filme) intent.getSerializableExtra("filme");

        if(filme != null){
            helper.preencherFormulario(filme);
        }





    }

    @Override // reescreve o codigo que tem na mãe e rodou o nosso método que criamos e retornou o menu inflado
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();//criar um menu para inflar ele... aumentar de tamanho, bexiga. Pegar o inflador
        menuInflater.inflate(R.menu.menu_cadastro_filmes, menu);//chama o menu q acabou de criar. inflar e chama o menu que ja acabou de criar. precisa de argumentos que é o layout do menu e passar o o objeto menu que é o que vc recebeu que esta na activity, mas ele está vazio, então teremos que inflar ele com o nosso coteúdo




        return super.onCreateOptionsMenu(menu);
    }

    @Override // vai passar o item selecionado - não é anonimo
    public boolean onOptionsItemSelected(MenuItem item) {
        // vai passar o item que foi selecionado no menu

        switch (item.getItemId()){
            case R.id.menu_salvar:


                 // CadastroFilmeHelper helper = new CadastroFilmeHelper(this) /Pode ser assim também// quer passar uma activity que será está// no construtor recebe a actuvity e fazer a ligação
                Filme filme = helper.getFilme();//os valores que estão sendo cadastrados vão ser retornado
                //Teste unitário ::: Toast.makeText(this, filme.getTitulo(), Toast.LENGTH_LONG).show();
                FilmeDAO dao = new FilmeDAO(this);// passando o contexto que é essa activity.
                //Cria o filme e chama o filme dao, passando os atributos para ela

                if(filme.getId() == 0){
                    dao.salvar(filme);
                } else{
                    dao.atualizar(filme);
                }


                dao.close();
                Toast.makeText(this, filme.getTitulo() + " gravado com sucesso", Toast.LENGTH_LONG).show();
                finish();

                //Abrir o banco
                //query de insert
                //fechar banco

                break;
            case R.id.menu_del:
                Toast.makeText(CadastroFilmeActivity.this,"Filme Excluido", Toast.LENGTH_SHORT).show();
                break;

            case R.id.menu_configuracoes:
                Toast.makeText(CadastroFilmeActivity.this,"configurações", Toast.LENGTH_SHORT).show();
                break;

            default:
                Toast.makeText(CadastroFilmeActivity.this,"Nada", Toast.LENGTH_SHORT).show();
                break;
        }

        /*if (item.getItemId() == R.id.menu_salvar){
            Toast.makeText(CadastroFilmeActivity.this,"Filme Salvo", Toast.LENGTH_SHORT).show();
        }//usando o if para pegar a ação*/

        //item.getItemId()// pegar o id da opção que foi selecionada

        return super.onOptionsItemSelected(item);
    }
}
