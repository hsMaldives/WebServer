package kr.ac.hansung.maldives.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.hansung.maldives.web.dao.EvaluationRepository;
import kr.ac.hansung.maldives.web.dao.PositionRepository;
import kr.ac.hansung.maldives.web.model.Evaluation;
import kr.ac.hansung.maldives.web.model.Position;

@Service
public class EvaluationService {
	
	@Autowired
	private EvaluationRepository evaluationRepositry;
	
	@Autowired
	private PositionRepository positionRepository;
	
	public List<Evaluation> findByUser_idxAndNotEmptyEvaluation(Long user_idx){
		return evaluationRepositry.findByPositionUserUserIdx(user_idx);
	}
	
	public List<Position> findByUser_idxAndEmptyEvaluation(Long user_idx){
		return positionRepository.findByUserUserIdxAndPositionIdxIsNull(user_idx);
	}
	
	public Position positionFindByOne(Long position_idx){
		return positionRepository.findOne(position_idx);
	}
	
	public void savePostEvaluation(Evaluation evaluation){
		Position position = positionFindByOne(evaluation.getPositionIdx());
		
		evaluation.setPositionIdx(null);
		evaluation.setPosition(position);
		evaluationRepositry.save(evaluation);
		
		position.setEvaBit(true);
		position.setEvaluation(evaluation);
		positionRepository.save(position);
	}
	
	public Evaluation saveEvaluation(Evaluation evaluation){
		return evaluationRepositry.save(evaluation);
	}
}
