package an.popov.kafkatest.api;

import an.popov.PersonDtoForKafka.dto.PersonDto;
import an.popov.kafkatest.service.ServiceProducerSendMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class Controller {

  private final ServiceProducerSendMessage sendMessage;


  @PostMapping("/kafka-producer")
  public void sendMessage(@RequestBody PersonDto personDto){
    log.info("В контролер поступил запрос на отправку сообщения в кафку. PersonDto = {}", personDto);
    sendMessage.sendMessageInKafka(personDto);
    log.info("Отправка сообщения успешно выполнена в кафку. Controller");
  }
}
