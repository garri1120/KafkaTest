package an.popov.kafkatest.producer;

import an.popov.PersonDtoForKafka.dto.PersonDto;

public interface KafkaService {
    void sendPerson(PersonDto personDto);
}
