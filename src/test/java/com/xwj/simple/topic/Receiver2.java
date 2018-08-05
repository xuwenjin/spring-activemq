package com.xwj.simple.topic;

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
 * activemq-主题模式-消息消费者2
 * 
 * @author xuwenjin
 */
public class Receiver2 {
	
	private static final String URL = "tcp://localhost:61616";

	private static final String TOPIC_NAME = "xwj_topic";

	public static void main(String[] args) throws Exception {
		// 创建ConnectionFactory
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,
				ActiveMQConnection.DEFAULT_PASSWORD, URL);

		// 创建连接
		Connection connection = connectionFactory.createConnection();

		// 启动连接
		connection.start();

		// 创建会话(第一个参数是是否开发事物，如果开启，需要在发送消息后提交事物)
		Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);

		// 创建一个目标
		Destination destination = session.createTopic(TOPIC_NAME);

		// 创建一个消费者
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
