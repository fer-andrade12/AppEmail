package br.com.SendEmail.email.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import br.com.SendEmail.email.dtos.EmailDto;
import br.com.SendEmail.email.models.EmailModel;
import br.com.SendEmail.email.services.EmailService;

@Component 
public class EmailConsumer {
	
	@Autowired
	EmailService emailService;
	
	// Anotação que vai comsumir a fila 
    @RabbitListener(queues= "${spring.rabbitmq.queue}")
	public void Listen(@Payload EmailDto emailDto) {
		EmailModel emailModel = new EmailModel();
		// Converte EmailDto em json
		BeanUtils.copyProperties(emailDto, emailModel);
		emailService.sendEmail(emailModel);
		System.out.println("Email Status: " + emailModel.getStatusEmail().toString());
	}
	
}
