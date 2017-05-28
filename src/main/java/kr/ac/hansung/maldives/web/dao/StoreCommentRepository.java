package kr.ac.hansung.maldives.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.ac.hansung.maldives.web.model.StoreComment;

public interface StoreCommentRepository extends JpaRepository<StoreComment, Long>{
	
	public List<StoreComment> findByStoreStoreIdx(long storeIdx);
}
