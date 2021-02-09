package au.com.jaycar.search.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.SpellCheckResponse;
import org.apache.solr.client.solrj.response.SpellCheckResponse.Suggestion;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.CommonParams;
import org.apache.solr.common.params.DisMaxParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import au.com.jaycar.search.entity.ProductSearchResult;
import au.com.jaycar.mdm.entity.Medias;
import au.com.jaycar.mdm.service.MdmMediasService;

@Component
public class SolrjService {

	@Autowired
	MdmMediasService mdmMediasService;
    // 定义http的solr服务

	@Autowired
    private HttpSolrClient httpSolrClient;


/*    public void add(ProductSearchResult productSearchResult) throws Exception {
        this.httpSolrClient.addBean(productSearchResult); //添加数据到solr服务器
        this.httpSolrClient.commit(); //提交
    }

    public void delete(List<String> ids) throws Exception {
        this.httpSolrClient.deleteById(ids);
        this.httpSolrClient.commit(); //提交
    }*/

	 public long searchItemsNumber(String keywords) throws Exception {
	        SolrQuery solrQuery = new SolrQuery(); //构造搜索条件
	        solrQuery.setQuery(keywords); //搜索关键词

	        solrQuery.setStart(0);
	        solrQuery.setRows(0);
	        QueryResponse queryResponse = this.httpSolrClient.query(solrQuery);

	        SolrDocumentList rs = queryResponse.getResults();

	        long numFound = rs.getNumFound();

	        return numFound;

	 }

	public void syncMdm() throws Exception {

		SolrQuery query = new SolrQuery();
		query.set("qt", "/dataimport");
		query.set("command", "full-import");

		this.httpSolrClient.query(query);
	}

	 public Set<String> suggest(String word) {

	        Set<String> wordList = new HashSet<String>();
	        try {
	            SolrQuery query = new SolrQuery();
	            query.set("q",  word);// 查询的词
	            query.set("qt", "/suggest");// 请求到suggest中
//	          query.set("spellcheck.count", "");// 返回数量
	            QueryResponse rsp = this.httpSolrClient.query(query);

	            // 上面取结果的代码
	            SpellCheckResponse re = rsp.getSpellCheckResponse();// 获取拼写检查的结果集
	            if (re != null) {
	                for (Suggestion s : re.getSuggestions()) {
	                    List<String> list = s.getAlternatives();// 获取所有 的检索词
	                    for (String spellWord : list) {
	                    	wordList.add(spellWord);

	                    }
	                }
//	              String t = re.getFirstSuggestion(word);// 获取第一个推荐词
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return wordList;
	    }



	 public List<ProductSearchResult> searchMlt(String keywords){

         SolrQuery query = new SolrQuery();
         query.setRequestHandler("/mlt");
         query.setQuery("proddescmarket:" + keywords);
         //mlt在查询时，打开/关闭 MoreLikeThisComponent 的布尔值
         query.setParam("mlt", "true");
/*         //fl 需要返回的字段
         query.setParam("mlt.fl", "code,proddescmarket,copyretail");
         query.setParam("mlt.qf", "code^1 proddescmarket^0.8 copyretail^0.4");


         query.setParam("mlt.minwl", "10");
         query.setParam("mlt.count", "15");
         query.setParam("mlt.boost", "true");*/

         //mtl.fl 根据哪些字段判断相似度
         //query.setParam("mlt.fl", "ask");
         //mlt.mintf 最小分词频率，在单个文档中出现频率小于这个值的词将不用于相似判断
         //query.setParam("mlt.mintf", "1");
         //mlt.mindf 最小文档频率，所在文档的个数小于这个值的词将不用于相似判断
         //query.setParam("mlt.mindf", "1");

         QueryResponse queryResponse = null;
		try {

			queryResponse = this.httpSolrClient.query(query);
		} catch (SolrServerException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

         List<ProductSearchResult> productSearchResults = queryResponse.getBeans(ProductSearchResult.class);

	        for(ProductSearchResult result : productSearchResults)
	        {
	        	if(!result.getImgmain().isEmpty()){
		        	Medias medias =  mdmMediasService.findByPK(Long.parseLong(result.getImgmain()));
		    		String id = medias == null? "" :  medias.getpCelumid() == null? "" : medias.getpCelumid().toString();
		    		result.setImgmain(id);
	        	}
	        }

	        return productSearchResults;

	 }

	/**
	 * @param keywords
	 * @param sort
	 * @param page
	 * @param rows
	 * @return
	 * @throws Exception
	 */
    public List<ProductSearchResult> search(String keywords, String sort, Integer page, Integer rows) throws Exception {

        SolrQuery solrQuery = new SolrQuery(); //构造搜索条件
        //solrQuery.setQuery(keywords); //搜索关键词
/*        'Relevance',
	      'Name(asc)',
	      'Name(desc)',
	      'Price(lowest first)',
	      'Price(highest first)',
	      'Most Recent'*/
        solrQuery.set(CommonParams.Q, keywords);
        solrQuery.set("defType", "edismax");
        solrQuery.set(DisMaxParams.QF, "code^1 prodbarcodeprimary^0.9 barcodes^0.9 proddescmarket^0.8 copyretail^0.4");
        solrQuery.set("q.op", "AND");
        if(sort != null && "Name(desc)".equals(sort))
        	solrQuery.setSort("productname", ORDER.desc);
        if(sort != null && "Name(asc)".equals(sort))
        	solrQuery.setSort("productname", ORDER.asc);
        if(sort != null && "Most Recent".equals(sort))
        	solrQuery.setSort("modifiedTS", ORDER.desc);
      /*  //是否需要高亮
        boolean isHighlighting = !StringUtils.equals("*", keywords) && StringUtils.isNotEmpty(keywords);

        if (isHighlighting) {
            // 设置高亮
            solrQuery.setHighlight(true); // 开启高亮组件
            solrQuery.addHighlightField("code");// 高亮字段
            solrQuery.setHighlightSimplePre("");// 标记，高亮关键字前缀
            solrQuery.setHighlightSimplePost("");// 后缀
        }*/
        // 设置分页 start=0就是从0开始，，rows=5当前返回5条记录，第二页就是变化start这个值为5就可以了。
        solrQuery.setStart((Math.max(page, 1) - 1) * rows);
        solrQuery.setRows(rows);
        // 执行查询
        QueryResponse queryResponse = this.httpSolrClient.query(solrQuery);

        List<ProductSearchResult> productSearchResults = queryResponse.getBeans(ProductSearchResult.class);
       /* if (isHighlighting) {
            // 将高亮的标题数据写回到数据对象中
            Map<String, Map<String, List<String>>> map = queryResponse.getHighlighting();
            for (Map.Entry<String, Map<String, List<String>>> highlighting : map.entrySet()) {
                for (ProductSearchResult reslut : productSearchResults) {
                    if (!highlighting.getKey().equals(reslut.getId())) {
                        continue;
                    }
                    reslut.setCode((StringUtils.join(highlighting.getValue().get("code"), "")));
                    break;
                }
            }
        }*/

        for(ProductSearchResult result : productSearchResults)
        {
        	if(!result.getImgmain().isEmpty()){
	        	Medias medias =  mdmMediasService.findByPK(Long.parseLong(result.getImgmain()));
	    		String id = medias == null? "" :  medias.getpCelumid() == null? "" : medias.getpCelumid().toString();
	    		result.setImgmain(id);
        	}
        }

        return productSearchResults;
    }

/*   public List<ProductSearchResult> searchSortByNameDesc(String keywords, Integer page, Integer rows) throws Exception {

        SolrQuery solrQuery = new SolrQuery(); //构造搜索条件
        //solrQuery.setQuery(keywords); //搜索关键词


        solrQuery.set(CommonParams.Q, keywords);
        solrQuery.set("defType", "edismax");
        solrQuery.set(DisMaxParams.QF, "code^1 proddescmarket^0.8 copyretail^0.5");

        solrQuery.setSort("proddescmarket", ORDER.desc);
        //是否需要高亮
        boolean isHighlighting = !StringUtils.equals("*", keywords) && StringUtils.isNotEmpty(keywords);

        if (isHighlighting) {
            // 设置高亮
            solrQuery.setHighlight(true); // 开启高亮组件
            solrQuery.addHighlightField("code");// 高亮字段
            solrQuery.setHighlightSimplePre("");// 标记，高亮关键字前缀
            solrQuery.setHighlightSimplePost("");// 后缀
        }
        // 设置分页 start=0就是从0开始，，rows=5当前返回5条记录，第二页就是变化start这个值为5就可以了。
        solrQuery.setStart((Math.max(page, 1) - 1) * rows);
        solrQuery.setRows(rows);
        // 执行查询
        QueryResponse queryResponse = this.httpSolrClient.query(solrQuery);

        List<ProductSearchResult> productSearchResults = queryResponse.getBeans(ProductSearchResult.class);
        if (isHighlighting) {
            // 将高亮的标题数据写回到数据对象中
            Map<String, Map<String, List<String>>> map = queryResponse.getHighlighting();
            for (Map.Entry<String, Map<String, List<String>>> highlighting : map.entrySet()) {
                for (ProductSearchResult reslut : productSearchResults) {
                    if (!highlighting.getKey().equals(reslut.getId())) {
                        continue;
                    }
                    reslut.setCode((StringUtils.join(highlighting.getValue().get("code"), "")));
                    break;
                }
            }
        }

        for(ProductSearchResult result : productSearchResults)
        {
        	if(!result.getImgmain().isEmpty()){
	        	Medias medias =  mdmMediasService.findByPK(Long.parseLong(result.getImgmain()));
	    		String id = medias == null? "" :  medias.getpCelumid() == null? "" : medias.getpCelumid().toString();
	    		result.setImgmain(id);
        	}
        }

        return productSearchResults;
    }*/
}
