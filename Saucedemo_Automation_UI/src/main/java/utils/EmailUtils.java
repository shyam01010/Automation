package utils;

import constants.FrameworkConstants;
import dataObjects.EmailConfig;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

import java.io.IOException;

public class EmailUtils {
    private EmailUtils() {

    }

    public static void sendEmail() throws EmailException, IOException {
        EmailConfig emailConfig = JacksonUtils.deserializeJson("emailConfigData.json", EmailConfig.class);
        MultiPartEmail email = new MultiPartEmail();
        EmailAttachment attachment = new EmailAttachment();
        attachment.setPath(FrameworkConstants.getExtentReportPath());
        attachment.setDisposition(EmailAttachment.ATTACHMENT);
        email.setHostName(emailConfig.getMailHost());
        email.setSmtpPort(Integer.parseInt(emailConfig.getSmtpPort()));
        String hostEmailId = emailConfig.getHostEmailID();
        String hostPassword = emailConfig.getPassword();
        email.setAuthenticator(new DefaultAuthenticator(hostEmailId, hostPassword));
        email.setStartTLSEnabled(true);
        email.setSocketTimeout(60000);
        email.setFrom(hostEmailId);
        email.setSubject(emailConfig.getEmailSubject());
        email.setMsg(emailConfig.getEmailMessage());
        for (String userEmail : emailConfig.getEmailUsers()) {
            email.addTo(userEmail);
        }

        email.attach(attachment).addTo(hostEmailId).send();
    }
}
