package kr.ac.hansung.maldives.web.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.hansung.maldives.web.dao.PointRepository;
import kr.ac.hansung.maldives.web.model.PointLog;
import kr.ac.hansung.maldives.web.model.PointLog.PointType;
import kr.ac.hansung.maldives.web.model.Store;
import kr.ac.hansung.maldives.web.model.User;

@Service
public class PointService {
	@Autowired
	private PointRepository pointRepository;

	@Autowired
	private AccountService accountService;

	public void findOne(Long point_idx) {
		pointRepository.getOne(point_idx);
	}

	public boolean checkSamePlace(Long store_idx,Long userIdx){
		LocalDateTime beginTime = LocalDateTime.now().minusMinutes(15);
		LocalDateTime endTime = LocalDateTime.now();
		
//		if(pointRepository.findByUserUserIdxAndTimestampBetween(userIdx, beginTime, endTime)==null)
//			return false;
		return false;
	}

	public PointLog addPoint(Store store, Long user_idx, PointType pointType, Integer acc_point) {
		User user = accountService.findOne(user_idx);

		PointLog point = new PointLog();
		point.setPointType(pointType);
		point.setAccPoint(acc_point);
		point.setStore(store);
		point.setTotalPoint(user.getPoint() + acc_point);
		point.setUser(user);

		user.setPoint(point.getTotalPoint());

		pointRepository.save(point);

		return point;
	}
}
