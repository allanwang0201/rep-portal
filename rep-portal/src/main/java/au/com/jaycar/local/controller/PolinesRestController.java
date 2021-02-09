package au.com.jaycar.local.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import au.com.jaycar.local.business.service.PolinesService;
import au.com.jaycar.local.entity.Polines;


@Controller
public class PolinesRestController {

	
	@Autowired
	private PolinesService polinesService;

	
	@RequestMapping(value="/polines", method = RequestMethod.GET)
	public List getPoliness() {
		return polinesService.findAll();
	}

	@RequestMapping(value="/polines/{id}", method = RequestMethod.GET)
	public ResponseEntity getPolines(@PathVariable("id") Integer id) {

		Polines polines = polinesService.findById(id);
		if (polines == null) {
			return new ResponseEntity("No Polines found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(polines, HttpStatus.OK);
	}
	
	@RequestMapping(value="/polines/code/{code}", method = RequestMethod.GET)
	public ResponseEntity getPolines(@PathVariable("code") String code) {

		Polines polines = polinesService.findByCode(code);
		if (polines == null) {
			return new ResponseEntity("No Polines found for code " + code, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(polines, HttpStatus.OK);
	}


	@RequestMapping(value="/polines", method = RequestMethod.POST)
	public ResponseEntity createPolines(@RequestBody Polines polines) {

		polinesService.create(polines);

		return new ResponseEntity(polines, HttpStatus.OK);
	}

	@RequestMapping(value="/polines/{id}", method = RequestMethod.DELETE)
	public ResponseEntity deletePolines(@PathVariable Integer id) {

		polinesService.delete(id);

		return new ResponseEntity(id, HttpStatus.OK);

	}

	@RequestMapping(value="/polines/{id}", method = RequestMethod.PUT)
	public ResponseEntity updatePolines(@PathVariable Integer id, @RequestBody Polines polines) {

		polinesService.update(polines);

/*		if (null == polines) {
			return new ResponseEntity("No Polines found for ID " + id, HttpStatus.NOT_FOUND);
		}
*/
		return new ResponseEntity(polines, HttpStatus.OK);
	}

}