package kr.ac.hansung.maldives.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.hansung.maldives.web.dao.CartItemRepository;
import kr.ac.hansung.maldives.web.model.Cart;
import kr.ac.hansung.maldives.web.model.CartItem;

@Service
public class CartItemService {
	@Autowired
	private CartItemRepository cartItemRepository;
	
	public void addCartItem(CartItem cartItem){
		cartItemRepository.saveAndFlush(cartItem);
	}
	
	public void removeCartItem(CartItem cartItem){
		cartItemRepository.delete(cartItem);
	}
	
	public void removeAllCartItems(Cart cart){
		cartItemRepository.deleteAll();
	}
	
	public CartItem getCartItemByCartIdAndProductId(Long cartId, Long productId){
		return cartItemRepository.findByCartIdAndProductId(cartId, productId);
	}
}
