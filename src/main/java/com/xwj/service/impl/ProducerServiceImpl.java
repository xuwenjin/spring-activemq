package com.xwj.service.impl;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.xwj.service.IProducerService;

/**
 * �����߽ӿ�ʵ����
 * 
 * @author xuwenjin
 */
@Service
public class ProducerServiceImpl implements IProducerService {

	@Resource(name = "jmsTemplate")
	private JmsTemplate jmsTemplate;

	public void sendMessage(Destination destination, final String msg) {
		System.out.println(Thread.currentThread().getName() + " �����" + destination.toString()
				+ "������Ϣ---------------------->" + msg);
		jmsTemplate.send(destination, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(msg);
			}
		});
	}

	public void sendMessage(final String msg) {
		String destination = jmsTemplate.getDefaultDestinationName();
		System.out.println(Thread.currentThread().getName() + " �����" + destination + "������Ϣ----------->" + msg);
		jmsTemplate.send(new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(msg);
			}
		});
	}

}
