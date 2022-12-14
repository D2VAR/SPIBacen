package br.com.spi.adapter.in.rest.controller;

import br.com.spi.adapter.out.dynamo.entity.ChavePixDynamo;
import br.com.spi.port.out.DatabaseOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chave-pix")
@RequiredArgsConstructor
public class Controller {
    private final DatabaseOutputPort db;

    @GetMapping("/{valorChave}")
    public ResponseEntity<ChavePixDynamo> getChavePix(@PathVariable String valorChave){
        var chave = db.getChavePix(valorChave);
        return ResponseEntity.ok(chave.orElseThrow(()->new RuntimeException("Chave nao existente!")));
    }
}
