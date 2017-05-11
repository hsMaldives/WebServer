package kr.ac.hansung.maldives.web.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
@Builder
@Entity
@Table(name="users")
public class User extends BaseEntity<Long>{
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userIdx;
 
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
    private Integer jobIdx;
    
    @Column(name = "point", nullable = false)
    private Integer point;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "sign_in_provider", length = 20)
    private SocialMediaType signInProvider;
    
    @Column(name = "deleted", nullable = false)
    private Boolean deleted;
    
    @ManyToMany
    @JoinTable(name="favorites")
    private List<Category> favories;

	@Override
	public Long getId() {
		return userIdx;
	}

}
