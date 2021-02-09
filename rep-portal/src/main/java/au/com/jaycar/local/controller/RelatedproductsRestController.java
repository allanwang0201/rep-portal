package au.com.jaycar.local.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import au.com.jaycar.local.business.service.RelatedproductsService;
import au.com.jaycar.local.entity.Products;
import au.com.jaycar.local.entity.Relatedproducts;


@RestController
public class RelatedproductsRestController {

	
	@Autowired
	private RelatedproductsService relatedproductsService;

	
	@GetMapping("/relatedproducts")
	public List getProductss() {
		return relatedproductsService.findAll();
	}

	@GetMapping("/relatedproducts/{id}")
	public ResponseEntity getRelatedProducts(@PathVariable("id") Integer id) {

		Relatedproducts relatedproducts = relatedproductsService.findById(id);
		if (relatedproducts == null) {
			return new ResponseEntity("No Products found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(relatedproducts, HttpStatus.OK);
	}
	
	@GetMapping("/relatedproducts/code/{code}")
	public ResponseEntity getRelatedProducts(@PathVariable("code") String code) {

		Relatedproducts relatedproducts = relatedproductsService.findByCode(code);
		if (relatedproducts == null) {
			return new ResponseEntity("No Products found for code " + code, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(relatedproducts, HttpStatus.OK);
	}


	@PostMapping(value = "/relatedproducts")
	public ResponseEntity createProducts(@RequestBody Relatedproducts relatedproducts) {

		relatedproductsService.create(relatedproducts);

		return new ResponseEntity(relatedproducts, HttpStatus.OK);
	}

	@DeleteMapping("/relatedproducts/{id}")
	public ResponseEntity deleteProducts(@PathVariable Integer id) {

		relatedproductsService.delete(id);

		return new ResponseEntity(id, HttpStatus.OK);

	}

	@PutMapping("/relatedproducts/{id}")
	public ResponseEntity updateProducts(@PathVariable Integer id, @RequestBody Relatedproducts relatedproducts) {

		relatedproductsService.update(relatedproducts);

/*		if (null == relatedproducts) {
			return new ResponseEntity("No Products found for ID " + id, HttpStatus.NOT_FOUND);
		}
*/
		return new ResponseEntity(relatedproducts, HttpStatus.OK);
	}

}