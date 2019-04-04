package br.senai.sp.agendacontatos.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.senai.sp.agendacontatos.R;
import br.senai.sp.agendacontatos.conversores.Imagem;
import br.senai.sp.agendacontatos.modelo.Contato;

public class ContatoAdapter extends BaseAdapter {

    private List<Contato> contatos;
    private Context context;

    public ContatoAdapter (Context context, List<Contato> contatos){
        this.contatos = contatos;
        this.context = context;
    }

    @Override

    /*O próprio método já diz o que ele faz:
     conta quantos itens existem na lista.
     Ou seja, o tamanho da lista.*/
    public int getCount() { return contatos.size(); }

    /*Agora vamos para o getItem(int position). Veja que ele quer saber
     um item a partir de uma posição.
     Isso é fácil! Basta apenas retornamos por meio
      do método get() mandando a posição:*/
    @Override
    public Object getItem(int position) {
        return contatos.get(position);
    }


    /*Vejamos o próximo: getItemId(int position). Esse método espera
     saber qual é o id do
     objeto que está sendo buscado. Porém, se verificarmos a nossa classe que
     representa um curso:*/
    @Override
    public long getItemId(int position) {
        return contatos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Contato contato = contatos.get(position);

        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.layout_lista_contatos, null);

        TextView txtNome =  view.findViewById(R.id.txt_nome);
        txtNome.setText(contato.getNome());

        TextView txtTelefone =  view.findViewById(R.id.txt_telefone);
        txtTelefone.setText(contato.getTelefone());

        ImageView foto = view.findViewById(R.id.image_contato);


        if(contato.getFoto() !=null){
            foto.setImageBitmap(Imagem.arrayToBitmap(contato.getFoto()));
        }


       return view;

    }
}
