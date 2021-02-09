package au.com.jaycar.warehouse.mapping;

import java.util.List;
import java.util.Map;

import au.com.jaycar.warehouse.vo.PorderItemVO;

public interface PorderItemVOMapper {

	List<PorderItemVO> findByItemAndDatabase(Map example);
	
}
