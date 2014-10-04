package seu.server;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import seu.entityclass.*;


public class DataBase {

    private Connection con = null;
    private PreparedStatement stat = null;
    private ResultSet result = null;
    int flag=0;
    
    //?????????????
    DataBase() {
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            String dbPath = new File("").getAbsolutePath().replace('\\', '/')+"/total.accdb";
            con = DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ=" + dbPath);

        }  catch (SQLException e) {
        	JOptionPane.showMessageDialog(null,e.toString());
        } catch (ClassNotFoundException e) {
        	JOptionPane.showMessageDialog(null,e.toString());
		}
    }
    
    /**
     * ?????????DAO????
     */
    public MainUser sqlQueryMainUser(String sql){
		MainUser mUser = new MainUser();
    	try {
    		stat=con.prepareStatement(sql);			
    		//??§Ó???
    		result = stat.executeQuery();
    		try {
    			while (result.next()) {
    				
    				mUser.setNumber(result.getString("sNumber"));
    				mUser.setPasswd(result.getString("Passwd"));
    				mUser.setUname(result.getString("uName"));
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, e.toString());
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
    	return mUser;
	}

    
    /**
     * ??????????DAO????
     */
    public StatusUser sqlQueryStatusUser(String sql){
    	StatusUser sUser = new StatusUser();
    	try {
    		stat=con.prepareStatement(sql);			
    		//??§Ó???
    		result = stat.executeQuery();
    		try {
    			while (result.next()) {
    				
    				sUser.setNumber(result.getString("sNumber"));
    				sUser.setPasswd(result.getString("Passwd"));
    				sUser.setuName(result.getString("uName"));
    				sUser.setSex(result.getString("Sex"));
    				sUser.setAge(result.getString("Age"));
    				sUser.setBirthDate(result.getString("BirthDate"));
    				sUser.setNation(result.getString("Nation"));
    				sUser.setPolitics(result.getString("Politics"));
    				sUser.setIDnumber(result.getString("IDnumber"));
    				sUser.setEduStartDate(result.getString("EduStartDate"));
    				sUser.setCollege(result.getString("College"));
    				sUser.setMajor(result.getString("Major"));
    				sUser.setSchoolingLength(result.getString("SchoolingLength"));
    				sUser.setEducation(result.getString("Education"));
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, e.toString());
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
    	return sUser;
    }

    public StatusUser sqlUpdateStatusUser(String sql, String str){
    	StatusUser sUser = new StatusUser();
    	try {
    		stat = con.prepareStatement(sql);
    		stat.executeUpdate();
    		
    		sUser = sqlQueryStatusUser(str);
    		
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
    	return sUser;
    }
    
    /**
     * ??????DAO????
     */
    public StatusCourse sqlQueryStatusCourse(String sql){
    	StatusCourse Course = new StatusCourse();
    	try {
    		stat=con.prepareStatement(sql);			
    		result = stat.executeQuery();
    		try {
    			while (result.next()) {
    				
    				Course.setcoursename(result.getString("Course"));
    				Course.setcredit(result.getString("Credit"));
    				Course.setucredithour(result.getString("CreditHour"));
    				Course.setteacher(result.getString("Teacher"));
    				flag=1;
    				continue;
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, e.toString());
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
    	return Course;
    }
   
    public StatusCourse selectStatusCourse(String str,String sql,String course){
    	StatusCourse Course = new StatusCourse();
    	try {
    		stat=con.prepareStatement(sql+course);			
    		result = stat.executeQuery();
    		try {
    			while (result.next()) {
    				if(result.getString(course).equals(course))
    				{
    					flag=1;
    					continue;
    				}
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, e.toString());
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
    	Course=sqlQueryStatusCourse(str);
    	return Course;
    }
    
    public void insertStatusCourse(String sql,int id,String course,int credit,int credithour,String teacher){
    	try {
    		stat = con.prepareStatement(sql);
    		stat.setInt(1, id);
    		stat.setString(2, course);
    		stat.setInt(3, credit);
    		stat.setInt(4, credithour);
    		stat.setString(5,teacher);
    		stat.executeUpdate();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
    }
    
    public void updateStatusCourse(String sql,int restnum,String str){
    	try {
    		stat=con.prepareStatement(sql);			
    		stat.setInt(1, restnum);
    		stat.setString(2, str);
			stat.executeUpdate();
    		
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
    }
    
    public String showcourse(String sql){
    	String total="";
    	try {
    		stat=con.prepareStatement(sql);
			result=stat.executeQuery();
			while(result.next())
	    	{
	    		String s=result.getString(2)+"/"+result.getString(3)+"/"+result.getString(4)+"/"+result.getString(5)+"/"+result.getString(6)+"/";
	    		total+=s;
	    	}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
    	return total;
    }
    
    public String showcourseIfo(String sql){
    	String total="";
    	try {
    		stat=con.prepareStatement(sql);
			result=stat.executeQuery();
			while(result.next())
	    	{
	    		String s=result.getString(2)+"/"+result.getString(3)+"/"+result.getString(4)+"/"+result.getString(5)+"/";
	    		total+=s;
	    	}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
    	return total;
    }
    
    public void deletecourseIfo(String sql){
    	try {
			stat = con.prepareStatement(sql);
			stat.executeUpdate();
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
    }
    
    public void subcourse(String str){
    	int num=0;
    	try {
    		String sql=" select * from Course where Course =?";
			stat=con.prepareStatement(sql);
			stat.setString(1, str);					
            result=stat.executeQuery();
            while(result.next())
			{
				num=result.getInt("RestNum");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
    	String ss="Update Course set RestNum=? where Course =?";
        try {
			stat=con.prepareStatement(ss);
			stat.setInt(1, (num+1));
			stat.setString(2, str);
			stat.executeUpdate();
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, e1.toString());
		}
    }
    
    /**
     * ?????????DAO????
     */
    public  String showbook(String Msql){
    		 String totle = "";
    		 try{
    			stat=con.prepareStatement(Msql);
    			result=stat.executeQuery();
			  	while(result.next()){			
			  		String s = result.getString(1)+"/"+result.getString(2)+"/"+result.getString(3)+"/"+result.getString(4)+"/"+
			  				result.getString(5)+"/"+result.getString(6)+"/"+result.getString(7)+"/"+result.getString(8)+"/";
			  		totle += s;
				}	
    		 }catch (Exception e) {		
    			 JOptionPane.showMessageDialog(null, e.toString());
    		 }
    		 return totle;
    }
    
    public String check(String Msql){
			List<User> users = new ArrayList<User>();
			String temp ="false";
    		try{
    			 stat=con.prepareStatement(Msql);
    			 result=stat.executeQuery();
				 while (result.next()) {
					 User User = new User();
					 User.setUname(result.getString("username"));
					 User.setPasswd(result.getString("password"));
					 users.add(User);
					 temp= "true";
				 }
    	    }catch (Exception e) {		
    	    	JOptionPane.showMessageDialog(null, e.toString());
    	    }
    		 return temp;
    }   
        
    public void addborrow(BorrowInfo a){
		String reader,writer,book,borrdat,backdat,datline,price;
		reader=a.getreadername();
		writer=a.getwriter();
		book=a.getbookname();
		price=a.getprice();
		datline=a.getdateline();
		borrdat=a.getborrowdate();
		backdat=a.getbackdate();
		String sql="INSERT INTO borrow (reader_name,book_name,book_price,book_writer,borrow_date,dateline,back_date) VALUES('"+reader+"','"+book+"','"+price+"','"+writer+"','"+borrdat+"','"+datline+"','"+backdat+"')";
		 try{
			 stat=con.prepareStatement(sql);
			 stat.executeUpdate();
		 }catch (Exception e) {		
			 JOptionPane.showMessageDialog(null, e.toString());
		 }
	}
        
    public void addbook(Book newbook){
		String name,wri,pric,cout,date;
		name=newbook.getbookname();
		String sqlsherch="select * from bookInfo where bookname='"+name+"'";
		String sql;
		String test=null;
		test=showbook(sqlsherch);
		if(test.length()>=1)//????????????
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
		 try{
			 stat=con.prepareStatement(sql);
			 stat.executeUpdate();
		 }catch (Exception e) {		
			 JOptionPane.showMessageDialog(null, e.toString());
		 }
	}
        
    public void deletebook(String name,String count){//?????????????????????????????????
		String sql;
		int c=Integer.parseInt(count);//?§Ø???????????????
		if(c==1)//?????????????? ????????
		{
			sql="delete from bookInfo where bookname ='"+name+"'";
			try{
				stat=con.prepareStatement(sql);
				stat.executeUpdate();
			}catch (Exception e) {		
				JOptionPane.showMessageDialog(null, e.toString());
			}
		}
		else{//????§Ø???????ï…?????????1
			String s =Integer.toString(c-1);
			sql="update bookInfo set count='"+s+"'where bookname='"+name+"'";
			try{
				stat=con.prepareStatement(sql);
				stat.executeUpdate();
			}catch (Exception e) {		
				 JOptionPane.showMessageDialog(null, e.toString());
			}
		}
    }

    public void deleteborrow(String bname,String uname){
    	String sql="delete from borrow where book_name='"+bname+"'and reader_name='"+uname+"'";
    	try{
    		stat=con.prepareStatement(sql);
			stat.executeUpdate();
    	}catch (Exception e) {		
    		JOptionPane.showMessageDialog(null, e.toString());
    	}
    }
    
    public String bookback(String username){
    	String sql="select * from borrow where reader_name ='"+username+"'";
    	String borrows=new String();
    	try{
    		stat=con.prepareStatement(sql);
    		result=stat.executeQuery();
		  	while(result.next()){			
				String s = result.getString(1)+"/"+result.getString(2)+"/"+result.getString(3)+"/"+result.getString(4)+"/"+
		  				result.getString(5)+"/"+result.getString(6)+"/"+result.getString(7)+"/"+result.getString(8)+"/";
		  		borrows += s;
			}	
    	}catch (Exception e) {		
    		JOptionPane.showMessageDialog(null, e.toString());
    	}
    	return borrows;
    }

    /**
     * ??????DAO????
     */
    public String showcom(String Msql)
    {
    		 String totle = "";
    		 try{
    			 stat=con.prepareStatement(Msql);
    			 result=stat.executeQuery();
    			  	while(result.next()){			
    			  		String s = result.getString(2)+"/"+result.getString(3)+"/"+result.getString(4)+"/";
    			  		totle += s;
    			  		System.out.println(totle);
    				}
    		 }catch(Exception e){
    			 JOptionPane.showMessageDialog(null, e.toString());
    		 }
    		 return totle;
    }
    
    public void buy(String Msql,String str,String i)
    {
    	try {
    		stat=con.prepareStatement(Msql);
		    int j=Integer.parseInt(i)-1;
		    stat.setInt(1, j);
		    stat.setString(2, str);
		    stat.executeUpdate();
		} catch (SQLException e3) {
			JOptionPane.showMessageDialog(null, e3.toString());
		}
    }
    		 

	public commodity searchcom(String sql){
		commodity com = new commodity();
		try {
			stat=con.prepareStatement(sql);			
			result = stat.executeQuery();
			try {
				while (result.next()) {
					com.setComname(result.getString("comname"));
					com.setPrice(result.getString("price"));
					com.setLeftnum(result.getString("leftnum"));
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, e.toString());
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
		return com;
	}
}

	