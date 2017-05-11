package kr.ac.hansung.maldives.web.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(indexes={ @Index(name="idx_persistent_login_username", columnList="username", unique = false) })
public class PersistentLogins {

	@Column(length=150)
	private String username;
	
	@Id
	@Column(length=64)
	private String series;
	
	@Column(length=64)
	private String token;
	
	private LocalDateTime lastUsed;
}
