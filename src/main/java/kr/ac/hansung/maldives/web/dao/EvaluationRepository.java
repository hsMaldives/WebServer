package kr.ac.hansung.maldives.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import kr.ac.hansung.maldives.web.model.Evaluation;

public interface EvaluationRepository extends JpaRepository<Evaluation, Long>{
	
	@Query("SELECT e FROM Evaluation e WHERE e.position.user.user_idx = :user_idx")
	public List<Evaluation> findByUser_idxAndNotEmptyEvaluation(@Param("user_idx")Long user_idx);
	
}
