package com.xwj.simple.queue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * activemq-����ģʽ-��Ϣ������
 * 
 * @author xuwenjin
 */
public class Sender {

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
		Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);

		// ����һ��Ŀ��
		Destination destination = session.createQueue(QUEUE_NAME);

		// ����һ��������
		MessageProducer producer = session.createProducer(destination);

		// �־û���ʽ(Ĭ�ϳ־û����˴����ò��־û�)
		producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

		// ������Ϣ
		sendMessage(session, producer);

		session.commit();

		connection.close();
	}

	public static void sendMessage(Session session, MessageProducer producer) throws Exception {
		for (int i = 1; i <= 10; i++) {
			TextMessage message = session.createTextMessage("xwj������Ϣ:" + i);
			// ������Ϣ��Ŀ�ĵط�
			System.out.println("������Ϣ��" + "ActiveMq ���͵���Ϣ" + i);
			producer.send(message);
		}
	}

}