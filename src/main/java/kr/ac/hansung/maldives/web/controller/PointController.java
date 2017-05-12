package kr.ac.hansung.maldives.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.hansung.maldives.web.model.Product;
import kr.ac.hansung.maldives.web.model.User;
import kr.ac.hansung.maldives.web.service.AccountService;
import kr.ac.hansung.maldives.web.service.ProductService;

@Controller
@RequestMapping("/point")
public class PointController {

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping("/pointShop")
	public String poingShop(Model model){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		User user = accountService.findByEmail(username);
		int point = user.getPoint();
		
		
		
		List<Product> products = productService.getProducts();
		
		
		model.addAttribute("products", products);
		model.addAttribute("point", point);
		
		return "point/pointShop";
	}
	
	@RequestMapping("/viewProduct/{productId}")
	public String viewProduct(@PathVariable Long productId, Model model){
		Product product = productService.getProductById(productId);
		model.addAttribute("product", product);
		
		return "point/viewProduct";
		
	}
	

	
	@RequestMapping("/cart")
	public String getCart(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName();
		
		User user = accountService.findByEmail(email);
		long cartId = user.getCart().getCartId();
		
		model.addAttribute("cartId", cartId);
		
		return "point/cart";
	}
	
}
