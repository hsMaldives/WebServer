package kr.ac.hansung.maldives.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.ac.hansung.maldives.web.model.Position;

public interface PositionRepository extends JpaRepository<Position, Long>{

	public List<Position> findByUserUserIdxAndPositionIdxIsNull(Long userIdx);
	
}
