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

import au.com.jaycar.local.business.service.StockService;
import au.com.jaycar.local.entity.Stock;


@RestController
public class StockRestController {

	
	@Autowired
	private StockService stockService;

	
	@GetMapping("/stock")
	public List getStocks() {
		return stockService.findAll();
	}

	@GetMapping("/stock/{id}")
	public ResponseEntity getStock(@PathVariable("id") Integer id) {

		Stock stock = stockService.findById(id);
		if (stock == null) {
			return new ResponseEntity("No Stock found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(stock, HttpStatus.OK);
	}
	
	@GetMapping("/stock/code/{code}")
	public ResponseEntity getStock(@PathVariable("code") String code) {

		Stock stock = stockService.findByCode(code);
		if (stock == null) {
			return new ResponseEntity("No Stock found for code " + code, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(stock, HttpStatus.OK);
	}


	@PostMapping(value = "/stock")
	public ResponseEntity createStock(@RequestBody Stock stock) {

		stockService.create(stock);

		return new ResponseEntity(stock, HttpStatus.OK);
	}

	@DeleteMapping("/stock/{id}")
	public ResponseEntity deleteStock(@PathVariable Integer id) {

		stockService.delete(id);

		return new ResponseEntity(id, HttpStatus.OK);

	}

	@PutMapping("/stock/{id}")
	public ResponseEntity updateStock(@PathVariable Integer id, @RequestBody Stock stock) {

		stockService.update(stock);

/*		if (null == stock) {
			return new ResponseEntity("No Stock found for ID " + id, HttpStatus.NOT_FOUND);
		}
*/
		return new ResponseEntity(stock, HttpStatus.OK);
	}

}