package com.xwj.service;

import javax.jms.Destination;
import javax.jms.TextMessage;

/**
 * �����߽ӿ�
 * 
 * @author xuwenjin
 */
public interface IConsumerService {

	TextMessage receive(Destination destination);

}
