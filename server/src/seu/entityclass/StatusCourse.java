package seu.entityclass;

import java.io.Serializable;

/*
 * 选课模块实体类
 */

public class StatusCourse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String coursename;
	private String credit;
	private String credithour;
	private String teacher;
	private String restnum;
 public int flag=0;
	
	public void setcoursename(String coursename){
		this.coursename = coursename;
	}
	
	public String getcoursename(){
		return coursename;
	}
	
	public void setcredit(String credit){
		this.credit = credit;
	}
	
	public String getcredit(){
		return credit;
	}
	
	public void setucredithour(String credithour){
		this.credithour = credithour;
	}
	
	public String getcredithour(){
		return credithour;
	}
	
	public void setteacher(String teacher){
		this.teacher = teacher;
	}
	
	public String getteacher(){
		return teacher;
	}
	
	public void setrestnum(String restnum){
		this.restnum = restnum;
	}
	
	public String getrestnum(){
		return restnum;
	}

	public int getflag() {
		return 0;
	}
	
	
}
