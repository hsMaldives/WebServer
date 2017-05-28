package kr.ac.hansung.maldives.web.dao2;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StoreMapper {
	
	public double getStoreAvgEvaluationByStoreIdx(@Param("storeIdx") long storeIdx);
}
