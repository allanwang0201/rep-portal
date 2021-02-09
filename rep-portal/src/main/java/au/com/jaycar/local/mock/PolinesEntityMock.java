
/*
 * Created on 29 Sep 2017 ( Time 15:10:47 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package au.com.jaycar.local.mock;

import java.util.LinkedList;
import java.util.List;

import au.com.jaycar.local.entity.jpa.PolinesEntity;
import au.com.jaycar.local.mock.tool.MockValues;

public class PolinesEntityMock {

	private MockValues mockValues = new MockValues();
	
	/**
	 * Creates an instance with random Primary Key
	 * @return
	 */
	public PolinesEntity createInstance() {
		// Primary Key values

		return createInstance( mockValues.nextInteger() );
	}
	
	/**
	 * Creates an instance with a specific Primary Key
	 * @param id1
	 * @return
	 */
	public PolinesEntity createInstance( Integer pk ) {
		PolinesEntity entity = new PolinesEntity();
		// Init Primary Key fields
		entity.setPk( pk) ;
		// Init Data fields
		entity.setCode( mockValues.nextString(10) ) ; // java.lang.String 
		entity.setPoitDuedate( mockValues.nextDate() ) ; // java.util.Date 
		entity.setPoitQtyord( mockValues.nextString(45) ) ; // java.lang.String 
		entity.setPordOrder( mockValues.nextString(45) ) ; // java.lang.String 
		entity.setWarehouse( mockValues.nextString(45) ) ; // java.lang.String 
		entity.setPolinescol( mockValues.nextString(45) ) ; // java.lang.String 
		entity.setFreeqty( mockValues.nextString(45) ) ; // java.lang.String 
		entity.setOutqty( mockValues.nextString(45) ) ; // java.lang.String 
		entity.setPoitLine( mockValues.nextString(45) ) ; // java.lang.String 
		entity.setPoitAckref( mockValues.nextString(45) ) ; // java.lang.String 
		entity.setReqdate( mockValues.nextDate() ) ; // java.util.Date 
		// Init Link fields (if any)
		return entity ;
	}
	
	/**
	 * Creates a list of instances
	 * @param count number of instances to be created
	 * @return
	 */
	public List<PolinesEntity> createList(int count) {
		List<PolinesEntity> list = new LinkedList<PolinesEntity>();		
		for ( int i = 1 ; i <= count ; i++ ) {
			list.add( createInstance() );
		}
		return list;
	}
}