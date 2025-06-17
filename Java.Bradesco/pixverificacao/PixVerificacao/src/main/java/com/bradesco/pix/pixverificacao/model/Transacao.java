package com.bradesco.pix.pixverificacao.model;

import java.time.LocalDateTime;

public class Transacao {
    private int id;
    private String chaveDestinatario;
    private double valor;
    private LocalDateTime dataHora;

    public Transacao(int id, String chaveDestinatario, double valor, LocalDateTime dataHora) {
        this.id = id;
        this.chaveDestinatario = chaveDestinatario;
        this.valor = valor;
        this.dataHora = dataHora;
    }

    public int getId() { return id; }
    public String getChaveDestinatario() { return chaveDestinatario; }
    public double getValor() { return valor; }
    public LocalDateTime getDataHora() { return dataHora; }
}
