package an.popov.kafkatest.consumer;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import an.popov.PersonDtoForKafka.dto.PersonDto;

import an.popov.kafkatest.KafkaTestApplication;

import an.popov.kafkatest.container.KafkaContainerTest;
import an.popov.kafkatest.service.SomethingService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.junit.jupiter.Testcontainers;

//@SpringBootTest(classes = {KafkaConsumer.class, SomethingServiceImpl.class})
//@ContextConfiguration(classes = {KafkaContainerTest.class, KafkaProducerTestConfig1.class})
@Slf4j
@SpringBootTest(classes = {KafkaTestApplication.class})
@ContextConfiguration(classes = {KafkaContainerTest.class})
@Testcontainers(disabledWithoutDocker = true)
@TestPropertySource(locations = "classpath:application.yml")
class KafkaConsumerTest {

  @Value("${spring.kafka.consumer.topics.approve-credit-topic}")
  private String topicName;

  @Autowired
  private KafkaTemplate<String, PersonDto> kafkaTemplate;

  @MockBean
  private SomethingService somethingService;

  @SpyBean
  private KafkaConsumer kafkaConsumer;
  @Captor
  private ArgumentCaptor<ConsumerRecord<String, PersonDto>> captor;


  @Test
  void getPersonDtoFromKafkaTest(){
    PersonDto personDtoInKafka = PersonDto.builder().firstname("Walter").lastname("White").age(57).build();
    kafkaTemplate.send(topicName, personDtoInKafka);
    kafkaTemplate.flush();

    doNothing().when(somethingService).save(any());

    verify(kafkaConsumer, timeout(5000)).getPersonDtoFromKafka(
        captor.capture());

    PersonDto personDtoFromKafka = captor.getValue().value();
    Assertions.assertAll(() -> {
      Assertions.assertNotNull(personDtoFromKafka);
      Assertions.assertEquals(personDtoInKafka.getFirstname(), personDtoFromKafka.getFirstname());
      log.info("++++++++++++++++++++++ PersonDto = {}", personDtoFromKafka);
    });

    verify(somethingService, times(1)).save(personDtoFromKafka);


  }
}


