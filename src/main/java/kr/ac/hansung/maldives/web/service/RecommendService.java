package kr.ac.hansung.maldives.web.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.hansung.maldives.web.dao2.IbcfMapper;
import kr.ac.hansung.maldives.web.dao2.UbcfMapper;
import kr.ac.hansung.maldives.web.model.Store;

@Transactional
@Service
public class RecommendService {

	@Autowired
	private IbcfMapper ibcfMapper;
	
	@Autowired
	private UbcfMapper ubcfMapper;

	public List<Store> getRecommendIBStore(long user_idx) {
		int colNum[] = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		
		List<Store> ls = ibcfMapper.selectStoresByUserIdx(user_idx, colNum, 2.0f);
		return ls;
	}

	public List<Store> getRecommendUBStore(long user_idx) {
		int colNum[] = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		List<Store> ls = ubcfMapper.getRecommendStoreByUserIdx(user_idx, colNum);
		return ls;
	}

}
