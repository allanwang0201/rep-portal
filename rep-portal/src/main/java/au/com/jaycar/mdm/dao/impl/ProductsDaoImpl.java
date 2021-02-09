package au.com.jaycar.mdm.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import au.com.jaycar.mdm.dao.ProductsDao;
import au.com.jaycar.mdm.entity.ProductsWithBLOBs;

@Repository
public class ProductsDaoImpl implements ProductsDao{

	
	@Resource(name = "sqlSession")
	private SqlSession sqlSession;

	public SqlSession getSqlSession() {
		return sqlSession;
	}
	
	public ProductsWithBLOBs findByCode(String code){
		
		ProductsWithBLOBs products = null;
		
        SqlSession session = getSqlSession();

		products =  session.selectOne("au.com.jaycar.mdm.mapping.ProductsMapper.selectByCode", code);
        
        return products;
        
	}
	
	
	public List<ProductsWithBLOBs> findAll(){
		
		List<ProductsWithBLOBs> products = null;
		
        SqlSession session = getSqlSession();

		products =  session.selectList("au.com.jaycar.mdm.mapping.ProductsMapper.selectAll");
        
        return products;
        
	}
	
}
