package kr.ac.hansung.maldives.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;

import kr.ac.hansung.maldives.web.model.RegistrationForm;
import kr.ac.hansung.maldives.web.model.SocialMediaType;
import kr.ac.hansung.maldives.web.model.User;
import kr.ac.hansung.maldives.web.service.DuplicateEmailException;
import kr.ac.hansung.maldives.web.service.UserService;
import kr.ac.hansung.maldives.web.utility.FacebookUtil;
import kr.ac.hansung.maldives.web.utility.SecurityUtil;

@Controller
@RequestMapping("/user")
@SessionAttributes("user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProviderSignInUtils providerSignInUtils;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		return "user/login";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String showRegistrationForm(WebRequest request, Model model) {
		Connection<?> connection = providerSignInUtils.getConnectionFromSession(request);

		RegistrationForm registration = createRegistrationDTO(connection);
		model.addAttribute("user", registration);

		return "user/registrationForm";
	}

	@RequestMapping(value ="/register", method = RequestMethod.POST)
    public String registerUserAccount(@Valid @ModelAttribute("user") RegistrationForm userAccountData,
                                      BindingResult result,
                                      WebRequest request) throws DuplicateConnectionException {
        if (result.hasErrors()) {
            return "user/registrationForm";
        }
 
        User registered = createUserAccount(userAccountData, result);
 
        if (registered == null) {
            return "user/registrationForm";
        }
        SecurityUtil.logInUser(registered);
        providerSignInUtils.doPostSignUp(registered.getEmail(), request);
 
        return "redirect:/";
    }
 
    private User createUserAccount(RegistrationForm userAccountData, BindingResult result) {
        User registered = null;
 
        try {
            registered = userService.registerNewUserAccount(userAccountData);
        }
        catch (DuplicateEmailException ex) {
            addFieldError(
                    "user",
                    "email",
                    userAccountData.getEmail(),
                    "NotExist.user.email",
                    result);
        }
 
        return registered;
    }
 
    private void addFieldError(String objectName, String fieldName, String fieldValue,  String errorCode, BindingResult result) {
        FieldError error = new FieldError(
                objectName,
                fieldName,
                fieldValue,
                false,
                new String[]{errorCode},
                new Object[]{},
                errorCode
        );
 
        result.addError(error);
    }
	
	private RegistrationForm createRegistrationDTO(Connection<?> connection) {
		RegistrationForm dto = new RegistrationForm();

		if (connection != null) {
			UserProfile socialMediaProfile = null;
			
			if(connection.getApi() instanceof Facebook){
				Facebook facebook = (Facebook)connection.getApi();
				
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
