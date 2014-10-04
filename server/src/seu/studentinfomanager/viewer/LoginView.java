package seu.studentinfomanager.viewer;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

import seu.studentinfomanager.model.StatusModel;

public class LoginView {

	private JFrame frame;
	private JTextField jtfUser;
	private JPasswordField passwordField;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginView window = new LoginView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u6B22\u8FCE\u8FDB\u5165\u5B66\u7C4D\u7BA1\u7406\u7CFB\u7EDF");
		
		JPanel jPanelBackGround = new JPanel(){
            private static final long serialVersionUID = 1L;
            
            protected void paintComponent(Graphics g) {
                try {
                    BufferedImage img = ImageIO.read(new File("src\\seu\\studentinfomanager\\image\\BackGround.jpg"));
                    g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        
        frame.getContentPane().add(jPanelBackGround);
        jPanelBackGround.setLayout(null);
        
        JLabel jblUser = new JLabel("\u7528\u6237\u540D\uFF1A");
        jblUser.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 16));
        jblUser.setBounds(98, 202, 80, 27);
        jPanelBackGround.add(jblUser);
        
        JLabel jblPasswd = new JLabel("\u5BC6\u7801\uFF1A");
        jblPasswd.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 16));
        jblPasswd.setBounds(98, 264, 66, 27);
        jPanelBackGround.add(jblPasswd);
        
        jtfUser = new JTextField();
        jtfUser.setBounds(201, 202, 230, 30);
        jPanelBackGround.add(jtfUser);
        jtfUser.setColumns(10);
        
        JLabel jblHelp = new JLabel("*\u5B66\u53F7");
        jblHelp.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 14));
        jblHelp.setBounds(472, 202, 58, 27);
        jPanelBackGround.add(jblHelp);
        
        passwordField = new JPasswordField();
        passwordField.setBounds(201, 264, 230, 30);
        jPanelBackGround.add(passwordField);
        
        JButton jbtLogin = new JButton("\u767B\u9646");
        jbtLogin.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		//to do
        		StatusModel.doLogin(jtfUser.getText(), new String(passwordField.getPassword()).trim());
        	}
        });
        
        jbtLogin.setFont(new Font("ËÎÌå", Font.BOLD, 14));
        jbtLogin.setBounds(135, 368, 120, 35);
        jPanelBackGround.add(jbtLogin);
        
        JButton jbtReturn = new JButton("\u8FD4\u56DE\u4E3B\u9875\u9762");
        jbtReturn.setFont(new Font("ËÎÌå", Font.BOLD, 14));
        
        jbtReturn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		//to do 
        		frame.setVisible(false);
        	}
        });
        
        jbtReturn.setBounds(371, 368, 120, 35);
        jPanelBackGround.add(jbtReturn);
        
		frame.setBounds(100, 100, 633, 489);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
