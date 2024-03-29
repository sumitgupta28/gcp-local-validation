package com.demo.gcp.gcppubsubdemo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.cloud.spring.pubsub.integration.inbound.PubSubInboundChannelAdapter;
import com.google.cloud.spring.pubsub.integration.outbound.PubSubMessageHandler;

@SpringBootApplication
public class GcpPubSubDemoApplication {

	private static final Logger LOGGER = LogManager.getLogger(GcpPubSubDemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(GcpPubSubDemoApplication.class, args);
	}

	@Value("${app.message.subscription.name}")
	private String subscriptionName;

	@Value("${app.message.subscription.name1}")
	private String subscriptionName1;

	@Value("${app.message.topic.name}")
	private String topicName;

	@Bean
	public PubSubInboundChannelAdapter messageChannelAdapter(@Qualifier("myInputChannel") MessageChannel inputChannel,
			PubSubTemplate pubSubTemplate) {
		LOGGER.info("subscriptionName - {} ", subscriptionName);
		PubSubInboundChannelAdapter adapter = new PubSubInboundChannelAdapter(pubSubTemplate, subscriptionName);
		adapter.setOutputChannel(inputChannel);
		return adapter;
	}

	@ServiceActivator(inputChannel = "myInputChannel")
	public void messageReceiver(String payload) {
		LOGGER.info("Message arrived! SubscriptionName: {} Payload: {}",subscriptionName, payload);
	}

	@Bean
	public PubSubInboundChannelAdapter messageChannelAdapter1(@Qualifier("myInputChannel") MessageChannel inputChannel,
			PubSubTemplate pubSubTemplate) {
		LOGGER.info("subscriptionName - {} ", subscriptionName1);
		PubSubInboundChannelAdapter adapter = new PubSubInboundChannelAdapter(pubSubTemplate, subscriptionName1);
		adapter.setOutputChannel(inputChannel);
		return adapter;
	}

	@ServiceActivator(inputChannel = "myInputChannel")
	public void messageReceiver1(String payload) {
		LOGGER.info("Message arrived! SubscriptionName: {} Payload: {}",subscriptionName1, payload);
	}

	@Bean
	public MessageChannel myInputChannel() {
		return new DirectChannel();
	}

	/**
	 * 
	 */

	@Bean
	@ServiceActivator(inputChannel = "demoOutputChannel")
	public MessageHandler messageSender(PubSubTemplate pubsubTemplate) {
		LOGGER.info("topicName - {} ", topicName);
		return new PubSubMessageHandler(pubsubTemplate, topicName);
	}

	@MessagingGateway(defaultRequestChannel = "demoOutputChannel")
	public interface PubsubOutboundGateway {
		void sendToPubsub(String text);
	}
}
