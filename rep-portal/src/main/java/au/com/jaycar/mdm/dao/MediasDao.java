package au.com.jaycar.mdm.dao;

import org.springframework.stereotype.Repository;

import au.com.jaycar.mdm.entity.Medias;
import au.com.jaycar.mdm.entity.Products;
import au.com.jaycar.mdm.entity.ProductsWithBLOBs;


@Repository
public interface MediasDao {

	public Medias findByPK(Long pk);

}