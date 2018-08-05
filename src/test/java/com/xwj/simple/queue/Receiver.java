package com.xwj.simple.queue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * activemq-����ģʽ-��Ϣ������
 * 
 * @author xuwenjin
 */
public class Receiver {
	
	private static final String URL = "tcp://localhost:61616";

	private static final String QUEUE_NAME = "xwj_queue";

	public static void main(String[] args) throws Exception {
		// ����ConnectionFactory
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,
				ActiveMQConnection.DEFAULT_PASSWORD, URL);

		// ��������
		Connection connection = connectionFactory.createConnection();

		// ��������
		connection.start();

		// �����Ự(��һ���������Ƿ񿪷���������������Ҫ�ڷ�����Ϣ���ύ����)
		Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);

		// ����һ��Ŀ��
		Destination destination = session.createQueue(QUEUE_NAME);

		// ����һ��������
		MessageConsumer consumer = session.createConsumer(destination);
		
		consumer.setMessageListener(new MessageListener() {
			@Override
			public void onMessage(Message message) {
				TextMessage testMessage = (TextMessage)message;
				try {
					System.out.println(testMessage.getText());
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		});
	}

}
