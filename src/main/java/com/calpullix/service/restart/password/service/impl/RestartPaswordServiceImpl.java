package com.calpullix.service.restart.password.service.impl;

import java.util.Optional;

import javax.mail.MessagingException;

import org.apache.commons.text.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calpullix.service.restart.password.model.Employee;
import com.calpullix.service.restart.password.model.RestarPasswordResponse;
import com.calpullix.service.restart.password.model.RestartPasswordRequest;
import com.calpullix.service.restart.password.model.Users;
import com.calpullix.service.restart.password.repository.UserRepository;
import com.calpullix.service.restart.password.service.AsyncEmail;
import com.calpullix.service.restart.password.service.RestartPasswordService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RestartPaswordServiceImpl implements RestartPasswordService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AsyncEmail asyncEmail;
	
	@Override
	public RestarPasswordResponse restartPassword(RestartPasswordRequest request) {
		final RestarPasswordResponse result = new RestarPasswordResponse();
		final Employee employee = new Employee();
		employee.setId(request.getId());
		Optional<Users> user = userRepository.findByIdemployee(employee);
		if (user.isPresent()) {
			result.setIsValid(Boolean.TRUE);
			RandomStringGenerator pwdGenerator = new RandomStringGenerator.Builder().withinRange(33, 45).build();
			String password = pwdGenerator.generate(10);
			user.get().setPassword(password);
			userRepository.save(user.get());
			try {
				asyncEmail.sendEmail(password, user.get().getName(), user.get().getEmail());
			} catch (MessagingException e) {
				log.error("::: Error email restart password ", e);
			}
		} else {
			result.setIsValid(Boolean.FALSE);
		}
		return result;
	}

	@Override
	public RestarPasswordResponse changePassword(RestartPasswordRequest request) {
		log.info(":: Change user password {} ", request);
		final RestarPasswordResponse result = new RestarPasswordResponse();
		final Employee employee = new Employee();
		employee.setId(request.getId());
		Optional<Users> user = userRepository.findByIdemployee(employee);
		if (user.isPresent() && user.get().getPassword().equals(request.getOldPassword())) {
			result.setIsValid(Boolean.TRUE);
			user.get().setPassword(request.getNewPassword());
			userRepository.save(user.get());
		} else {
			result.setIsValid(Boolean.FALSE);
		}
		log.info(":: End up change user password {} ", result);
		return result;
	}
	

}
