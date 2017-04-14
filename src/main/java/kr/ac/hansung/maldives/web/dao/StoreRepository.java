package kr.ac.hansung.maldives.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import kr.ac.hansung.maldives.web.model.Store;

public interface StoreRepository extends JpaRepository<Store, Integer>{

	@Query("SELECT s FROM Store s WHERE s.store_idx < :lim")
	public List<Store> findByStore_idx(@Param("lim") Integer lim);


}

