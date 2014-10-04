package seu.entityclass;

import java.io.Serializable;

/*
 * 商店模块实体类
 */

public class commodity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3355730942004582133L;
	private String comname;
	private String price;
	private String leftnum;
	public String getComname() {
		return comname;
	}
	public  void setComname(String comname) {
		this.comname = comname;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getLeftnum() {
		return leftnum;
	}
	public void setLeftnum(String leftnum) {
		this.leftnum = leftnum;
	}
	

}
