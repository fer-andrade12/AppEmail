package br.com.SendEmail.email.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.SendEmail.email.models.EmailModel;

public interface EmailRepository extends JpaRepository<EmailModel, UUID>{
	
}
