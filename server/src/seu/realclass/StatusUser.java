package seu.realclass;

import java.io.Serializable;

/*
 * 学籍管理模块实体类
 */

public class StatusUser implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String number;
	private String passwd;
	
	private String uName;
	private String sex;
	
	private String age;
	private String birthDate;
	private String nation;
	private String politics;
	private String IDnumber;
	
	private String eduStartDate;
	private String college;
	private String major;
	private String schoolingLength;
	private String education;
	
	public void setNumber(String number){
		this.number = number;
	}
	
	public String getNumber(){
		return number;
	}
	
	public void setPasswd(String passwd){
		this.passwd = passwd;
	}
	
	public String getPasswd(){
		return passwd;
	}
	
	public void setuName(String uName){
		this.uName = uName;
	}
	
	public String getuName(){
		return uName;
	}
	
	public void setSex(String sex){
		this.sex = sex;
	}
	
	public String getSex(){
		return sex;
	}
	
	public void setAge(String age){
		this.age = age;
	}
	
	public String getAge(){
		return age;
	}
	
	public void setBirthDate(String birthDate){
		this.birthDate = birthDate;
	}
	
	public String getBirthDate(){
		return birthDate;
	}
	public void setNation(String nation){
		this.nation = nation;
	}
	
	public String getNation(){
		return nation;
	}
	public void setPolitics(String politics){
		this.politics = politics;
	}
	
	public String getPolitics(){
		return politics;
	}
	public void setIDnumber(String IDnumber){
		this.IDnumber = IDnumber;
	}
	
	public String getIDnumber(){
		return IDnumber;
	}
	public void setEduStartDate(String eduStartDate){
		this.eduStartDate = eduStartDate;
	}
	
	public String getEduStartDate(){
		return eduStartDate;
	}
	public void setCollege(String college){
		this.college = college;
	}
	
	public String getCollege(){
		return college;
	}
	
	public void setMajor(String major){
		this.major = major;
	}
	
	public String getMajor(){
		return major;
	}
	
	public void setSchoolingLength(String schoolingLength){
		this.schoolingLength = schoolingLength;
	}
	
	public String getSchoolingLength(){
		return schoolingLength;
	}
	
	public void setEducation(String education){
		this.education = education;
	}
	
	public String getEducation(){
		return education;
	}
	
}
