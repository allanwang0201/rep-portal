package au.com.jaycar.mdm.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import au.com.jaycar.common.database.DataSourceContextHolder;
import au.com.jaycar.mdm.entity.RelatedProducts;
import au.com.jaycar.mdm.service.MdmRelatedProductsService;
import au.com.jaycar.mvc.interceptor.RequestLimit;


@Controller
public class RelatedProductsController {

	
	@Autowired
	private MdmRelatedProductsService mdmRelatedProductsService;

	@RequestLimit
	@RequestMapping(value = "/mdm/relatedProducts/code/{code}", method = RequestMethod.GET)
	public ResponseEntity getProducts(@PathVariable("code") String code) {

		DataSourceContextHolder.setDbType("mdmDataSource"); 
		
		List<RelatedProducts> relatedProducts = mdmRelatedProductsService.findByCode(code);
		if (relatedProducts == null) {
			return new ResponseEntity("No Products found for code " + code, HttpStatus.NOT_FOUND);
		}
	

		return new ResponseEntity(relatedProducts, HttpStatus.OK);
	}



}