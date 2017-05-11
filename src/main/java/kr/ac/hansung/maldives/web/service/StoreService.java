package kr.ac.hansung.maldives.web.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.hansung.maldives.web.dao.StoreRepository;
import kr.ac.hansung.maldives.web.model.Store;

@Transactional
@Service
public class StoreService {

	@Autowired
	private StoreRepository storeRepository;

	public List<Store> getStores() {
		return storeRepository.findAll();
	}

	public List<Store> getStoresByBound(double startX, double endX, double startY, double endY) {
		return storeRepository.findByBound(startX, endX, startY, endY);
	}

	public Store getStoreById(Long store_idx) {
		return storeRepository.getOne(store_idx);
	}
	
	public Store addStore(Store store){		
		return storeRepository.saveAndFlush(store);
	}

}
