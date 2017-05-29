package kr.ac.hansung.maldives.web.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.hansung.maldives.web.dao2.IbcfMapper;
import kr.ac.hansung.maldives.web.dao2.UbcfMapper;
import kr.ac.hansung.maldives.web.model.Evaluation;
import kr.ac.hansung.maldives.web.model.Store;

@Transactional
@Service
public class RecommendService {

	@Autowired
	private StoreService storeService;

	@Autowired
	private EvaluationService evaluationService;

	@Autowired
	private IbcfMapper ibcfMapper;
	
	@Autowired
	private UbcfMapper ubcfMapper;

	public List<Store> getRecommendIBStore(long user_idx) {

		List<Store> mine = new ArrayList<Store>();
		List<Evaluation> e = evaluationService.findByUser_idxAndNotEmptyEvaluation(user_idx);
		for (int i = 0; i < e.size(); i++) {
			Store store = e.get(i).getPosition().getStore();
			if (!mine.contains(store)) {
				mine.add(store);
			}
		}
		e.clear();
		List<Store> ls = new ArrayList<Store>();
		int colNum[] = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		
		ls = ibcfMapper.selectStoresByUserIdx(user_idx, colNum, 2.0f);
		if (ls.size() != 0) {
			for (int j = 0; j < ls.size();) {
				if (mine.contains(ls.get(j))) {
					ls.remove(ls.get(j));
				} else {
					j++;
				}
			}

		}
		return ls;
	}

	

	public List<Store> getRecommendUBStore(long user_idx) {
		int colNum[] = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		List<Store> ls = ubcfMapper.getRecommendStoreByUserIdx(user_idx, colNum);
		return ls;
	}

}
