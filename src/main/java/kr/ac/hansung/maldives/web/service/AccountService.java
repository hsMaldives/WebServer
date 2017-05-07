package kr.ac.hansung.maldives.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.ac.hansung.maldives.web.dao.UserRepository;
import kr.ac.hansung.maldives.web.model.ChangeAccountForm;
import kr.ac.hansung.maldives.web.model.CustomUserDetails;
import kr.ac.hansung.maldives.web.model.User;

@Service
public class AccountService {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	public User findOne(Long user_idx){
		return userRepository.findOne(user_idx);
	}

	public void changeAccountInfo(ChangeAccountForm changeAccount, CustomUserDetails userDetails) {
		User user = findOne(userDetails.getUser_idx());
		
		if(changeAccount.getPassword() != null 
				&& ! changeAccount.getPassword().isEmpty()){
			user.setPassword(passwordEncoder.encode(changeAccount.getPassword()));
		}
		
		user.setNickname(changeAccount.getNickname());
		user.setAge(changeAccount.getAge());
		user.setSex(changeAccount.getSex());
		user.setJob_idx(changeAccount.getJob_idx());
		
		userRepository.save(user);
		
		//TODO 사용자 로그인 세션 갱신 필요
	}
}
