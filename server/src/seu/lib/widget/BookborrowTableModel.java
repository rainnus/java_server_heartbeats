package seu.lib.widget;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;


import seu.lib.soket.ClientSocket;
import seu.lib.view.Business;


public class BookborrowTableModel extends AbstractTableModel{
	
	Vector col;
	Vector row;
	String result;
	Vector row1;
	int index=8;
	int totleSize;
	int size;
	//定义操作数据库的东西
	String s;
	
	//构造函数：用来初始化数据模型
	public BookborrowTableModel(){
		row=new Vector();
		
		result = new String();
		String msg="30";
		ClientSocket myClient = new ClientSocket();
		System.out.println("11111111111111111111");
		result=myClient.getStrMessage(msg);
		System.out.println(result);
		
		while (result !="")
		{
			int i=1;
			row1=new Vector();
			for( i=1;i<=index;i++)
			{
				
				row1.add(check());
				
			}
			row.add(row1);
		}
		
		
		
		
	/*	String sql ="select * from borrow"; 
		
		
		System.out.println(sql);
		
		
		row=Business.showbook(sql);
		
		//列名*/
		col = new Vector();
		col.add("序号");
		col.add("读者");
		col.add("所借书名");	
		col.add("图书价格");
		col.add("作者");
		col.add("借出时间");
		col.add("可借天数");
		col.add("应还时间");

		
	}
	
	

    private String check () {
        totleSize = result.length();
        if (totleSize != 0) {
            size = result.indexOf("/");
            s = result.substring(0,size);
            if((size+1)!=totleSize)
            {result = result.substring(size+1,totleSize);}
            else{
            	result="";
            	return s;
            }
            
        }
        
        return s;
    }
    
    public void update(){
    	row=new Vector();
		
		result = new String();
		String msg="30";
		ClientSocket myClient = new ClientSocket();
	//	System.out.println("11111111111111111111");
		result=myClient.getStrMessage(msg);
		System.out.println(result);
		
		while (result !="")
		{
			int i=1;
			row1=new Vector();
			for( i=1;i<=index;i++)
			{
				
				row1.add(check());
				
			}
			row.add(row1);
		}
		
		
		
	
		col = new Vector();
		col.add("序号");
		col.add("读者");
		col.add("所借书名");	
		col.add("图书价格");
		col.add("作者");
		col.add("借出时间");
		col.add("可借天数");
		col.add("应还时间");
    	
    }
	//获取列名
	public String getColumnName(int column) {
		return (String)this.col.get(column);
	}


	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return this.row.size();
	}


	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return this.col.size();
	}


	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return ((Vector)this.row.get(rowIndex)).get(columnIndex);
	}


}
