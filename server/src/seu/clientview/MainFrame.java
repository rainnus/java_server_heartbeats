package seu.clientview;

import java.awt.CardLayout;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import seu.entityclass.MainUser;
import seu.entityclass.StatusUser;
import seu.studentinfomanager.model.ClientSocket;
import seu.studentinfomanager.viewer.MainView;
import seu.lib.view.LibMain;
import java.awt.Color;
import javax.swing.JLabel;
public class MainFrame extends JFrame {
	
	/**
	 * 
	 */
	public static CardLayout c=new CardLayout();
	private MainView mainView;
	private static JPanel LibPanel; 
	private static final long serialVersionUID = 1L;
	MainUser mUser = new MainUser();   //公用类
	JPanel jpMain;
	JPanel toolpanel;
	JPanel cardpanel;
	
	JButton jbBeg;	//开始
	JButton jbStu;  //学籍
	JButton jbCou;	//选课
	JButton jbLib;	//图书馆
	JButton jbSto;	//商店
	JButton jbInfo;	//关于
	int nButtonSelected;

	ImageIcon icon_background = new ImageIcon(this.getClass().getResource("/image/main_background.png"));
	ImageIcon icon_jbbeg = new ImageIcon(this.getClass().getResource("/image/main_jbbeg.png"));
	ImageIcon icon_jbbeg2 = new ImageIcon(this.getClass().getResource("/image/main_jbbeg2.png"));
	ImageIcon icon_jbstu = new ImageIcon(this.getClass().getResource("/image/main_jbstu.png"));
	ImageIcon icon_jbstu2 = new ImageIcon(this.getClass().getResource("/image/main_jbstu2.png"));
	ImageIcon icon_jbcou = new ImageIcon(this.getClass().getResource("/image/main_jbcou.png"));
	ImageIcon icon_jbcou2 = new ImageIcon(this.getClass().getResource("/image/main_jbcou2.png"));
	ImageIcon icon_jblib = new ImageIcon(this.getClass().getResource("/image/main_jblib.png"));
	ImageIcon icon_jblib2 = new ImageIcon(this.getClass().getResource("/image/main_jblib2.png"));
	ImageIcon icon_jbsto = new ImageIcon(this.getClass().getResource("/image/main_jbsto.png"));	
	ImageIcon icon_jbsto2 = new ImageIcon(this.getClass().getResource("/image/main_jbsto2.png"));
	ImageIcon icon_jbinfo = new ImageIcon(this.getClass().getResource("/image/main_jbinfo.png"));	
	ImageIcon icon_jbinfo2 = new ImageIcon(this.getClass().getResource("/image/main_jbinfo2.png"));
	ImageIcon icon_exit = new ImageIcon(this.getClass().getResource("/image/main_exit.png"));
	ImageIcon icon_exit2 = new ImageIcon(this.getClass().getResource("/image/main_exit2.png"));
	ImageIcon icon_mini = new ImageIcon(this.getClass().getResource("/image/main_mini.png"));
	ImageIcon icon_mini2 = new ImageIcon(this.getClass().getResource("/image/main_mini2.png"));
	
//	JButton buttons[] = new JButton[]{jbBeg,jbStu,jbCou,jbLib,jbSto};
	JButton buttons[];
	ImageIcon button_icons[][] = new ImageIcon[][]{{icon_jbbeg, icon_jbbeg2},
												  {icon_jbstu, icon_jbstu2}, 
												  {icon_jbcou, icon_jbcou2 },
												  {icon_jblib, icon_jblib2}, 
												  {icon_jbsto, icon_jbsto2},
												  {icon_jbinfo, icon_jbinfo2}};
	
	JButton jbExit;		//关闭按钮
    JButton jbMini;		//最小化按钮
    
    // for dragable
    Point pLoc = null;
    Point pTmp = null;
    boolean bIsDragged = false;
    
	//// 构造函数  //////////////////////////////////////////////////////
	public MainFrame() {
		
		//// ini
		//// layout  ///////////////////////////////////////////
		
		jpMain = new JPanel(){
            private static final long serialVersionUID = 1L;           
            protected void paintComponent(Graphics g) {
                try {
                    BufferedImage img = ImageIO.read(new File("src\\image\\main_background.png"));
                	g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        getContentPane().add(jpMain);
        jpMain.setLayout(null);
      
    	toolpanel = new JPanel();
    	toolpanel.setForeground(Color.BLACK);
		toolpanel.setBounds(0, 0, 120, 600);
		jpMain.add(toolpanel);
		
		cardpanel = new JPanel();
		cardpanel.setBounds(120, 60, 680, 540);
		cardpanel.setLayout(c);
		jpMain.add(cardpanel);
       
		ClientSocket mysocket = new ClientSocket();
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append("10");
		sBuffer.append(mUser.getNumber());
		StatusUser user2 = mysocket.getSUserMessage(sBuffer.toString());
		mainView = new MainView(user2);
		cardpanel.add(mainView,"stu");
		
        LibPanel=new LibMain().mainpanel;
        cardpanel.add(LibPanel,"LibMain");
      //  mainView =new MainView();
       // jpMain.add(mainView,"stu");
   
		
		
       
		jbBeg = new JButton(icon_jbbeg);
		jbStu = new JButton(icon_jbstu);
		jbCou = new JButton(icon_jbcou);
		jbLib = new JButton(icon_jblib);
		jbSto = new JButton(icon_jbsto);
		jbInfo = new JButton(icon_jbinfo);
		
		buttons = new JButton[]{jbBeg,jbStu,jbCou,jbLib,jbSto,jbInfo};
		for(int i=0; i<5; i++) {
		//	buttons[i] = new JButton(button_icons[i][0]);
			buttons[i].setRolloverIcon(button_icons[i][1]);
			buttons[i].setPressedIcon(button_icons[i][1]);
			buttons[i].setSelectedIcon(button_icons[i][1]);
			
			jpMain.add(buttons[i]);
			buttons[i].setBounds(0, 60*i, 120, 60);
			buttons[i].setBorder(null);
		}
		toolpanel.setLayout(null);
		
		jbInfo.setRolloverIcon(icon_jbinfo2);
		jbInfo.setPressedIcon(icon_jbinfo2);
		jbInfo.setSelectedIcon(icon_jbinfo2);
		toolpanel.add(jbInfo);
		jbInfo.setBounds(0, 549, 121, 51);
		jbInfo.setBorder(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(MainFrame.class.getResource("/img/77.jpg")));
		lblNewLabel.setBounds(0, 0, 121, 600);
		toolpanel.add(lblNewLabel);
		
		jbExit = new JButton(icon_exit);
		jbExit.setRolloverIcon(icon_exit2);
		jbExit.setPressedIcon(icon_exit2);
		jbExit.setSelectedIcon(icon_exit2);
		jpMain.add(jbExit);
		jbExit.setBounds(770, 0, 30, 30);
		jbExit.setBorder(null);
		
		jbMini = new JButton(icon_mini);
		jbMini.setRolloverIcon(icon_mini2);
		jbMini.setPressedIcon(icon_mini2);
		jbMini.setSelectedIcon(icon_mini2);
		jpMain.add(jbMini);
		jbMini.setBounds(740, 0, 30, 30);
		jbMini.setBorder(null);
		
	
			
		

		
//		JLabel jlBackground = new JLabel(icon_background);
//		add(jlBackground);
//		jlBackground.setBounds(0,0,800,600);

		//// add listener ///////////////////////////////////////
		// exit button
		jbExit.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
                    System.exit(0);  
            }  
		});
		// minux button
		jbMini.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) { 
                    setExtendedState(JFrame.ICONIFIED);                 
            }  
        }); 
		
		//
		jbBeg.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e){
					setSed(0);
				
				// change panel												
				}
			}
		);
		//
		jbStu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				setSed(1);
				
				// change panel				
			}
		}); 
		jbCou.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				setSed(2);
				
				// change panel				
			}
		});
		jbLib.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				setSed(3);
				//new LibMain();
				// change panel				
			}
		});
		jbSto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				setSed(4);
				
				// change panel				
			}
		});
		jbInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				setSed(5);
				
				// change panel				
			}
		});
		
		buttons[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//			     ClientSocket mysocket = new ClientSocket();
//					StringBuffer sBuffer = new StringBuffer();
//					sBuffer.append("10/");
//					sBuffer.append(mUser.getNumber());
//					StatusUser user2 = mysocket.getSUserMessage(sBuffer.toString());
//					mainView = new MainView(user2);
					
					
				//	mainView.setBounds(120, 60, 680, 540);
				//	jpMain.add(mainView);
				c.show(cardpanel, "stu");
				
			}
		});
		buttons[3].addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
//				LibMain libview=new LibMain();
//				libview.mainpanel.setVisible(true);
//				jpMain.add(libview.mainpanel);
//				libview.setBounds(120, 60, 680, 540);
				c.show(cardpanel, "LibMain");
				
			}
			
		});
		/////////////////////////////////////////////////////
		setDragable();
		nButtonSelected = 0;
		setSed(0);
		
		setSize(800,600);					//大小
		setLocationRelativeTo(null);		//居中
		setUndecorated(true);
		setVisible(true);
		
	}
	
	//// 2 help functions   ////////////////////////////////////////////////////
	// setSed = set (looks like) selected
	void setSed(int to_select) {
		setNSed(nButtonSelected);
		nButtonSelected = to_select;
		buttons[to_select].setIcon(button_icons[to_select][1]);		
	}
	// setNSed = set (looks like) not selected
	void setNSed(int selected) {
		buttons[selected].setIcon(button_icons[selected][0]);
	}
	
	////setDragable 拖动窗体的方法  /////////////////////////////////////////////////////
	private void setDragable() {
		this.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseReleased(java.awt.event.MouseEvent e) {
				bIsDragged = false;
				//当鼠标不拖动时，设置鼠标显示的样式
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			public void mousePressed(java.awt.event.MouseEvent e) {
				pTmp = new Point(e.getX(), e.getY());
				bIsDragged = true;
				//当鼠标点击时，鼠标显示的样式
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		//添加鼠标运动监听器
		this.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
			public void mouseDragged(java.awt.event.MouseEvent e) {
				if (bIsDragged) {
					//重新设置界面的位置
					pLoc = new Point(getLocation().x + e.getX() - pTmp.x,
					getLocation().y + e.getY() - pTmp.y);
					setLocation(pLoc);
				}
			}
		});
	}
}
