package kr.ac.hansung.maldives.web.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "product")
public class Product implements Serializable {

	private static final long serialVersionUID = 821312233310048495L;

	@Transient
	private MultipartFile productImage;
	private String imageFileName;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "product_id")
	private Long id;

	@NotEmpty(message = "The product name must not be null")
	private String name;
	private String category;

	@Min(value = 0, message = "The product price must not be less than zero")
	private Integer price;
	@NotEmpty(message = "The product manufacturer must not be null")
	private String manufacturer;
	@Min(value = 0, message = "The product unitInStock must not be less than zero")
	private Integer unitInStock;
	private String description;


}
