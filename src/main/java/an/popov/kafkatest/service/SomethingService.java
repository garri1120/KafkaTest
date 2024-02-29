package an.popov.kafkatest.service;

import an.popov.PersonDtoForKafka.dto.PersonDto;

public interface SomethingService {
    void save(PersonDto personDto);

}
