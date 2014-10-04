package seu.entityclass;

import java.io.Serializable;

/*
 * �û���¼ע��ʵ�幫����
 */

public class MainUser implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String Number;
	private String uName;
	private String passwd;
	private boolean isRegisted = false;
	
	public void setNumber(String number){
		this.Number = number;
	}
	
	public String getNumber(){
		return Number;
	}
	
	public void setUname(String name){
		this.uName = name;
	}
	
	public String getUname(){
		return uName;
	}
	
	public void setPasswd(String pd){
		this.passwd = pd;
	}
	
	public String getPasswd(){
		return passwd;
	}
	
	public void setIsRegisted(boolean ir){
		this.isRegisted = ir;
	}
	
	public boolean getIsRegisted(){
		return isRegisted;
	}
}
