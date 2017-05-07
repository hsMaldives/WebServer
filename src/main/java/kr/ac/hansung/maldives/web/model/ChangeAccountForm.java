package kr.ac.hansung.maldives.web.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import kr.ac.hansung.maldives.web.validation.PasswordsNotEqual;
import lombok.Data;

@PasswordsNotEqual(passwordFieldName = "password", passwordVerificationFieldName = "passwordVerification")

@Data
public class ChangeAccountForm {

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
}
