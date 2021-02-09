/*
 * Created on 29 Sep 2017 ( Time 15:10:29 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package au.com.jaycar.local.business.service.mapping;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import au.com.jaycar.local.entity.Relatedproducts;
import au.com.jaycar.local.entity.jpa.RelatedproductsEntity;

/**
 * Mapping between entity beans and display beans.
 */
@Component
public class RelatedproductsServiceMapper extends AbstractServiceMapper {

	/**
	 * ModelMapper : bean to bean mapping library.
	 */
	private ModelMapper modelMapper;
	
	/**
	 * Constructor.
	 */
	public RelatedproductsServiceMapper() {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	/**
	 * Mapping from 'RelatedproductsEntity' to 'Relatedproducts'
	 * @param relatedproductsEntity
	 */
	public Relatedproducts mapRelatedproductsEntityToRelatedproducts(RelatedproductsEntity relatedproductsEntity) {
		if(relatedproductsEntity == null) {
			return null;
		}

		//--- Generic mapping 
		Relatedproducts relatedproducts = map(relatedproductsEntity, Relatedproducts.class);

		return relatedproducts;
	}
	
	/**
	 * Mapping from 'Relatedproducts' to 'RelatedproductsEntity'
	 * @param relatedproducts
	 * @param relatedproductsEntity
	 */
	public void mapRelatedproductsToRelatedproductsEntity(Relatedproducts relatedproducts, RelatedproductsEntity relatedproductsEntity) {
		if(relatedproducts == null) {
			return;
		}

		//--- Generic mapping 
		map(relatedproducts, relatedproductsEntity);

	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ModelMapper getModelMapper() {
		return modelMapper;
	}

	protected void setModelMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

}