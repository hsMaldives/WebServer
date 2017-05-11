package kr.ac.hansung.maldives.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.hansung.maldives.web.dao.CartRepository;
import kr.ac.hansung.maldives.web.model.Cart;

@Service
public class CartService {

	@Autowired
	CartRepository cartRepository;
	
	public Cart getCartById(Long cartId){
		return cartRepository.getOne(cartId);
	}
}
