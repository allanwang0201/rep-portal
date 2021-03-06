/*
 * Created on 29 Sep 2017 ( Time 15:10:47 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */

package au.com.jaycar.local.persistence.services.jpa;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import au.com.jaycar.local.entity.jpa.PolinesEntity;
import au.com.jaycar.local.persistence.commons.jpa.GenericJpaService;
import au.com.jaycar.local.persistence.commons.jpa.JpaOperation;
import au.com.jaycar.local.persistence.services.PolinesPersistence;

/**
 * JPA implementation for basic persistence operations ( entity "Polines" )
 * 
 * @author Telosys Tools Generator
 *
 */
public class PolinesPersistenceJPA extends GenericJpaService<PolinesEntity, Integer> implements PolinesPersistence {

	/**
	 * Constructor
	 */
	public PolinesPersistenceJPA() {
		super(PolinesEntity.class);
	}

	@Override
	public PolinesEntity load( Integer pk ) {
		return super.load( pk );
	}

	@Override
	public boolean delete( Integer pk ) {
		return super.delete( pk );
	}

	@Override
	public boolean delete(PolinesEntity entity) {
		if ( entity != null ) {
			return super.delete( entity.getPk() );
		}
		return false ;
	}

	@Override
	public long countAll() {
		// JPA operation definition 
		JpaOperation operation = new JpaOperation() {
			@Override
			public Object exectue(EntityManager em) throws PersistenceException {
				Query query = em.createNamedQuery("PolinesEntity.countAll");
				return query.getSingleResult() ;
			}
		} ;
		// JPA operation execution 
		return (Long) execute(operation);
	}

}
