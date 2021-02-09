package au.com.jaycar.warehouse.service;

import java.util.List;

import au.com.jaycar.warehouse.entity.Stock;

public interface WarehouseStockService {

	public List<Stock> findAll(String item);
	
	public Stock findByItemAndDatabase(String item, String database);
}
