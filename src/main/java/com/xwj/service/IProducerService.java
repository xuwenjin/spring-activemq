package com.xwj.service;

import javax.jms.Destination;

/**
 * �����߽ӿ�
 * 
 * @author xuwenjin
 */
public interface IProducerService {
	
	void sendMessage(String message);
	
	void sendMessage(Destination destination, String message);
	
}
