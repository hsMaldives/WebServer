package kr.ac.hansung.maldives.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import kr.ac.hansung.maldives.web.model.Position;

public interface PositionRepository extends JpaRepository<Position, Long>{

	@Query("SELECT p FROM Position p LEFT JOIN p.evaluation e WHERE p.user.user_idx = :user_idx AND e.position_idx IS NULL")
	public List<Position> findByUser_idxAndEmptyEvaluation(@Param("user_idx")Long user_idx);
	
}
