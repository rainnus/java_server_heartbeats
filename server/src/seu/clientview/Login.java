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

public class Login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public JTextField jtfLName;
	JPasswordField jpfLPassword;
	JButton jbLogin;
	JButton jbLReset;
	JButton jbGoToSignup;
	JButton jbExit;		//关闭按钮
    JButton jbMini;		//最小化按钮
	
    ImageIcon icon_background = new ImageIcon(this.getClass().getResource("/image/login_background.png"));
    ImageIcon icon_login = new ImageIcon(this.getClass().getResource("/image/login_login.png"));
    ImageIcon icon_login2 = new ImageIcon(this.getClass().getResource("/image/login_login2.png"));
    ImageIcon icon_reset = new ImageIcon(this.getClass().getResource("/image/login_reset.png"));
    ImageIcon icon_reset2 = new ImageIcon(this.getClass().getResource("/image/login_reset2.png"));
    ImageIcon icon_gotos = new ImageIcon(this.getClass().getResource("/image/login_gotos.png"));
    ImageIcon icon_gotos2 = new ImageIcon(this.getClass().getResource("/image/login_gotos2.png"));
    ImageIcon icon_exit = new ImageIcon(this.getClass().getResource("/image/login_exit.png"));
    ImageIcon icon_exit2 = new ImageIcon(this.getClass().getResource("/image/login_exit2.png"));
    ImageIcon icon_mini = new ImageIcon(this.getClass().getResource("/image/login_mini.png"));
    ImageIcon icon_mini2 = new ImageIcon(this.getClass().getResource("/image/login_mini2.png"));
    
    // for dragable
    Point pLoc = null;
    Point pTmp = null;
    boolean bIsDragged = false;
    
    //// 
	public Login() {
		// ini
		jtfLName = new JTextField(10);
		jpfLPassword = new JPasswordField(10);
		
		jbLogin = new JButton(icon_login);
		jbLogin.setRolloverIcon(icon_login2);
		jbLogin.setPressedIcon(icon_login2);
		jbLogin.setSelectedIcon(icon_login2);
		
		jbLReset = new JButton(icon_reset);
		jbLReset.setRolloverIcon(icon_reset2);
		jbLReset.setPressedIcon(icon_reset2);
		jbLReset.setSelectedIcon(icon_reset2);
		
		jbGoToSignup = new JButton(icon_gotos);
		jbGoToSignup.setRolloverIcon(icon_gotos2);
		jbGoToSignup.setPressedIcon(icon_gotos2);
		jbGoToSignup.setSelectedIcon(icon_gotos2);
		
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
			
		add(jtfLName);
		jtfLName.setBounds(120, 220, 220, 25);
		add(jpfLPassword);
		jpfLPassword.setBounds(120, 250, 220, 25);
		
		add(jbLogin);
		jbLogin.setBounds(80, 300, 100, 30);
		jbLogin.setBorder(null);
		add(jbLReset);
		jbLReset.setBounds(220, 300, 100, 30);
		jbLReset.setBorder(null);
		
		add(jbGoToSignup);
		jbGoToSignup.setBounds(340, 220, 40, 25);
		jbGoToSignup.setBorder(null);
		
		add(jbExit);
		jbExit.setBounds(370, 0, 30, 30);
		jbExit.setBorder(null);
		add(jbMini);
		jbMini.setBounds(340, 0, 30, 30);
		jbMini.setBorder(null);
		
		add(jlBackground);
		jlBackground.setBounds(0,0,400,400);
		
		// add listener
		// login button
		jbLogin.addActionListener(new loginListener());
		// reset button
		jbLReset.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {
            	l_reset();
            }
		});

		jbGoToSignup.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) { 
            	@SuppressWarnings("unused")
				Signup signup = new Signup();
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
		setDragable();
		
		setSize(400,400);					//大小
		setLocationRelativeTo(null);		//居中
		setUndecorated(true);
		setVisible(true);
	}
	
	
	//// setDragable 拖动窗体的方法
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
	
	//// reset
	void l_reset() {
		jtfLName.setText("");
		jpfLPassword.setText("");
	}
	
	//// listener for login button
	class loginListener implements ActionListener {
        public void actionPerformed(ActionEvent e) { 
        	String name = jtfLName.getText();
			char password[] = jpfLPassword.getPassword();
			String pw = String.valueOf(password);
			
			if (name.length() == 0 | pw.length() == 0 ) {
				JOptionPane.showMessageDialog(null, "用户名和密码不能为空！");
			}
			else {
				String massage = "00/" + name + '/' + pw;
				ClientSocket mysocket = new ClientSocket();
				
				MainUser user2 = mysocket.getMUserMessage(massage);
				if(user2.getPasswd().equals(pw)){
					dispose();
					MainFrame mainFrame = new MainFrame();
					mainFrame.mUser = user2;
				}else {
					JOptionPane.showMessageDialog(null, "用户名或密码不正确，请重新输入！");
					l_reset();
				}			
			}
		}
	}
}




