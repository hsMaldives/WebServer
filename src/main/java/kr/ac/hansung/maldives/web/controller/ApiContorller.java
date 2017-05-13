package kr.ac.hansung.maldives.web.controller;

import java.lang.reflect.InvocationTargetException;
import java.security.Principal;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.hansung.maldives.model.DaumStoreItem;
import kr.ac.hansung.maldives.model.StoreAndRating;
import kr.ac.hansung.maldives.web.model.CustomUserDetails;
import kr.ac.hansung.maldives.web.model.Evaluation;
import kr.ac.hansung.maldives.web.model.Location;
import kr.ac.hansung.maldives.web.model.PointLog;
import kr.ac.hansung.maldives.web.model.PointLog.PointType;
import kr.ac.hansung.maldives.web.model.Position;
import kr.ac.hansung.maldives.web.model.Store;
import kr.ac.hansung.maldives.web.model.User;
import kr.ac.hansung.maldives.web.service.AccountService;
import kr.ac.hansung.maldives.web.service.EvaluationService;
import kr.ac.hansung.maldives.web.service.PointService;
import kr.ac.hansung.maldives.web.service.PositionService;
import kr.ac.hansung.maldives.web.service.StoreService;
import lombok.extern.slf4j.Slf4j;

@Slf4j

@RestController
@RequestMapping("/api/rating")
public class ApiContorller {

	@Autowired
	private StoreService storeService;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private PointService pointService;
	
	@Autowired
	private PositionService positionService;
	
	@Autowired
	private EvaluationService evaluationService;

	@RequestMapping(value = "/storeAndRatingInfo", method = RequestMethod.POST)
	public PointLog ratingInfo(@RequestBody StoreAndRating storeAndRating, Principal principal) {
		
		CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		User user = accountService.findOne(userDetails.getUserIdx());
		DaumStoreItem dsi = storeAndRating.getStoreInfo();
		Store store = storeService.getStoreByDsiId(dsi.getId());
		
		if(store == null){
			store = storeService.addStoreByDaumStore(dsi);
		}
		
		Position postion = positionService.addPosition(user, store);
		
		Evaluation evaluation = new Evaluation();
		
		evaluation.setPosition(postion);
		for(int i=0; i<storeAndRating.getRating().length; i++){
			try {
				PropertyUtils.setProperty(evaluation, "eva" + (i+1), storeAndRating.getRating()[i]);
			} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
				log.warn("[별점 등록] Rating 변환 중 예외 발생 (Rating: {}, index: {})", storeAndRating.getRating(), i);
			}
			
		}

		evaluationService.saveEvaluation(evaluation);
		
		log.info("[별점 등록] storeAndRating: {}, User: {}", storeAndRating, principal);
		
		// TODO 포인드 적립 필요
		///////////////////////////////////////
		// pointService.checkSamePlace(storeAndRating.getStore_idx(),
		/////////////////////////////////////// user.getUser_idx());

		// PointLog pointLog = pointService.addPoint(storeAndRating.getS,
		// user.getUser_idx(), PointType.RATING, 30);

		// return pointLog;

		return null;
	}

	@RequestMapping(value = "/onlyLocationInfo", method = RequestMethod.POST)
	public boolean onlyLocationInfo(@RequestBody DaumStoreItem dsi, Principal principal) {
		
		CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		User user = accountService.findOne(userDetails.getUserIdx());
		Store store = storeService.getStoreByDsiId(dsi.getId());
		
		if(store == null){
			store = storeService.addStoreByDaumStore(dsi);
		}
		
		positionService.addPosition(user, store);
		
		log.info("[위치 정보 저장] daumStoreItem: {}, User: {}", dsi, principal);
		
		return true;
	}

	// @RequestMapping(value = "/locationInfo", method = RequestMethod.GET)
	// public @ResponseBody List<Store> locationInfo(@RequestBody Location
	// location) {
	// System.out.println(location);
	// log.info("[위치 정보] location: {}", location);
	//
	// // 가장 가까운 매장 정보
	// final double LATI = location.getLati();
	// final double LONGI = location.getLongi();
	// final double FOR_RADIUS = 0.0045; // 반경 약 500m //0.009; //반경 약 1000m
	// // //0.0135;//반경 약 1500m
	//
	// // GeoApiContext context = new
	// // GeoApiContext().setApiKey("AIzaSyCZqKafyvL0OerIRPWS_XV9GhPcWPIAL_w");
	// //
	// // LatLng loc = new LatLng(location.getLati(), location.getLongi());
	// // NearbySearchRequest searchRequest =
	// // PlacesApi.nearbySearchQuery(context, loc);
	// //
	// // try {
	// // PlacesSearchResponse searchResponse =
	// // searchRequest.type(PlaceType.FOOD) // 검색조건
	// // .radius(500).await(); // 조건에 맞는 매장 조회
	// //
	// // PlacesSearchResult searchResults[] = searchResponse.results;
	// //
	// // for (PlacesSearchResult searchResult : searchResults) {
	// //
	// // if (searchResult.permanentlyClosed == false) {
	// // Store store = new Store();
	// //
	// // String placeId = searchResult.placeId;
	// // float rating = searchResult.rating;
	// // String vicinity = searchResult.vicinity;
	// //
	// // store.setAddress(searchResult.formattedAddress);
	// // store.setLatitude(searchResult.geometry.location.lat);
	// // store.setLongitude(searchResult.geometry.location.lng);
	// // store.setName(searchResult.name);
	// // }
	// //
	// // }
	// //
	// // } catch (ApiException | InterruptedException | IOException e) {
	// // // TODO Auto-generated catch block
	// // e.printStackTrace();
	// // }
	//
	// List<Store> stores = storeService.getStoresByBound(location.getLati() -
	// FOR_RADIUS,
	// location.getLati() + FOR_RADIUS, location.getLongi() - FOR_RADIUS,
	// location.getLongi() + FOR_RADIUS);
	//
	// class MyComparator implements Comparator<Store> {
	//
	// @Override
	// public int compare(Store o1, Store o2) {
	// return (int) ((Math.pow(o1.getLatitude() - LATI, 2) +
	// Math.pow(o1.getLongitude() - LONGI, 2))
	// - (Math.pow(o2.getLatitude() - LATI, 2) + Math.pow(o2.getLongitude() -
	// LONGI, 2)));
	// }
	//
	// }
	//
	// stores.sort(new MyComparator());
	//
	// return stores;
	// }

}
