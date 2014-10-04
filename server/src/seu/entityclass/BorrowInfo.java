package seu.entityclass;

/*
 * 图书馆模块实体类
 */

public class BorrowInfo {
	public String readername=null;
	public String readerID=null;
	public String bookname=null;
	public String borrowdate=null;
	public String dateline;
	public String backdate=null;
	public String price;
	public String writer;
	public void setprice(String bprice){
		this.price=bprice;
	}
	public String getprice(){
		return price;
	}
	public void setwriter(String bwriter){
		this.writer=bwriter;
	}
	public String getwriter(){
		return writer;
	}
	public void setreadername(String name){
		this.readername=name;
	}
	public String getreadername(){
		return readername;
	}
	public void setreaderID(String id){
		this.readerID=id;
		
	}
	public String getreaderID(){
		return readerID;
	}
	public void setbookname(String bkname){
		this.bookname=bkname;
	}
	public String getbookname(){
		return bookname;
	}
	public void setborrowdate(String time){
		this.borrowdate=time;
	}
	public String getborrowdate(){
		return borrowdate;
	}
	public void setdateline(String datlin){
		this.dateline=datlin;
	}
	public String getdateline(){
		return dateline;
	}
	public void setbackdate(String bdate){
		this.backdate=bdate;
	}
	public String getbackdate(){
		return backdate;
	}

}
