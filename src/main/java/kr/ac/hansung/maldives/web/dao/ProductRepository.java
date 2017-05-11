package kr.ac.hansung.maldives.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.ac.hansung.maldives.web.model.Product;


public interface ProductRepository extends JpaRepository<Product, Long>{

}
