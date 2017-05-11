package kr.ac.hansung.maldives.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.ac.hansung.maldives.web.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

}
