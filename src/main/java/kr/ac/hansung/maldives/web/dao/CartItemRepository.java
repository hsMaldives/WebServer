package kr.ac.hansung.maldives.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import kr.ac.hansung.maldives.web.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

	@Query("SELECT c FROM CartItem c WHERE cartId=:cartId AND productId=:productId")
	public CartItem findByCartIdAndProductId(@Param(value = "cartId") long cartId,
			@Param(value = "productId") long productId);

}
