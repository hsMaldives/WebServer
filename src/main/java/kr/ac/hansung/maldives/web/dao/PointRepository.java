package kr.ac.hansung.maldives.web.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.ac.hansung.maldives.web.model.PointLog;

public interface PointRepository extends JpaRepository<PointLog, Long>{
	
	//수정요망
	public List<PointLog> findByUserUserIdxAndTimestampBetween(Long userIdx, LocalDateTime beginTime, LocalDateTime endTime);
	
}
