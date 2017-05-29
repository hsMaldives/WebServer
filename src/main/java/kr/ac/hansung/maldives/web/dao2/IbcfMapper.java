package kr.ac.hansung.maldives.web.dao2;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.ac.hansung.maldives.web.model.Store;

@Mapper
public interface IbcfMapper {

	public List<Store> selectStoresByUserIdx(@Param("userIdx") long userIdx, @Param("colNum") int colNum[], @Param("avgEva") float avgEva);
	
	public List<Store> getAssociationStoresByRowNames(@Param("rowNames") long rowNames, @Param("colNum") int colNum[]);
}
