
/*
 * Created on 29 Sep 2017 ( Time 15:10:48 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package au.com.jaycar.local.mock;

import java.util.LinkedList;
import java.util.List;

import au.com.jaycar.local.entity.jpa.RelatedproductsEntity;
import au.com.jaycar.local.mock.tool.MockValues;

public class RelatedproductsEntityMock {

	private MockValues mockValues = new MockValues();
	
	/**
	 * Creates an instance with random Primary Key
	 * @return
	 */
	public RelatedproductsEntity createInstance() {
		// Primary Key values

		return createInstance( mockValues.nextInteger() );
	}
	
	/**
	 * Creates an instance with a specific Primary Key
	 * @param id1
	 * @return
	 */
	public RelatedproductsEntity createInstance( Integer pk ) {
		RelatedproductsEntity entity = new RelatedproductsEntity();
		// Init Primary Key fields
		entity.setPk( pk) ;
		// Init Data fields
		entity.setSourcecode( mockValues.nextString(10) ) ; // java.lang.String 
		entity.setTargetcode( mockValues.nextString(10) ) ; // java.lang.String 
		entity.setRelationtype( mockValues.nextString(45) ) ; // java.lang.String 
		// Init Link fields (if any)
		return entity ;
	}
	
	/**
	 * Creates a list of instances
	 * @param count number of instances to be created
	 * @return
	 */
	public List<RelatedproductsEntity> createList(int count) {
		List<RelatedproductsEntity> list = new LinkedList<RelatedproductsEntity>();		
		for ( int i = 1 ; i <= count ; i++ ) {
			list.add( createInstance() );
		}
		return list;
	}
}
