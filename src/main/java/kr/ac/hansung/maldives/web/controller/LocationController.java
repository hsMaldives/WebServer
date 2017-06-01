
package kr.ac.hansung.maldives.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.ac.hansung.maldives.web.model.CustomUserDetails;
import kr.ac.hansung.maldives.web.model.Store;
import kr.ac.hansung.maldives.web.model.StoreComment;
import kr.ac.hansung.maldives.web.property.WhereyouProperty;
import kr.ac.hansung.maldives.web.service.StoreService;
import lombok.extern.slf4j.Slf4j;

@Slf4j

@Controller
@RequestMapping("/location")
public class LocationController {
	
	@Autowired
	private StoreService storeService;
	
	@Autowired
	private WhereyouProperty whereyouProperty;
	
	@RequestMapping("/")
	public String location(Model model) {
		
		model.addAttribute("googleMapApiKey",whereyouProperty.getGoogleMapApiKey());
		
		return "location/location";
	}

	@RequestMapping("/detail/{storeIdx}")
	public String locationDetail(@PathVariable(name="storeIdx") Long storeIdx, Model model) {
		Store store = storeService.getStoreById(storeIdx);
		List<Store> associationStores = storeService.ibcfGetAssociationStoresByRowNames(storeIdx);
		
		store.setComments(storeService.getCommentList(storeIdx));
		
		model.addAttribute("store", store);
		model.addAttribute("associationStores", associationStores);
		model.addAttribute("googleMapApiKey",whereyouProperty.getGoogleMapApiKey());
		
		return "location/detail";
	}
	
	@RequestMapping(value="/detail/{storeIdx}/comment/", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<Void> addComment(@PathVariable(name="storeIdx") Long storeIdx, String comment){
		CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		
		storeService.addComment(storeIdx, userDetails.getUserIdx(), comment);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/detail/{storeIdx}/comment/{commentIdx}", method=RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<Void> deleteComment(@PathVariable(name="storeIdx") Long storeIdx,
			@PathVariable(name="commentIdx") Long commentIdx, HttpServletRequest request){
		CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		StoreComment comment = storeService.getComment(commentIdx);
		
		if(! comment.getUser().getUserIdx().equals(userDetails.getUserIdx()) 
				&& comment.getStore().getStoreIdx().equals(storeIdx)){
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		storeService.deleteComment(commentIdx);
		
		log.info("[매장 댓글 삭제] 성공 (comment: {}, user: {}, IP: {})", comment, userDetails, request.getRemoteAddr());
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping("/getStores")
	public @ResponseBody List<Store> getStoresByBound(@RequestParam(required=false, defaultValue="") String categoryCode, 
			double startX, double endX, double startY, double endY) {
		List<Store> stores = storeService.findByCategoryCategoryCodeStartingWithAndBound(categoryCode, startX, endX, startY, endY);
		
		return stores;
	}

}
