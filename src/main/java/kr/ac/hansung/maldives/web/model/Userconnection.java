package kr.ac.hansung.maldives.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import lombok.Data;

@Data
@IdClass(UserconnectionPk.class)
@Entity
public class Userconnection {

	@Id
	@Column(name="userid", length=255)
	private String userId;
	
	@Id
	@Column(name="providerid",length=255)
	private String providerId;
	
	@Id
	@Column(name="provideruserid",length=255)
	private String providerUserId;
	
	private Integer rank;
	
	@Column(name="displayname",length=255)
	private String displayName;
	
	@Column(name="profileurl",length=512)
	private String profileUrl;
	
	@Column(name="imageurl",length=512)
	private String imageUrl;
	
	@Column(name="accesstoken",length=255)
	private String accessToken;
	
	@Column(length=255)
	private String secret;
	
	@Column(name="refreshtoken",length=255)
	private String refreshToken;
	
	@Column(name="expiretime")
	private Long expireTime;
}
