package com.bradesco.pix.pixverificacao.controller;

import com.bradesco.pix.pixverificacao.service.VerificadorPixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/pix")
public class PixController {

    @Autowired
    private VerificadorPixService verificadorPixService;

    @GetMapping("/verificar/{chavePix}")
    public String verificarPix(@PathVariable String chavePix) {
        return verificadorPixService.verificar(chavePix);
    }

    @PostMapping("/denunciar")
    public String denunciarChave(@RequestBody Map<String, String> body) {
        String chave = body.get("chavePix");
        return verificadorPixService.denunciar(chave);
    }

}





