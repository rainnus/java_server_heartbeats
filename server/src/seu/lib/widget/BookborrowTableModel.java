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
	//����������ݿ�Ķ���
	String s;
	
	//���캯����������ʼ������ģ��
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
		
		//����*/
		col = new Vector();
		col.add("���");
		col.add("����");
		col.add("��������");	
		col.add("ͼ��۸�");
		col.add("����");
		col.add("���ʱ��");
		col.add("�ɽ�����");
		col.add("Ӧ��ʱ��");

		
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
		col.add("���");
		col.add("����");
		col.add("��������");	
		col.add("ͼ��۸�");
		col.add("����");
		col.add("���ʱ��");
		col.add("�ɽ�����");
		col.add("Ӧ��ʱ��");
    	
    }
	//��ȡ����
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
