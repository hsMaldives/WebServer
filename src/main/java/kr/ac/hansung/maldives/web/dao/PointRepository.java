package kr.ac.hansung.maldives.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import kr.ac.hansung.maldives.web.model.PointLog;

public interface PointRepository extends JpaRepository<PointLog, Long>{
	
	@Query("SELECT p FROM PointLog p WHERE p.user.user_idx = :user_idx AND (p.timestamp between timestamp(DATE_SUB(NOW(),INTERVAL 15 MINUTE)) AND timestamp(NOW()))")
	public List<PointLog> findByUser_idxAndTime(@Param("user_idx")Long user_idx);
	
}
