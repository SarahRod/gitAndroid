package br.senai.sp.agendacontatos.modelo;

import java.io.Serializable;

public class Contato implements Serializable{
/***ela eh uma interface que serve como "tag", idicando que os
 * objetos dessa classes podem ser transformados em um pedaco de codigo
 * binario, e pode ser relida em outro lugar, restaurando o objeto original**/

    private int id;
    private String nome;
    private String endereco;
    private String telefone;
    private String email;
    private String linkedin;
    private byte[] foto;








    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }


    @Override
    public String toString() {


        return "\n" + this.nome + "\n" + this.telefone + "\n" ;
    }
}
