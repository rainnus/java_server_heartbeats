package seu.entityclass;
/*
 * 图书馆模块实体类
 */
public class Book extends User{
	public String ID;
	public String bookname;
	public String writer;
	public String price;
	public String count;
	public String deadline;
	public void setID(String id){
		this.ID=id;
	}
	public String getID(){
		return ID;
	}
	public void setbookname(String bname){
		this.bookname=bname;
		
	}
	public String getbookname(){
		return bookname;
	}
	public void setwriter(String Writer){
		this.writer=Writer;
	}
	public String getwriter(){
		return writer;
	}
	public void setprice(String Price){
		this.price=Price;
	}
	public String getprice(){
		return price;
	}
	public void setcount(String Count){
		this.count=Count;
	}
	public String getcount(){
		return count;
	}
	public void setdeadl(String Deadline){
		this.deadline=Deadline;
	}
	public String getdeadl(){
		return deadline;
	}
	
}
