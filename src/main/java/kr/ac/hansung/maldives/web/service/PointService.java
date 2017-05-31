package kr.ac.hansung.maldives.web.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.hansung.maldives.web.dao.PointRepository;
import kr.ac.hansung.maldives.web.model.PointLog;
import kr.ac.hansung.maldives.web.model.PointLog.PointType;
import kr.ac.hansung.maldives.web.model.Product;
import kr.ac.hansung.maldives.web.model.User;
import kr.ac.hansung.maldives.web.property.WhereyouProperty;

@Service
public class PointService {

	@Autowired
	private PointRepository pointRepository;

	@Autowired
	private AccountService accountService;

	private int evalPoint;
	//checksameplace()?

	public PointLog addPoint(Long user_idx) {
		
		
		User user = accountService.findOne(user_idx);
		user.setPoint(user.getPoint() + evalPoint);
		
		
		PointLog pointLog = new PointLog();
		pointLog.setAccPoint(evalPoint);
		pointLog.setPointType(PointType.RATING);
		pointLog.setTotalPoint(user.getPoint());		
		pointLog.setUser(user);
		pointLog.setTimestamp(LocalDateTime.now());

		pointRepository.saveAndFlush(pointLog);

		return pointLog;
	}

	public PointLog addPointWithEvent(Long user_idx, Integer point) {
		User user = accountService.findOne(user_idx);
		user.setPoint(user.getPoint() + point);

		PointLog pointLog = new PointLog();
		pointLog.setAccPoint(point);
		pointLog.setPointType(PointType.EVENT);
		pointLog.setTotalPoint(user.getPoint());		
		pointLog.setUser(user);
		pointLog.setTimestamp(LocalDateTime.now());

		pointRepository.saveAndFlush(pointLog);

		return pointLog;
	}

	public PointLog usePoint(Long user_idx, Product product) {
		User user = accountService.findOne(user_idx);
		user.setPoint(user.getPoint() - product.getPrice());

		PointLog pointLog = new PointLog();
		pointLog.setAccPoint(-product.getPrice());
		pointLog.setPointType(PointType.SPEND);
		pointLog.setTotalPoint(pointLog.getTotalPoint());		
		pointLog.setUser(user);
		pointLog.setTimestamp(LocalDateTime.now());

		pointRepository.saveAndFlush(pointLog);

		return pointLog;

	}

	public Integer spendedPoint(Long user_idx) {

		List<PointLog> pointLogs = pointRepository.findByUserUserIdxAndPointType(user_idx, PointType.SPEND);

		int spendedPoint = 0;

		for (PointLog pointlog : pointLogs) {
			spendedPoint += pointlog.getAccPoint();
		}

		return -spendedPoint;
	}
	
	@Autowired
	public void setEvalPoint(WhereyouProperty whereyouProperty){
		this.evalPoint = whereyouProperty.getEvalPoint();
	}
}
