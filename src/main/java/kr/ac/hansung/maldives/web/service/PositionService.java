package kr.ac.hansung.maldives.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.hansung.maldives.web.dao.PositionRepository;
import kr.ac.hansung.maldives.web.model.Position;
import kr.ac.hansung.maldives.web.model.Store;
import kr.ac.hansung.maldives.web.model.User;

@Service
public class PositionService {
	@Autowired
	PositionRepository positionRepository;
	
	public Position addPosition(User user, Store store){
		Position position = new Position();
		position.setUser(user);
		position.setStore(store);
		return positionRepository.saveAndFlush(position);
	}
}
