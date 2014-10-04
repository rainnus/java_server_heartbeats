package seu.lib.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import seu.lib.bz.Book;
import seu.lib.bz.BorrowInfo;
import seu.lib.soket.ClientSocket;

import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;

public class BookBack extends JFrame{
	private JTextField tfusername;
	private JComboBox jcombox;
	public static JPanel panelmain;
	private JLabel lbll;
	private JLabel label_1;
	private JPasswordField passwordField;
	private JLabel label_2;
	private JLabel label_3;
	private JTextField tfborrowdate;
	private JTextField tfbackdate;
	private JButton jbtok;
	private JButton jbtno;
	public BookBack(){
		super("还书界面");
		setBounds(460,200,680,540);
		getContentPane().setLayout(null);
		panelmain=new JPanel();
		panelmain.setBounds(new Rectangle(0, 0, 680, 540));
		//	panelmain.add(scroll)
		this.setContentPane(panelmain);
			
		panelmain.setLayout(null);
		
		JLabel label = new JLabel("\u5B66\u751F\u59D3\u540D");
		label.setBounds(134, 58, 55, 24);
		panelmain.add(label);
		
		tfusername = new JTextField();
		tfusername.setBounds(370, 58, 148, 20);
		panelmain.add(tfusername);
		tfusername.setColumns(10);
		
		
		jcombox=new JComboBox();
		jcombox.addItem("");
		jcombox.setModel(new DefaultComboBoxModel());
		jcombox.setBounds(87, 311, 83, 31);
		jcombox.addActionListener(new jcomboxAction());
		jcombox.addItem("请选择");
		panelmain.add(jcombox);
		
		lbll = new JLabel("\u6240\u501F\u4E66\u540D");
		lbll.setBounds(101, 269, 55, 31);
		panelmain.add(lbll);
		
		label_1 = new JLabel(" \u5BC6\u7801");
		label_1.setHorizontalAlignment(SwingConstants.TRAILING);
		label_1.setBounds(124, 130, 46, 14);
		panelmain.add(label_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(370, 123, 148, 24);
		panelmain.add(passwordField);
		
		JButton jbtcheck = new JButton("check");
		jbtcheck.setBounds(453, 174, 63, 31);
		jbtcheck.addActionListener(new checkAction());
		panelmain.add(jbtcheck);
		
		label_2 = new JLabel("\u501F\u51FA\u65F6\u95F4");
		label_2.setBounds(291, 277, 46, 14);
		panelmain.add(label_2);
		
		label_3 = new JLabel("\u5E94\u8FD8\u65F6\u95F4");
		label_3.setBounds(478, 277, 46, 14);
		panelmain.add(label_3);
		
		tfborrowdate = new JTextField();
		tfborrowdate.setDisabledTextColor(Color.BLACK);
		tfborrowdate.setBounds(291, 316, 86, 20);
		tfborrowdate.setForeground(Color.BLUE);
		//tfborrowdate.setFont(new font)
		panelmain.add(tfborrowdate);
		tfborrowdate.setColumns(10);
		tfborrowdate.setEnabled(false);
		
		tfbackdate = new JTextField();
		tfbackdate.setDisabledTextColor(Color.BLACK);
		tfbackdate.setBounds(478, 322, 86, 20);
		panelmain.add(tfbackdate);
		tfbackdate.setColumns(10);
		tfbackdate.setEnabled(false);
		
		jbtok = new JButton("\u786E\u8BA4\u5F52\u8FD8");
		jbtok.setBounds(171, 373, 63, 36);
		jbtok.addActionListener(new okAction());
		panelmain.add(jbtok);
		
		jbtno = new JButton("\u53D6\u6D88");
		jbtno.setBounds(461, 373, 63, 36);
		jbtno.addActionListener(new noAction());
		panelmain.add(jbtno);
		
		imagelable = new JLabel("");
		imagelable.setIcon(null);
		imagelable.setBounds(0, 0, 664, 502);
		panelmain.add(imagelable);
		//setVisible(true);
		
	}
	private int length;
	private String[] booktemp;
	private String[] borrtemp;
	private String[] backtemp;
	private Book[] onebook;
	private JLabel imagelable;
	int lentemp;
	int index=8;
	int totleSize;
	int size;
	String s;
	public String Myborrows;
	private class noAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
			tfusername.setText(null);
			passwordField.setText(null);
			tfborrowdate.setText("");
			tfbackdate.setText(null);
			//jcombox.repaint();
			System.out.println(lentemp);
			for(int i=0;i<lentemp;i++)
				{
					if(!(jcombox.getItemAt(i).equals("请选择")))
						jcombox.removeItemAt(i);
				}
//			//setVisible(false);
			
		}
		
	}
	private class checkAction implements ActionListener{

	
		private Vector row1=new Vector();
		private Vector row=new Vector();
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String nametemp=tfusername.getText();;
			System.out.println(nametemp);
			String result=new String();
			String pwdtemp=new String(passwordField.getPassword());
			ClientSocket myClient = new ClientSocket();
			String msg="32/"+nametemp+"/"+pwdtemp;
			System.out.println(msg);
			result=myClient.getStrMessage(msg);
			System.out.println(result);
			//String sql="select * from userinfo where username='"+nametemp+"' and password  ='"+ pwdtemp+"'"; 
			
			if(result.equals("true"))
			{
				try{
					
						System.out.println("密码输入正确");
						Myborrows=new String();
						//onebook=new ArrayList<book>();
						System.out.println("f+++++++++++++++++++++++++");
						//Myborrows=Business.bookback(nametemp);
						String msg2="38/"+nametemp;
						ClientSocket myClient1 = new ClientSocket();
						Myborrows=myClient1.getStrMessage(msg2);
						System.out.println(Myborrows);
						
						System.out.println(Myborrows.length());
						
						length=Myborrows.length();
						System.out.println(Myborrows);
						if(length==0)
						{
							JOptionPane.showMessageDialog(null,"亲，你还没借书额,先去借一本吧!");
							setVisible(false);
							
						}
						else
						{
							booktemp=new String[length];
							borrtemp=new String[length];
							backtemp=new String[length];
							onebook=new Book[length];
						//	for(int i=0;i<length;i++)
							lentemp=0;
							int i=0;
							while (Myborrows !="")
							{		
									
									int j=1;
									row1=new Vector();
									for( j=1;j<=index;j++)
									{
										
										row1.add(check());
										
									}
								//	row.add(row1);
								
								onebook[i] = new Book();
								onebook[i].setbookname((String)row1.get(2));
								onebook[i].setwriter((String)row1.get(4));
								onebook[i].setprice((String)row1.get(3));
								onebook[i].setdeadl((String)row1.get(6));
								onebook[i].setcount("1");
								//onebook[i].setcount();
								booktemp[i]=(String)row1.get(2);
								borrtemp[i]=(String)row1.get(5);
								
								backtemp[i]=(String)row1.get(7);
								jcombox.addItem((String)row1.get(2));
								lentemp++;
								i++;
								
							}
						}
				}catch(Exception ex){
						ex.printStackTrace();}
				
			}
			else
			{
				JOptionPane.showMessageDialog(null,"请输入正确的用户名和密码!");//弹出提示框
			}
			
			
		}
		
		 private String check () {
		        totleSize = Myborrows.length();
		        if (totleSize != 0) {
		            size = Myborrows.indexOf("/");
		            s = Myborrows.substring(0,size);
		            if((size+1)!=totleSize)
		            {Myborrows = Myborrows.substring(size+1,totleSize);}
		            else{
		            	Myborrows="";
		            	return s;
		            }
		            
		        }
		        
		        return s;
		    }
	}
	private class jcomboxAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
				System.out.println(jcombox.getSelectedItem());
				System.out.println(lentemp);
			
				for(int j=0;j<lentemp;j++)
				{		
					System.out.println(booktemp[j]);
					if(jcombox.getSelectedItem().equals(booktemp[j]))
					
					{	tfborrowdate.setText(borrtemp[j]);
						tfbackdate.setText(backtemp[j]);
						System.out.println(backtemp[j]);
					}
				}
				
			
		}
		
		
		
	}
	private class okAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String bname,uname;
			for(int j=0;j<length;j++)
			{
				if(jcombox.getSelectedItem().equals(booktemp[j]))
				{
					bname=(String)jcombox.getSelectedItem();
					uname=tfusername.getText();
					
					
					//Business.deleteborrow(bname, uname);
					String msg1="37/"+bname+"/"+uname;
					ClientSocket myClient = new ClientSocket();
					myClient.getStrMessage(msg1);

					JOptionPane.showMessageDialog(null, "成功归还<"+bname+">");
					setVisible(false);
					//Business.addbook(onebook[j]);
					
					String msg2="35/"+onebook[j].getbookname()+"/"+onebook[j].getwriter()+"/"+onebook[j].getprice()+"/"+onebook[j].getcount()+"/"+onebook[j].getdeadl();
					ClientSocket myClient2 = new ClientSocket();
					myClient2.getStrMessage(msg2);
					
					jcombox.removeItem(jcombox.getSelectedItem());
					tfborrowdate.setText("");
					tfbackdate.setText(null);
					
					
				}
			}
				
			
		}
		
	}
	
	
}
