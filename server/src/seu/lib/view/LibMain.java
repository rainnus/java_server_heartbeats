package seu.lib.view;


import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.*;
import javax.swing.border.BevelBorder;


import seu.lib.Login.LogIn;
//import seu.lib.Login.LogIn.ResetAction;
/*
 * ͼ���������
 */

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Rectangle;





	public class LibMain extends JFrame {
		/**
		 * ͼ���������
		 */
		private static final long serialVersionUID = 1L;
		public static final JPanel mainpanel =new JPanel(); //���洰��]
		//private JToolBar toolBar;
		private JMenuBar menuBar;
		private JPanel toolpanel;
		private ButtonGroup bg;
		public static JPanel addpanel;
		public static JPanel backpanel;
		public static JPanel deletepanel;
		public static JPanel borinfopanel;
		public static JPanel showpanel;
		public static  JPanel cardpanel=new JPanel();
		//private JTabbedPane tabelpanel=new JTabbedPane(JTabbedPane.TOP);
		
		public static CardLayout c=new CardLayout();
		 public static void addIFame(JInternalFrame iframe){
			 mainpanel.add(iframe);     //����ִ���			 
		 }
		 
		 public LibMain(){  //Main�Ĺ��캯��
			 super();
			 setBounds(new Rectangle(0, 0, 0, 540));
			 
			 setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //���ùرհ�ť�����¼�
			 Toolkit tool=Toolkit.getDefaultToolkit();  //����������
			 Dimension screenSize=tool.getScreenSize();//�����Ļ��С
			 this.setContentPane(mainpanel);
			 setSize(696,583);
			 setLocation((screenSize.width-getWidth())/2,(screenSize.height-getHeight())/2);//���ô���λ��
			 setTitle("ͼ��ݹ���ϵͳ");
			 mainpanel.setLayout(null);
			cardpanel.setBounds(0, 31, 680, 540);
			 bg = new ButtonGroup();
		//	 menuBar=creatMenu();
			// setJMenuBar(menuBar);
			 //getContentPane().add(toolBar,BorderLayout.NORTH);
		//	 tabelpanel.add("��ҳ",mainpanel);
			mainpanel.add(cardpanel);
			cardpanel.setLayout(c);
			

			
			// getContentPane().add(toolBar,BorderLayout.NORTH);//���ù�����
			
			//��ӱ���ͼ
			 final JLabel bakimage_label = new JLabel("");
			 bakimage_label.setBounds(0, 0, 634, 385);
			 cardpanel.add(bakimage_label);
			 bakimage_label.setHorizontalAlignment(SwingConstants.CENTER);
			 bakimage_label.setIcon(new ImageIcon(LibMain.class.getResource("/img/79.jpg")));
			 
			 
			 toolpanel=createToolpanel();//�����������ķ���
			 mainpanel.add(toolpanel);
			  mainpanel.addComponentListener(new ComponentAdapter(){
				  public void componentResized(ComponentEvent e)
				  {
					  bakimage_label.setSize(mainpanel.getWidth(),mainpanel.getHeight());
					  toolpanel.setSize(bakimage_label.getWidth(),33);
				  }
			  });
			  mainpanel.setBackground(Color.WHITE);
			  mainpanel.setBounds(560, 0, 1, 361);
			 
			//  getContentPane().add(mainpanel);
			  showpanel=new showBooks().mainpanel;
			  addpanel=new BookAdd().panelmain;
			  backpanel=new BookBack().panelmain;
			  deletepanel=new BookDelete().panelmain;
			  borinfopanel=new showBorrow().panelmain;
//			  showpanel.setVisible(false);
//			  addpanel.setVisible(false);
//			  backpanel.setVisible(false);
//			  deletepanel.setVisible(false);
//			  borinfopanel.setVisible(false);
			//  cardpanel.setLayout(c);
			//  cardpanel.add(mainpanel,"main");
			  
			  cardpanel.add(showpanel,"allbooks");
			  cardpanel.add(addpanel,"addbook");
			  cardpanel.add(backpanel,"bookback");
			  cardpanel.add(deletepanel,"bookdelete");
			  cardpanel.add(borinfopanel,"borinfo");
			 
			 // setVisible(true);
			  
			
		  }
			  
		 


		public static JButton jbtbookborrow;//����ͼ�鰴ť
		public static JButton jbtbookadd;//���ͼ�鰴ť
		public static JButton jbtbookback;//�黹ͼ�鰴ť
		public static JButton jbtadmain; //����ԱȨ�ް�ť
		public static JButton jbtborinfo;//������Ϣ��ť
		public static JButton deletebook;
		public static JButton jbtmain;
		
		private JPanel createToolpanel() {//�����������ķ���
			// TODO Auto-generated method stub
			bg.add( jbtbookborrow);
			bg.add( jbtbookadd);
			bg.add( jbtbookback);
			bg.add( jbtadmain);
			bg.add( deletebook);
			
			toolpanel=new JPanel();
			toolpanel.setForeground(Color.BLACK);
			toolpanel.setSize(634, 33);
			toolpanel.setBounds(10, 0, 680, 38);
			toolpanel.setLocation(0, 0);
			//toolpanel.setForeground(Color.RED);
			toolpanel.setBackground(Color.BLACK);
			//toolpanel.setBounds(new Rectangle(0, 0, 634, 14));
			toolpanel.setBorder(new BevelBorder(BevelBorder.RAISED));// ���ñ߿�
			toolpanel.setLayout(null);
			
			//��ҳ�İ�ť
			jbtmain=new JButton();
			jbtmain.setToolTipText("\u4E3B\u9875");
			jbtmain.setText("\u4E3B\u9875");
			jbtmain.setIcon(null);
			jbtmain.setAlignmentX(Component.RIGHT_ALIGNMENT);
			jbtmain.setVerticalTextPosition(SwingConstants.TOP);
			jbtmain.setBounds(0, 0, 90, 33);
			jbtmain.setPreferredSize(new Dimension(33, 33));
			jbtmain.setSelected(true);
			jbtmain.setHideActionText(true);
			jbtmain.addActionListener(new MenuAction());
			toolpanel.add(jbtmain);
			
			//���ͼ�鰴ť
			jbtbookadd=new JButton();
			jbtbookadd.setToolTipText("���ͼ��");
			jbtbookadd.setText("���ͼ��");
			jbtbookadd.setEnabled(false);
			jbtbookadd.setIcon(null);
			jbtbookadd.setAlignmentX(Component.RIGHT_ALIGNMENT);
			jbtbookadd.setVerticalTextPosition(SwingConstants.TOP);
			jbtbookadd.setBounds(385, 0, 100, 33);
			jbtbookadd.setPreferredSize(new Dimension(33, 33));
			jbtbookadd.setSelected(true);
			jbtbookadd.setHideActionText(true);
			jbtbookadd.addActionListener(new MenuAction());
			toolpanel.add(jbtbookadd);
			
			//������Ϣ��ť����
			 jbtborinfo = new JButton();
			 jbtborinfo.addActionListener(new MenuAction());
			 jbtborinfo.setVerticalTextPosition(SwingConstants.TOP);
			 jbtborinfo.setToolTipText("\u501F\u9605\u8BB0\u5F55");
			 jbtborinfo.setText("\u501F\u9605\u8BB0\u5F55");
			 jbtborinfo.setSelected(true);
			 jbtborinfo.setPreferredSize(new Dimension(33, 33));
			 jbtborinfo.setHideActionText(true);
			 jbtborinfo.setEnabled(false);
			 jbtborinfo.setAlignmentX(1.0f);
			 jbtborinfo.setBounds(580, 0, 100, 33);
			 toolpanel.add(jbtborinfo);
			 
			//ͼ����İ�ť
			jbtbookborrow=new JButton();
			jbtbookborrow.setText("\u56FE\u4E66\u501F\u9605");
			jbtbookborrow.setToolTipText("ͼ�����");
			jbtbookborrow.setIcon(null);
			jbtbookborrow.setBounds(89, 0, 100, 33);
			jbtbookborrow.setPreferredSize(new Dimension(33, 33));
			jbtbookborrow.setSelected(true);
			jbtbookborrow.setHideActionText(true);
			jbtbookborrow.addActionListener(new MenuAction());//������
			toolpanel.add(jbtbookborrow);
			
			 deletebook = new JButton();
			 deletebook.setText("\u5220\u9664\u56FE\u4E66");
			 deletebook.setIcon(null);
			 deletebook.addActionListener(new MenuAction());
			 deletebook.setEnabled(false);
			 deletebook.setVerticalTextPosition(SwingConstants.TOP);
			 deletebook.setToolTipText("\u5220\u9664\u56FE\u4E66");
			 deletebook.setSelected(true);
			 deletebook.setPreferredSize(new Dimension(33, 33));
			 deletebook.setHideActionText(true);
			 deletebook.setAlignmentX(1.0f);
			 deletebook.setBounds(481, 0, 100, 33);
			 toolpanel.add(deletebook);
			 
			jbtbookback=new JButton();
			jbtbookback.setText("\u56FE\u4E66\u5F52\u8FD8");
			jbtbookback.setIcon(null);
			jbtbookback.setToolTipText("ͼ��黹");
			jbtbookback.setBounds(187, 0, 100, 33);
			jbtbookback.setPreferredSize(new Dimension(33, 33));
			jbtbookback.setHideActionText(true);
			jbtbookback.addActionListener(new MenuAction());//������
			toolpanel.add(jbtbookback);
			
			jbtadmain=new JButton();
			jbtadmain.setText("\u7BA1\u7406\u5458\u767B\u9646");
			jbtadmain.setToolTipText("����ԱȨ��");
			jbtadmain.setBounds(287, 0, 100, 33);
			jbtadmain.setPreferredSize(new Dimension(33, 33));
			jbtadmain.setHideActionText(true);
			jbtadmain.addActionListener(new MenuAction());//������
			//jbtadmain.enable(false);
			toolpanel.add(jbtadmain);
			 
			//tabelpanel.add("admain");
			 	
			return toolpanel;
		}
	}






