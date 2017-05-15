package kr.ac.hansung.maldives.web.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.hansung.maldives.model.DaumStoreItem;
import kr.ac.hansung.maldives.web.dao.StoreRepository;
import kr.ac.hansung.maldives.web.model.Store;

@Transactional
@Service
public class StoreService {

	@Autowired
	private StoreRepository storeRepository;

	@Autowired
	private CategoryService categoryService;

	public List<Store> getStores() {
		return storeRepository.findAll();
	}

	public List<Store> getStoresByBound(double startX, double endX, double startY, double endY) {
		return storeRepository.findByBound(startX, endX, startY, endY);
	}
	
	public Store getStoreByDsiId(String dsiId){
		return storeRepository.findByDsiId(dsiId);
	}

	public Store getStoreById(Long store_idx) {
		return storeRepository.getOne(store_idx);
	}
	
	public Store addStore(Store store){		
		return storeRepository.saveAndFlush(store);
	}

	public Store addStoreByDaumStore(DaumStoreItem dsi) {
		Store store = new Store();
		
		store.setAddress(dsi.getAddress());
		store.setDsiId(dsi.getId());
		store.setLatitude(dsi.getLatitude());
		store.setLongitude(dsi.getLongitude());
		store.setName(dsi.getTitle());
		store.setImageUrl(dsi.getImageUrl());
		store.setPhone(dsi.getPhone());
		store.setDirection(dsi.getDirection());
		store.setCategory(categoryService.getCategory(dsi.getCategory()));
		
		storeRepository.save(store);
		
		return store;
	}

}
