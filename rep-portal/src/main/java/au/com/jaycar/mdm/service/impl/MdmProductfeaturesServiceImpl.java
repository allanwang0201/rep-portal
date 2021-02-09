package au.com.jaycar.mdm.service.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import au.com.jaycar.mdm.dao.ProductfeaturesDao;
import au.com.jaycar.mdm.entity.Productfeatures;

import au.com.jaycar.mdm.service.MdmProductfeaturesService;

import java.util.List;

@Component
public class MdmProductfeaturesServiceImpl implements MdmProductfeaturesService {

	@Resource
	private ProductfeaturesDao productfeaturesDao;

	@Override
	public List<Productfeatures> findByProductPK(Long pk) {

    List<Productfeatures> productfeatures = productfeaturesDao.findByProductPK(pk);
		return productfeatures;
	}

}
