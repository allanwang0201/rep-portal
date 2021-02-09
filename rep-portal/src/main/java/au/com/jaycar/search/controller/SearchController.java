package au.com.jaycar.search.controller;

import java.util.List;
import java.util.Set;

import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;

import au.com.jaycar.common.database.DataSourceContextHolder;
import au.com.jaycar.search.service.SolrjService;
import au.com.jaycar.search.entity.ProductSearchResult;

@Controller
public class SearchController {

	@Autowired
	private SolrjService service;

	@RequestMapping(value = "/products/solr", method = RequestMethod.GET)
	public ResponseEntity searchProducts(@RequestParam(value="item", required=true) String item, @RequestParam(value="sort", required=true) String sort, @RequestParam(value="page", required=true) String page,  @RequestParam(value="rows", required=true) String rows) {

		DataSourceContextHolder.setDbType("mdmDataSource");


		List<ProductSearchResult> results = null;
		try {
			results = service.search(item, sort, Integer.valueOf(page), Integer.valueOf(rows));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity(results, HttpStatus.OK);
	}

	@RequestMapping(value = "/products/solr/mlt", method = RequestMethod.GET)
	public ResponseEntity searchMltProducts(@RequestParam(value="item", required=true) String item) {

		DataSourceContextHolder.setDbType("mdmDataSource");

		List<ProductSearchResult> results = null;
		try {
			results = service.searchMlt(item);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity(results, HttpStatus.OK);
	}

	@RequestMapping(value = "/products/solr/suggest", method = RequestMethod.GET)
	public ResponseEntity searchSuggest(@RequestParam(value="item", required=true) String item) {

		DataSourceContextHolder.setDbType("mdmDataSource");


		Set<String> results = null;
		try {
			results = service.suggest(item);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity(results, HttpStatus.OK);
	}
	/*
	 * get the search result count
	 */
	@RequestMapping(value = "/products/solr/items/{query}", method = RequestMethod.GET)
	public ResponseEntity searchProductsNumber(@PathVariable("query") String query) {
		DataSourceContextHolder.setDbType("mdmDataSource");


		long results = 0;
		try {
			results = service.searchItemsNumber(query);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Gson gson = new Gson();
	    return ResponseEntity.ok(gson.toJson(results));
	}
}
