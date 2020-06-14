package com.calpullix.service.restart.password.service;

import com.calpullix.service.restart.password.model.RestarPasswordResponse;
import com.calpullix.service.restart.password.model.RestartPasswordRequest;

public interface RestartPasswordService {

	RestarPasswordResponse restartPassword(RestartPasswordRequest request);
	
	RestarPasswordResponse changePassword(RestartPasswordRequest request);
	
}
