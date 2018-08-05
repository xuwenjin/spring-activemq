package com.xwj.service.impl;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.xwj.service.IConsumerService;

/**
 * �����߽ӿ�ʵ����(���������ֶ��ģ�ʹ���Զ������Ϣ�����������Զ���)
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
			System.out.println("�Ӷ���" + destination.toString() + "�յ�����Ϣ��\t" + textMessage.getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
		return textMessage;
	}

}
