package com.xwj.service;

import javax.jms.Destination;

/**
 * 生产者接口
 * 
 * @author xuwenjin
 */
public interface IProducerService {
	
	void sendMessage(String message);
	
	void sendMessage(Destination destination, String message);
	
}
