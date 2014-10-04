//package seu.serverview;
//
//import java.awt.EventQueue;
//import javax.swing.JFrame;
//import javax.swing.JButton;
//import javax.swing.JOptionPane;
//import javax.swing.JTextArea;
//import java.awt.event.ActionListener;
//import java.awt.event.ActionEvent;
//
//public class MainView {
//
//	private JFrame frame;
//	MainTread serve=null;
//
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MainView window = new MainView();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					JOptionPane.showMessageDialog(null, e.toString());
//				}
//			}
//		});
//	}
//
//	/**
//	 * Create the application.
//	 */
//	public MainView() {
//		initialize();
//	}
//
//	/**
//	 * Initialize the contents of the frame.
//	 */
//	private void initialize() {
//		frame = new JFrame();
//		frame.setBounds(100, 100, 605, 393);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.getContentPane().setLayout(null);
//
//		final JButton btnNewButton = new JButton("打开服务器");
//		final JButton button = new JButton("关闭服务器");
//		btnNewButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				 serve=new MainTread();
//				 btnNewButton.setEnabled(false);
//				 button.setEnabled(true);
//			}
//		});
//		btnNewButton.setBounds(171, 23, 115, 39);
//		frame.getContentPane().add(btnNewButton);
//
//		JTextArea textArea = new JTextArea();
//		textArea.setBounds(75, 139, 432, 181);
//		frame.getContentPane().add(textArea);
//
//		button.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				serve.close();
//				btnNewButton.setEnabled(true);
//				button.setEnabled(false);
//			}
//		});
//		button.setBounds(338, 23, 115, 39);
//		frame.getContentPane().add(button);
//	}
//}
