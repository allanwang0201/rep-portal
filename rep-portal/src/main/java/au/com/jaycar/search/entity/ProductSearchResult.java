package au.com.jaycar.search.entity;


import org.apache.solr.client.solrj.beans.Field;

public class ProductSearchResult {

    @Field("id")
    private String id;

	@Field("code")
    private String code;

    @Field("proddescmarket")
    private String proddescmarket;


    @Field("copyretail")
    private String copyretail;

    @Field("imgmain")
    private String imgmain;


  @Field("prodbarcodeprimary")
    private String prodbarcodeprimary;

    public String getImgmain() {
		return imgmain;
	}

	public void setImgmain(String imgmain) {
		this.imgmain = imgmain;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getProddescmarket() {
		return proddescmarket;
	}

	public void setProddescmarket(String proddescmarket) {
		this.proddescmarket = proddescmarket;
	}

    public String getCopyretail() {
        return copyretail;
    }

    public void setCopyretail(String copyretail) {
        this.copyretail = copyretail;
    }

  public String getProdbarcodeprimary() {
    return prodbarcodeprimary;
  }

  public void setProdbarcodeprimary(String prodbarcodeprimary) {
    this.prodbarcodeprimary = prodbarcodeprimary;
  }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ProductSearchResult [id=");
        builder.append(id);
        builder.append(", code=");
        builder.append(code);
        builder.append(", prodbarcodeprimary=");
        builder.append(prodbarcodeprimary);
        builder.append(", proddescmarket=");
        builder.append(proddescmarket);
        builder.append(", copyretail=");
        builder.append(copyretail);
        builder.append("]");
        return builder.toString();
    }

}
