package com.example.FootbalLeague.mail.service;

import com.example.FootbalLeague.Model.Token;
import com.example.FootbalLeague.mail.domain.Mail;



/**
 * Method to send an E-Mail.
 */
public interface MailService {
    void sendEmail(Mail mail);
    void sendValidationMail(String email, Token token);

    /**
     * This Method sends an Email to reset the password.
     *
     * @param email email of User
     */
    void sendResetMail(String email);

}
