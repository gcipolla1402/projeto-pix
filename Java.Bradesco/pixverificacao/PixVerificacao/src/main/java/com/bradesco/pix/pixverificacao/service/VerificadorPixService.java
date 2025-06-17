package com.bradesco.pix.pixverificacao.service;

import com.bradesco.pix.pixverificacao.model.ScoreConfianca;
import com.bradesco.pix.pixverificacao.model.Usuario;
import com.bradesco.pix.pixverificacao.model.VendedorVerificado;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import com.bradesco.pix.pixverificacao.model.Denuncia;


@Service
public class VerificadorPixService {

    private List<Usuario> usuarios = new ArrayList<>();
    private List<ScoreConfianca> scores = new ArrayList<>();
    private List<VendedorVerificado> verificados = new ArrayList<>();
    private List<Denuncia> denuncias = new ArrayList<>();
    private int idDenuncia = 1;

    public VerificadorPixService() {
        usuarios.add(new Usuario(1, "João", "joao123"));
        usuarios.add(new Usuario(2, "Maria", "maria_pix"));
        usuarios.add(new Usuario(3, "Golpista", "suspeito"));

        scores.add(new ScoreConfianca(1, 80));
        scores.add(new ScoreConfianca(2, 45));
        scores.add(new ScoreConfianca(3, 20));

        verificados.add(new VendedorVerificado(1));
    }

    public String verificar(String chavePix) {
        Usuario usuario = usuarios.stream()
                .filter(u -> u.getChavePix().equalsIgnoreCase(chavePix))
                .findFirst()
                .orElse(null);

        if (usuario == null) return "❌ CHAVE PIX NÃO ENCONTRADA.";

        if (verificados.stream().anyMatch(v -> v.getIdUsuario() == usuario.getId())) {
            return "✅ VENDEDOR VERIFICADO. TRANSAÇÃO SEGURA!";
        }

        int score = scores.stream()
                .filter(s -> s.getIdUsuario() == usuario.getId())
                .map(ScoreConfianca::getScore)
                .findFirst()
                .orElse(50);

        if (score < 30) return "⚠️ RISCO ALTO: Score muito baixo. Pode ser golpe!";
        else if (score < 60) return "ℹ️ Score médio. Pouco Histórico.";
        else return "✅ Confiável com base no score.";
    }

    public String denunciar(String chavePix) {
        Usuario usuario = usuarios.stream()
                .filter(u -> u.getChavePix().equalsIgnoreCase(chavePix))
                .findFirst()
                .orElse(null);

        if (usuario == null) {
            return "❌ Chave Pix não encontrada.";
        }

        for (ScoreConfianca s : scores) {
            if (s.getIdUsuario() == usuario.getId()) {
                s.setScore(Math.max(s.getScore() - 10, 0));
                denuncias.add(new Denuncia(idDenuncia++, chavePix, LocalDateTime.now()));
                return "⚠️ Denúncia registrada. Score reduzido.";
            }
        }

        // Se o usuário não tiver score ainda
        scores.add(new ScoreConfianca(usuario.getId(), 20));
        denuncias.add(new Denuncia(idDenuncia++, chavePix, LocalDateTime.now()));
        return "⚠️ Denúncia registrada. Score inicial definido como 20.";
    }

    public List<Denuncia> getDenuncias() {
        return denuncias;
    }
}


