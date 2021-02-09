package au.com.jaycar.common.springdao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import au.com.jaycar.util.UtilFuns;

/*
 * 执行sql语句类
 * 调用方法：
 * 引入配置问题 
 * 
 */
public class SqlDao {

	private UtilFuns utilFuns = new UtilFuns();
    private JdbcTemplate jdbcTemplate;


	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public int findInt(String sql){

		int i = jdbcTemplate.queryForObject(sql, Integer.class);
		return i;
	}
	
	public int findInt(String sql, Object[] objs){

		int i = jdbcTemplate.queryForObject(sql, objs, Integer.class);
		return i;
	}
	
	//返回单值
	public String getSingleValue(String sql){

		StringBuffer sBuf = new StringBuffer();
		List jlist = jdbcTemplate.queryForList(sql);
		Iterator ite = jlist.iterator();
        while(ite.hasNext()){
            Map map = (Map)ite.next();
            for(Object o:map.keySet()){
            	sBuf.append(String.valueOf(map.get(o))).append(",");
            }
        }
        if(sBuf!=null && sBuf.length()>1){
        	sBuf.delete(sBuf.length()-1, sBuf.length());	//del last char
        }
		return sBuf.toString();
	}
	
	public String getSingleValue(String sql, Object[] objs){

		StringBuffer sBuf = new StringBuffer();
		List jlist = null;
		if(utilFuns.arrayValid(objs)){
			jlist = jdbcTemplate.queryForList(sql, objs);
		} else {
			jlist = jdbcTemplate.queryForList(sql);
		}
		Iterator ite = jlist.iterator();
		while(ite.hasNext()){
            Map map = (Map)ite.next();
            for(Object o:map.keySet()){
            	sBuf.append(String.valueOf(map.get(o))).append(",");
            }
        }
        if(sBuf!=null && sBuf.length()>1){
        	sBuf.delete(sBuf.length()-1, sBuf.length());	//del last char
        }
		return sBuf.toString();
	}
	
	public String[] toArray(String sql){

		String[] strs = null;
		List aList = this.executeSQL(sql);
		if(aList.size()>0){
			int count = aList.size();
			strs = new String[ count ];
			for(int i=0; i<count; i++) {
				strs[ i ] = String.valueOf(aList.get(i));
			}
			return strs;
		}else{
			return null;
		}
	}
	
	public List executeSQL(String sql){

		List<String> aList = new ArrayList();
		List jlist = jdbcTemplate.queryForList(sql);
		Iterator ite = jlist.iterator();
		while(ite.hasNext()){
			Map map = (Map)ite.next();
			for(Object o:map.keySet()){
				if(map.get(o.toString())==null){
					aList.add("");		//对象不存在时，写空串
				}else{
					aList.add(map.get(o.toString()).toString());
				}
			}
		}
		return aList;
	}
	
	public List executeSQL(String sql, Object[] objs){

		List aList = new ArrayList();
		List jlist = null;
		if(utilFuns.arrayValid(objs)){
			jlist = jdbcTemplate.queryForList(sql, objs);
		} else {
			jlist = jdbcTemplate.queryForList(sql);
		}
		Iterator ite = jlist.iterator();
		while(ite.hasNext()){
            Map map = (Map)ite.next();
            for(Object o:map.keySet()){
            	aList.add((String)map.get(o));
            }
        }
        return aList;
	}
	
	public List executeSQLForList(String sql, Object[] objs){

		List aList = new ArrayList();
		List jlist = null;
		if(utilFuns.arrayValid(objs)){
			jlist = jdbcTemplate.queryForList(sql, objs);
		} else {
			jlist = jdbcTemplate.queryForList(sql);
		}
		
		Iterator ite = jlist.iterator();
		
		List list;
		
		while(ite.hasNext()){
            Map map = (Map)ite.next();
            list = new ArrayList();
            
            for(Object o:map.keySet()){
            	list.add(map.get(o));
            }
            aList.add(list.toArray());
        }
		
		return aList;
	}
	
	public int updateSQL(String sql){

		int i = jdbcTemplate.update(sql);
		return i;
	}
	
	public int updateSQL(String sql, Object[] objs){

		int i = jdbcTemplate.update(sql, objs);
		return i;
	}
	
	public int[] batchSQL(String[] sql){

		return jdbcTemplate.batchUpdate(sql);
	}
}
