package au.com.jaycar.warehouse.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import au.com.jaycar.warehouse.dao.PorderItemDao;

import au.com.jaycar.warehouse.service.WarehousePolinesService;
import au.com.jaycar.warehouse.vo.PorderItemVO;

@Component
public class WarehousePolinesServiceImpl implements WarehousePolinesService {
	
	@Resource
	PorderItemDao porderItemDao;
	
	@Override
	public List<PorderItemVO> getPolines(String item, String database) {
		
		Map paraMap=new HashMap();
		//paraMap.put("item", '%'+item+'%');
		paraMap.put("database", database);

		return porderItemDao.findByItemAndDatabase(item, database);
	}
}
