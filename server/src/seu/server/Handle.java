package seu.server;

import java.io.IOException;
import java.net.Socket;

import seu.entityclass.Book;
import seu.entityclass.BorrowInfo;
import seu.entityclass.MainUser;
import seu.entityclass.StatusCourse;
import seu.entityclass.StatusUser;
import seu.entityclass.commodity;

/**
 * 
 */
public class Handle {

    private String[] s;
    @SuppressWarnings("unused")
	private int i;
    private DataBase db;
    private Task task;
    Socket socket = null;

    public Handle() {
    }

    public void infoHandle(String[] s, int i, Socket socket) {
        task = new Task(socket);
        this.s = s;
        this.i = i;
        this.socket = socket;
        db = new DataBase();
        firstHandle();
    }

    /**
     * 检索分类客户端发送命令模块的函数
     */
    private void firstHandle () {
        char zero = s[i - 1].charAt(0);
        char first = s[0].charAt(0);
        char second = s[0].charAt(1);

        switch (first) {

	        /**
	         * 用户登录模块指令处理
	         */
            case '0' :
                switch (second) {
                    case '0': {
                    	String sql = "select * from mainuserinfo where sNumber = '" + s[1] + "'";
                		MainUser mUser = new MainUser();
                		mUser = db.sqlQueryMainUser(sql);
                        if(zero == '1'){
                            if(mUser.getNumber() != null){
                                Server.clients.add(socket);
                                Server.SocketEntityMap.put(socket, mUser);
                                task.write(mUser);
                                Task.isKeep = true;
                            }else {
                                task.write(mUser);
                                Task.isKeep = false;
                            }


                        }else {
                            task.write(mUser);
                            try {
                                Task.isKeep = false;
                                socket.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }


                        break;
                    }
                    case '1':{

                        break;
                    }
                }
                break;

            /**
	         * 学籍管理模块指令处理
	         */
            case '1':
                switch (second) {
                	case '0':{
                		String sql = "select * from StatusInfo where sNumber = '" + s[1] + "'";
                		StatusUser sUser = new StatusUser();
                		sUser = db.sqlQueryStatusUser(sql);
                		task.write(sUser);
                		break;
                	}
                	case '1':{
                		String sql = "update StatusInfo set " + s[1] + " = '" + s[2] + "' where sNumber = '" + s[3] + "'";
                		String strsql = "select * from StatusInfo where sNumber = '" + s[3] + "'";
                		StatusUser sUser = new StatusUser();
                		sUser = db.sqlUpdateStatusUser(sql, strsql);
                		task.write(sUser);
                		break;
                	}
                	case '2':{
                		String sql = "update StatusInfo set Sex = '',Age = '',BirthDate = '',Nation = '',Politics = '',IDnumber = '',EduStartDate = '',College = '',Major = '',SchoolingLength = '',Education = '' where sNumber = '" + s[1] + "'";
                		String strsql = "select * from StatusInfo where sNumber = '" + s[1] + "'";
                		StatusUser sUser = new StatusUser();
                		sUser = db.sqlUpdateStatusUser(sql, strsql);
                		task.write(sUser);
                		break;
                	}
                }
                break;

            /**
	         * 选课模块指令处理
	         */
            case '2':
                switch (second) {
	                case '0':{
	            		String sql = "select * from Schedule where Course= '"+s[1]+"'";
	            		StatusCourse sCourse = new StatusCourse();
	            		sCourse = db.sqlQueryStatusCourse(sql);
	            		sCourse.flag=db.flag;
	            		task.write(sCourse);
	            		break;
	            	}
	            	case '1':{
	            		String sql = "Update Course set RestNum=? where Course =? ";
	            		StatusCourse sCourse = new StatusCourse();
	            		db.updateStatusCourse(sql, Integer.parseInt(s[1]),s[2]);
	            		task.write(sCourse);
	            		break;
	            	}
	            	case '2':{
	            		String sql = "insert into Schedule (id,course,credit,credithour,teacher) values(?,?,?,?,?)";
	            		StatusCourse sCourse = new StatusCourse();
	            		db.insertStatusCourse(sql, Integer.parseInt(s[1]), s[2], Integer.parseInt(s[3]), Integer.parseInt(s[4]), s[5]);
	            		task.write(sCourse);
	            		break;
	            	}
	            	case '3':{
	            		String tem=null;
	            		String sql="select * from Course ";
	            		tem=db.showcourse(sql);
	            		task.write(tem);
	            		break;
	            	}
	            	case '4':{
	            		String tem=null;
	            		String str="select * from Schedule ";
	            		tem=db.showcourseIfo(str);
	            		task.write(tem);
	            		break;
	            	}
	            	case '5':{
	            		String sql="delete from Schedule where Course = '"+s[1]+" '";
	            		StatusCourse course=new StatusCourse();
	            		db.deletecourseIfo(sql);
	            		task.write(course);
	            		break;
	            	}
	            	case '6':{
	            		StatusCourse course=new StatusCourse();
	            		db.subcourse(s[1]);
	            		task.write(course);
	            		break;
	            	}
	            	case '7':{
	            		String sql = "select * from Course where Course like '%"+ s[1]+"%'";
	            		StatusCourse sCourse = new StatusCourse();
	            		sCourse = db.sqlQueryStatusCourse(sql);
	            		sCourse.flag=db.flag;
	            		task.write(sCourse);
	            		break;
	            	}
	            	case '8':{
	            		String tem=null;
	            		String sql="select * from Course where Course like '%"+ s[1]+"%'";
	            		tem=db.showcourse(sql);
	            		task.write(tem);
	            		break;
	            	}
	            	case '9':{
	            		String sql = "select * from Course where Teacher like '%"+ s[1]+"%'";
	            		StatusCourse sCourse = new StatusCourse();
	            		sCourse = db.sqlQueryStatusCourse(sql);
	            		sCourse.flag=db.flag;
	            		task.write(sCourse);
	            		break;
	            	}
	            	case '*':{
	            		String tem=null;
	            		String sql="select * from Course where Teacher like '%"+ s[1]+"%'";
	            		tem=db.showcourse(sql);
	            		task.write(tem);
	            		break;
	            	}
                }
                break;

            /**
	         * 图书馆模块指令处理
	         */
            case '3':
                switch (second) {
                case '0'://  ?????????????í???é?è????jtable
            		String row=new String();
            		String sql="select * from borrow";
            		row=db.showbook(sql);
            		task.write(row);
            		break;
            	case '1'://??·?·¨?????????ù?????é  showbooks
            		String a=new String();
            		String b=new String();
            		a=this.s[1];
            		b=this.s[2];
            		String row1=new String();
            		String sql1=new String();
            		sql1="select * from bookInfo";

            		if(b!="")
            		{
            			sql1="select * from bookInfo where "+a+" = '"+b+"'";
            		}
            		row1=db.showbook(sql1);
            		try{
            			task.write(row1);
            			}catch(Exception ex){
            				ex.printStackTrace();
            			}
            		break;
            	case '2'://???????????ì?é???§????    check
            		String user,pass;
            		user=this.s[1];
            		pass=this.s[2];
            		String row2=new String();
            		String sql2="select * from userinfo where username = '"+user+"' and password  ='"+ pass+"'";
            		row2=db.check(sql2);
            		task.write(row2);
            		break;
            	case '3':
            		BorrowInfo temp=new BorrowInfo();
            		temp.setreadername(this.s[1]);
					temp.setbookname(this.s[2]);
					temp.setwriter(this.s[3]);
					temp.setborrowdate(this.s[4]);
					temp.setdateline(this.s[5]);
					temp.setprice(this.s[6]);
					temp.setbackdate(this.s[7]);
            		db.addborrow(temp);
            		break;
            	case '4':
            		String name,count;
            		name=this.s[1];
            		count=this.s[2];
            		System.out.println(""+name+"     "+count);
            		db.deletebook(name, count);
            		break;
            	case '5':
            		Book temp1 = new Book();
            		temp1.setbookname(this.s[1]);
    				temp1.setwriter(this.s[2]);
    				temp1.setprice(this.s[3]);
    				temp1.setcount(this.s[4]);
    				temp1.setdeadl(this.s[5]);
    				db.addbook(temp1);
            		break;
            	case '6'://admain login check
            		String admainuser=new String();
            		String adpass=new String();
            		admainuser=this.s[1];
            		adpass=this.s[2];
            		String row3=new String();
            		String sql3="select * from libadmain where username = '"+admainuser+"' and password  ='"+ adpass+"'";

            		row3=db.check(sql3);
            		System.out.println(row3);
            		task.write(row3);
            		break;
            	case '7':
            		String uname=new String();
            		String bname=new String();
            		bname=this.s[1];
            		uname=this.s[2];
            		db.deleteborrow(bname, uname);

            		break;
            	case '8':
            		String borrows=new String();
            		String username=new String();
            		username=this.s[1];
            		borrows=db.bookback(username);
            		task.write(borrows);
            		break;
            	case '9':
            		String name1,count1;
            		name1=this.s[1];
            		count1=this.s[2];
            		db.deletebook(name1, count1);

            		break;

                }
                break;

            /**
	         * 商店模块指令处理
	         */
            case '4':
                switch (second) {
	                case '0':{
	            		String Msql="select *from commodity";
	            		String result=new String();
	            		result=db.showcom(Msql);
	            		task.write(result);
	            		break;
	            	}
	            	case '1':{
	            		String ss="Update commodity set leftnum=? where comname =?";
	            		db.buy(ss,s[1],s[2]);
	            		task.write(s[1]);
	            		break;
	            	}
	            	case '2':{
	            		String Msql="select *from commodity";
	            		String result=new String();
	            		result=db.showcom(Msql);
	            		task.write(result);
	            		break;
	            	}
	            	case'3':{
	            		String sql = "select * from commodity where comname = '" + s[1] + "'";
	            		commodity com= new commodity();
	            		com = db.searchcom(sql);
	            		task.write(com);
	            		break;
	            	}
                }
                break;
        }
    }
}
