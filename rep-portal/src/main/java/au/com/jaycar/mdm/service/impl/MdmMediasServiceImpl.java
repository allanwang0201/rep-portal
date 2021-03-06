/*
 * Created on 29 Sep 2017 ( Time 15:10:29 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package au.com.jaycar.mdm.service.impl;


import java.util.Map;

import javax.annotation.Resource;

import java.util.HashMap;

import au.com.jaycar.mdm.dao.MediasDao;
import au.com.jaycar.mdm.dao.ProductsDao;
import au.com.jaycar.mdm.entity.Medias;
import au.com.jaycar.mdm.entity.Products;
import au.com.jaycar.mdm.entity.ProductsWithBLOBs;
import au.com.jaycar.mdm.service.MdmMediasService;
import au.com.jaycar.mdm.service.MdmProductsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Implementation of ProductsService
 */
@Component
public class MdmMediasServiceImpl implements MdmMediasService {

	@Resource
	MediasDao mediasDao;


	@Override
	public Medias findByPK(Long pk) {
		
		if(pk == null)
			return null;
		
		Map<String, Object> queryParameters = new HashMap<String, Object>();
		queryParameters.put("pk", pk);
		
		Medias medias = mediasDao.findByPK(pk);
		return medias;
		
	}
}
