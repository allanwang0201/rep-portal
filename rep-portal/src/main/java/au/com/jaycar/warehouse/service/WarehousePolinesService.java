package au.com.jaycar.warehouse.service;



import java.util.List;

import au.com.jaycar.warehouse.vo.PorderItemVO;


public interface WarehousePolinesService {

	
	public List<PorderItemVO> getPolines(String item, String database);
}
