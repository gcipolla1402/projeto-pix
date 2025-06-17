package com.bradesco.pix.pixverificacao.service;

import com.bradesco.pix.pixverificacao.model.Transacao;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class DetectorDeFraudeService {

    private List<Transacao> transacoes = new ArrayList<>();

    public String registrarTransacao(Transacao t) {
        transacoes.add(t);

        long recentes = transacoes.stream()
                .filter(tx -> Duration.between(tx.getDataHora(), t.getDataHora()).toMinutes() < 1)
                .count();

        if (recentes >= 5) {
            return " SUSPEITA: Muitas transações em menos de 1 minuto!";
        }

        long repeticoes = transacoes.stream()
                .filter(tx -> tx.getChaveDestinatario().equalsIgnoreCase(t.getChaveDestinatario()) &&
                        tx.getValor() == t.getValor())
                .count();

        if (repeticoes > 3) {
            return " SUSPEITA: Transações repetidas para o mesmo destinatário!";
        }

        return "Transação registrada sem suspeita.";
    }

    public List<Transacao> getTransacoes() {
        return transacoes;
    }
}

