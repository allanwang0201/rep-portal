package au.com.jaycar.mdm.dao;

import au.com.jaycar.mdm.entity.Productfeatures;

import java.util.List;

public interface ProductfeaturesDao {

	List<Productfeatures> findByProductPK(Long pk);

}
