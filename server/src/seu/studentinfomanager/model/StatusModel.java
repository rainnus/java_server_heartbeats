package seu.studentinfomanager.model;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import seu.entityclass.StatusUser;
import seu.studentinfomanager.viewer.MainView;

public class StatusModel {
	
	private static MainView mView;
	
	public static void doLogin(String user, String passwd){
		//连接服务器进行信息验证
		if( user.length() == 0 || passwd.length() == 0 ){
			JOptionPane.showMessageDialog(null, "用户名和密码不能为空！");
		}
		else{
			//建立socket通讯
			ClientSocket mysocket = new ClientSocket();
			
			StringBuffer sBuffer = new StringBuffer();
			sBuffer.append("10/");
			sBuffer.append(user + "/");
			sBuffer.append(passwd);
			
			//从服务器返回对象信息
			StatusUser user2 = mysocket.getSUserMessage(sBuffer.toString());
			if(user2.getPasswd().equals(passwd)){
				
				mView = new MainView(user2);
				JFrame frame = new JFrame();
				frame.setContentPane(mView);
				frame.setSize(600, 500);
				frame.setResizable(false);
				frame.setVisible(true);
			}else {
				JOptionPane.showMessageDialog(null, "用户名或密码不正确，请重新输入！");
			}				
		}
	}
	
	public static void changeItemInfo(String item, String newInfo, StatusUser user){
		//建立socket通讯
		ClientSocket mysocket = new ClientSocket();
		
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append("11/");
		sBuffer.append(item + "/");
		sBuffer.append(newInfo + "/");
		sBuffer.append(user.getNumber());
				
		//从服务器得到反馈信息
		StatusUser user2 = mysocket.getSUserMessage(sBuffer.toString());
		if ( !user2.equals(user) ) {
			mView = new MainView(user2);
			JFrame frame = new JFrame();
			frame.setContentPane(mView);
			frame.setSize(800, 600);
			frame.setVisible(true);
			JOptionPane.showMessageDialog(null, "信息更改成功！");
		} else {
			JOptionPane.showMessageDialog(null, "信息传输失败！");
		}
	}
	
	public static void deleteUserInfo(String number){
		//建立socket通讯
		ClientSocket mysocket = new ClientSocket();
		
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append("12/");
		sBuffer.append(number);

		//从服务器得到反馈信息
		StatusUser user2 = (StatusUser)mysocket.getSUserMessage(sBuffer.toString());
		if (user2 != null) {
			mView = new MainView(user2);
			JFrame jFrame = new JFrame();
			jFrame.setContentPane(mView);
			jFrame.add(mView);
			jFrame.setVisible(true);
			JOptionPane.showMessageDialog(null, "信息删除成功！");
		} else {
			JOptionPane.showMessageDialog(null, "信息删除失败！");
		}
	}
}
