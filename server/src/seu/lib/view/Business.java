package seu.lib.view;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import seu.lib.bz.Book;
import seu.lib.bz.BorrowInfo;
import seu.lib.bz.User;


public class Business {


     static String dbpath = new File("").getAbsolutePath().replace('\\', '/') + "/vlibrary.accdb";
	 static String url = "jdbc:odbc:DRIVER={Microsoft Access Driver (*.mdb, *.accdb)};DBQ="+dbpath; 
	 String user="";
	 String passwd=null;
	 private static Connection conn = null;   
	 public   Business()  
	 {
		 try { 
			     Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");         // �������ݿ�ĵ�ַ          //	
				   
			              // ���������ݿ������        
					  conn = DriverManager.getConnection(url,user,passwd);        
					  System.out.println("�ɹ����ӵ����ݿ⣺" + conn);       
			 }       catch(Exception ex)
					 {        
						 System.out.println("����ʧ�ܣ�" + ex);    
					 }
			
	 }
	 public static  String check(String Msql){
		   // PreparedStatement ps=null;
		 	
		    Statement st;
			ResultSet rs=null;	
			List<User> users = new ArrayList<User>();
			String temp = null;
		 try{
			 if(conn==null) new Business();//
			 
			  conn=DriverManager.getConnection(url);		
			  st=(Statement)conn.createStatement();
			  System.out.println("j111111111111111"+temp);
			  System.out.println(Msql);
			  rs=st.executeQuery(Msql);	
				 System.out.println("fdsafdsf"+rs);
				
				//ִ�в���
				//rs=ps.executeQuery(Msql);
				 while (rs.next()) {
					 System.out.println("+++++++++++++"+rs);
					 User User = new User();
					 User.setUname(rs.getString("username"));
					 User.setPasswd(rs.getString("password"));
					 
					 System.out.println("*****************411"+User.uname);
					 users.add(User);
					 temp= User.uname;//ʹtemp����
				 }
				
	        }catch (Exception e) {		
			    e.printStackTrace();
			   
	             }
		 finally{
				//�ر���Դ
				try {
					if(rs!=null)
						rs.close();
					if(conn!=null)
						conn.close();
					
				} catch (Exception e) {
					
				}
		 }
		 return temp;
	      }   
	 
	// @SuppressWarnings("unchecked")
	public static Vector showbook(String Msql){
		 Vector Books=new Vector();
		 Statement st = null;
		 ResultSet rs=null;
		 try{
			 if(conn==null) new Business();//
			 
			  conn=DriverManager.getConnection(url);		
			 st=(Statement)conn.createStatement();
			  System.out.println(Msql);
			 
			  rs=st.executeQuery(Msql);
			  	while(rs.next())
				{			
			  	 
					Vector u = new Vector();
					u.add(rs.getString(1));
					u.add(rs.getString(2));
					u.add(rs.getString(3));
					u.add(rs.getString(4));
					u.add(rs.getString(5));
					u.add(rs.getString(6));
					u.add(rs.getString(7));
					u.add(rs.getString(8));
 
				//	System.out.println("����bookinfo���");
					Books.add(u);
					// System.out.println("��ʼ����bookinfo");
					
				}	
				
		
		 }catch (Exception e) {		
		    e.printStackTrace(); }
		 
		 
		 return Books;
	 }
	 		
	public static void addbook(Book newbook){
		String name,wri,pric,cout,date;
		name=newbook.getbookname();
		String sqlsherch="select * from bookInfo where bookname='"+name+"'";
		String sql;
		Vector test=null;
		test=showbook(sqlsherch);
		if(test.size()>=1)//֤�������Ѵ���
		{
			sql="update bookInfo set count=count+1 where bookname='"+name+"'";
			
		}
		
		else
		{
			wri=newbook.getwriter();
			pric=newbook.getprice();
			cout=newbook.getcount();
			date=newbook.getdeadl();
			sql="INSERT INTO bookInfo (bookname,writer,price,count,deadline) VALUES('"+name+"','"+wri+"','"+pric+"','"+cout+"','"+date+"')";
		}
			Statement st = null;
			 ResultSet rs=null;
			 try{
				 if(conn==null) new Business();//
				 
				  conn=DriverManager.getConnection(url);		
				 st=(Statement)conn.createStatement();
				  System.out.println(sql);
				 
				  st.executeUpdate(sql);
			 }catch (Exception e) {		
				    e.printStackTrace(); }
		
		
	}
	@SuppressWarnings("null")
	public static List<BorrowInfo> bookback(String username){
		String sql="select * from borrow where reader_name ='"+username+"'";
		List<BorrowInfo> borrows=new ArrayList<BorrowInfo>();
		 Statement st = null;
		 ResultSet rs=null;
		 try{
			 if(conn==null) new Business();//
			 
			  conn=DriverManager.getConnection(url);		
			 st=(Statement)conn.createStatement();
			  System.out.println(sql);
			 
			  rs=st.executeQuery(sql);
			  	while(rs.next())
				{			
			  	 
					BorrowInfo u = new BorrowInfo();
					u.setreadername(rs.getString("reader_name"));

					u.setprice(rs.getString("book_price"));
					u.setwriter(rs.getString("book_writer"));
					u.setbookname(rs.getString("book_name"));
					u.setdateline(rs.getString("dateline"));
					u.setborrowdate(rs.getString("borrow_date"));
					u.setbackdate(rs.getString("back_date"));

				
						borrows.add(u);
						System.out.println("��ӳɹ�"+u.getbookname());
	
					
					
				}	
				
		 }catch (Exception e) {		
		    e.printStackTrace(); }
		 return borrows;
	}
	public static void addborrow(BorrowInfo a){
		String reader,writer,book,borrdat,backdat,datline,price;
		reader=a.getreadername();
		writer=a.getwriter();
		book=a.getbookname();
		price=a.getprice();
		datline=a.getdateline();
		borrdat=a.getborrowdate();
		backdat=a.getbackdate();
		String sql="INSERT INTO borrow (reader_name,book_name,book_price,book_writer,borrow_date,dateline,back_date) VALUES('"+reader+"','"+book+"','"+price+"','"+writer+"','"+borrdat+"','"+datline+"','"+backdat+"')";
		Statement st = null;
		 try{
			 if(conn==null) new Business();//
			 
			  conn=DriverManager.getConnection(url);		
			 st=(Statement)conn.createStatement();
			  System.out.println(sql);
			  st.executeUpdate(sql);
		 }catch (Exception e) {		
			    e.printStackTrace(); }
		
	}
	
	public static void deleteborrow(String bname,String uname){
		// TODO Auto-generated method stub
		String sql="delete from borrow where book_name='"+bname+"'and reader_name='"+uname+"'";
		Statement st = null;
		 try{
			 if(conn==null) new Business();//
				 
			 conn=DriverManager.getConnection(url);		
			 st=(Statement)conn.createStatement();
			 System.out.println(sql);
			  st.executeUpdate(sql);
			 }catch (Exception e) {		
			   e.printStackTrace(); }
				
		
	}
	public static void deletebook(String name,String count){//����Աɾ��ͼ�����ͼ����ĺ����ͼ����������
		
		String sql;
		int c=Integer.parseInt(count);//�ж�ѡ��ͼ�����������
		if(c==1)//���������ֻ��һ�� ��ֱ��ɾ��
		{
			
			sql="delete from bookInfo where bookname ='"+name+"'";
			Statement st = null;
			 try{
				 if(conn==null) new Business();//
				 
				  conn=DriverManager.getConnection(url);		
				 st=(Statement)conn.createStatement();
				  System.out.println(sql);
				  st.executeUpdate(sql);
			 }catch (Exception e) {		
				    e.printStackTrace(); }
		}
		else{//����ж౾ͬ����ͼ�飬�ͽ�������1
			String s =Integer.toString(c-1);
			sql="update bookInfo set count='"+s+"'where bookname='"+name+"'";
			Statement st = null;
			 try{
				 if(conn==null) new Business();//
				 
				  conn=DriverManager.getConnection(url);		
				 st=(Statement)conn.createStatement();
				  System.out.println(sql);
				  st.executeUpdate(sql);
			 }catch (Exception e) {		
				    e.printStackTrace(); }
			
		}
	}

		     
}
