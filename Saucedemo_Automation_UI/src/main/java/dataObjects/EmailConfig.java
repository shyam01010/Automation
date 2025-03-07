package dataObjects;

import java.util.ArrayList;
import java.util.List;

import static listeners.TestListener.suiteName;

public class EmailConfig {
    private String mailHost;
    private String hostEmailID;
    private String password;
    private String smtpPort;
    private String emailSubject;
    private String emailMessage;
    private List<String> emailUsers = new ArrayList<>();

    public String getSmtpPort() {
        return smtpPort;
    }

    public void setSmtpPort(String smtpPort) {
        this.smtpPort = smtpPort;
    }

    public String getEmailSubject() {
        return "****"+suiteName + emailSubject;
    }

    public void setEmailSubject(String emailSubject) {
        this.emailSubject = emailSubject;
    }

    public String getEmailMessage() {
        return emailMessage;
    }

    public void setEmailMessage(String emailMessage) {
        this.emailMessage = emailMessage;
    }

    public String getMailHost() {
        return mailHost;
    }

    public void setMailHost(String mailHost) {
        this.mailHost = mailHost;
    }

    public String getHostEmailID() {
        return hostEmailID;
    }

    public void setHostEmailID(String emailID) {
        this.hostEmailID = emailID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getEmailUsers() {
        return emailUsers;
    }

    public void setEmailUsers(List<String> emailUsers) {
        this.emailUsers = emailUsers;
    }
}