package br.com.spi.adapter.in.consumer;

import br.com.spi.exception.TransacaoValidadaParseException;
import br.com.spi.port.out.ValidacaoTransacaoInputPort;
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
import static org.mockito.Mockito.doNothing;

@ExtendWith(MockitoExtension.class)

class TransacaoPixValidadaConsumerTest {

    @Mock
    private ValidacaoTransacaoInputPort inputPort;
    @InjectMocks
    private TransacaoPixValidadaConsumer consumer;
    @Mock
    Acknowledgment ack;

    @Test
    @DisplayName("deveVerificarSeMensagemDeSucessoDoTopicoKafkaFoiConsumida")
    void listenSuccess() {
        String mensagemKafka = "{\"transaction_id\":\"dd09838c-8a32-4a4c-8d4e-e3d0078719bc\",\"pix_realizado\":\"TRUE\",\"codigo_banco_origem\":\"341\",\"cpf_cnpj\":\"33344455567\",\"nome\":\"cliente\",\"tipo_chave\":\"EMAIL\",\"chave_destino\":\"cliente@teste.com\",\"valor_transferencia\":\"100.50\"}";
        ConsumerRecord<String, String> payload = new ConsumerRecord<>("topic",1,1L,"key",mensagemKafka);

        doNothing().when(inputPort).retornarValidacao(any());
        doNothing().when(ack).acknowledge();

        consumer.listenSuccess(payload,ack);

        verify(inputPort,times(1)).retornarValidacao(any());
        verify(ack,times(1)).acknowledge();
    }

    @Test
    @DisplayName("deveVerificarSeMensagemDeFalhaDoTopicoKafkaFoiConsumida")
    void listenFail() {
        String mensagemKafka = "{\"transaction_id\":\"dd09838c-8a32-4a4c-8d4e-e3d0078719bc\",\"pix_realizado\":\"FALSE\",\"codigo_banco\":\"341\",\"numero_conta\":\"3213214\",\"agencia_conta\":\"4040\",\"cpf_cnpj\":\"33344455567\",\"nome\":\"cliente\",\"tipo_chave\":\"EMAIL\",\"valor_chave\":\"cliente@teste.com\"}";
        ConsumerRecord<String, String> payload = new ConsumerRecord<>("topic",1,1L,"key",mensagemKafka);

        doNothing().when(inputPort).retornarValidacao(any());
        doNothing().when(ack).acknowledge();

        consumer.listenFail(payload,ack);

        verify(inputPort,times(1)).retornarValidacao(any());
        verify(ack,times(1)).acknowledge();
    }

    @Test
    @DisplayName("deveLancarExceptionDeParseamentoIncorreto")
    void listenThrowsException() {

        String mensagemKafka = "{\"id\":\"chaveCampoIdIncorreta\",\"pix_realizado\":\"FALSE\",\"codigo_banco\":\"341\",\"numero_conta\":\"3213214\",\"agencia_conta\":\"4040\",\"cpf_cnpj\":\"33344455567\",\"nome\":\"cliente\",\"tipo_chave\":\"EMAIL\",\"valor_chave\":\"cliente@teste.com\"}";

        ConsumerRecord<String, String> payload = new ConsumerRecord<>("topic",1,1L,"key",mensagemKafka);

        TransacaoValidadaParseException ex = assertThrows(TransacaoValidadaParseException.class,
                () -> consumer.listenSuccess(payload,ack));

        verify(ack,times(1)).acknowledge();
        assertEquals("Erro ao converter mensagem recebida", ex.getMessage());
        assertTrue(ex.getCause() instanceof JsonProcessingException);
    }

}