package com.xwj.service;

import javax.annotation.Resource;
import javax.jms.Destination;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-*.xml" })
public class IProducerServiceTest {

	@Resource
	private IProducerService producerService;

	@Resource(name = "queueDestination")
	private Destination queueDestination;

	@Resource(name = "topicDestination")
	private Destination topicDestination;

	@Test
	public void testSendMessageString() {
		System.out.println("-----" + Thread.currentThread().getName() + "---------");
		String message = "Hello World!";
		producerService.sendMessage(message);
	}

	@Test
	public void testSendMessageDestinationString() {
		String message = "Hello World!";
		producerService.sendMessage(queueDestination, message);
	}

	@Test
	public void testSendTopicMessageDestinationString() {
		for (int i = 0; i < 10; i++) {
			producerService.sendMessage(topicDestination, "test---" + i);
		}
	}

}
