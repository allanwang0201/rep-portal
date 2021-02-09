package au.com.jaycar.warehouse.vo;

import java.math.BigDecimal;
import java.util.Date;
import au.com.jaycar.warehouse.entity.Porder;

public class PorderItemVO {

    private BigDecimal freeQty;

    private BigDecimal outQty;
    
    private Integer poitLine;
    
    private Date poitReqdate;
    
    private Date poitDuedate;
    
    private String poitAckref;
    
    private BigDecimal poitQtyord;
    
    private Porder porder;

	public BigDecimal getFreeQty() {
		return freeQty;
	}

	public void setFreeQty(BigDecimal freeQty) {
		this.freeQty = freeQty;
	}

	public BigDecimal getOutQty() {
		return outQty;
	}

	public void setOutQty(BigDecimal outQty) {
		this.outQty = outQty;
	}

	public Integer getPoitLine() {
		return poitLine;
	}

	public void setPoitLine(Integer poitLine) {
		this.poitLine = poitLine;
	}

	public Date getPoitReqdate() {
		return poitReqdate;
	}

	public void setPoitReqdate(Date poitReqdate) {
		this.poitReqdate = poitReqdate;
	}

	public Date getPoitDuedate() {
		return poitDuedate;
	}

	public void setPoitDuedate(Date poitDuedate) {
		this.poitDuedate = poitDuedate;
	}

	public String getPoitAckref() {
		return poitAckref;
	}

	public void setPoitAckref(String poitAckref) {
		this.poitAckref = poitAckref;
	}

	public BigDecimal getPoitQtyord() {
		return poitQtyord;
	}

	public void setPoitQtyord(BigDecimal poitQtyord) {
		this.poitQtyord = poitQtyord;
	}

	public Porder getPorder() {
		return porder;
	}

	public void setPorder(Porder porder) {
		this.porder = porder;
	}
}
