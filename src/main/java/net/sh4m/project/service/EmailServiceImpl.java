package net.sh4m.project.service;

import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.SendFailedException;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import net.sh4m.project.ErrorMessageConstant;

@Service
public class EmailServiceImpl implements EmailService {
	private static final Logger logger = Logger.getLogger(EmailServiceImpl.class);
	
	@Value("#{propVal['PROJECT.EMAILSMTP.HOSTNAME']}")
	private String projectEmailSMTPHostname;
	
	@Value("#{propVal['PROJECT.EMAILSMTP.PORT']}")
    private int projectEmailSMTPPort;
	
	@Override
	public Map<String, String> sendEmailByDoNotReplyEmail(String toEmail, String toCCEmail, String subjectEmail,
			String data) {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
     
		//mailSender.setHost("SMTP.PROJECT.COM");
        mailSender.setHost(projectEmailSMTPHostname);
        mailSender.setPort(projectEmailSMTPPort);
        
		
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setTo(toEmail);
			helper.setFrom("donotreply.tornado@schawk.com");
			helper.setSubject(subjectEmail);

			message.setContent(data, "text/html");
			mailSender.send(message);
			logger.info("Successful send email to " + toEmail + " subject : " + subjectEmail);
			//logger.info(data);
			return null;
		} catch(MailSendException | SendFailedException e){
			Map<String, String> err = new HashMap<String, String>();
			logger.error(e);
			e.printStackTrace();
			err.put(ErrorMessageConstant.SEND_EMAIL_KEY, ErrorMessageConstant.EMAIL_ADDRESS_NOT_FOUND_VAL);
			return err;
		}	catch (MessagingException e) {
			Map<String, String> err = new HashMap<String, String>();
			logger.error(e);
			e.printStackTrace();
			err.put(ErrorMessageConstant.SEND_EMAIL_KEY, ErrorMessageConstant.SEND_EMAIL_FAILED_VAL);
			return err;
		}
		
		
		
	}

}
