package an.popov.kafkatest.service.impl;

import an.popov.PersonDtoForKafka.dto.PersonDto;
import an.popov.kafkatest.producer.KafkaService;
import an.popov.kafkatest.service.ServiceProducerSendMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ServiceProducerSendMessageImpl implements ServiceProducerSendMessage {

    private final KafkaService kafkaService;
    @Override
    public void sendMessageInKafka(PersonDto personDto) {
        log.info("В service поступил запрос на отправку сообщения в кафку. PersonDto = {}", personDto);
        kafkaService.sendPerson(personDto);
        log.info("Отправка сообщения успешно выполнена в кафку. ServiceProducerSendMessageImpl");
    }
}
