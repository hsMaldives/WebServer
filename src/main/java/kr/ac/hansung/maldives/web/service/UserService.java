package kr.ac.hansung.maldives.web.service;

import java.util.List;

import kr.ac.hansung.maldives.web.model.Job;
import kr.ac.hansung.maldives.web.model.RegistrationForm;
import kr.ac.hansung.maldives.web.model.User;

public interface UserService {

	public User registerNewUserAccount(RegistrationForm userAccountData) throws DuplicateEmailException;
	
	public List<Job> getJobList();
	
	
}