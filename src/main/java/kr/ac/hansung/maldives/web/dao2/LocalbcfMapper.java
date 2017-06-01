package kr.ac.hansung.maldives.web.dao2;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.ac.hansung.maldives.web.model.Store;

@Mapper
public interface LocalbcfMapper {

	public List<Store> getRecommendStoreByLocationLimit10(@Param("categoryCode") String categoryCode,
			@Param("startX") double startX, @Param("endX") double endX, @Param("startY") double startY, @Param("endY") double endY);
	
}
