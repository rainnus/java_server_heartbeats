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
		final BorderLayout borderLayout=new BorderLayout();//�������ֹ�����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//���ùرհ�ť�����¼�
		borderLayout.setVgap(10);//���������Ĵ�ֱ����
		getContentPane().setLayout(borderLayout);//ʹ�ò��ֹ�����
		
		setTitle("ѧ������ϵͳ");
		Toolkit tool =Toolkit.getDefaultToolkit(); //���Ĭ�ϵĹ�����
		Dimension screenSize=tool.getScreenSize();//�����Ļ�Ĵ�С
		setSize(285,194);
		setLocation((screenSize.width-getWidth())/4,(screenSize.height-getHeight())/4);//���ô���λ��
		final JPanel mainpanel=new JPanel();//���������
		mainpanel.setLayout(new BorderLayout());//���ñ߿򲼾�
		mainpanel.setBorder(new EmptyBorder(0,0,0,0));//���ñ߿�Ϊ0
		getContentPane().add(mainpanel);//��������뵱����
		final JLabel imagelabel =new JLabel(new ImageIcon("1.jpg"));//����һ����ͼƬ�ı�ǩ
		imagelabel.setOpaque(true);// ���û�����߽��ڵ���������
		imagelabel.setBackground(Color.GREEN);//���ñ�����ɫ
		imagelabel.setPreferredSize(new Dimension(260,60));//���ñ�ǩ��С
		mainpanel.add(imagelabel,BorderLayout.NORTH);//��ӱ�ǩ��������
		final JPanel centerPanel=new JPanel();
		final GridLayout gridlayout=new GridLayout();//�������񲼾ֹ�������
		gridlayout.setHgap(5); //�������֮��ƽ�о���
		gridlayout.setVgap(20); //ʱ�������Ĵ�ֱ����
		centerPanel.setLayout(gridlayout);
		mainpanel.add(centerPanel);
		
		final JLabel usernamelabel=new JLabel();
		usernamelabel.setHorizontalAlignment(SwingConstants.CENTER);
		usernamelabel.setPreferredSize(new Dimension(0,0));//���������С
		usernamelabel.setMinimumSize(new Dimension(0,0));//������С�Ĵ�С
		centerPanel.add(usernamelabel);
		usernamelabel.setText("�û���");
		username.setPreferredSize(new Dimension(0,0));//���������С
		centerPanel.add(username);
		final JLabel pwdlabel=new JLabel();//�˱�ǩ���ڶ���
		pwdlabel.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(pwdlabel);
		pwdlabel.setText("��   ��");
		//password.setDocument(new Document(6));//�������볤��Ϊ6
		password.setEchoChar('*');//��������Ļ����ַ�
		
		password.addKeyListener(new KeyAdapter(){//���������
			public void keyPressed(final KeyEvent e){//���������¼�
				if(e.getKeyCode()==10)//������˻س���
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
		Login.setText("��½");
		//System.out.println("fdasfsdafdasfadsf");
		southpanel.add(Login);
		Reset=new JButton();
		Reset.addActionListener(new ResetAction());
		Reset.setText("����");
		southpanel.add(Reset);
		setVisible(true);//���ó������ɼ�
		setResizable(false);//���ô��岻�ɸı��С
		
		
	}
	
	
	public class ResetAction implements ActionListener{
		public void actionPerformed(final ActionEvent e){
			username.setText("");//�����û��������Ϊ��
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
				System.out.println("�ѷ���");
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
				System.out.println("��½�ɹ�");
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
				JOptionPane.showMessageDialog(null,"��������ȷ���û���������!");//������ʾ��
			}
			
		}
	}
	
	
}