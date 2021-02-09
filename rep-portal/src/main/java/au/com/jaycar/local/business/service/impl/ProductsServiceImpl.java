/*
 * Created on 29 Sep 2017 ( Time 15:10:29 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package au.com.jaycar.local.business.service.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import java.util.Date;
import java.util.HashMap;

import au.com.jaycar.local.business.service.ProductsService;
import au.com.jaycar.local.business.service.mapping.ProductsServiceMapper;
import au.com.jaycar.local.entity.Products;
import au.com.jaycar.local.entity.jpa.ProductsEntity;
import au.com.jaycar.local.persistence.PersistenceServiceProvider;
import au.com.jaycar.local.persistence.services.ProductsPersistence;

import org.springframework.stereotype.Component;

/**
 * Implementation of ProductsService
 */
@Component
public class ProductsServiceImpl implements ProductsService {

	private ProductsPersistence productsPersistence;

	@Resource
	private ProductsServiceMapper productsServiceMapper;
	
	public ProductsServiceImpl() {
		productsPersistence = PersistenceServiceProvider.getService(ProductsPersistence.class);
	}
		
	@Override
	public Products findById(Integer pk) {
		ProductsEntity entity = productsPersistence.load(pk);
		return productsServiceMapper.mapProductsEntityToProducts(entity);
	}
	
	@Override
	public Products findByCode(String code) {
		
		String queryName = "ProductsEntity.findByCode";
		Map<String, Object> queryParameters = new HashMap<String, Object>();
		queryParameters.put("code", code);
		ProductsEntity entity = productsPersistence.loadByNamedQuery(queryName, queryParameters).get(0);
		return productsServiceMapper.mapProductsEntityToProducts(entity);
	}


	@Override
	public List<Products> findAll() {
		List<ProductsEntity> entities = productsPersistence.loadAll();
		List<Products> beans = new ArrayList<Products>();
		for(ProductsEntity entity : entities) {
			beans.add(productsServiceMapper.mapProductsEntityToProducts(entity));
		}
		return beans;
	}

	@Override
	public Products save(Products products) {
		return update(products) ;
	}

	@Override
	public Products create(Products products) {
		if(productsPersistence.load(products.getPk()) != null) {
			throw new IllegalStateException("already.exists");
		}
		ProductsEntity productsEntity = new ProductsEntity();
		productsServiceMapper.mapProductsToProductsEntity(products, productsEntity);
		ProductsEntity productsEntitySaved = productsPersistence.save(productsEntity);
		return productsServiceMapper.mapProductsEntityToProducts(productsEntitySaved);
	}

	@Override
	public Products update(Products products) {
		ProductsEntity productsEntity = productsPersistence.load(products.getPk());
		productsServiceMapper.mapProductsToProductsEntity(products, productsEntity);
		ProductsEntity productsEntitySaved = productsPersistence.save(productsEntity);
		return productsServiceMapper.mapProductsEntityToProducts(productsEntitySaved);
	}

	@Override
	public void delete(Integer pk) {
		productsPersistence.delete(pk);
	}

	public ProductsPersistence getProductsPersistence() {
		return productsPersistence;
	}

	public void setProductsPersistence(ProductsPersistence productsPersistence) {
		this.productsPersistence = productsPersistence;
	}

	public ProductsServiceMapper getProductsServiceMapper() {
		return productsServiceMapper;
	}

	public void setProductsServiceMapper(ProductsServiceMapper productsServiceMapper) {
		this.productsServiceMapper = productsServiceMapper;
	}

}
