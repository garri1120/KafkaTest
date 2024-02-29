package an.popov.kafkatest.service.impl;

import an.popov.PersonDtoForKafka.dto.PersonDto;
import an.popov.kafkatest.service.SomethingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SomethingServiceImpl implements SomethingService {

  @Override
  public void save(PersonDto personDto) {
    log.info("++++ SomethingServiceImpl получил сообщение из кафки. Message = [{}]", personDto);
  }
}
