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
 * activemq-队列模式-消息生产者
 * 
 * @author xuwenjin
 */
public class Sender {

	private static final String URL = "tcp://localhost:61616";

	private static final String QUEUE_NAME = "xwj_queue";

	public static void main(String[] args) throws Exception {
		// 创建ConnectionFactory
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,
				ActiveMQConnection.DEFAULT_PASSWORD, URL);

		// 创建连接
		Connection connection = connectionFactory.createConnection();

		// 启动连接
		connection.start();

		// 创建会话(第一个参数是是否开发事物，如果开启，需要在发送消息后提交事物)
		Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);

		// 创建一个目标
		Destination destination = session.createQueue(QUEUE_NAME);

		// 创建一个生产者
		MessageProducer producer = session.createProducer(destination);

		// 持久化方式(默认持久化，此处设置不持久化)
		producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

		// 发送消息
		sendMessage(session, producer);

		session.commit();

		connection.close();
	}

	public static void sendMessage(Session session, MessageProducer producer) throws Exception {
		for (int i = 1; i <= 10; i++) {
			TextMessage message = session.createTextMessage("xwj发送消息:" + i);
			// 发送消息到目的地方
			System.out.println("发送消息：" + "ActiveMq 发送的消息" + i);
			producer.send(message);
		}
	}

}