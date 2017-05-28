package kr.ac.hansung.maldives.web.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.ibatis.binding.BindingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.hansung.maldives.model.DaumStoreItem;
import kr.ac.hansung.maldives.web.dao.StoreCommentRepository;
import kr.ac.hansung.maldives.web.dao.StoreRepository;
import kr.ac.hansung.maldives.web.dao.UserRepository;
import kr.ac.hansung.maldives.web.dao2.StoreMapper;
import kr.ac.hansung.maldives.web.dao2.UbcfMapper;
import kr.ac.hansung.maldives.web.model.CustomUserDetails;
import kr.ac.hansung.maldives.web.model.Store;
import kr.ac.hansung.maldives.web.model.StoreComment;
import kr.ac.hansung.maldives.web.model.User;

@Transactional
@Service
public class StoreService {

	@Autowired
	private StoreRepository storeRepository;
	
	@Autowired
	private StoreCommentRepository storeCommentRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private StoreMapper storeMapper;
	
	@Autowired
	private UbcfMapper ubcfMapper;

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private AccountService accountService;

	public List<Store> getStores() {
		return storeRepository.findAll();
	}

	public List<Store> findByCategoryCategoryCodeStartingWithAndBound(String categoryCode, double startX, double endX, double startY, double endY) {
		List<Store> stores = storeRepository.findByCategoryCategoryCodeStartingWithAndBound(categoryCode, startX, endX, startY, endY);
		
		for(Store store: stores){
			try {
				store.setAvgEvaluation(getStoreAvgEvaluationByStoreIdx(store.getStoreIdx()));
			} catch(BindingException e){
				/* 평가 정보가 없을때 발생 */
			}
		}
		
		return stores;
	}
	
	public Store getStoreByDsiId(String dsiId){
		Store store = storeRepository.findByDsiId(dsiId);
		
		if(store != null){
			store.setAvgEvaluation(getStoreAvgEvaluationByStoreIdx(store.getStoreIdx()));
		}
		
		return store;
	}

	public Store getStoreById(Long store_idx) {
		Store store = storeRepository.getOne(store_idx);
		
		if(store != null){
			store.setAvgEvaluation(getStoreAvgEvaluationByStoreIdx(store.getStoreIdx()));
		}
		
		return store;
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
		store.setCategory(categoryService.getCategory(dsi.getCategory()));
		
		storeRepository.save(store);
		
		return store;
	}
	
	public Double getStoreAvgEvaluationByStoreIdx(long storeIdx){
		return storeMapper.getStoreAvgEvaluationByStoreIdx(storeIdx);
	}

	public List<Store> getAssociationStoreByStoreIdx(long storeIdx){
		int colNum[] = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		
		return ubcfMapper.getAssociationStoreByStoreIdx(storeIdx, colNum);
	}
	
	public boolean addComment(long storeIdx, long userIdx, String comment) {
		Store store = storeRepository.getOne(storeIdx);
		User user = userRepository.getOne(userIdx);
		
		if(store == null || user == null){
			return false;
		}
		
		StoreComment storeComment = StoreComment.builder()
												.store(store)
												.comment(comment)
												.timestamp(LocalDateTime.now())
												.deleted(false)
												.user(user)
												.build();
		
		storeCommentRepository.save(storeComment);
		
		return true;
	}
	
	public List<StoreComment> getCommentList(long storeIdx){
		List<StoreComment> storeComments = storeCommentRepository.findByStoreStoreIdx(storeIdx);
		
		return storeComments;
	}

	public StoreComment getComment(Long storeIdx) {
		return storeCommentRepository.findOne(storeIdx);
	}
	
	public void deleteComment(Long storeIdx){
		storeCommentRepository.delete(storeIdx);
	}

}
