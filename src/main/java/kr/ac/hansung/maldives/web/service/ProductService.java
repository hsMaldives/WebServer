package kr.ac.hansung.maldives.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.hansung.maldives.web.dao.ProductRepository;
import kr.ac.hansung.maldives.web.model.Product;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public Product getProductById(Long id) {
		return productRepository.getOne(id);
	}

	public List<Product> getProducts() {
		return productRepository.findAll();
	}

	public void addProduct(Product product) {
		productRepository.saveAndFlush(product);
	}

	public void deleteProduct(Product product) {
		productRepository.delete(product);
	}

	public void editProduct(Product product) {
		productRepository.saveAndFlush(product);
	}

}
