package kr.ac.hansung.maldives.web.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ShippingAddress implements Serializable {

	private static final long serialVersionUID = 3107142982545634326L;

	@Id
	@GeneratedValue
	private Integer id;

	private String address;

	private String country;

	private String zipCode;

}
