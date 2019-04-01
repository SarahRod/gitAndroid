package br.senai.sp.agendacontatos;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;

import br.senai.sp.agendacontatos.dao.ContatoDAO;
import br.senai.sp.agendacontatos.helper.CadastroContatoHelper;
import br.senai.sp.agendacontatos.modelo.Contato;

public class CadastroContato extends AppCompatActivity {

    private CadastroContatoHelper helper;

    public static final int GALERIA_REQUEST = 5000;
    public static final int CAMERA_REQUEST = 1000;

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

    private Button btnCamera;
    private Button btnGaleria;
    private ImageView imgFoto;
    private String caminhoFoto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_contato);


            txtNome = findViewById(R.id.txt_nome);
            txtEndereco = findViewById(R.id.txt_endereco);
            txtTelefone = findViewById(R.id.txt_telefone);
            txtEmail = findViewById(R.id.txt_email);
            txtLinkedin = findViewById(R.id.txt_linkedin);



        helper = new CadastroContatoHelper(this);

        btnCamera = findViewById(R.id.btn_camera);
        btnGaleria = findViewById(R.id.btn_galeria);
        imgFoto = findViewById(R.id.image_view_contato);


        btnGaleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentGaleria = new Intent(Intent.ACTION_GET_CONTENT);
                intentGaleria.setType("image/*");
                startActivityForResult(intentGaleria, GALERIA_REQUEST);

            }
        });







        Intent intent = getIntent();
        Contato contato = (Contato) intent.getSerializableExtra("contato");

        if(contato != null){
            helper.preencherContato(contato);
        }



    }

    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    @Nullable Intent data) {


        if(resultCode == RESULT_OK){

            try {
                //Log.d("Retorno,", String.valueOf(resultCode));


                /**serve para nãoo travar a aplicação ao não selecionar a imagem*/
                if(resultCode != GALERIA_REQUEST){

                    InputStream inputStream = getContentResolver().openInputStream(data.getData());


                    /*fabrica os bits da imagem ele é a imagem*/
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    imgFoto.setImageBitmap(bitmap);

                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


        }



        super.onActivityResult(requestCode, resultCode, data);
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
