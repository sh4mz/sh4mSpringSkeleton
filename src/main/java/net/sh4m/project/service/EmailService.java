package net.sh4m.project.service;

import java.util.Map;

public interface EmailService {

	Map<String, String> sendEmailByDoNotReplyEmail(String toEmail, String toCCEmail, String subjectEmail, String data);

}
