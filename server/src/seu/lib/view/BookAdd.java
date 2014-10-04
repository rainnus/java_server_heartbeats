package seu.lib.view;

import javax.swing.*;

import seu.lib.bz.Book;
import seu.lib.soket.ClientSocket;

import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Vector;

public class BookAdd extends JFrame{
	private JTextField tfbookname;
	private JTextField tfacount;
	private JTextField tfborrowdate;
	private JTextField tfwriter;
	private JTextField tfprice;
	public static  JPanel panelmain;
	public BookAdd() {
		super();
		setTitle("图书相关信息添加");
		
		setBounds(460,200,692,540);
		getContentPane().setLayout(null);
		panelmain=new JPanel();
		this.setContentPane(panelmain);
		panelmain.setLayout(null);
		
		final JPanel imagepanel = new JPanel();
		imagepanel.setBounds(0, 0, 686, 136);
		panelmain.add(imagepanel);
		imagepanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(BookAdd.class.getResource("/img/0.jpg")));
		lblNewLabel.setBounds(0, 0, 684, 135);
		imagepanel.add(lblNewLabel);
		
		JLabel label = new JLabel("\u56FE\u4E66\u540D\u79F0");
		label.setBounds(146, 147, 75, 23);
		panelmain.add(label);
		
		JLabel label_1 = new JLabel("\u5B58\u50A8\u6570\u91CF");
		label_1.setBounds(146, 312, 75, 23);
		panelmain.add(label_1);
		
		JLabel label_2 = new JLabel("\u53EF\u501F\u5929\u6570");
		label_2.setBounds(146, 366, 75, 23);
		getContentPane().add(label_2);
		
		tfbookname = new JTextField();
		tfbookname.setBounds(323, 147, 227, 23);
		panelmain.add(tfbookname);
		
		tfbookname.setColumns(10);
		tfacount = new JTextField();
		tfacount.setBounds(323, 313, 227, 20);
		panelmain.add( tfacount);
		tfacount.setColumns(10);
		tfborrowdate = new JTextField();
		tfborrowdate.setColumns(10);
		tfborrowdate.setBounds(323, 366, 227, 23);
		panelmain.add(tfborrowdate);
		
		JButton btnsave = new JButton("\u4FDD\u5B58");
		btnsave.addActionListener(new saveAction() );
		
		btnsave.setBounds(132, 427, 89, 23);
		panelmain.add(btnsave);
		
		JButton btnback = new JButton("\u5173\u95ED");
		btnback.setBounds(461, 427, 89, 23);
		btnback.addActionListener(new backAction());
		panelmain.add(btnback);
		
		tfwriter = new JTextField();
		tfwriter.setColumns(10);
		tfwriter.setBounds(323, 200, 227, 23);
		getContentPane().add(tfwriter);
		
		tfprice = new JTextField();
		tfprice.setColumns(10);
		tfprice.setBounds(323, 255, 227, 23);
		panelmain.add(tfprice);
		
		JLabel label_3 = new JLabel("\u4F5C\u8005");
		label_3.setBounds(158, 200, 75, 23);
		panelmain.add(label_3);
		
		JLabel label_4 = new JLabel("\u56FE\u4E66\u4EF7\u683C");
		label_4.setBounds(146, 255, 75, 23);
		panelmain.add(label_4);
		
		setResizable(false);
	//	this.setVisible(true);
		
	}
	
	private class backAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			setVisible(false);
			
		}
		
	}
	private class saveAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String sqlsearch="select * from bookInfo where bookname= '"+tfbookname.getText()+"'";
			String test=null;
			String temp1,temp2;
			temp1="bookname";
			temp2=tfbookname.getText();
			String msg="31/"+temp1+"/"+temp2;
			ClientSocket myClient = new ClientSocket();
			test=myClient.getStrMessage(msg);
			
			//test=Business.showbook(sqlsearch);
			
			if((tfbookname.getText())=="")
			{
				System.out.println("空书名   "+tfbookname.getText());
				JOptionPane.showMessageDialog(null, "书名不能为空！！");
			}
			else if(test.length()>1)
			{
				JOptionPane.showMessageDialog(null, "该书已存在，请重新输入您要添加的新书");
			}
			else 
			{
			
				String msg3="35/"+tfbookname.getText()+"/"+tfwriter.getText()+"/"+tfprice.getText()+"/"+tfacount.getText()+"/"+tfborrowdate.getText();
				ClientSocket myClient1 = new ClientSocket();
				test=myClient1.getStrMessage(msg3);
				
				
				JOptionPane.showMessageDialog(null, "添加图书成功");
				setVisible(false);
			}
			
		}
		
	}
}
