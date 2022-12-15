package br.com.spi.adapter.in.rest.controller;

import br.com.spi.infrastructure.dto.ChavePixRequest;
import br.com.spi.infrastructure.dto.ChavePixResponse;
import br.com.spi.port.in.CrudChavePixInputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chave-pix")
@RequiredArgsConstructor
public class Controller {
    private final CrudChavePixInputPort service;

    @GetMapping("/{valorChave}")
    public ResponseEntity<ChavePixResponse> getChavePix(@PathVariable String valorChave){
        var chave = service.getChavePix(valorChave);
        return ResponseEntity.ok(chave);
    }

    @PostMapping
    public ResponseEntity<Void> saveChavePix(@RequestBody ChavePixRequest requestChave){
        service.saveChavePix(requestChave);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<Void> updateChavePix(@RequestBody ChavePixRequest requestChave){
        service.updateChavePix(requestChave);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{valorChave}")
    public ResponseEntity<Void> deleteChavePix(@PathVariable String valorChave){
        service.deleteChavePix(valorChave);
        return ResponseEntity.noContent().build();
    }

}
