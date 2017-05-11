package kr.ac.hansung.maldives.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.ac.hansung.maldives.web.dao.JobRepository;
import kr.ac.hansung.maldives.web.dao.UserRepository;
import kr.ac.hansung.maldives.web.model.Job;
import kr.ac.hansung.maldives.web.model.RegistrationForm;
import kr.ac.hansung.maldives.web.model.User;
import kr.ac.hansung.maldives.web.model.User.UserBuilder;
import kr.ac.hansung.maldives.web.model.UserRole;

@Service
public class RepositoryUserService implements UserService {

	private PasswordEncoder passwordEncoder;

	private UserRepository repository;
	
	@Autowired
	private JobRepository jobRepository;

	@Autowired
	public RepositoryUserService(PasswordEncoder passwordEncoder, UserRepository repository) {
		this.passwordEncoder = passwordEncoder;
		this.repository = repository;
	}

	@Transactional
	@Override
	public User registerNewUserAccount(RegistrationForm userAccountData) throws DuplicateEmailException {
		if (emailExist(userAccountData.getEmail())) {
			throw new DuplicateEmailException(
					"The email address: " + userAccountData.getEmail() + " is already in use.");
		}

		String encodedPassword = encodePassword(userAccountData);

		UserBuilder user = User.builder()
				.email(userAccountData.getEmail())
				.password(encodedPassword)
				.name(userAccountData.getName())
				.nickname(userAccountData.getNickname())
				.role(UserRole.ROLE_USER)
				.age(userAccountData.getAge())
				.sex(userAccountData.getSex())
				.jobIdx(userAccountData.getJobIdx())
				.point(0)
				.deleted(false);

		if (userAccountData.isSocialSignIn()) {
			user.signInProvider(userAccountData.getSignInProvider());
		}

		User registered = user.build();

		return repository.save(registered);
	}

	private boolean emailExist(String email) {
		User user = repository.findByEmail(email);

		if (user != null) {
			return true;
		}

		return false;
	}

	private String encodePassword(RegistrationForm dto) {
		String encodedPassword = null;

		if (dto.isNormalRegistration()) {
			encodedPassword = passwordEncoder.encode(dto.getPassword());
		}

		return encodedPassword;
	}

	@Override
	public List<Job> getJobList() {
		return jobRepository.findAll();
	}
}