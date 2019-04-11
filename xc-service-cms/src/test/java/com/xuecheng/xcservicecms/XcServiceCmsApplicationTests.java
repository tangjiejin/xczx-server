package com.xuecheng.xcservicecms;

import com.xuecheng.xcservicecms.rabbitmq.RabbitConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class XcServiceCmsApplicationTests {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    public void rabbitmqProducer() {
        for (int i = 0; i<10; i++){
            String message = "消息" + (i+1);
            rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_TOPICS_INFORM,"inform.sms.email",message);

            System.out.println(message);
        }

    }

}
