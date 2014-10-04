package seu.lib.Login;
import seu.lib.view.Business;
import seu.lib.view.LibMain;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.Document;

public class LogIn extends JFrame{
	//private static final Operater Type=null;
//	private static Operater user;
	private JTextField username =new JTextField(10);
	private JPasswordField password = new JPasswordField(10);
	private JButton Login;
	private JButton Reset;
	private JButton btnTest;
	public LogIn(){
		super();
		final BorderLayout borderLayout=new BorderLayout();//创建布局管理器
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置关闭按钮处理事件
		borderLayout.setVgap(10);//设置组件间的垂直距离
		getContentPane().setLayout(borderLayout);//使用布局管理器
		
		setTitle("学生管理系统");
		Toolkit tool =Toolkit.getDefaultToolkit(); //获得默认的工具箱
		Dimension screenSize=tool.getScreenSize();//获得屏幕的大小
		setSize(285,194);
		setLocation((screenSize.width-getWidth())/4,(screenSize.height-getHeight())/4);//设置窗体位置
		final JPanel mainpanel=new JPanel();//创建主面板
		mainpanel.setLayout(new BorderLayout());//设置边框布局
		mainpanel.setBorder(new EmptyBorder(0,0,0,0));//设置边框为0
		getContentPane().add(mainpanel);//将窗体加入当容器
		final JLabel imagelabel =new JLabel(new ImageIcon("1.jpg"));//创建一个放图片的标签
		imagelabel.setOpaque(true);// 设置绘制其边界内的所有像素
		imagelabel.setBackground(Color.GREEN);//设置背景颜色
		imagelabel.setPreferredSize(new Dimension(260,60));//设置标签大小
		mainpanel.add(imagelabel,BorderLayout.NORTH);//添加标签到主界面
		final JPanel centerPanel=new JPanel();
		final GridLayout gridlayout=new GridLayout();//创建网格布局管理器、
		gridlayout.setHgap(5); //设置组件之间平行距离
		gridlayout.setVgap(20); //时至组件间的垂直距离
		centerPanel.setLayout(gridlayout);
		mainpanel.add(centerPanel);
		
		final JLabel usernamelabel=new JLabel();
		usernamelabel.setHorizontalAlignment(SwingConstants.CENTER);
		usernamelabel.setPreferredSize(new Dimension(0,0));//设置组件大小
		usernamelabel.setMinimumSize(new Dimension(0,0));//设置最小的大小
		centerPanel.add(usernamelabel);
		usernamelabel.setText("用户名");
		username.setPreferredSize(new Dimension(0,0));//设置组件大小
		centerPanel.add(username);
		final JLabel pwdlabel=new JLabel();//此标签用于对齐
		pwdlabel.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(pwdlabel);
		pwdlabel.setText("密   码");
		//password.setDocument(new Document(6));//设置密码长度为6
		password.setEchoChar('*');//设置密码的回显字符
		
		password.addKeyListener(new KeyAdapter(){//监听密码框
			public void keyPressed(final KeyEvent e){//监听按键事件
				if(e.getKeyCode()==10)//如果按了回车键
				Login.doClick();
				
			}
		});
		centerPanel.add(password);
		final JPanel southpanel=new JPanel();
		mainpanel.add(southpanel,BorderLayout.SOUTH);
		Login=new JButton();
		Login.addActionListener(new LogInAction());
		
		btnTest = new JButton("test");
		btnTest.addActionListener(new btnTestAction());
		
		southpanel.add(btnTest);
		Login.setText("登陆");
		//System.out.println("fdasfsdafdasfadsf");
		southpanel.add(Login);
		Reset=new JButton();
		Reset.addActionListener(new ResetAction());
		Reset.setText("重置");
		southpanel.add(Reset);
		setVisible(true);//设置常创建可见
		setResizable(false);//设置窗体不可改变大小
		
		
	}
	
	
	public class ResetAction implements ActionListener{
		public void actionPerformed(final ActionEvent e){
			username.setText("");//设置用户名输入框为空
			password.setText("");
			
		}
	}
	private class btnTestAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			try{
				Socket socket=new Socket("localhost",8000);
				DataOutputStream toserve;
				DataInputStream fromserve;
				fromserve=new DataInputStream(socket.getInputStream());
				toserve=new DataOutputStream(socket.getOutputStream());
				int i = 10;
				toserve.write(i);
				toserve.flush();
				System.out.println("已发送");
				int re=fromserve.readInt();
//				System.out.println(re);
			}catch (IOException ex){
				System.out.println(ex);
			}
		}
		
	}
	private class LogInAction implements  ActionListener{
		public void actionPerformed(final ActionEvent e){
			String nametemp=username.getText();
			String pwdtemp=new String(password.getPassword());
		//	Business btemp=new Business();
			String Msql="select * from userinfo where username = '"+nametemp+"' and password  ='"+ pwdtemp+"'"; 
			if(Business.check(Msql)!=null)
				 
				
			{
				System.out.println("登陆成功");
				try{
					LibMain frame=new LibMain();
					frame.setVisible(true);
					LogIn.this.setVisible(false);
					
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null,"请输入正确的用户名和密码!");//弹出提示框
			}
			
		}
	}
	
	
}