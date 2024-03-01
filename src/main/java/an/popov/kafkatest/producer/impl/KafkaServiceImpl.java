//package an.popov.kafkatest.producer;
//
//import an.popov.PersonDtoForKafka.dto.PersonDto;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Service;
//
//@Slf4j
//@Service
//@RequiredArgsConstructor
//public class KafkaServiceImpl implements KafkaService{
//
//  @Value("${spring.kafka.producer.topics.approve-credit-topic}")
//  private String topicName;
//
//  private final KafkaTemplate<String, PersonDto> kafkaTemplate;
//  @Override
//  public void sendPerson(PersonDto personDto) {
//      log.info("В KafkaServiceImpl поступил запрос на отправку сообщения в кафку. PersonDto = {}", personDto);
//      kafkaTemplate.send(topicName, personDto);
//      log.info("Отправка сообщения успешно выполнена в кафку. KafkaServiceImpl");
//  }
//}
