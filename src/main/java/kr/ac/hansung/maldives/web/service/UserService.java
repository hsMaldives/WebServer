package kr.ac.hansung.maldives.web.service;

import kr.ac.hansung.maldives.web.model.RegistrationForm;
import kr.ac.hansung.maldives.web.model.User;

public interface UserService {

	public User registerNewUserAccount(RegistrationForm userAccountData) throws DuplicateEmailException;
}