package kr.ac.hansung.maldives.web.utility;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import kr.ac.hansung.maldives.web.model.CustomUserDetails;
import kr.ac.hansung.maldives.web.model.User;

/**
 * 보안 관련 Utility
 * @author MingyuBae
 *
 */
public class SecurityUtil {
	
	/**
	 * 사용자 로그인 처리
	 * @param user 사용자 정보
	 */
	public static void logInUser(User user) {
		CustomUserDetails userDetails = CustomUserDetails.builder()
				.name(user.getName())
				.user_idx(user.getUser_idx())
				.password(user.getPassword())
				.role(user.getRole())
				.socialSignInProvider(user.getSignInProvider())
				.username(user.getEmail())
				.build();

		Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
}