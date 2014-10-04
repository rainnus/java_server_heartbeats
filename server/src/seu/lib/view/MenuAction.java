package seu.lib.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import seu.lib.Login.AdmainLogin;

//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;

public class MenuAction implements  ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	
		
		if (e.getSource()==LibMain.jbtbookadd){
			System.out.println("跳至 添加图书界面");
			LibMain.c.show(LibMain.cardpanel, "addbook");
//			Main.showpanel.setVisible(false);
//			Main.deletepanel.setVisible(false);
//			Main.borinfopanel.setVisible(false);
//			Main.backpanel.setVisible(false);
//			//Main.addpanel.setVisible(false);

			//new bookAdd();
			
		}
		else if(e.getSource()==LibMain.jbtmain){
			System.out.println("跳至 主界面");
			//Main.c.show(Main.cardpanel, "borinfo");
			LibMain.showpanel.setVisible(false);
			LibMain.deletepanel.setVisible(false);
			LibMain.borinfopanel.setVisible(false);
			LibMain.backpanel.setVisible(false);
			LibMain.addpanel.setVisible(false);

			
		}
		else if(e.getSource()==LibMain.jbtborinfo){
			System.out.println("跳至 图书借阅界面");
			LibMain.c.show(LibMain.cardpanel, "borinfo");
//			Main.showpanel.setVisible(false);
//			Main.deletepanel.setVisible(false);
//			//Main.borinfopanel.setVisible(false);
//			Main.backpanel.setVisible(false);
//			Main.addpanel.setVisible(false);

			
		}
		else if (e.getSource()==LibMain.deletebook){
			System.out.println("跳至 删除图书界面");
			LibMain.c.show(LibMain.cardpanel, "bookdelete");
//			Main.showpanel.setVisible(false);
//			//Main.deletepanel.setVisible(false);
//			Main.borinfopanel.setVisible(false);
//			Main.backpanel.setVisible(false);
//			Main.addpanel.setVisible(false);

			
		}

		else if (e.getSource()==LibMain.jbtbookborrow){
			System.out.println("跳至 图书借阅界面");
			
			LibMain.c.show(LibMain.cardpanel, "allbooks");
//		//	Main.showpanel.setVisible(false);
//			Main.deletepanel.setVisible(false);
//			Main.borinfopanel.setVisible(false);
//			Main.backpanel.setVisible(false);
//			Main.addpanel.setVisible(false);

			
		}
		else if (e.getSource()==LibMain.jbtbookback){
			System.out.println("跳至 图书归还界面");
			LibMain.c.show(LibMain.cardpanel, "bookback");
//			Main.showpanel.setVisible(false);
//			Main.deletepanel.setVisible(false);
//			Main.borinfopanel.setVisible(false);
//		//	Main.backpanel.setVisible(false);
//			Main.addpanel.setVisible(false);

			//new bookback();
		}
		else if (e.getSource()==LibMain.jbtadmain){
			new AdmainLogin();
		}
//		else if (e.getSource()==Main.item1){
//			System.out.println("跳转至showbook");
//			showBooks fram=new showBooks();
//			//fram.setVisible(true);
//			
//		}
		
	}
	
	
	

}
