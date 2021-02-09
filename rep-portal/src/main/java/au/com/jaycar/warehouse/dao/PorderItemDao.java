package au.com.jaycar.warehouse.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import au.com.jaycar.warehouse.vo.PorderItemVO;

public interface PorderItemDao extends BaseDao<PorderItemVO>{

	List<PorderItemVO> findByItemAndDatabase(@Param(value = "item") String item, @Param(value = "database") String database); 

}
