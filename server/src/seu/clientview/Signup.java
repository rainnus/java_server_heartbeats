package seu.clientview;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import seu.entityclass.MainUser;

public class Signup extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextField jtfSNumber;
	JTextField jtfSName;
	JPasswordField jpfSPassword;
	JPasswordField jpfSPwAgain;
	
	JButton jbSignup;
	JButton jbSReset;
	JButton jbBackToLogin;
	
	JButton jbExit;		//关闭按钮
    JButton jbMini;		//最小化按钮
    
    ImageIcon icon_background = new ImageIcon(this.getClass().getResource("/image/signup_background.png"));
    ImageIcon icon_signup = new ImageIcon(this.getClass().getResource("/image/signup_signup.png"));
    ImageIcon icon_signup2 = new ImageIcon(this.getClass().getResource("/image/signup_signup2.png"));
    ImageIcon icon_reset = new ImageIcon(this.getClass().getResource("/image/signup_reset.png"));
    ImageIcon icon_reset2 = new ImageIcon(this.getClass().getResource("/image/signup_reset2.png"));
    ImageIcon icon_backtol = new ImageIcon(this.getClass().getResource("/image/signup_backtol.png"));
    ImageIcon icon_backtol2 = new ImageIcon(this.getClass().getResource("/image/signup_backtol2.png"));
    ImageIcon icon_exit = new ImageIcon(this.getClass().getResource("/image/signup_exit.png"));
    ImageIcon icon_exit2 = new ImageIcon(this.getClass().getResource("/image/signup_exit2.png"));
    ImageIcon icon_mini = new ImageIcon(this.getClass().getResource("/image/signup_mini.png"));
    ImageIcon icon_mini2 = new ImageIcon(this.getClass().getResource("/image/signup_mini2.png"));
    
    // for dragable
    Point pLoc = null;
    Point pTmp = null;
    boolean bIsDragged = false;
    
    //// 构造函数
    public Signup() {
    	// ini
		jtfSNumber = new JTextField(10);
		jtfSName = new JTextField(10);
		jpfSPassword = new JPasswordField(10);
		jpfSPwAgain = new JPasswordField(10);
		
		jbSignup = new JButton(icon_signup);
		jbSignup.setRolloverIcon(icon_signup2);
		jbSignup.setPressedIcon(icon_signup2);
		jbSignup.setSelectedIcon(icon_signup2);
		
		jbSReset = new JButton(icon_reset);
		jbSReset.setRolloverIcon(icon_reset2);
		jbSReset.setPressedIcon(icon_reset2);
		jbSReset.setSelectedIcon(icon_reset2);
		
		jbBackToLogin = new JButton(icon_backtol);
		jbBackToLogin.setRolloverIcon(icon_backtol2);
		jbBackToLogin.setPressedIcon(icon_backtol2);
		jbBackToLogin.setSelectedIcon(icon_backtol2);
		
		jbExit = new JButton(icon_exit);
		jbExit.setRolloverIcon(icon_exit2);
		jbExit.setPressedIcon(icon_exit2);
		jbExit.setSelectedIcon(icon_exit2);
		
		jbMini = new JButton(icon_mini);
		jbMini.setRolloverIcon(icon_mini2);
		jbMini.setPressedIcon(icon_mini2);
		jbMini.setSelectedIcon(icon_mini2);
		
		JLabel jlBackground = new JLabel(icon_background);
		
		// layout
		setLayout(null);
		
		add(jtfSNumber);
		jtfSNumber.setBounds(120, 220, 220, 25);
		add(jtfSName);
		jtfSName.setBounds(120, 250, 220, 25);
		add(jpfSPassword);
		jpfSPassword.setBounds(120, 280, 220, 25);
		add(jpfSPwAgain);
		jpfSPwAgain.setBounds(120, 310, 220, 25);
		
		add(jbSignup);
		jbSignup.setBounds(80, 360, 100, 30);
		jbSignup.setBorder(null);
		add(jbSReset);
		jbSReset.setBounds(220, 360, 100, 30);
		jbSReset.setBorder(null);
		
		add(jbBackToLogin);
		jbBackToLogin.setBounds(0, 370, 60, 25);
		jbBackToLogin.setBorder(null);
		
		add(jbExit);
		jbExit.setBounds(370, 0, 30, 30);
		jbExit.setBorder(null);
		add(jbMini);
		jbMini.setBounds(340, 0, 30, 30);
		jbMini.setBorder(null);
		
		add(jlBackground);
		jlBackground.setBounds(0,0,400,400);
		
		// add listener
		// signup button
		jbSignup.addActionListener(new signupListener());
		// reset button
		jbSReset.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {
            	s_reset();
            }
		});
		// back to login button
		jbBackToLogin.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {
            	@SuppressWarnings("unused")
				Login login = new Login();
            	dispose();
            }
		});
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
		
		///////////////////
		setDragable();						//可拖动
		setSize(400,400);					//大小
		setLocationRelativeTo(null);		//居中
		setUndecorated(true);
		setVisible(true);
    }
    
    // reset
 	void s_reset() {
 		jtfSNumber.setText("");
 		jtfSName.setText("");
 		jpfSPassword.setText("");
 		jpfSPwAgain.setText("");
 	}
 	

 	////setDragable 拖动窗体的方法
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
	
 	// listener for signup button
 	class signupListener implements ActionListener {
         public void actionPerformed(ActionEvent e) {  
        	 String number = jtfSNumber.getText();
        	 String name = jtfSName.getText();
 			 char password[] = jpfSPassword.getPassword();
 			 char pwagain[] = jpfSPwAgain.getPassword();
 			 String pw = String.valueOf(password);
 			 String pwag = String.valueOf(pwagain);
 			
 			 // if space : warn
 			 if (number.length() == 0 || 
 			     name.length() == 0 ||
 			     pw.length() == 0 ||
 			     pwag.length() == 0 ) {
 		 		 JOptionPane.showMessageDialog(null, "每项内容都不能为空！");
 			 }
 			 else if ( ! pw.equals(pwag)) {  
 				 JOptionPane.showMessageDialog(null, "两次密码不一致，请重新确认输入！");		 
 				 jpfSPassword.setText("");
 		 		 jpfSPwAgain.setText("");
 			 }
 			 else {
 				 String massage = "01/" + number + '/' + name + '/' + pw ;
 				 ClientSocket mysocket = new ClientSocket();
				 MainUser user2 = mysocket.getMUserMessage(massage);
 				 if( user2.getIsRegisted() ) {
 					JOptionPane.showMessageDialog(null, "该学号用户已注册，请直接登陆！");
 					Login login = new Login();
 					login.jtfLName.setText(number);
 	            	dispose();
 				 }
 				 else {
 					JOptionPane.showMessageDialog(null, "注册成功，请返回登陆！");
 					Login login = new Login();
 					login.jtfLName.setText(number);
 	            	dispose();
 				 }
 			 }
         }
 	}
	
}
	
	


