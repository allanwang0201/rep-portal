package au.com.jaycar.warehouse.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import au.com.jaycar.warehouse.dao.PorderItemDao;
import au.com.jaycar.warehouse.entity.PoitemExample;
import au.com.jaycar.warehouse.entity.PoitemExample.Criteria;
import au.com.jaycar.warehouse.vo.PorderItemVO;


@Repository
public class PorderItemDaoImpl extends BaseDaoImpl<PorderItemVO> implements PorderItemDao{
	
	public PorderItemDaoImpl(){
		//设置命名空间
		super.setNs("au.com.jaycar.warehouse.mapping.PorderItemVOMapper");
	}
	
	@Override
	public List<PorderItemVO> findByItemAndDatabase(@Param(value = "item") String item, @Param(value = "database") String database) {
		
		List<PorderItemVO> porderItems = null;
		
		SqlSession session = getSqlSession();
		
		Map<String, Object> paraMap= new HashMap<String, Object>();
		paraMap.put("item", item);
		paraMap.put("database", database);

	    porderItems =  session.selectList("au.com.jaycar.warehouse.mapping.PorderItemVOMapper.find", paraMap);
	        
		
		return porderItems;
		/*List<PorderItemVO> porderItems = null;
		PorderItemVO stock = null;
		Map paraMap=new HashMap();
		paraMap.put("item", item);
		paraMap.put("database", database);
		
		PoitemExample example = new PoitemExample();
		
		Criteria criteria = example.createCriteria();
		//List<String> product = new ArrayList<String>();
		//product.add("TRG465");
		//criteria.andStkStkqtyEqualTo(BigDecimal.valueOf(466l));//(product);//("TRG465");
		
		
		criteria.andPoitProductLike("%" + item + "%");
		criteria.andPoitCostcodeEqualTo(database);
		example.or(criteria);
		
		
        SqlSession session = getSqlSession();

        porderItems =  session.selectList("au.com.jaycar.warehouse.mapping.PorderItemVOMapper.findByItemAndDatabase", example);
        
        return porderItems;*/
		
		
		
	}
}
