package com.bradesco.pix.pixverificacao.model;

import java.time.LocalDateTime;

public class Denuncia {
    private int id;
    private String chavePix;
    private LocalDateTime data;

    public Denuncia(int id, String chavePix, LocalDateTime data) {
        this.id = id;
        this.chavePix = chavePix;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public String getChavePix() {
        return chavePix;
    }

    public LocalDateTime getData() {
        return data;
    }
}

