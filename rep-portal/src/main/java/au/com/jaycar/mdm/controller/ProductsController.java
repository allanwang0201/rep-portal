package au.com.jaycar.mdm.controller;

import javax.annotation.Resource;

import au.com.jaycar.mdm.entity.Productfeatures;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;

import au.com.jaycar.common.database.DataSourceContextHolder;
import au.com.jaycar.mdm.entity.Medias;
import au.com.jaycar.mdm.entity.ProductsWithBLOBs;
import au.com.jaycar.mdm.service.MdmMediasService;
import au.com.jaycar.mdm.service.MdmProductfeaturesService;
import au.com.jaycar.mdm.service.MdmProductsService;
import au.com.jaycar.mvc.interceptor.RequestLimit;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import java.io.IOException;
import java.util.List;

@Controller
public class ProductsController {


	@Autowired
	private MdmProductsService mdmProductsService;

	@Autowired
	private MdmMediasService mdmMediasService;

	@Autowired
	private MdmProductfeaturesService mdmProductfeaturesService;

	@RequestLimit
	@RequestMapping(value = "/mdm/products/code/{code}", method = RequestMethod.GET)
	public ResponseEntity getProducts(@PathVariable("code") String code) {

		DataSourceContextHolder.setDbType("mdmDataSource");

		ProductsWithBLOBs products = mdmProductsService.findByCode(code);
		if (products == null) {
			return new ResponseEntity("No Products found for code " + code, HttpStatus.NOT_FOUND);
		}


		//Productfeatures features =  mdmProductfeaturesService.findByProductPK(products.getPk());

		au.com.jaycar.local.entity.Products productVO = new au.com.jaycar.local.entity.Products();

		productVO.setCode(code);

		productVO.setPProddescmarket(products.getpProddescmarket());
		productVO.setPCopyretail(products.getpCopyretail());
		productVO.setPProdstatus(products.getpProdstatus());
		productVO.setpProdclasssales(products.getpProdclasssales());
		productVO.setPProdwarrext(products.getpProdwarrext() == null? "3" :products.getpProdwarrext().toString());
		productVO.setPProdwarrexclusion(products.getpProdwarrexclusion());
		productVO.setpProductapplication(products.getpProductapplication());
		productVO.setpKeysellingfeature1(products.getpKeysellingfeature1());
		productVO.setpKeysellingfeature2(products.getpKeysellingfeature2());
		productVO.setpKeysellingfeature3(products.getpKeysellingfeature3());
		productVO.setpKeysellingfeature4(products.getpKeysellingfeature4());
		productVO.setpKeysellingfeature5(products.getpKeysellingfeature5());
		productVO.setPProdbarcodeprimary(products.getpProdbarcodeprimary());

		HttpResponse response;

		final DefaultHttpClient httpClient = new DefaultHttpClient();
		final String phpwebservice = "http://oophpapp.live.jeg.domain/webservices/ws_replacementprod.php?PRODCODE=" + code;
		final HttpGet postRequest = new HttpGet(phpwebservice);

		String fullResponse = "";
		try {
			response = httpClient.execute(postRequest);
			fullResponse = new BasicResponseHandler().handleResponse(response);
		} catch (ClientProtocolException e) {

		} catch (IOException e) {

		}
		productVO.setPDrivermain(fullResponse);


		Medias medias =  mdmMediasService.findByPK(products.getpImgmain());
		productVO.setPImgmain(medias == null? "" :  medias.getpCelumid() == null? "" : medias.getpCelumid().toString());

		medias =  mdmMediasService.findByPK(products.getpImgfront());
		productVO.setPImgfront(medias == null? "" :  medias.getpCelumid() == null? "" : medias.getpCelumid().toString());

		medias =  mdmMediasService.findByPK(products.getpImgrear());
		productVO.setPImgrear(medias == null? "" :  medias.getpCelumid() == null? "" : medias.getpCelumid().toString());

		medias =  mdmMediasService.findByPK(products.getpImglside());
		productVO.setPImglside(medias == null? "" :  medias.getpCelumid() == null? "" : medias.getpCelumid().toString());

		medias =  mdmMediasService.findByPK(products.getpImgrside());
		productVO.setPImgrside(medias == null? "" :  medias.getpCelumid() == null? "" : medias.getpCelumid().toString());

		medias =  mdmMediasService.findByPK(products.getpImgrside());
		productVO.setPImgrside(medias == null? "" :  medias.getpCelumid() == null? "" : medias.getpCelumid().toString());

		medias =  mdmMediasService.findByPK(products.getpImgtop());
		productVO.setPImgtop(medias == null? "" :  medias.getpCelumid() == null? "" : medias.getpCelumid().toString());

		medias =  mdmMediasService.findByPK(products.getpImginsit());
		productVO.setPImginsit(medias == null? "" :  medias.getpCelumid() == null? "" : medias.getpCelumid().toString());

		medias =  mdmMediasService.findByPK(products.getpImgacc());
		productVO.setPImgacc(medias == null? "" :  medias.getpCelumid() == null? "" : medias.getpCelumid().toString());

		medias =  mdmMediasService.findByPK(products.getpImgpkd());
		productVO.setPImgpkd(medias == null? "" :  medias.getpCelumid() == null? "" : medias.getpCelumid().toString());


		medias =  mdmMediasService.findByPK(products.getpImgclose());
		productVO.setPImgclose(medias == null? "" :  medias.getpCelumid() == null? "" : medias.getpCelumid().toString());

		medias =  mdmMediasService.findByPK(products.getpImginuse());
		productVO.setPImginuse(medias == null? "" :  medias.getpCelumid() == null? "" : medias.getpCelumid().toString());


		medias =  mdmMediasService.findByPK(products.getpImgopen());
		productVO.setPImgopen(medias == null? "" :  medias.getpCelumid() == null? "" : medias.getpCelumid().toString());

		medias =  mdmMediasService.findByPK(products.getpImgclosed());
		productVO.setPImgclosed(medias == null? "" :  medias.getpCelumid() == null? "" : medias.getpCelumid().toString());

		medias =  mdmMediasService.findByPK(products.getpImglinediagram());
		productVO.setPImglinediagram(medias == null? "" :  medias.getpCelumid() == null? "" : medias.getpCelumid().toString());

		medias =  mdmMediasService.findByPK(products.getpImgfeatures());
		productVO.setPImgfeatures(medias == null? "" :  medias.getpCelumid() == null? "" : medias.getpCelumid().toString());

		//////////////////////////////////////////////////
    StringBuilder barcodes = new StringBuilder();
    List<Productfeatures> productfeatures = mdmProductfeaturesService.findByProductPK(products.getPk());
    for (Productfeatures feature: productfeatures) {
        barcodes.append(feature.getpStringvalue() + ' ');
    }
    productVO.setPCopywholesale(barcodes.toString());

		return new ResponseEntity(productVO, HttpStatus.OK);
	}


	@RequestMapping(value = "/mdm/product/img/{imgcode}", method = RequestMethod.GET)
	public ResponseEntity getProductImg(@PathVariable("code") String imgcode) {

		DataSourceContextHolder.setDbType("mdmDataSource");

		Medias medias =  mdmMediasService.findByPK(Long.parseLong(imgcode));
		String id = medias == null? "" :  medias.getpCelumid() == null? "" : medias.getpCelumid().toString();
		Gson gson = new Gson();
	    return ResponseEntity.ok(gson.toJson(id));

	}


	@RequestMapping(value = "/mdm/product/target/code/{code}", method = RequestMethod.GET)
	public ResponseEntity getRelatedProductImg(@PathVariable("code") String code) {

		DataSourceContextHolder.setDbType("mdmDataSource");

		ProductsWithBLOBs products = mdmProductsService.findByCode(code);
		if (products == null) {
			return new ResponseEntity("No Products found for code " + code, HttpStatus.NOT_FOUND);
		}

		au.com.jaycar.local.entity.Products productVO = new au.com.jaycar.local.entity.Products();

		productVO.setCode(code);
		productVO.setPProddescmarket(products.getpProddescmarket());
		Medias medias =  mdmMediasService.findByPK(products.getpImgmain());
		productVO.setPImgmain(medias == null? "" :  medias.getpCelumid() == null? "" : medias.getpCelumid().toString());

		return new ResponseEntity(productVO, HttpStatus.OK);
	}

}
