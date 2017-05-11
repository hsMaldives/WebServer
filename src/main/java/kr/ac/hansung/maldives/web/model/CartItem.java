package kr.ac.hansung.maldives.web.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
public class CartItem implements Serializable {

	private static final long serialVersionUID = -213127954507414254L;

	@Id
	@Autowired
	private Long cartItemId;

	@ManyToOne
	@JoinColumn(name = "cartId")
	@JsonIgnore // serialization될 때 루프를 끊을 수 있음.
	private Cart cart;

	@ManyToOne
	@JoinColumn(name = "productId")
	@JsonIgnore
	private Product product;

	private Integer quantity; // 수량

	private Integer totalPrice;
}
