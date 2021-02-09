package au.com.jaycar.mdm.dao.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import au.com.jaycar.mdm.dao.MediasDao;
import au.com.jaycar.mdm.dao.ProductsDao;
import au.com.jaycar.mdm.entity.Medias;
import au.com.jaycar.mdm.entity.ProductsWithBLOBs;

@Repository
public class MediasDaoImpl implements MediasDao{

	
	@Resource(name = "sqlSession")
	private SqlSession sqlSession;

	public SqlSession getSqlSession() {
		return sqlSession;
	}
	
	@Override
	public Medias findByPK(Long pk){
		
		Medias medias = null;
		
        SqlSession session = getSqlSession();

        try {
			medias =  session.selectOne("au.com.jaycar.mdm.mapping.MediasMapper.selectByPK", pk);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return medias;
        
	}

	

	
}
