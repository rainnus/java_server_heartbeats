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
//	private Socket socket; // 用于对客户端的通信
//	private ServerSocket server; // 服务端server
//
//	public MainTread() {
//		this.start();		//启动服务主线程
//	}
//
//	/**
//	 * 关闭服务函数，关闭服务端
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
//	 * 线程run函数
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
