package kr.ac.hansung.maldives.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.ac.hansung.maldives.web.model.Evaluation;

public interface EvaluationRepository extends JpaRepository<Evaluation, Long>{
	
	public List<Evaluation> findByPositionUserUserIdx(Long userIdx);
	
}
