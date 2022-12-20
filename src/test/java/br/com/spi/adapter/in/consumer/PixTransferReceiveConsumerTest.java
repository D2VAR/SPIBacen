package br.com.spi.adapter.in.consumer;

import br.com.spi.exception.TransacaoPixParseException;
import br.com.spi.port.in.PixTransferReceive;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.support.Acknowledgment;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PixTransferReceiveConsumerTest{

    @Mock
    private PixTransferReceive inputPort;
    @Mock
    Acknowledgment ack;
    @InjectMocks
    private PixTransferReceiveConsumer consumer;

    @Test
    @DisplayName("deveVerificarSeMensagemDoTopicoKafkaFoiConsumida")
    void listen() {
        String mensagemKafka = "{\"transaction_id\":\"dd09838c-8a32-4a4c-8d4e-e3d0078719bc\",\"nome\":\"cliente\",\"cpf_cnpj\":\"33344455567\",\"tipo_chave\":\"EMAIL\",\"chave_destino\":\"cliente@teste.com\",\"valor_transferencia\":\"100.50\",\"codigo_banco_origem\":\"341\"}";
        ConsumerRecord<String, String> payload = new ConsumerRecord<>("topic",1,1L,"key",mensagemKafka);

        doNothing().when(inputPort).validatePix(any());
        doNothing().when(ack).acknowledge();

        consumer.listen(payload,ack);

        verify(inputPort,times(1)).validatePix(any());
        verify(ack,times(1)).acknowledge();
    }

    @Test
    @DisplayName("deveLancarExceptionDeParseamentoIncorreto")
    void listenThrowsException() {

        String mensagemKafka = "{\"id\":\"chaveCampoIdIncorreta\",\"nome\":\"cliente\",\"cpf_cnpj\":\"33344455567\",\"tipo_chave\":\"EMAIL\",\"chave_destino\":\"cliente@teste.com\",\"valor_transferencia\":\"100.50\",\"codigo_banco_origem\":\"341\"}";
        ConsumerRecord<String, String> payload = new ConsumerRecord<>("topic",1,1L,"key",mensagemKafka);

        TransacaoPixParseException ex = assertThrows(TransacaoPixParseException.class,
                () -> consumer.listen(payload,ack));

        verify(ack,times(1)).acknowledge();
        assertEquals("Erro ao converter mensagem recebida", ex.getMessage());
        assertTrue(ex.getCause() instanceof JsonProcessingException);
    }
}