package br.com.spi.adapter.in.rest.controller;

import br.com.spi.infrastructure.dto.chave.ChavePixExistsResponse;
import br.com.spi.port.in.ChavePixInput;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chave-pix")
@RequiredArgsConstructor
public class ChavePixController{
    private final ChavePixInput service;

    @GetMapping("/{valorChave}")
    public ResponseEntity<ChavePixExistsResponse> chavePixExists(@PathVariable String valorChave){
        var response = service.chavePixExistsWithBody(valorChave);
        return ResponseEntity.ok(response);
    }
}
