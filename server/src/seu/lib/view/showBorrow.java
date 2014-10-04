package seu.lib.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import seu.lib.widget.bookinfoModel;
import seu.lib.widget.BookborrowTableModel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class showBorrow  extends JFrame{
	public static JPanel panelmain;
	public JTable table;
	public JScrollPane jslpane;
	BookborrowTableModel bookmodel;
	public showBorrow(){
		super("借阅记录 :");
		setBounds(460,200,697,540);
		getContentPane().setLayout(null);
		panelmain=new JPanel();
		this.setContentPane(panelmain);
		panelmain.setLayout(null);
		bookmodel=new BookborrowTableModel();
		table=new JTable();
		table.setModel(bookmodel);
		jslpane=new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		table.setFillsViewportHeight(true);//使表格占满整个jslpane
		jslpane.setBounds(0, 113, 681, 297);
		panelmain.add(jslpane);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(showBorrow.class.getResource("/img/2.jpg")));
		lblNewLabel.setBounds(0, 0, 681, 115);
		panelmain.add(lblNewLabel);
		
		JButton refresh = new JButton("\u5237\u65B0");
		refresh.addActionListener(new refreshAction());
		refresh.setBounds(290, 442, 89, 27);
		panelmain.add(refresh);
		//setVisible(true);
	}
	private class refreshAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			bookmodel.update();
			table.setModel(bookmodel);
			jslpane.updateUI();
			
		}
		
	}
}
