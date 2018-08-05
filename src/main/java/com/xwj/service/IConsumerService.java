package com.xwj.service;

import javax.jms.Destination;
import javax.jms.TextMessage;

/**
 * 消费者接口
 * 
 * @author xuwenjin
 */
public interface IConsumerService {

	TextMessage receive(Destination destination);

}
