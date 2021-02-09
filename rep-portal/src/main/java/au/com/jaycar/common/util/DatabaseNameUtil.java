package au.com.jaycar.common.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class DatabaseNameUtil {

	

	@Autowired
	private JdbcTemplate jdbcTemplate;   
	
	public String getDatabaseName(String code, String branch, String warehouse){
		
		if ((Long.valueOf(branch) >= 300 && Long.valueOf(branch) < 400) || (warehouse == "NSW" && branch=="")){//Checks for Warehouse see if item is in DCRTA
			warehouse = checkWarehouse(code).trim();
		}
		
		String database = null;
		

		switch (warehouse)
		{
			case "NSW": database = "DCNSW";
			break;

			case "DCRTA":
			database = "DCRTA";
			break;
			
			case "AKL":
			database = "DCAUK";
			break;		

		}
		return database;
		
	}
	
	
	private String checkWarehouse(String code){//Checks for Warehouse see if item is in DCRTA
		
		
		String warehouse = null;
		
		String sql = "SELECT a.cmco_product, a.cmco_loc FROM mdst.cmcomprod a left join MDST.cmtprod b on a.cmco_product = b.cmt_intern WHERE b.cmt_table= 'EDI-RTM' AND a.cmco_company = 'rtau' AND b.cmt_exterp = ? ";
		
		
		List rows  = this.jdbcTemplate.queryForList(sql, new Object[]{code}, new int[]{java.sql.Types.CHAR});
		
		
               
		for(int i=0;i<rows.size();i++){
			Map map = (Map) rows.get(i);
		
			warehouse = (String) map.get("cmco_loc");
		}
     
	
		//echo $warehouse;
		if(warehouse!="DCRTA"){
			warehouse="NSW";
		}
		
		return warehouse;
	}

	
	public String tranferRtmCode(String code, String branch){
		//If rtm branch code, we need to map to electus product code.
		Long rtmProd = 0l;
		if(Long.valueOf(branch) >= 300 && Long.valueOf(branch) < 400) {
			rtmProd = 1l;
			String  rtmProdCode = code;
			
			code = getRtmProd(rtmProdCode);
		}
		return code;
	}
	
	private String getRtmProd(String rtmProdCode) {
		// TODO Auto-generated method stub
		return rtmProdCode;
	}

}
