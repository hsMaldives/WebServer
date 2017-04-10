package kr.ac.hansung.maldives.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name="user_accounts")

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
@Builder
public class User extends BaseEntity<Long>{
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
 
    @Column(name = "email", length = 100, nullable = false, unique = true)
    private String email;
 
    @Column(name = "first_name", length = 100,nullable = false)
    private String firstName;
 
    @Column(name = "last_name", length = 100, nullable = false)
    private String lastName;
 
    @Column(name = "password", length = 255)
    private String password;
 
    @Enumerated(EnumType.STRING)
    @Column(name = "role", length = 20, nullable = false)
    private UserRole role;
 
    @Enumerated(EnumType.STRING)
    @Column(name = "sign_in_provider", length = 20)
    private SocialMediaType signInProvider;

}
