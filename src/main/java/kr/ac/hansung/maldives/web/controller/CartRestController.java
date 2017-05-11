package kr.ac.hansung.maldives.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.hansung.maldives.web.model.Cart;
import kr.ac.hansung.maldives.web.model.CartItem;
import kr.ac.hansung.maldives.web.model.Product;
import kr.ac.hansung.maldives.web.model.User;
import kr.ac.hansung.maldives.web.service.AccountService;
import kr.ac.hansung.maldives.web.service.CartItemService;
import kr.ac.hansung.maldives.web.service.CartService;
import kr.ac.hansung.maldives.web.service.ProductService;

@RestController
@RequestMapping("/rest/cart")
public class CartRestController {

	@Autowired
	private CartService cartService;
	@Autowired
	private CartItemService cartItemService;
	@Autowired
	private AccountService accountService;
	@Autowired
	private ProductService productService;

	@RequestMapping(value = "/{cartId}", method = RequestMethod.GET)
	public ResponseEntity<Cart> getCartById(@PathVariable(value = "cartId") long cartId) {
		Cart cart = cartService.getCartById(cartId);
		return new ResponseEntity<Cart>(cart, HttpStatus.OK);
	}

	@RequestMapping(value = "/add/{productId}", method = RequestMethod.PUT)
	public ResponseEntity<Void> addItem(@PathVariable(value = "productId") long productId) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName(); // annotaion-driven에 bean
													// 추가해야함
													// (servlet-context.xml)

		User user = accountService.findByEmail(email);
		Product product = productService.getProductById(productId);
		Cart cart = user.getCart();

		List<CartItem> cartItems = cart.getCartItems();

		for (int i = 0; i < cartItems.size(); i++) {
			if (product.getId().equals(cartItems.get(i).getProduct().getId())) {
				CartItem cartItem = cartItems.get(i);
				cartItem.setQuantity(cartItem.getQuantity() + 1);
				//cartItem.setTotalPrice(cartItem.getTotalPrice() + product.getPrice());
				cartItem.setTotalPrice(cartItem.getQuantity() * product.getPrice());
				cartItemService.addCartItem(cartItem);

				return new ResponseEntity<Void>(HttpStatus.OK);
			}
		}

		CartItem cartItem = new CartItem();
		cartItem.setProduct(product);
		cartItem.setQuantity(1);
		cartItem.setTotalPrice(product.getPrice()*cartItem.getQuantity());
		cartItem.setCart(cart);

		cartItemService.addCartItem(cartItem);
		product.getCartItems().add(cartItem);
		cart.getCartItems().add(cartItem);

		return new ResponseEntity<Void>(HttpStatus.OK);

	}

	@RequestMapping(value = "/cartitem/{productId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> removeItem(@PathVariable(value = "productId") long productId) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName();
		User user = accountService.findByEmail(email);
		Cart cart = user.getCart();

		CartItem cartItem = cartItemService.getCartItemByCartIdAndProductId(cart.getCartId(), productId);
		cartItemService.removeCartItem(cartItem);

		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);

	}

	@RequestMapping(value = "/{cartId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> clearCart(@PathVariable(value = "cartId") long cartId) {
		Cart cart = cartService.getCartById(cartId);
		cartItemService.removeAllCartItems(cart);

		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
