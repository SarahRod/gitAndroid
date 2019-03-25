package br.senai.sp.catlogodefilmes;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import br.senai.sp.conversores.Imagem;
import br.senai.sp.modelo.Filme;

public class CadastroFilmeHelper {
    private EditText txtTitulo;
    private EditText txtDiretor;
    private EditText txtDataLancamento;
    private EditText txtGenero;
    private EditText txtDuracao;
    private RatingBar nota;
    private Filme filme;
    private ImageView foto;

    public CadastroFilmeHelper(CadastroFilmeActivity activity){ //metodo que tem o msm nome da classe e não tem retorno-- esse metodo vai ser chamado primeiro -- como não é uma actitivy então vai ter que passar a activity que vai passar as informacoes


        txtTitulo = activity.findViewById(R.id.txt_titulo); // O R por natureza é global
        txtDiretor = activity.findViewById(R.id.txt_diretor);
        txtDataLancamento = activity.findViewById(R.id.txt_data_lancamento);
        txtGenero = activity.findViewById(R.id.txt_genero);
        txtDuracao = activity.findViewById(R.id.txt_duracao);
        nota = activity.findViewById(R.id.rate_nota); // construir um filme
        foto = activity.findViewById(R.id.image_view_filme);

        //ja cria o filme ao clicar
        filme = new Filme();

    }

    //fazer o metodo filme vai ter que retornar para quem chamou. Retornar o filme inteiro. pois não retorna uma coisa mas varias.
    public Filme getFilme(){ // retorno para retornar o filme


        filme.setDataLancamento(txtDataLancamento.getText().toString());
        filme.setTitulo(txtTitulo.getText().toString());
        filme.setDiretor(txtDiretor.getText().toString());
        filme.setGenero(txtGenero.getText().toString());
        filme.setDuracao(txtDuracao.getText().toString());
        filme.setNota(nota.getProgress());//filme.setNota((int)nota.getRating()); // é um int pois retorna um número, fazer um casting pois tinha que usar um float. tb pode utilizar o getProgress que retorna o progresso


        /*contúdo do imageview convertido em bitmap*/



        Bitmap bm = ((BitmapDrawable)foto.getDrawable()).getBitmap();

        Bitmap bitmapReduzido = Bitmap.createScaledBitmap(bm, 300, 300,true);
        /*converter o bitmap em array de bytes*/
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmapReduzido.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream);
        byte[] fotoArray = byteArrayOutputStream.toByteArray();

        filme.setFoto(fotoArray);



        return filme;
    }
     public void preencherFormulario (Filme filme){
        txtDataLancamento.setText(filme.getDataLancamento());
        txtDiretor.setText(filme.getDiretor());
        txtGenero.setText(filme.getGenero());
        txtDuracao.setText(filme.getDuracao());
        txtTitulo.setText(filme.getTitulo());
        nota.setProgress(filme.getNota());

        // se o tamanho que vier for maior que zero, ai guarda a foto
        if(filme.getFoto() != null){
            //transformar o array de bytes em bitmaps

            foto.setImageBitmap(Imagem.arrayToBitmap(filme.getFoto()));

        }

        //esse é o da classe
        this.filme = filme;


     }


}
