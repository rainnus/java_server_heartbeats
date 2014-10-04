package seu.lib.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;

import seu.lib.Login.LogIn;
import seu.lib.bz.Book;
import seu.lib.bz.BorrowInfo;
import seu.lib.soket.ClientSocket;

import java.awt.Color;
import javax.swing.SwingConstants;

public class BookBorrow extends JFrame{
	private JTextField tfbookname;
	private String book_name=null;
	private String	borrow_date=null;
	private String book_count=null;
	private JTextField tfreadername;
	private JTextField tfbookwriter;
	private JLabel label_1;
	private JLabel label_2;
	private JTextField tfborrowtime;
	private JTextField tfbacktime;
	private JLabel label_3;
	private JLabel label_4;
	private JLabel label_5;
	private JPasswordField passwordField;
	private String datline;
	private String price;
	static public JPanel panelmain;
	public BookBorrow (BorrowInfo borrow1,String count)
	{
		super();
		
		datline=borrow1.getdateline();
		price=borrow1.getprice();
		setTitle("图书借阅界面");
		book_name=borrow1.getbookname();
		borrow_date=borrow1.getborrowdate();
		book_count=count;
		setBounds(460,200,511,448);
		getContentPane().setLayout(null);
		panelmain=new JPanel();
		this.setContentPane(panelmain);
		panelmain.setLayout(null);
		
		tfbookname = new JTextField();
		tfbookname.setBounds(261, 80, 122, 20);
		panelmain.add(tfbookname);
		tfbookname.setText(book_name);
		tfbookname.setEditable(false);//设置为不可通过键盘输入
		tfbookname.setColumns(10);
		
		tfreadername = new JTextField();
		tfreadername.setBounds(261, 27, 122, 20);
		panelmain.add(tfreadername);
		tfreadername.setColumns(10);
		
		tfbookwriter = new JTextField();
		tfbookwriter.setDisabledTextColor(Color.BLACK);
		tfbookwriter.setColumns(10);
		tfbookwriter.setBounds(261, 125, 122, 20);
		tfbookwriter.setEnabled(false);
		tfbookwriter.setText(borrow1.getwriter());
		panelmain.add(tfbookwriter);
		
		JLabel label = new JLabel("\u8BFB\u8005\u59D3\u540D");
		label.setBounds(74, 30, 46, 14);
		panelmain.add(label);
		
		label_1 = new JLabel("   \u4F5C\u8005");
		label_1.setBounds(74, 128, 64, 14);
		panelmain.add(label_1);
		
		label_2 = new JLabel("   \u4E66\u540D");
		label_2.setBounds(74, 83, 64, 14);
		panelmain.add(label_2);
		
		
		DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.FULL); 
		// Create our Gregorian Calendar. 
		GregorianCalendar cal = new GregorianCalendar(); 
		// Set the date and time of our calendar 
		// to the system&s date and time 
		cal.setTime(new Date()); 
		System.out.println("System Date: " + dateFormat.format(cal.getTime())); 
	//	System.out.println(bartDateFormat.format(date1));
		tfborrowtime = new JTextField();
		tfborrowtime.setText(dateFormat.format(cal.getTime()));
		tfborrowtime.setEditable(false);
		tfborrowtime.setColumns(10);
		tfborrowtime.setBounds(261, 166, 122, 20);
		panelmain.add(tfborrowtime);
		
		//System.out.println(date);
		int day=Integer.parseInt(datline);
		cal.add(GregorianCalendar.DAY_OF_MONTH, day);		//获取给当前日期加上date后的日期
		tfbacktime = new JTextField();		
		tfbacktime.setText(dateFormat.format(cal.getTime()));
		tfbacktime.setEditable(false);
		tfbacktime.setColumns(10);
		tfbacktime.setBounds(261, 223, 122, 20);
		panelmain.add(tfbacktime);
		
		label_3 = new JLabel("\u501F\u51FA\u65F6\u95F4");
		label_3.setBounds(87, 169, 64, 14);
		panelmain.add(label_3);
		
		label_4 = new JLabel("\u5E94\u8FD8\u65F6\u95F4");
		label_4.setBounds(87, 226, 64, 14);
		panelmain.add(label_4);
		
		label_5 = new JLabel("\u786E\u8BA4\u5BC6\u7801");
		label_5.setBounds(87, 276, 64, 14);
		panelmain.add(label_5);
		
		JButton btcheck = new JButton("\u786E\u8BA4\u501F\u4E66");
		btcheck.setBounds(74, 362, 93, 23);
		panelmain.add(btcheck);
		btcheck.addActionListener(new checkAction() );
		
		JButton btclose = new JButton("\u8FD4\u56DE");
		btclose.setBounds(302, 362, 81, 23);
		panelmain.add(btclose);
		btclose.addActionListener(new closeAction());
		
		passwordField = new JPasswordField();
		passwordField.setBounds(261, 273, 122, 20);
		panelmain.add(passwordField);
		
		JLabel imagelabel = new JLabel("");
		imagelabel.setHorizontalAlignment(SwingConstants.CENTER);
		imagelabel.setIcon(new ImageIcon(BookBorrow.class.getResource("/img/698.jpg")));
		imagelabel.setBounds(0, 0, 495, 409);
		panelmain.add(imagelabel);
		
		//setVisible(true);
	}
	private class closeAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			setVisible(false);
		//	showBooks.c.next(showBooks.mainpanel);
			
		}
		
	}
	private class checkAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String user=null;
			
			String pass=null;
			String result=new String();
			user=tfreadername.getText();
			pass=new String(passwordField.getPassword());
			ClientSocket myClient = new ClientSocket();
			String msg="32/"+user+"/"+pass;
			result=myClient.getStrMessage(msg);
			
			if(result.equals("true"))
				 
				
			{
				System.out.println("成功借阅此书籍");
				try{
				/*	BorrowInfo temp=new BorrowInfo();
					temp.setreadername(tfreadername.getText());
					temp.setbookname(tfbookname.getText());
					temp.setwriter(tfbookwriter.getText());
					temp.setborrowdate(tfborrowtime.getText());
					temp.setdateline(datline);
					temp.setprice(price);
					temp.setbackdate(tfbacktime.getText());*/
					//Business.addborrow(temp);
					String msg1="33/"+tfreadername.getText()+"/"+tfbookname.getText()+"/"+tfbookwriter.getText()+"/"+tfborrowtime.getText()+"/"+datline+"/"+price+"/"+tfbacktime.getText();
					ClientSocket myClient1 = new ClientSocket();
					myClient1.getStrMessage(msg1);
					
					JOptionPane.showMessageDialog(null, "成功借阅此书");
					setVisible(false);
					
					//Business.deletebook(book_name, book_count);//调用删除图书的函数表示该书被借走一本
					String msg2="34/"+tfbookname.getText()+"/"+book_count;
					System.out.println(msg2);
					ClientSocket myClient2 = new ClientSocket();
					myClient2.getStrMessage(msg2);
					System.out.println("成功删除此书 ");
					
					
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null,"借阅失败，请检查你的用户名密码");//弹出提示框
			}
			
		
		}
		
	}
}
