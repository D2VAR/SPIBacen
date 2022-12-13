package br.com.spi.adapter.in.controller;

import br.com.spi.domain.dto.ChavePixResponse;
import br.com.spi.port.in.VerificacaoChavePixInputPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bacen/")
public class VerificacaoChavePixController {

    private final VerificacaoChavePixInputPort inputPort;

    @GetMapping("/verifica-chave/{valorChave}")
    public ResponseEntity<ChavePixResponse> getChavePixByValor(@PathVariable String valorChave){
        log.info("Verificacao de Chave Pix - request: {}", valorChave);
        ChavePixResponse response = inputPort.findChavePixByValor(valorChave);
        return ResponseEntity.ok(response);
    }
}