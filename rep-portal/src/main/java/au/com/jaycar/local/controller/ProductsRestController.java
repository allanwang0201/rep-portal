package au.com.jaycar.local.controller;

import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;

import au.com.jaycar.common.database.DataSourceContextHolder;
import au.com.jaycar.local.business.service.ProductsService;
import au.com.jaycar.local.entity.Products;
import au.com.jaycar.mdm.service.MdmMediasService;


@Controller
public class ProductsRestController {

	
	@Autowired
	private ProductsService productsService;
	
	
	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public List getProductss() {
		return productsService.findAll();
	}

	@RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
	public ResponseEntity getProducts(@PathVariable("id") Integer id) {

		Products products = productsService.findById(id);
		if (products == null) {
			return new ResponseEntity("No Products found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(products, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/products/code/{code}", method = RequestMethod.GET)
	public ResponseEntity getProducts(@PathVariable("code") String code) {

		Products products = productsService.findByCode(code);
		if (products == null) {
			return new ResponseEntity("No Products found for code " + code, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(products, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/products", method = RequestMethod.POST)
	public ResponseEntity createProducts(@RequestBody Products products) {

		productsService.create(products);

		return new ResponseEntity(products, HttpStatus.OK);
	}

	@RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
	public ResponseEntity deleteProducts(@PathVariable Integer id) {

		productsService.delete(id);

		return new ResponseEntity(id, HttpStatus.OK);

	}

	@RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
	public ResponseEntity updateProducts(@PathVariable Integer id, @RequestBody Products products) {

		productsService.update(products);

/*		if (null == products) {
			return new ResponseEntity("No Products found for ID " + id, HttpStatus.NOT_FOUND);
		}
*/
		return new ResponseEntity(products, HttpStatus.OK);
	}

}