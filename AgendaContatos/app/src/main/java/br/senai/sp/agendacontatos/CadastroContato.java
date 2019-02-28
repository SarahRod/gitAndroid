package br.senai.sp.agendacontatos;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.senai.sp.agendacontatos.dao.ContatoDAO;
import br.senai.sp.agendacontatos.helper.CadastroContatoHelper;
import br.senai.sp.agendacontatos.modelo.Contato;

public class CadastroContato extends AppCompatActivity {

    private CadastroContatoHelper helper;
    private TextInputLayout layoutTxtNome;
    private TextInputLayout layoutTxtEndereco;
    private TextInputLayout layoutTxtTelefone;
    private TextInputLayout layoutTxtEmail;
    private TextInputLayout layoutTxtLinkedin;
    private EditText txtNome;
    private EditText txtEndereco;
    private EditText txtTelefone;
    private EditText txtEmail;
    private EditText txtLinkedin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_contato);

        layoutTxtNome = findViewById(R.id.layout_txt_nome);
        layoutTxtEndereco = findViewById(R.id.layout_txt_endereco);
        layoutTxtTelefone = findViewById(R.id.layout_txt_telefone);
        layoutTxtEmail = findViewById(R.id.layout_txt_email);
        layoutTxtLinkedin =findViewById(R.id.layout_txt_linkedin);
        txtNome = findViewById(R.id.txt_nome);
        txtEndereco = findViewById(R.id.txt_endereco);
        txtTelefone = findViewById(R.id.txt_telefone);
        txtEmail = findViewById(R.id.txt_email);
        txtLinkedin = findViewById(R.id.txt_linkedin);




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



                    if (validar() == true){

                        if (contato.getId() == 0) {
                            dao.salvar(contato);
                        } else {

                            dao.atualizar(contato);
                        }

                        dao.close();
                        Toast.makeText(this, contato.getNome() + " gravado com Sucesso", Toast.LENGTH_LONG).show();
                        finish();
                    } else{
                        Toast.makeText(this, contato.getNome() + " Preencha os campos corretamente", Toast.LENGTH_LONG).show();
                    }






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


    private boolean validar(){
        boolean validado = true;

        if(txtNome.getText().toString().isEmpty()){
            layoutTxtNome.setErrorEnabled(true);
            layoutTxtNome.setError("Por favor, digite o Nome");
        }else{
            layoutTxtNome.setErrorEnabled(false);
        }


        if(txtEndereco.getText().toString().isEmpty()){
            layoutTxtEndereco.setErrorEnabled(true);
            layoutTxtEndereco.setError("Por favor, digite o Endereço");
        }else{
            layoutTxtEndereco.setErrorEnabled(false);
        }


        if(txtTelefone.getText().toString().isEmpty()){
            layoutTxtTelefone.setErrorEnabled(true);
            layoutTxtTelefone.setError("Por favor, digite o Telefone");
        }else{
            layoutTxtTelefone.setErrorEnabled(false);
        }

        if(txtEmail.getText().toString().isEmpty()){
            layoutTxtEmail.setErrorEnabled(true);
            layoutTxtEmail.setError("Por favor, digite o Email");
        }else{
            layoutTxtEmail.setErrorEnabled(false);
        }

        if(txtLinkedin.getText().toString().isEmpty()){
            layoutTxtLinkedin.setErrorEnabled(true);
            layoutTxtLinkedin.setError("Por favor, digite o o endereço do Linkedin");
        }else{
            layoutTxtLinkedin.setErrorEnabled(false);
        }


       return validado;
    }




}
