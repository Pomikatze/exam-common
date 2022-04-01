package storage.rabbitMQ.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import personservice.dto.DeliveryRequestDto;
import personservice.model.DeliveryRequestEntity;
import personservice.service.DeliveryRequestService;

@EnableRabbit
@Component
public class RabbitMqListener {

    Logger logger = LoggerFactory.getLogger(RabbitMqListener.class);

    @RabbitListener(queues = "person-service")
    public void processStorageQueue(DeliveryRequestEntity message) {
        logger.info("New DeliveryRequest insert into DB");
        logger.info("{}", message);
    }

}
