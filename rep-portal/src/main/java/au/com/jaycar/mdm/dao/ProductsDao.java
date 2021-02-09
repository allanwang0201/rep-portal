package au.com.jaycar.mdm.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import au.com.jaycar.mdm.entity.Products;
import au.com.jaycar.mdm.entity.ProductsWithBLOBs;


@Repository
public interface ProductsDao {

	public ProductsWithBLOBs findByCode(String code);

	public List<ProductsWithBLOBs> findAll();

}
