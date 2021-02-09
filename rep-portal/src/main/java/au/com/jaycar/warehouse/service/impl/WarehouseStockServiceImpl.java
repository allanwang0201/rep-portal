package au.com.jaycar.warehouse.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import au.com.jaycar.warehouse.dao.StockDao;
import au.com.jaycar.warehouse.entity.Stock;
import au.com.jaycar.warehouse.service.WarehouseStockService;

@Component
public class WarehouseStockServiceImpl implements WarehouseStockService{

	
	
	@Resource
	StockDao stockDao;
	
	@Override
	public List<Stock> findAll(String item) {

		stockDao.findByProduct(item);
		
		
		 return null;
	}
	
	@Override
	public Stock findByItemAndDatabase(String item, String database) {

		return stockDao.findByItemAndDatabase(item, database);
	}
	
}
