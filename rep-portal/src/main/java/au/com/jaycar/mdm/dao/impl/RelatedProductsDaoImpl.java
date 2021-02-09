package au.com.jaycar.mdm.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import au.com.jaycar.mdm.dao.ProductsDao;
import au.com.jaycar.mdm.dao.RelatedProductsDao;
import au.com.jaycar.mdm.entity.ProductsWithBLOBs;
import au.com.jaycar.mdm.entity.RelatedProducts;

@Repository
public class RelatedProductsDaoImpl implements RelatedProductsDao{

	
	@Resource(name = "sqlSession")
	private SqlSession sqlSession;

	public SqlSession getSqlSession() {
		return sqlSession;
	}
	
	public List<RelatedProducts> findByCode(String code){
		
		List<RelatedProducts> relatedProducts = null;
		
        SqlSession session = getSqlSession();

        relatedProducts =  session.selectList("au.com.jaycar.mdm.mapping.RelatedProductsMapper.selectByCode", code);
        
        return relatedProducts;
        
	}
	
}
