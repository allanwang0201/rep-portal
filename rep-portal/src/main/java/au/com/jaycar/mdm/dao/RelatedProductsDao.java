package au.com.jaycar.mdm.dao;

import java.util.List;

import au.com.jaycar.mdm.entity.RelatedProducts;

public interface RelatedProductsDao {

	List<RelatedProducts> findByCode(String code);

}
