package au.com.jaycar.warehouse.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import au.com.jaycar.common.database.DataSourceContextHolder;
import au.com.jaycar.common.util.DatabaseNameUtil;
import au.com.jaycar.local.entity.Polines;
import au.com.jaycar.mvc.interceptor.RequestLimit;
import au.com.jaycar.warehouse.service.WarehousePolinesService;
import au.com.jaycar.warehouse.vo.PorderItemVO;

@Controller
public class PolinesController {

	
	@Autowired
	private WarehousePolinesService porderItemService;

	@Autowired
	private DatabaseNameUtil databaseNameUtil;
	
	@RequestLimit
	@RequestMapping("/warehouse/polines")
	public ResponseEntity getPolines(@RequestParam(value="item", required=true) String item, @RequestParam(value="branch", required=true) String branch,  @RequestParam(value="warehouse", required=true, defaultValue = "NSW") String warehouse)  {

		
		DataSourceContextHolder.setDbType("warehouseDataSource");
	
		String database = databaseNameUtil.getDatabaseName(item, branch, warehouse);
		String code =  databaseNameUtil.tranferRtmCode(item, branch);
		
		List<PorderItemVO> porders = porderItemService.getPolines(code, database);
		
		if (porders == null) {
			return new ResponseEntity("No Polines found for code " + code, HttpStatus.NOT_FOUND);
		}
		
		
		List<Polines> polines = new ArrayList<Polines>();
		
		for(PorderItemVO porder : porders)
		{
			Polines poline = new Polines();
			
			poline.setPordOrder(porder.getPorder().getPordOrder());
			poline.setPoitQtyord(porder.getPoitQtyord().toString());
			poline.setPoitDuedate(porder.getPoitDuedate());
			
			
			DateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

			String date = df.format(porder.getPoitDuedate());
			
			poline.setPoitDuedateString(date);
			
			polines.add(poline);
			
		}

		return new ResponseEntity(polines, HttpStatus.OK);
	}

}
