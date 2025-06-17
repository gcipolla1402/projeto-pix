package com.bradesco.pix.pixverificacao.controller;

import com.bradesco.pix.pixverificacao.model.Transacao;
import com.bradesco.pix.pixverificacao.service.DetectorDeFraudeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/fraude")
public class FraudeController {

    @Autowired
    private DetectorDeFraudeService detector;

    @PostMapping("/registrar")
    public String registrarTransacao(@RequestBody Transacao transacao) {
        Transacao t = new Transacao(
                transacao.getId(),
                transacao.getChaveDestinatario(),
                transacao.getValor(),
                LocalDateTime.now()
        );
        return detector.registrarTransacao(t);
    }

    @GetMapping("/todas")
    public List<Transacao> listarTransacoes() {
        return detector.getTransacoes();
    }
}


