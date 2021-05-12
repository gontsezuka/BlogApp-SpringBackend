package com.zukalover.BlogApplication.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import com.zukalover.BlogApplication.exceptions.BlogApplicationException;
import com.zukalover.BlogApplication.model.NotificationEmail;

@Service
public class MailService {
	
	private final Logger LOGGER = LoggerFactory.getLogger(MailService.class);
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private MailContentBuilder mailContentBuilder;
	
	public void sendMail(NotificationEmail notificationEmail)
	{
		MimeMessagePreparator messagePreparator = mimeMessage -> {
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
			messageHelper.setFrom("ggmochoana@gmail.com");
			messageHelper.setTo(notificationEmail.getRecipient());
			messageHelper.setSubject(notificationEmail.getSubject());
			messageHelper.setText(mailContentBuilder.build(notificationEmail.getBody()));
		};
		
		try {
			javaMailSender.send(messagePreparator);
			LOGGER.info("Activation Email Sent");
		}catch(MailException e)
		{
			//Throw Runtime exception
			//24
			throw new BlogApplicationException("Error sending message");
		}
		
	}
}
 