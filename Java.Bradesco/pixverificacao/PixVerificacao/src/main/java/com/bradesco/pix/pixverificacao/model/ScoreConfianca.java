package com.bradesco.pix.pixverificacao.model;

public class ScoreConfianca {
    private int idUsuario;
    private int score;

    public ScoreConfianca(int idUsuario, int score) {
        this.idUsuario = idUsuario;
        this.score = score;
    }

    public int getIdUsuario() {
        return idUsuario;
    }
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

}
