package au.com.jaycar.mdm.entity;


public class RelatedProducts {
	
	  private Integer pk;

	  private String sourcecode;

	  public Integer getPk() {
		return pk;
	}

	public void setPk(Integer pk) {
		this.pk = pk;
	}

	public String getSourcecode() {
		return sourcecode;
	}

	public void setSourcecode(String sourcecode) {
		this.sourcecode = sourcecode;
	}

	public String getTargetcode() {
		return targetcode;
	}

	public void setTargetcode(String targetcode) {
		this.targetcode = targetcode;
	}

	public String getRelationtype() {
		return relationtype;
	}

	public void setRelationtype(String relationtype) {
		this.relationtype = relationtype;
	}

	private String targetcode;

	  private String relationtype;
}
