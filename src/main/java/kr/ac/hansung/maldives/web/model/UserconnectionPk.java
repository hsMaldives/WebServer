package kr.ac.hansung.maldives.web.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class UserconnectionPk implements Serializable {

	private static final long serialVersionUID = 521682819730216057L;
	
	private String userId;
	private String providerId;
	private String providerUserId;
}
