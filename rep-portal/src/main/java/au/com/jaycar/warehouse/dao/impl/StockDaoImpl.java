package au.com.jaycar.warehouse.dao.impl;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;


import au.com.jaycar.warehouse.dao.StockDao;
import au.com.jaycar.warehouse.entity.Stock;
import au.com.jaycar.warehouse.entity.StockExample;
import au.com.jaycar.warehouse.entity.StockExample.Criteria;

@Repository
public class StockDaoImpl extends BaseDaoImpl<Stock> implements StockDao{

	public StockDaoImpl(){
		//设置命名空间
		super.setNs("au.com.jaycar.warehouse.mapping.StockMapper");
	}

	@Override
	public Stock findByProduct(String code) {
		Stock stock = null;
       	
		Map<String, Object> paraMap= new HashMap<String, Object>();
		paraMap.put("code", code);
		paraMap.put("database", "DCNSW");
		
		
        SqlSession session = getSqlSession();

        stock = (Stock) session.selectList("au.com.jaycar.warehouse.mapping.StockMapper.selectTest").get(0);
        
        return stock;
		
	}
	
	public Stock findByItemAndDatabase(@Param(value = "item") String item, @Param(value = "database") String database) {
/*		List<Stock> stocks = null;
		Stock stock = null;
		Map paraMap=new HashMap();
		paraMap.put("code", code);
		paraMap.put("database", database);
		
		StockExample example = new StockExample();
		
		Criteria criteria = example.createCriteria();
		List<String> product = new ArrayList<String>();
		product.add("TRG465");

		criteria.andStkProductLike("%" + code + "%");
		criteria.andStkLocEqualTo(database);
		example.or(criteria);
		*/
		Stock stock = null;
		
        SqlSession session = getSqlSession();
        
		Map<String, Object> paraMap= new HashMap<String, Object>();
		paraMap.put("item", item);
		paraMap.put("database", database);

		

        stock =  session.selectOne("au.com.jaycar.warehouse.mapping.StockMapper.findByItemAndDatabase", paraMap);
        
        return stock;
		
	}

}
