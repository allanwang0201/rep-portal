package au.com.jaycar.mdm.service;

import java.util.List;

import au.com.jaycar.mdm.entity.RelatedProducts;

public interface MdmRelatedProductsService {

	List<RelatedProducts> findByCode(String code);

}
