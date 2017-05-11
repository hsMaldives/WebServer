package kr.ac.hansung.maldives.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.hansung.maldives.web.model.Product;
import kr.ac.hansung.maldives.web.service.ProductService;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;

	@RequestMapping("/products")
	public String getProducts(Model model) {
		
		List<Product> products = productService.getProducts();
		
		model.addAttribute("products", products);
		return "products/tiles";
	}
	
	@RequestMapping("/viewProduct/{productId}")
	public String viewProduct(@PathVariable Long productId, Model model){
		Product product = productService.getProductById(productId);
		model.addAttribute("product", product);
		
		return "viewProduct/tiles";
		
	}
}
