package com.calpullix.service.restart.password.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.calpullix.service.restart.password.model.Employee;
import com.calpullix.service.restart.password.model.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer>  {

	Optional<Users> findByIdemployee(Employee idEmployee);
	
}
