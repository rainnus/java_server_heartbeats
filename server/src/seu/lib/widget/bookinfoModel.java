

package seu.lib.widget;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import seu.lib.soket.ClientSocket;
import seu.lib.view.Business;
import seu.lib.view.showBooks;

public class bookinfoModel extends AbstractTableModel{

	Vector row;
	Vector col;
	String result;
	Vector row1;
	int index=8;
	int totleSize;
	int size;
	String s;
	//����������ݿ�Ķ���
	
	//���캯����������ʼ������ģ��
	public bookinfoModel(String a,String b){
		
		
		
	row=new Vector();
	s=new String();
	
	result = new String();
	String msg="31";
	ClientSocket myClient = new ClientSocket();
	//System.out.println("11111111111111111111");
	
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
	//	System.out.println(sql);
		
	//	row = new Vector();
		//row=Business.showbook(sql);
		
		//����
		col = new Vector();
		col.add("���");
		col.add("����");
		col.add("����");	
		col.add("�۸�");
		col.add("��������");
		col.add("�ɽ�����");
		//col.add("");
		//col.add("");

		
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

	//��ȡ����
	public String getColumnName(int column) {
		return (String)this.col.get(column);
	}

	//�õ����ж�����
	public int getRowCount() {
		// TODO Auto-generated method stub
		return this.row.size();
	}

	//�õ����ж�����
	public int getColumnCount() {
		return this.col.size();
	}

	//�õ�ĳ��ĳ�е�����
	public Object getValueAt(int rowIndex, int columnIndex) {
		return ((Vector)this.row.get(rowIndex)).get(columnIndex);
	}
	public void UpdateModel(String a,String b){
		
		row=new Vector();
		s=new String();
		
		result = new String();
		String msg="31/"+a+"/"+b;
		ClientSocket myClient = new ClientSocket();
		System.out.println(msg+"update ����00000000000000");
		result=myClient.getStrMessage(msg);
		System.out.println("����Ѿ�����"+result);
		
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
		//	System.out.println(sql);
			
		//	row = new Vector();
			//row=Business.showbook(sql);
			
			//����
			col = new Vector();
			col.add("���");
			col.add("����");
			col.add("����");	
			col.add("�۸�");
			col.add("��������");
			col.add("�ɽ�����");
			//col.add("");
			//col.add("");

			
		
		
	}
	public void updatemodel()
	{
		row=new Vector();
		s=new String();
		
		result = new String();
		String msg="31";
		ClientSocket myClient = new ClientSocket();
		//System.out.println("11111111111111111111");
		
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
		//	System.out.println(sql);
			
		//	row = new Vector();
			//row=Business.showbook(sql);
			
			//����
			col = new Vector();
			col.add("���");
			col.add("����");
			col.add("����");	
			col.add("�۸�");
			col.add("��������");
			col.add("�ɽ�����");
			//col.add("");
			//col.add("");
	}
}


