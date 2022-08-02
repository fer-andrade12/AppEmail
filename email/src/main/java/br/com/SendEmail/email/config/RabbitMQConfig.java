package br.com.SendEmail.email.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
	
	@Value("${spring.rabbitmq.queue}")
	private String queue;
	
	@Bean
	public Queue queue() {
		return new Queue(queue, true);
	}
	
	// Suporte java converte atravéz do Payload do EmailConsumer
	@Bean
	public Jackson2JsonMessageConverter massageConverter() {
		return new Jackson2JsonMessageConverter();
	}
}
