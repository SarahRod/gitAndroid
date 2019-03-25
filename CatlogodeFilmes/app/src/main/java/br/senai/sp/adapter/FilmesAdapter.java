package br.senai.sp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import br.senai.sp.catlogodefilmes.R;
import br.senai.sp.conversores.Imagem;
import br.senai.sp.modelo.Filme;

public class FilmesAdapter extends BaseAdapter {

    //Como não criamos um construtor ele usa  a base dele
    private List<Filme> filmes;
    private Context context;

    public FilmesAdapter(Context context, List<Filme> filmes){
        this.filmes = filmes;
        this.context = context;
    }
    //retorna o tamanho da list view
    //Ela vai ver qual o tamanho da textview
    @Override
    public int getCount() {
        return filmes.size();
    }

    @Override
    public Object getItem(int position) {
        //Verificar a posição   pegar na lista para exibir
        return filmes.get(position);
    }

    @Override
    public long getItemId(int position) {

        //
        return filmes.get(position).getId();
    }

    //VAi desenhar o objeto(Lista)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //TextView textView = new TextView(context);
        //Recebe mas na posição que estou agora
        Filme filme = filmes.get(position);

        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.layout_lista_filmes,null);


        TextView txtDiretor = view.findViewById(R.id.txt_diretor);
        txtDiretor.setText(filme.getDiretor());

        TextView txtTitulo = view.findViewById(R.id.txt_titulo);
        txtTitulo.setText(filme.getTitulo());


        ImageView foto = view.findViewById(R.id.image_filme);


        if(filme.getFoto() !=null){
            foto.setImageBitmap(Imagem.arrayToBitmap(filme.getFoto()));
        }



        /*RatingBar nota = view.findViewById(R.id.rate_nota);
        nota.setProgress(filme.getNota());*/

        /*RatingBar nota = view.findViewById(R.id.rate_nota);
        nota.setProgress(filme.getNota());*/




        //list view passando a lita
        //textView.setText(filme.getTitulo());

        return view;
    }
    //Importar a classe BaseAdapter(Abstrata-nao pose instanciar/Serve para criar outras classes usando os recursos dela)



}
