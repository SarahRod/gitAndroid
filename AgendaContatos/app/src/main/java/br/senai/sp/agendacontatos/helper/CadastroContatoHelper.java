package br.senai.sp.agendacontatos.helper;

import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.senai.sp.agendacontatos.CadastroContato;
import br.senai.sp.agendacontatos.R;
import br.senai.sp.agendacontatos.modelo.Contato;

public class CadastroContatoHelper {

    private EditText txtNome;
    private EditText txtEndereco;
    private EditText txtTelefone;
    private EditText txtEmail;
    private EditText txtLinkedin;
    private Contato contato;
    private TextInputLayout layoutTxtNome;
    private TextInputLayout layoutTxtEndereco;
    private TextInputLayout layoutTxtTelefone;
    private TextInputLayout layoutTxtEmail;
    private TextInputLayout layoutTxtLinkedin;


    public CadastroContatoHelper(CadastroContato activity){

        txtNome = activity.findViewById(R.id.txt_nome);
        txtEndereco = activity.findViewById(R.id.txt_endereco);
        txtTelefone = activity.findViewById(R.id.txt_telefone);
        txtEmail = activity.findViewById(R.id.txt_email);
        txtLinkedin = activity.findViewById(R.id.txt_linkedin);

        layoutTxtNome = activity.findViewById(R.id.layout_txt_nome);
        layoutTxtTelefone = activity.findViewById(R.id.layout_txt_telefone);
        layoutTxtEmail = activity.findViewById(R.id.layout_txt_email);
        layoutTxtEndereco = activity.findViewById(R.id.layout_txt_endereco);
        layoutTxtLinkedin = activity.findViewById(R.id.layout_txt_linkedin);

        contato = new Contato();
    }

    public Contato getContato() {

        contato.setNome(txtNome.getText().toString());
        contato.setEndereco(txtEndereco.getText().toString());
        contato.setTelefone(txtTelefone.getText().toString());
        contato.setEmail(txtEmail.getText().toString());
        contato.setLinkedin(txtLinkedin.getText().toString());

        return contato;
    }

    public void preencherContato (Contato contato){
        txtNome.setText(contato.getNome());
        txtEndereco.setText(contato.getEndereco());
        txtTelefone.setText(contato.getTelefone().toString());
        txtEmail.setText(contato.getEmail());
        txtLinkedin.setText(contato.getLinkedin());

        this.contato = contato;
    }

   /* public  boolean validarEmail(){
        boolean validadoEmail = false;

       if (txtEmail.toString().length() > 0){
           String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
           Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
           Matcher matcher = pattern.matcher((CharSequence) txtEmail);
           if (matcher.matches()) {
               validadoEmail = true;
           }

       }


        return validadoEmail;
    }*/




    public boolean validar(){
        boolean validado = true;

        if(txtNome.getText().toString().isEmpty()){
            layoutTxtNome.setErrorEnabled(true);
            layoutTxtNome.setError("Por favor, digite o Nome");
            validado=false;

        }else{
            layoutTxtNome.setErrorEnabled(false);
        }


        if(txtEndereco.getText().toString().isEmpty()){
            layoutTxtEndereco.setErrorEnabled(true);
            layoutTxtEndereco.setError("Por favor, digite o Endereço");
            validado=false;
        }else{
            layoutTxtEndereco.setErrorEnabled(false);
        }



        if(txtTelefone.getText().toString().isEmpty()){
            layoutTxtTelefone.setErrorEnabled(true);
            layoutTxtTelefone.setError("Por favor, digite o Telefone");
            validado=false;
        }else{
            layoutTxtTelefone.setErrorEnabled(false);
        }

        if(txtEmail.getText().toString().isEmpty()){
            layoutTxtEmail.setErrorEnabled(true);
            layoutTxtEmail.setError("Por favor, digite o Email");
            validado=false;
        }else{
            layoutTxtEmail.setErrorEnabled(false);
        }


        if (!txtEmail.getText().toString().matches("[a-zA-Z0-9-_.]+@[a-z]+.[a-z]+")) {
                layoutTxtEmail.setErrorEnabled(true);
                layoutTxtEmail.setError("Por favor, digite o email conforme o exemplo");
                validado = false;
        }

        if(!txtTelefone.getText().toString().matches("^\\([0-9]{2}\\) [0-9]{4}-[0-9]{4}$")){
            layoutTxtTelefone.setErrorEnabled(true);
            layoutTxtTelefone.setError("Por favor, digite o Telefone conforme o exemplo");
            validado = false;

        }



        if(txtLinkedin.getText().toString().isEmpty()){
            layoutTxtLinkedin.setErrorEnabled(true);
            layoutTxtLinkedin.setError("Por favor, digite o o endereço do Linkedin");
            validado=false;
        }else{
            layoutTxtLinkedin.setErrorEnabled(false);
        }


        return validado;
    }

    public void limparCampos(){
        txtNome.setText("");
        txtEndereco.setText("");
        txtTelefone.setText("");
        txtEmail.setText("");
        txtLinkedin.setText("");
    }



}
