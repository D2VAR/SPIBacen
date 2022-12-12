package br.com.bacen.port.in;

import br.com.bacen.domain.dto.ChavePixResponse;

public interface VerificacaoChavePixInputPort {

    ChavePixResponse findChavePixByValor(String valorChave);
}
