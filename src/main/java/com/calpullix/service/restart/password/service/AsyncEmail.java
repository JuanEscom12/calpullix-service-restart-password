package com.calpullix.service.restart.password.service;

import javax.mail.MessagingException;

public interface AsyncEmail {

	void sendEmail(final String password, final String name, final String email) throws MessagingException;

}
