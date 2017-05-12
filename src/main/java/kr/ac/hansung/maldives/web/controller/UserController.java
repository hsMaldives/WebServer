package kr.ac.hansung.maldives.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.DuplicateConnectionException;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;

import kr.ac.hansung.maldives.web.model.ChangeAccountForm;
import kr.ac.hansung.maldives.web.model.CustomUserDetails;
import kr.ac.hansung.maldives.web.model.Job;
import kr.ac.hansung.maldives.web.model.RegistrationForm;
import kr.ac.hansung.maldives.web.model.SocialMediaType;
import kr.ac.hansung.maldives.web.model.User;
import kr.ac.hansung.maldives.web.service.AccountService;
import kr.ac.hansung.maldives.web.service.DuplicateEmailException;
import kr.ac.hansung.maldives.web.service.UserService;
import kr.ac.hansung.maldives.web.utility.FacebookUtil;
import kr.ac.hansung.maldives.web.utility.SecurityUtil;

/**
 * 사용자 관련 처리 Controller
 * 
 * @author MingyuBae
 *
 */
@Controller
@RequestMapping("/user")
@SessionAttributes("user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private AccountService accountService;

	@Autowired
	private ProviderSignInUtils providerSignInUtils;

	/**
	 * 로그인 페이지
	 * 
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		return "user/login";
	}

	/**
	 * 회원가입 form 페이지
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String showRegistrationForm(@RequestParam(required=false) RegistrationForm registration,
			WebRequest request, Model model) {
		Connection<?> connection = providerSignInUtils.getConnectionFromSession(request);
		List<Job> jobList = userService.getJobList();
		
		if(registration == null){
			registration = createRegistrationDTO(connection);
		}
		
		model.addAttribute("user", registration);
		model.addAttribute("jobList", jobList);

		return "user/registrationForm";
	}

	/**
	 * 회원가입 처리
	 * 
	 * @param userAccountData
	 *            사용자가 입력한 계정 form
	 * @param result
	 *            form 검증 결과
	 * @param request
	 * @return
	 * @throws DuplicateConnectionException
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerUserAccount(@Valid @ModelAttribute("user") RegistrationForm userAccountData,
			BindingResult result, WebRequest request, Model model) throws DuplicateConnectionException {
		if (result.hasErrors()) {
			return showRegistrationForm(userAccountData, request, model);
		}

		User registered = createUserAccount(userAccountData, result);

		if (registered == null) {
			return showRegistrationForm(userAccountData, request, model);
		}
		SecurityUtil.logInUser(registered);
		providerSignInUtils.doPostSignUp(registered.getEmail(), request);

		return "redirect:/";
	}
	
	@RequestMapping(value="/change-account", method=RequestMethod.GET)
	public String changeUserInfoForm(@RequestParam(required=false) ChangeAccountForm changeAccount, Model model){
		CustomUserDetails userDetails = (CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = accountService.findOne(userDetails.getUserIdx());
		List<Job> jobList = userService.getJobList();
		
		if(changeAccount == null){
			changeAccount = new ChangeAccountForm();
			changeAccount.setAge(user.getAge());
			changeAccount.setJobIdx(user.getJob().getJobIdx());
			changeAccount.setNickname(user.getNickname());
			changeAccount.setSex(user.getSex());
		}
		
		model.addAttribute("user", user);
		model.addAttribute("jobList", jobList);
		model.addAttribute("changeAccount", changeAccount);
		
		return "user/changeAccountForm";
	}
	
	@RequestMapping(value="/change-account", method=RequestMethod.POST)
	public String changeUserInfoPost(@Valid @ModelAttribute("changeAccount") ChangeAccountForm changeAccount, 
									BindingResult result, Model model){
		CustomUserDetails userDetails = (CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if (result.hasErrors()) {
			return changeUserInfoForm(changeAccount, model);
		}
		
		accountService.changeAccountInfo(changeAccount, userDetails);
		
		return "redirect:/user/change-account";
	}

	/**
	 * 사용자 계정 생성
	 * 
	 * @param userAccountData
	 *            사용자가 입력한 계정 form
	 * @param result
	 *            form 검증 결과
	 * @return 생성된 계정 데이터
	 */
	private User createUserAccount(RegistrationForm userAccountData, BindingResult result) {
		User registered = null;

		try {
			registered = userService.registerNewUserAccount(userAccountData);
		} catch (DuplicateEmailException ex) {
			addFieldError("user", "email", userAccountData.getEmail(), "NotExist.user.email", result);
		}

		return registered;
	}

	/**
	 * 검증 오류 추가
	 * 
	 * @param objectName
	 *            객체명
	 * @param fieldName
	 *            필드명
	 * @param fieldValue
	 *            필드내용
	 * @param errorCode
	 *            오류코드
	 * @param result
	 *            form 검증 결과
	 */
	private void addFieldError(String objectName, String fieldName, String fieldValue, String errorCode,
			BindingResult result) {
		FieldError error = new FieldError(objectName, fieldName, fieldValue, false, new String[] { errorCode },
				new Object[] {}, errorCode);

		result.addError(error);
	}

	/**
	 * 회원가입 방식에 따라 적합한 회원가입 form 생성
	 * 
	 * @param connection
	 *            소셜 연결 데이터
	 * @return 회원가입 폼
	 */
	private RegistrationForm createRegistrationDTO(Connection<?> connection) {
		RegistrationForm dto = new RegistrationForm();

		if (connection != null) {
			UserProfile socialMediaProfile = null;

			if (connection.getApi() instanceof Facebook) {
				/* 소셜 로그인이 facebook 방식일 경우 */
				Facebook facebook = (Facebook) connection.getApi();

				/* facebook API 버전 변경으로 기존 방식을 사용할 수 없어 우회처리 */
				socialMediaProfile = FacebookUtil.fetchUserProfile(facebook);
			} else {
				socialMediaProfile = connection.fetchUserProfile();
			}

			dto.setEmail(socialMediaProfile.getEmail());
			dto.setName(socialMediaProfile.getLastName() + socialMediaProfile.getFirstName());

			ConnectionKey providerKey = connection.getKey();
			dto.setSignInProvider(SocialMediaType.valueOf(providerKey.getProviderId().toUpperCase()));
		}

		return dto;
	}
}
