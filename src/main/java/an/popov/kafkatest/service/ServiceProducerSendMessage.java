package an.popov.kafkatest.service;

import an.popov.PersonDtoForKafka.dto.PersonDto;

public interface ServiceProducerSendMessage {
    void sendMessageInKafka(PersonDto personDto);
}
