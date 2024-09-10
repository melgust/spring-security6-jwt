package gt.edu.umg.demodb.service;

import java.util.List;

import jakarta.mail.Multipart;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import gt.edu.umg.demodb.utils.AppProperty;
import gt.edu.umg.demodb.utils.ObjParameter;

@Service
public class MailService {
	
	@Autowired
	private TemplateEngine templateEngine;
	
	@Autowired
	JavaMailSender javaMailSender;
	
	String from;
	
	public MailService(AppProperty properties) {
		from = properties.getFromMail();
	}
	
	public void sendEmail(String to, String subject, String template, List<ObjParameter> parameters) throws Exception {
		Context context = new Context();
		for (ObjParameter objParameter : parameters) {
			context.setVariable(objParameter.getField(), objParameter.getValue());
		}
		String[] mails = to.split(";");
		String body = templateEngine.process(template, context);
		MimeMessage message =  javaMailSender.createMimeMessage();
		MimeMessageHelper mail = new MimeMessageHelper(message);
		for (String m : mails) {
			mail.setTo(m.trim());
		}
		mail.setFrom(from);
		mail.setSubject(subject);
		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setText(body, "utf-8", "html");
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);
		message.setContent(multipart);
		javaMailSender.send(message);
	}

}
