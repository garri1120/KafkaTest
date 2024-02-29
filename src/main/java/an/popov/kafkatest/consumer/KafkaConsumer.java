package an.popov.kafkatest.consumer;

import an.popov.PersonDtoForKafka.dto.PersonDto;
import an.popov.kafkatest.service.SomethingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaConsumer {


  private final SomethingService somethingService;

  @KafkaListener(
      topics = "${spring.kafka.consumer.topics.approve-credit-topic}",
      groupId = "${spring.kafka.consumer.group-id}",
      autoStartup = "${spring.kafka.consumer.enabled}"
  )
  public void getPersonDtoFromKafkaCustomDeserializer(ConsumerRecord<String, PersonDto> consumerRecord) {
    PersonDto personDto = consumerRecord.value();
    log.info("++++++++++++++ KafkaConsumer. Успешно получено сообщение из кафки. Topic = [{}], message = {}", consumerRecord.topic(), personDto);
    somethingService.save(personDto);
    log.info("+++++++++++++ KafkaConsumer. Сообщение успешно отправлено и обработано в SomethingService. Message = {}", personDto);
  }
}
