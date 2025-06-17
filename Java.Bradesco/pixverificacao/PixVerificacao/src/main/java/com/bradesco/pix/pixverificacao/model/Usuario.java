package com.bradesco.pix.pixverificacao.model;

public class Usuario {
    private int id;
    private String nome;
    private String chavePix;

    public Usuario(int id, String nome, String chavePix){
        this.id = id;
        this.nome = nome;
        this.chavePix = chavePix;
    }
    public int getId() {
        return id;
    }
    public String getNome(){
        return nome;
    }
    public String getChavePix() {
        return chavePix;
    }
}
