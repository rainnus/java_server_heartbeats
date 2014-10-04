package seu.studentinfomanager.model;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import seu.entityclass.StatusUser;
import seu.studentinfomanager.viewer.MainView;

public class StatusModel {
	
	private static MainView mView;
	
	public static void doLogin(String user, String passwd){
		//���ӷ�����������Ϣ��֤
		if( user.length() == 0 || passwd.length() == 0 ){
			JOptionPane.showMessageDialog(null, "�û��������벻��Ϊ�գ�");
		}
		else{
			//����socketͨѶ
			ClientSocket mysocket = new ClientSocket();
			
			StringBuffer sBuffer = new StringBuffer();
			sBuffer.append("10/");
			sBuffer.append(user + "/");
			sBuffer.append(passwd);
			
			//�ӷ��������ض�����Ϣ
			StatusUser user2 = mysocket.getSUserMessage(sBuffer.toString());
			if(user2.getPasswd().equals(passwd)){
				
				mView = new MainView(user2);
				JFrame frame = new JFrame();
				frame.setContentPane(mView);
				frame.setSize(600, 500);
				frame.setResizable(false);
				frame.setVisible(true);
			}else {
				JOptionPane.showMessageDialog(null, "�û��������벻��ȷ�����������룡");
			}				
		}
	}
	
	public static void changeItemInfo(String item, String newInfo, StatusUser user){
		//����socketͨѶ
		ClientSocket mysocket = new ClientSocket();
		
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append("11/");
		sBuffer.append(item + "/");
		sBuffer.append(newInfo + "/");
		sBuffer.append(user.getNumber());
				
		//�ӷ������õ�������Ϣ
		StatusUser user2 = mysocket.getSUserMessage(sBuffer.toString());
		if ( !user2.equals(user) ) {
			mView = new MainView(user2);
			JFrame frame = new JFrame();
			frame.setContentPane(mView);
			frame.setSize(800, 600);
			frame.setVisible(true);
			JOptionPane.showMessageDialog(null, "��Ϣ���ĳɹ���");
		} else {
			JOptionPane.showMessageDialog(null, "��Ϣ����ʧ�ܣ�");
		}
	}
	
	public static void deleteUserInfo(String number){
		//����socketͨѶ
		ClientSocket mysocket = new ClientSocket();
		
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append("12/");
		sBuffer.append(number);

		//�ӷ������õ�������Ϣ
		StatusUser user2 = (StatusUser)mysocket.getSUserMessage(sBuffer.toString());
		if (user2 != null) {
			mView = new MainView(user2);
			JFrame jFrame = new JFrame();
			jFrame.setContentPane(mView);
			jFrame.add(mView);
			jFrame.setVisible(true);
			JOptionPane.showMessageDialog(null, "��Ϣɾ���ɹ���");
		} else {
			JOptionPane.showMessageDialog(null, "��Ϣɾ��ʧ�ܣ�");
		}
	}
}
