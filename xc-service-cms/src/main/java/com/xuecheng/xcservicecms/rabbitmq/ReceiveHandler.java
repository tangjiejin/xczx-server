package com.xuecheng.xcservicecms.rabbitmq;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @description: ${description}
 * @author: Mr.Tang
 * @create: 2019-04-11 16:23
 **/
@Component
public class ReceiveHandler {

    private Logger logger = LoggerFactory.getLogger(ReceiveHandler.class);

    @RabbitListener(queues = {RabbitConfig.QUEUE_INFORM_EMAIL})
    public void receiveEmail(String msg, Message message, Channel channel){

        logger.info("Email收到消息：" + msg);

    }

    @RabbitListener(queues = {RabbitConfig.QUEUE_INFORM_SMS})
    public void receiveSms(String msg, Message message, Channel channel){

        logger.info("Sms收到消息：" + msg);

    }

}
