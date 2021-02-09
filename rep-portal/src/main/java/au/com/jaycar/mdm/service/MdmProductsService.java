package au.com.jaycar.mdm.service;

import java.util.List;

import au.com.jaycar.mdm.entity.ProductsWithBLOBs;

public interface MdmProductsService {

	
	ProductsWithBLOBs findByCode(String code);

	List<ProductsWithBLOBs> findAll();
	
}
