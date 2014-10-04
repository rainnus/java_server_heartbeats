package seu.lib.view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import seu.lib.soket.ClientSocket;
import seu.lib.widget.bookinfoModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;

public class BookDelete extends JFrame{
	public static  JPanel panelmain;
	public JTable table;
	public JScrollPane jslpane;
	private JButton delete;
	private JButton back;
	bookinfoModel bookinfo;
	public BookDelete(){
		super("删除图书");
		setBounds(460,200,699,450);
		getContentPane().setLayout(null);
		panelmain=new JPanel();
		this.setContentPane(panelmain);
		panelmain.setLayout(null);
		//table=new JTable(new bookinfoModel(null,null));
		bookinfo=new bookinfoModel(null, null);
		table=new JTable();
		table.setModel(bookinfo);
		jslpane=new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		table.setFillsViewportHeight(true);//使表格占满整个jslpane
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
//					 
						System.out.println("fresh");
					   
			
					}
					
					
					}
				
					 
				});
		jslpane.setBounds(0, 113, 683, 299);
		panelmain.add(jslpane);
		
		delete = new JButton("\u5220\u9664");
		delete.setBounds(137, 50, 89, 23);
		delete.addActionListener(new deleteAction());
		panelmain.add(delete);
		
		back = new JButton("\u5237\u65B0");
		back.addActionListener(new backAction() );
		
		back.setBounds(421, 50, 89, 23);
		panelmain.add(back);
		
		
	//	setVisible(true);;
	}
	private class deleteAction implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int temp=0;
			temp=table.getSelectedColumnCount();
			System.out.println(temp);
			if(temp==0)
			{
				JOptionPane.showMessageDialog(null, "请先选中你要删除的书哦~~");
			}
			else
			{
				String bookname=null;
				
				String count=null;
				bookname=(String) table.getValueAt(table.getSelectedRow(), 1);
				count=(String) table.getValueAt(table.getSelectedRow(), 4);
			//	System.out.println(bookname+"   "+count);
				//Business.deletebook(bookname,count);
				String msg="39/"+bookname+"/"+count;
				ClientSocket myClient = new ClientSocket();
				System.out.println(msg);
				myClient.getStrMessage(msg);
				
				
			}
		}
		
	
	}
	private class backAction implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			bookinfo.updatemodel();
			table.setModel(bookinfo);
			jslpane.updateUI();
		}
		
	}



	
	

}
