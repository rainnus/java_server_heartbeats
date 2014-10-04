package seu.lib.view;
import seu.lib.Login.LogIn.ResetAction;
import seu.lib.bz.Book;
import seu.lib.bz.BorrowInfo;
import seu.lib.widget.bookinfoModel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;
import java.util.Vector;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.Color;
import java.awt.CardLayout;
import javax.swing.border.EtchedBorder;



public class showBooks extends JFrame{
	//static public JPanel mainpanel=new JPanel();
	public JPanel borrowpanel=new JPanel();
	private static JFrame frame=new JFrame();
	public static JPanel mainpanel;
	private static JScrollPane jslpane=new JScrollPane();
    public static JTable table1=new JTable(null);
    public static JTable table;
    bookinfoModel bookmodel;
	Vector cloun=null;
	Book bookforbor=new Book();
	JScrollPane scroll =new JScrollPane();
	public JTextField textField;//搜索文本
	public static JComboBox jcombox;
	public JButton btnseek;
	public String temp1 = "";//传到model里用的参数
	public String temp2 = "";
	static public CardLayout c =new CardLayout();
	public showBooks(){
		super("books show");
		setBounds(460,200,694,576);
		getContentPane().setLayout(null);
		mainpanel=new JPanel();
	//	panelmain.add(scroll)
		this.setContentPane(mainpanel);
		//mainpanel.setLayout(c);
		mainpanel.setBounds(0, 31, 634, 385);
	//	borrowpanel.setBounds(460,200,467,447);
		borrowpanel.setBackground(Color.RED);
	//	mainpanel.add(Bookspanel, "图书浏览");
		
		borrowpanel.setLayout(null);	
		mainpanel.setLayout(null);
		
        bookmodel=new bookinfoModel(temp1,temp2);
       // table=new JTable(new bookinfoModel(temp1,temp2));
		table=new JTable();
		table.setModel(bookmodel);
		//table.setBounds(0, 113, 466, 409);
		jslpane=new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	  
		
		
		table.setFillsViewportHeight(true);//使表格占满整个jslpane
		jslpane.setBounds(0, 113, 681, 332);
		mainpanel.add(jslpane);
		  table.addMouseListener(new MouseAdapter()
			{
				 public void mouseReleased(java.awt.event.MouseEvent e)
				{
					
				//是否左建双击
				if (e.getClickCount() == 2 && SwingUtilities.isLeftMouseButton(e))
				{
					
					//bookmodel.updatemodel();
					// String str=table.getValueAt(table.getSelectedRow(), 1).toString();
					JOptionPane.showMessageDialog(null, "你已选中《"+table.getValueAt(table.getSelectedRow(), 1)+"》");
				   System.out.println("您已选中此书：");
				   System.out.println("《"+table.getValueAt(table.getSelectedRow(), 1)+"》");//获取名称所在列的信息
				 

				   
		
				}
				
				
				}
			
				 
			});
		  	jcombox=new JComboBox();
			jcombox.setModel(new DefaultComboBoxModel());
			jcombox.setBounds(176, 56, 83, 31);
			jcombox.addItem("书名");
			jcombox.addItem("作者");
			jcombox.addItem("价格");
			jcombox.addItem("所存数量");
			jcombox.addItem("可借天数");
			mainpanel.add(jcombox);

			textField = new JTextField();
			textField.setBounds(324, 61, 94, 20);
			mainpanel.add(textField);
			textField.setColumns(10);
			
			btnseek = new JButton("\u641C\u7D22\u56FE\u4E66");
			btnseek.setBounds(475, 56, 77, 31);
			btnseek.addActionListener(new seekAction());
			mainpanel.add( btnseek);
			
			JButton btnborrow = new JButton("图书借阅");
			btnborrow.setBounds(214, 478, 83, 29);
			btnborrow.addActionListener(new borrowAction());
			mainpanel.add(btnborrow);
			
			JButton btnallbooks = new JButton("\u5237\u65B0");
			btnallbooks.setBounds(453, 478, 83, 29);
			btnallbooks.addActionListener(new allbooksAction());
			mainpanel.add(btnallbooks);
		//	setVisible(true);
		}
	    

	    
	private class borrowAction implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			// TODO Auto-generated method stub
			String bookname,date,count;
			int temp=0;
			temp=table.getSelectedColumnCount();
			
			if(temp==0)
			{
				JOptionPane.showMessageDialog(null, "请先选中你要借阅的书哦~~");
			}
			else
			{
				BorrowInfo temp1=new BorrowInfo();
				temp1.bookname=(String) table.getValueAt(table.getSelectedRow(), 1);
				count=(String) table.getValueAt(table.getSelectedRow(), 4);
				temp1.writer=(String) table.getValueAt(table.getSelectedRow(), 2);
				temp1.price=(String) table.getValueAt(table.getSelectedRow(), 3);
				temp1.dateline=(String) table.getValueAt(table.getSelectedRow(), 5);
				
				BookBorrow jf=new BookBorrow(temp1,count);
				jf.setVisible(true);
				//bookBorrow jf=new bookBorrow(temp1,count);
				//borrowpanel=jf.panelmain;
				////mainpanel.add(borrowpanel,"Bookspanel");
				//setVisible(false);
				//c.next(mainpanel);
			}
			
			
		}
		
	}
	
	private class allbooksAction implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			//bookmodel=new bookinfoModel(temp1,temp2);
			bookmodel.updatemodel();
			table.setModel(bookmodel);
			jslpane.updateUI();

			
			
			
			//showBooks.jslpane.setVisible(true);
			
		}
		
	}
	
	
	private class jcomboxAction implements ActionListener
	{

		

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		
			
		}
		
		
	}
	
	
	private  class seekAction  implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			temp2=textField.getText();
			if(textField.getText().length()<1)
			{
				System.out.println("请输入数据");
				JOptionPane.showMessageDialog(null, "请输入你要查找的书籍");
			}
			else if((jcombox.getSelectedItem().equals("书名"))&&(temp2!=null))
			{
				System.out.println("现在执行的是第一个else函数");
				temp1="bookname";

			}	
			else if ((jcombox.getSelectedItem().equals("作者"))&&(temp2!=null))
			{
				System.out.println("现在执行的是第二个else函数");

				temp1="writer";
	
			}
			else if(jcombox.getSelectedItem().equals("价格"))
			{
				temp1="price";
				
			}
			else if (jcombox.getSelectedItem().equals("所存数量"))
			{
				temp1="count";

			}
			else if (jcombox.getSelectedItem().equals("可借天数"))
			{
				temp1="deadline";
			}
				
			bookmodel.UpdateModel(temp1, temp2);
			table.setModel(bookmodel);
			jslpane.updateUI();
			
			
			
			
		}
		
	}
}

