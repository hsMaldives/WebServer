package kr.ac.hansung.maldives.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import kr.ac.hansung.maldives.web.model.Store;

public interface StoreRepository extends JpaRepository<Store, Long>{

	@Query("SELECT s FROM Store s WHERE :startX < s.latitude and s.latitude< :endX and :startY < s.longitude and s.longitude < :endY")
	public List<Store> findByBound(@Param("startX")Double startX, @Param("endX")Double endX, @Param("startY")Double startY, @Param("endY")Double endY);
	
	public Store findByDsiId(String dsiId);
	
}

