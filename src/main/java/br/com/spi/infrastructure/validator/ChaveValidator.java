package br.com.spi.infrastructure.validator;

import br.com.spi.adapter.exception.ChaveValidatorException;
import br.com.spi.domain.dto.ChavePixDTO;
import org.springframework.stereotype.Component;

@Component
public class ChaveValidator {

    public void validate(ChavePixDTO chavePixDTO) {
        isNull(chavePixDTO);
        isNull(chavePixDTO.getNumeroConta());
        isNull(chavePixDTO.getCpfCnpj());
        isNull(chavePixDTO.getNome());
        isNull(chavePixDTO.getValorChave());
//        isNull(chavePixDTO.getTipoChave());

        isValidNumber(chavePixDTO.getCodBanco());
        isValidNumber(chavePixDTO.getAgenciaConta());
    }

    private void isNull(Object obj){
        if(obj ==null){
            throw new ChaveValidatorException("Chave nula ou com campos nulos não pode ser cadastrada.");
        }
    }

    private void isValidNumber(String string){
        isNull(string);
        try{
            Integer.parseInt(string);
        }catch (Exception ex){
            throw new ChaveValidatorException("Chave Inválida, campo numérico não pode ser convertido para integer.");
        }
    }

    //TODO: Validar tipo_chave e valor chave
}
