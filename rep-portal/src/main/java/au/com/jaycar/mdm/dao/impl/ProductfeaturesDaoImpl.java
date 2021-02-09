package au.com.jaycar.mdm.dao.impl;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import au.com.jaycar.mdm.dao.ProductfeaturesDao;
import au.com.jaycar.mdm.entity.Productfeatures;
import au.com.jaycar.mdm.entity.Products;

import java.util.List;

@Repository
public class ProductfeaturesDaoImpl implements ProductfeaturesDao{


	@Resource(name = "sqlSession")
	private SqlSession sqlSession;

	public SqlSession getSqlSession() {
		return sqlSession;
	}

	@Override
	public List<Productfeatures> findByProductPK(Long pk) {

		List<Productfeatures> productfeatures = null;

        SqlSession session = getSqlSession();

        productfeatures =  session.selectList("au.com.jaycar.mdm.mapping.ProductfeaturesMapper.selectByProductPK", pk);

        return productfeatures;
	}

}
