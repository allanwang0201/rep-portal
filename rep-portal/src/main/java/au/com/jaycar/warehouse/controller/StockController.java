package au.com.jaycar.warehouse.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;

import au.com.jaycar.common.database.DataSourceContextHolder;
import au.com.jaycar.common.util.DatabaseNameUtil;
import au.com.jaycar.common.util.HtmlParser;
import au.com.jaycar.mvc.interceptor.RequestLimit;
import au.com.jaycar.warehouse.entity.Stock;
import au.com.jaycar.warehouse.service.WarehouseStockService;


@Controller
public class StockController {


	@Autowired
	private WarehouseStockService warehouseStockService;

	@Autowired
	private DatabaseNameUtil databaseNameUtil;


	@Autowired
	private HtmlParser htmlParser;


	@RequestLimit
	@RequestMapping("warehouse/stock")
	public ResponseEntity getStock(@RequestParam(value="item", required=true) String item, @RequestParam(value="branch", required=true) String branch,  @RequestParam(value="warehouse", required=true, defaultValue = "NSW") String warehouse)  {

		DataSourceContextHolder.setDbType("warehouseDataSource");

		String database = databaseNameUtil.getDatabaseName(item, branch, warehouse);
		String code =  databaseNameUtil.tranferRtmCode(item, branch);


		Stock stock =  warehouseStockService.findByItemAndDatabase(code, database);
		if (stock == null) {
			return new ResponseEntity("No Stock found for code " + code, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(stock, HttpStatus.OK);
	}

    @RequestMapping("warehouse/pricing")
    public ResponseEntity<String> GetPricing(@RequestParam(value="item", required=true) String item) {

       String pricing = htmlParser.getHtmlContent("http://branch.jaycar.com.au/mdm/index.php?itemCode=" + item + "&page=price&catalogueversion=","utf-8");
       Gson gson = new Gson();
       return ResponseEntity.ok(gson.toJson(pricing));
    }

  @RequestMapping("warehouse/bundle")
  public ResponseEntity<String> GetBundle(@RequestParam(value="item", required=true) String item) {

    String bundle = htmlParser.getHtmlContent("http://branch.jaycar.com.au/mdm/index.php?itemCode=" + item + "&page=description&catalogueversion=","utf-8");
    Gson gson = new Gson();
    return ResponseEntity.ok(gson.toJson(bundle));
  }

}
