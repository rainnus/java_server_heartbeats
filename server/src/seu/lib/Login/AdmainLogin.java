package seu.lib.Login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import seu.lib.soket.ClientSocket;
import seu.lib.view.Business;
import seu.lib.view.LibMain;


public class AdmainLogin extends JFrame {
	private JTextField admain;
	private JPasswordField password = new JPasswordField(10);
	
	public AdmainLogin(){
		super();
		setTitle("管理员登陆");
		setBounds(460,200,441,304);
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u7528\u6237\u540D");
		label.setBounds(93, 53, 63, 26);
		getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u5BC6\u7801");
		label_1.setBounds(93, 101, 63, 26);
		getContentPane().add(label_1);
		
		final JButton btnLogin = new JButton("\u767B\u9646");
		btnLogin.setBounds(93, 179, 77, 32);
		getContentPane().add(btnLogin);
		
		JButton btnback = new JButton("\u53D6\u6D88");
		btnback.setBounds(243, 179, 77, 32);
		getContentPane().add(btnback);
		
		admain = new JTextField();
		admain.setBounds(201, 55, 135, 23);
		getContentPane().add(admain);
		admain.setColumns(10);
		
		password = new JPasswordField();
		password.setBounds(201, 104, 135, 23);
		getContentPane().add(password);
	
		password.setEchoChar('*');//设置密码的回显字符
		
		password.addKeyListener(new KeyAdapter(){//监听密码框
			public void keyPressed(final KeyEvent e){//监听按键事件
				if(e.getKeyCode()==10)//如果按了回车键
				btnLogin.doClick();
				
			}
		});
		
		
		setVisible(true);
		setResizable(false);
		btnLogin.addActionListener(new btnLoginAction());
		btnback.addActionListener(new btnbackAction());
		
		
	}
	private class btnLoginAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String nametemp=admain.getText();
			String pwdtemp=new String(password.getPassword());
			
			//Business btemp=new Business();
			String msg="36/"+nametemp+"/"+pwdtemp;
			System.out.println(msg);
			ClientSocket myClient1 = new ClientSocket();
			String test=null;
			test=myClient1.getStrMessage(msg);
			System.out.println("      this is        "+test);
			
			if((test.equals("true")))
				 
				
			{
				try{
					//Main frame=new Main();
					System.out.println("已获得管理员权限");
					
					//frame.setVisible(true);
					LibMain.jbtbookadd.setEnabled(true);
					LibMain.jbtborinfo.setEnabled(true);
					LibMain.deletebook.setEnabled(true);
					JOptionPane.showMessageDialog(null, "已获取管理员权限！试试右边的按钮吧~~");
					AdmainLogin.this.setVisible(false);
					
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
	private class btnbackAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			setVisible(false);
		}

	}
}
