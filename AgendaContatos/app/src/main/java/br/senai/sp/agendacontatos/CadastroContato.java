package br.senai.sp.agendacontatos;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
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
    //
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
    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_contato);

        /*
            layoutTxtNome = findViewById(R.id.layout_txt_nome);
            layoutTxtEndereco = findViewById(R.id.layout_txt_endereco);
            layoutTxtTelefone = findViewById(R.id.layout_txt_telefone);
            layoutTxtEmail = findViewById(R.id.layout_txt_email);
            layoutTxtLinkedin =findViewById(R.id.layout_txt_linkedin);
        */
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

                    if(helper.validar() ==true) {
                        if (contato.getId() == 0) {
                            dao.salvar(contato);
                            dao.close();
                            Toast.makeText(this, contato.getNome() + " gravado com sucesso", Toast.LENGTH_LONG).show();
                            finish();

                        } else {
                            dao.atualizar(contato);
                            dao.close();
                            Toast.makeText(this, contato.getNome() + " atualizado com sucesso", Toast.LENGTH_LONG).show();
                            finish();
                        }
                    } else{
                        Toast.makeText(this, "Verifique se os campos estão preenchidos", Toast.LENGTH_LONG).show();
                    }

            break;
            case R.id.menu_limpar:

                helper.limparCampos();

                break;

            case R.id.menu_sair:

                AlertDialog.Builder builder =  new AlertDialog.Builder(this);
                builder.setTitle("Sair");
                builder.setMessage("Tem certeza que deseja sair sem salvar?");

                builder.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent mainActivity = new Intent(CadastroContato.this, MainActivity.class);
                        startActivity(mainActivity);
                    }
                }).setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                builder.create().show();

                break;

            default:
                Toast.makeText(CadastroContato.this,"Nada", Toast.LENGTH_SHORT).show();
                break;


        }

        return super.onOptionsItemSelected(item);
    }



}
