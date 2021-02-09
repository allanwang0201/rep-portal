package au.com.jaycar.mdm.service;

import au.com.jaycar.mdm.entity.Productfeatures;

import java.util.List;


public interface MdmProductfeaturesService {

  List<Productfeatures>  findByProductPK(Long pk);
}
