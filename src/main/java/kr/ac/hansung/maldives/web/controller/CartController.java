package kr.ac.hansung.maldives.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.hansung.maldives.web.model.User;
import kr.ac.hansung.maldives.web.service.AccountService;

@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private AccountService accountService;
	
	@RequestMapping
	public String getCart(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName();
		
		User user = accountService.findByEmail(email);
		long cartId = user.getCart().getCartId();
		
		model.addAttribute("cartId", cartId);
		
		return "cart/tiles";
	}
	
}
