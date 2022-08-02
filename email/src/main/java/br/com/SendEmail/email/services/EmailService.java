package br.com.SendEmail.email.services;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import br.com.SendEmail.email.enums.StatusEmail;
import br.com.SendEmail.email.models.EmailModel;
import br.com.SendEmail.email.repositories.EmailRepository;


@Service
public class EmailService {

	@Autowired
	EmailRepository emailRepository;

	@Autowired
	private JavaMailSender emailSender;

	// envia email e grava no banco
	public EmailModel sendEmail(EmailModel emailModel) {
		// set a data de envio
		emailModel.setSendDateEmail(LocalDateTime.now());
		try {
			// cria instância o JavaMailSender
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom(emailModel.getEmailFrom());
			message.setTo(emailModel.getEmailTo());
			message.setSubject(emailModel.getSubject());
			message.setText(emailModel.getText());
			emailSender.send(message);
			// Status enum
			emailModel.setStatusEmail(StatusEmail.SENT);
		} catch (MailException e) {
			emailModel.setStatusEmail(StatusEmail.ERRO);
		}
		return emailRepository.save(emailModel);
	}

	public Page<EmailModel> findAll(Pageable pageable) {
		return emailRepository.findAll(pageable);
	}

	public Optional<EmailModel> findById(UUID emailId) {
		return emailRepository.findById(emailId);
	}
}
