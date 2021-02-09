package au.com.jaycar.warehouse.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import au.com.jaycar.warehouse.entity.Stock;


public interface StockDao extends BaseDao<Stock> {

	
	public Stock findByProduct(String code);

	public Stock findByItemAndDatabase(@Param(value = "item") String item, @Param(value = "database") String database);
}
