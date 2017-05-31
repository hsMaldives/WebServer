package kr.ac.hansung.maldives.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.ac.hansung.maldives.web.model.Delivery;
import kr.ac.hansung.maldives.web.model.Product;
import kr.ac.hansung.maldives.web.model.User;
import kr.ac.hansung.maldives.web.service.AccountService;
import kr.ac.hansung.maldives.web.service.PointService;
import kr.ac.hansung.maldives.web.service.ProductService;

@Controller
@RequestMapping("/point")
public class PointController {

	@Autowired
	private AccountService accountService;

	@Autowired
	private ProductService productService;

	@Autowired
	private PointService pointService;

	@RequestMapping("/pointShop")
	public String poingShop(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		User user = accountService.findByEmail(username);
		int point = user.getPoint();

		List<Product> products = productService.getProducts();
		int spendedPoint = pointService.spendedPoint(user.getUserIdx());

		model.addAttribute("products", products);
		model.addAttribute("point", point);
		model.addAttribute("spendedPoint", spendedPoint);

		return "point/pointShop";
	}

	@RequestMapping("/viewProduct/{productId}")
	public String viewProduct(@PathVariable Long productId, @RequestParam(required = false) Integer error,
			Model model) {
		Product product = productService.getProductById(productId);
		model.addAttribute("product", product);

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		User user = accountService.findByEmail(username);
		int point = user.getPoint();
		int spendedPoint = pointService.spendedPoint(user.getUserIdx());

		if (error != null) {
			model.addAttribute("errorMsg", "포인트를 확인해 주세요");
		}

		model.addAttribute("point", point);
		model.addAttribute("spendedPoint", spendedPoint);

		return "point/viewProduct";

	}

	@RequestMapping(value="/buyProduct/{productId}", method=RequestMethod.GET)
	public String buyProduct(@PathVariable Long productId, Model model) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		User user = accountService.findByEmail(username);

		Product product = productService.getProductById(productId);

		if (user.getPoint() < product.getPrice()) {
			
			return "redirect:/point/viewProduct/" + productId + "?error=1";
		}

		Delivery delivery = new Delivery();
		delivery.setAddress(user.getShippingAddress().getAddress());
		delivery.setCountry(user.getShippingAddress().getCountry());
		// delivery.setPhone(phone);
		delivery.setUsername(user.getName());
		delivery.setZipcode(user.getShippingAddress().getZipCode());

		model.addAttribute("product", product);
		model.addAttribute("user", user);
		model.addAttribute("delivery", delivery);

		return "point/buyProduct";
	}
	
	@RequestMapping(value="/buyProduct/{productId}", method=RequestMethod.POST)
	public String buyComplete(Delivery delivery,@PathVariable Long productId, Model model){
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		User user = accountService.findByEmail(username);
		
		Product product = productService.getProductById(productId);
				
		pointService.usePoint(user.getId(), product);		
		int spendedPoint = pointService.spendedPoint(user.getUserIdx());
		
		model.addAttribute("product",product);
		model.addAttribute("user", user);
		model.addAttribute("spendedPoint", spendedPoint);
		
		return "point/buyComplete";
	}

}
