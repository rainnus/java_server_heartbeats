//package seu.serverview;
//
//import java.io.IOException;
//import java.net.*;
//
//import javax.swing.JOptionPane;
//
//import seu.server.ThreadPool;
//
//public class MainTread extends Thread {
//	private Socket socket; // ���ڶԿͻ��˵�ͨ��
//	private ServerSocket server; // �����server
//
//	public MainTread() {
//		this.start();		//�����������߳�
//	}
//
//	/**
//	 * �رշ��������رշ����
//	 */
//	@SuppressWarnings("deprecation")
//	public void close() {
//		try {
//			this.stop();
//			server.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * �߳�run����
//	 */
//	@Override
//	public void run() {
//		try {
//			server = new ServerSocket(8090);
//			ThreadPool pool = new ThreadPool();
//			while (true) {
//				socket = server.accept();
//				pool.execute(socket);
//			}
//		} catch (IOException e) {
//			JOptionPane.showMessageDialog(null, e.toString());
//		}
//	}
//}
//
