package com.xwj.service.impl;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.xwj.service.IConsumerService;

/**
 * 消费者接口实现类(此做法是手动的，使用自定义的消息监听器才是自动的)
 * 
 * @author xuwenjin
 */
@Service
public class ConsumerServiceImpl implements IConsumerService {

	@Resource(name = "jmsTemplate")
	private JmsTemplate jmsTemplate;

	@Override
	public TextMessage receive(Destination destination) {
		TextMessage textMessage = (TextMessage) jmsTemplate.receive(destination);
		try {
			System.out.println("从队列" + destination.toString() + "收到了消息：\t" + textMessage.getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
		return textMessage;
	}

}
