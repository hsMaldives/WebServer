package kr.ac.hansung.maldives.web.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.social.security.SocialUser;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CustomUserDetails extends SocialUser {

	private static final long serialVersionUID = -4950595906773152481L;

	private Long id;
	private String firstName;
	private String lastName;
	private UserRole role;
	private SocialMediaType socialSignInProvider;

	public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}
	
	public static Builder builder(){
		return new Builder();
	}

	public static class Builder {

		private Long id;
		private String username;
		private String firstName;
		private String lastName;
		private String password;

		private UserRole role;
		private SocialMediaType socialSignInProvider;
		private Set<GrantedAuthority> authorities;

		public Builder() {
			this.authorities = new HashSet<>();
		}

		public Builder firstName(String firstName) {
			this.firstName = firstName;
			return this;
		}

		public Builder id(Long id) {
			this.id = id;
			return this;
		}

		public Builder lastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		public Builder password(String password) {
			if (password == null) {
				password = "SocialUser";
			}

			this.password = password;
			return this;
		}

		public Builder role(UserRole role) {
			this.role = role;

			SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.toString());
			this.authorities.add(authority);

			return this;
		}

		public Builder socialSignInProvider(SocialMediaType socialSignInProvider) {
			this.socialSignInProvider = socialSignInProvider;
			return this;
		}

		public Builder username(String username) {
			this.username = username;
			return this;
		}

		public CustomUserDetails build() {
			CustomUserDetails user = new CustomUserDetails(username, password, authorities);

			user.id = id;
			user.firstName = firstName;
			user.lastName = lastName;
			user.role = role;
			user.socialSignInProvider = socialSignInProvider;

			return user;
		}
	}
}
