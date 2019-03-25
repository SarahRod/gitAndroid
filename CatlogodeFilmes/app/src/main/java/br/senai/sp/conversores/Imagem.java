package br.senai.sp.conversores;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Imagem {
    /*não precisa instanciar*/
    public static Bitmap arrayToBitmap(byte[]imagemArray){


        Bitmap imagemBitmap = BitmapFactory.decodeByteArray(imagemArray,0, imagemArray.length);//esses int pode ser qualquer
        return imagemBitmap;
    }

}
