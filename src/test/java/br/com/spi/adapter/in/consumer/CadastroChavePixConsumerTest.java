package br.com.spi.adapter.in.consumer;

import br.com.spi.exception.ChavePixParseException;
import br.com.spi.port.in.ChavePixRegistrationInputPort;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.support.Acknowledgment;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CadastroChavePixConsumerTest {

    @Mock
    private ChavePixRegistrationInputPort inputPort;
    @InjectMocks
    private CadastroChavePixConsumer consumer;

    @Test
    @DisplayName("deveVerificarSeMensagemDoTopicoKafkaFoiConsumida")
    void listen() {

        Acknowledgment ack = Mockito.mock(Acknowledgment.class);
        String mensagemKafka = "{\"transaction_id\":\"dd09838c-8a32-4a4c-8d4e-e3d0078719bc\",\"codigo_banco\":\"341\",\"numero_conta\":\"3213214\",\"agencia_conta\":\"4040\",\"cpf_cnpj\":\"33344455567\",\"nome\":\"cliente\",\"tipo_chave\":\"EMAIL\",\"valor_chave\":\"cliente@teste.com\"}";
        ConsumerRecord<String, String> payload = new ConsumerRecord<>("topic",1,1L,"key",mensagemKafka);

        doNothing().when(inputPort).registerChavePix(any());
        doNothing().when(ack).acknowledge();

        consumer.listen(payload,ack);

        verify(inputPort,times(1)).registerChavePix(any());
        verify(ack,times(1)).acknowledge();
    }

    @Test
    @DisplayName("deveLancarExceptionDeParseamentoIncorreto")
    void listenThrowsException() {

        Acknowledgment ack = Mockito.mock(Acknowledgment.class);
        String mensagemKafka = "{\"id\":\"chaveCampoIdIncorreta\",\"codigo_banco\":\"341\",\"numero_conta\":\"3213214\",\"agencia_conta\":\"4040\",\"cpf_cnpj\":\"33344455567\",\"nome\":\"cliente\",\"tipo_chave\":\"EMAIL\",\"valor_chave\":\"cliente@teste.com\"}";
        ConsumerRecord<String, String> payload = new ConsumerRecord<>("topic",1,1L,"key",mensagemKafka);

        ChavePixParseException ex = assertThrows(ChavePixParseException.class,
                () -> consumer.listen(payload,ack));

        verify(ack,times(1)).acknowledge();
        assertEquals("Erro ao converter mensagem recebida", ex.getMessage());
        assertTrue(ex.getCause() instanceof JsonProcessingException);
    }
}