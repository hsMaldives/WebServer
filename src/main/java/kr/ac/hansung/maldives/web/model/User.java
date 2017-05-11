package kr.ac.hansung.maldives.web.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name="users")

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
@Builder
public class User extends BaseEntity<Long>{
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long user_idx;
 
    @Column(name = "email", length = 150, nullable = false, unique = true)
    private String email;
    
    @Column(name = "password", length = 255)
    private String password;
 
    @Column(name = "name", length = 20, nullable = false)
    private String name;
 
    @Column(name = "nickname", length = 20, nullable = false)
    private String nickname;
 
    @Enumerated(EnumType.STRING)
    @Column(name = "role", length = 20, nullable = false)
    private UserRole role;
    
    @Column(name = "age", nullable = false)
    private Integer age;
    
    @Column(name = "sex", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Sex sex;
    
    @Column(name = "job_idx", nullable = false)
    private Integer job_idx;
    
    @Column(name = "point", nullable = false)
    private Integer point;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "sign_in_provider", length = 20)
    private SocialMediaType signInProvider;
    
    @Column(name = "deleted", nullable = false)
    private Boolean deleted;
    
    @OneToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(unique = true)
	private ShippingAddress shippingAddress;

	@OneToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(unique = true)
	private Cart cart;

	@Override
	public Long getId() {
		return user_idx;
	}

}
