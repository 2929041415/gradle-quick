package com.combanc.service.sendmail;

public interface ISendEmailHelper {

    void sendEmail(String recieveEmailUser, String subject, String content, String htmlContent);
}
