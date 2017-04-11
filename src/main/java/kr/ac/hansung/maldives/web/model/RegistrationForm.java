package kr.ac.hansung.maldives.web.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import kr.ac.hansung.maldives.web.validation.PasswordsNotEmpty;
import kr.ac.hansung.maldives.web.validation.PasswordsNotEqual;
import lombok.Data;

@PasswordsNotEmpty(triggerFieldName = "signInProvider", passwordFieldName = "password", passwordVerificationFieldName = "passwordVerification")
@PasswordsNotEqual(passwordFieldName = "password", passwordVerificationFieldName = "passwordVerification")
@Data
public class RegistrationForm {
	@Email
	@NotEmpty
	@Size(max = 100)
	private String email;

	@NotEmpty
	@Size(max = 20)
	private String name;
	
	@NotEmpty
	@Size(max = 20)
	private String nickname;

	private String password;

	private String passwordVerification;
	
	@NotNull
	private Integer age;
	
	@NotNull
	private Sex sex;
	
	@NotNull
	private Integer job_idx;

	private SocialMediaType signInProvider;

	// Constructor is omitted for the of clarity.

	public boolean isNormalRegistration() {
		return signInProvider == null;
	}

	public boolean isSocialSignIn() {
		return signInProvider != null;
	}

	// other methods are omitted for the sake of clarity.
}
