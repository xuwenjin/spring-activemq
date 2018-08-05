package com.xwj.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * �Զ�����Ϣ������
 * 
 * @author xuwenjin
 */
public class QueueMessageListener implements MessageListener {

	@Override
	public void onMessage(Message message) {
		TextMessage tm = (TextMessage) message;
		try {
			System.out.println("QueueMessageListener���������ı���Ϣ��\t" + tm.getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
