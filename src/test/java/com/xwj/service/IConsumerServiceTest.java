package com.xwj.service;

import javax.annotation.Resource;
import javax.jms.Destination;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/applicationContext.xml", "classpath:spring/spring-*.xml" })
public class IConsumerServiceTest {

	@Resource
	private IConsumerService consumerService;

	@Resource(name = "queueDestination")
	private Destination destination;

	@Test
	public void testReceive() {
		System.out.println("-----" + Thread.currentThread().getName() + "---------");
		consumerService.receive(destination);
	}

}
