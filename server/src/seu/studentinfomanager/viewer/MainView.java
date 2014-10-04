package seu.studentinfomanager.viewer;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import org.omg.CORBA.PUBLIC_MEMBER;

import seu.entityclass.StatusUser;
import seu.studentinfomanager.model.StatusModel;

public class MainView extends JPanel{

	StatusUser sUser = new StatusUser();
	
	//private JPanel contentPanel = new JPanel(); //主面板
	private JPanel topPanel = new JPanel();     //顶面板
	private JPanel centerPanel = new JPanel();  //中间面板
	
	private JLabel jblBackground = new JLabel();
	final private JPanel jplmyindex = new JPanel();
	final private JPanel jplinfoChange = new JPanel();
	
	private JLabel lblImage;
	private JTable tableInfo;
	
	private JComboBox<String> jcbItemchange;
	private JTextField jtfItemChange;
	
	private JButton jbtindex = new JButton();
	private JButton jbtupdate = new JButton();
	
	ImageIcon icon_jbstumyindex = new ImageIcon("src\\seu\\studentinfomanager\\image\\sta_info.png");
	ImageIcon icon_jbstumyindex2 = new ImageIcon("src\\seu\\studentinfomanager\\image\\sta_info2.png");
	ImageIcon icon_jbstuchangeinfo = new ImageIcon("src\\seu\\studentinfomanager\\image\\sta_weihu.png");
	ImageIcon icon_jbstuchangeinfo2 = new ImageIcon("src\\seu\\studentinfomanager\\image\\sta_weihu2.png");
	
	JButton buttons[] = new JButton[]{jbtindex,jbtupdate};
	ImageIcon button_icons[][] = new ImageIcon[][]{{icon_jbstumyindex, icon_jbstumyindex2} , {icon_jbstuchangeinfo, icon_jbstuchangeinfo2 }};
	
	private int nButtonSelected = 0;
	
	public MainView() {
		initialize();
	}

	public MainView(StatusUser sUser){
		this.sUser = sUser;
		initialize();
		setVisible(true);
	}
	
	private void initialize() {
		
		//本模块所有功能面板切换选项按钮
        topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
        topPanel.setBackground(Color.gray);
        
		for(int i=0; i<2; i++) {
			buttons[i] = new JButton(button_icons[i][0]);
			buttons[i].setRolloverIcon(button_icons[i][1]);
			buttons[i].setPressedIcon(button_icons[i][1]);
			buttons[i].setSelectedIcon(button_icons[i][1]);
			
			topPanel.add(buttons[i]);
			buttons[i].setBounds(100*i, 0, 100, 60);
			buttons[i].setBorder(null);
		}
		//设置面板信息
        setLayout(new BorderLayout());
        
        //个人主页子面板
        tableInfo = new JTable();                //显示学籍信息
        tableInfo.setBounds(10, 15, 336, 390);
        tableInfo.setFont(new Font("宋体", Font.BOLD, 16));
        tableInfo.setGridColor(Color.GREEN);
        tableInfo.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        tableInfo.setBackground(new Color(230, 230, 250));
        
        DefaultTableModel tModel = new DefaultTableModel(
            	new Object[][] {
            		{"    姓名", sUser.getuName()},
            		{"    学号", sUser.getNumber()},
            		{"    性别", sUser.getSex()},
            		{"    民族", sUser.getNation()},
            		{"    身份证号",sUser.getIDnumber()},
            		{"    出生年月", sUser.getBirthDate()},
            		{"    年龄", sUser.getAge()},
            		{"    政治面貌", sUser.getPolitics()},
            		{"    入学年份", sUser.getEduStartDate()},
            		{"    学院", sUser.getCollege()},
            		{"    专业", sUser.getMajor()},
            		{"    学制", sUser.getSchoolingLength()},
            		{"    学历", sUser.getEducation()},
            	},
            	new String[] {
                		"信息项", "信息"
                	}){
						private static final long serialVersionUID = 1L;
						
						//设置表格信息不可更改
						public boolean isCellEditable(int row, int column)
			            {
			                return false;
			            }
        };
        
        tableInfo.setModel(tModel);
        tableInfo.getColumnModel().getColumn(0).setPreferredWidth(15);
        jplmyindex.setLayout(null);
        tableInfo.setRowHeight(30);
        jplmyindex.add(tableInfo);
        
        lblImage = new JLabel("");
        lblImage.setBounds(395, 34, 182, 227);
        ImageIcon icon;
        if(sUser!=null){
        	icon = new ImageIcon("src\\seu\\studentinfomanager\\image\\"+sUser.getNumber()+".jpg");
        	icon.setImage(icon.getImage().getScaledInstance(lblImage.getWidth(),lblImage.getHeight(),Image.SCALE_DEFAULT));
        }else{
        	icon = new ImageIcon("src\\seu\\studentinfomanager\\image\\1.jpg");
        	icon.setImage(icon.getImage().getScaledInstance(182,227,Image.SCALE_DEFAULT));
        }
        lblImage.setIcon(icon);
        jplmyindex.add(lblImage);
        
        //信息维护面板
        jplinfoChange.setLayout(null);
        
        JLabel label_1 = new JLabel("\u8BF7\u9009\u62E9\u8981\u4FEE\u6B63\u7684\u4FE1\u606F\uFF1A");
        label_1.setBounds(50, 48, 177, 30);
        label_1.setFont(new Font("宋体", Font.BOLD, 14));
        jplinfoChange.add(label_1);
        
        jcbItemchange = new JComboBox<String>();
        jcbItemchange.setModel(new DefaultComboBoxModel<String>(new String[] {"\u653F\u6CBB\u9762\u8C8C", "\u5B66\u9662", "\u4E13\u4E1A", "\u5B66\u5236", "\u5B66\u5386"}));
        jcbItemchange.setBounds(49, 88, 178, 30);
        
        final String[] iteminfo = new String[]{sUser.getPolitics(),sUser.getCollege(),sUser.getMajor(),sUser.getSchoolingLength(),sUser.getEducation()};
        
        jcbItemchange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int index = jcbItemchange.getSelectedIndex();
				jtfItemChange.setText(iteminfo[index]);
			}
		});
        jplinfoChange.add(jcbItemchange);
        
        JLabel label_2 = new JLabel("\u8BF7\u66F4\u6539\u4FE1\u606F\uFF1A");
        label_2.setFont(new Font("宋体", Font.BOLD, 14));
        label_2.setBounds(321, 48, 116, 22);
        jplinfoChange.add(label_2);
        
        jtfItemChange = new JTextField(sUser.getPolitics());
        jtfItemChange.setBounds(321, 89, 170, 30);
        jplinfoChange.add(jtfItemChange);
        jtfItemChange.setColumns(10);
        
        JButton jbtOK = new JButton("\u786E\u5B9A");
        jbtOK.setBounds(377, 206, 116, 30);
        final String[] item = new String[]{"Politics" , "College", "Major", "SchoolingLength", "Education"};
        jbtOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if ( iteminfo[jcbItemchange.getSelectedIndex()].equals(jtfItemChange.getText().trim())) {
					JOptionPane.showMessageDialog(null, "修改信息和原始信息相同，请重新填写！");
				} 
				else {
					StatusModel.changeItemInfo(item[jcbItemchange.getSelectedIndex()], jtfItemChange.getText().trim(), sUser);
				}
			}
		});
        jplinfoChange.add(jbtOK);
        
        JButton jbtDelete = new JButton("\u5220\u9664\u6211\u7684\u5B66\u7C4D\u4FE1\u606F");
        jbtDelete.setFont(new Font("宋体", Font.BOLD, 14));
        jbtDelete.setForeground(Color.RED);
        jbtDelete.setBounds(50, 296, 177, 30);
        jbtDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				StatusModel.deleteUserInfo(sUser.getNumber());
			}
		});
        
        jplinfoChange.add(jbtDelete);
        
        buttons[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setSed(0);
				if (0>nButtonSelected) {
					slidePanel(jplmyindex,2);
				}else {
					slidePanel(jplmyindex,1);
				}
				nButtonSelected = 0;
			}
		});
        
        buttons[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setSed(1);
				if(1>nButtonSelected){
					slidePanel(jplinfoChange,2);
				}
				else {
					slidePanel(jplinfoChange,1);
				}
				nButtonSelected = 1;
			}
		});
        
        centerPanel = new JPanel(){
        	private static final long serialVersionUID = 1L;           
            protected void paintComponent(Graphics g) {
                try {
                    BufferedImage img = ImageIO.read(new File("src//seu//studentinfomanager//image//BackGround.jpg"));
                	g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        centerPanel.setLayout(null);
        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
	}
	
	public JLabel getLblImage(){
		return lblImage;
	}
	
	public JTable getTableInfo(){
		return tableInfo;
	}
	
	public JComboBox<String> getJcbItemchange(){
		return jcbItemchange;
	}
	
	public JTextField getJtfItemChange(){
		return jtfItemChange;
	}
	
	// 滑动效果方法
	public void slidePanel(final JPanel panel , final int dir) {
		panel.setBounds(0, 0, centerPanel.getWidth(), centerPanel.getHeight());
		int count = centerPanel.getComponentCount();
		List<Component> list = new ArrayList<Component>();
		for (Component comp : centerPanel.getComponents()) {
			list.add(comp);
		}
		if (count > 0) {
			for (int i = 0; i < count; i++) {
				Component comp = centerPanel.getComponent(i);

				if (comp instanceof JPanel) {
					final JPanel currentPanel = (JPanel) comp;
					if (currentPanel != panel) {
						
						new Thread() {
							public void run() {
								switch (dir){
									case 1:// 左→右
										int x1 = -centerPanel.getWidth();
	
										for (int i = 0; i <= centerPanel.getWidth(); i += 5) {
											// 设置面板位置
											currentPanel.setBounds(i, 0,
													centerPanel.getWidth(),
													centerPanel.getHeight());
											panel.setBounds(x1, 0,
													centerPanel.getWidth(),
													centerPanel.getHeight());
											try {
												Thread.sleep(2);
											} catch (InterruptedException e) {
												JOptionPane.showMessageDialog(null, e.toString());
											}
											x1 += 5;
										}
										break;
										
									case 2://右→左
										int x2 = centerPanel.getWidth();
	
										for (int i = 0; i >= -centerPanel.getWidth(); i -= 5) {
											currentPanel.setBounds(i, 0,
													centerPanel.getWidth(),
													centerPanel.getHeight());
											panel.setBounds(x2, 0,
													centerPanel.getWidth(),
													centerPanel.getHeight());
											try {
												Thread.sleep(2);
											} catch (InterruptedException e) {
												JOptionPane.showMessageDialog(null, e.toString());
											}
											x2 -= 5;
										}
										break;
										
									case 3:
										break;
									case 4:
										break;
								}
								centerPanel.remove(currentPanel);
								panel.setBounds(0, 0, centerPanel.getWidth(),
										centerPanel.getHeight());
							}
						}.start();
						break;
					}
				}
			}
		}

		if (!list.contains(panel)) {
			centerPanel.add(panel);// 添加要切换的面板
		}

		centerPanel.validate();// 重构内容面板
		centerPanel.repaint();// 重绘内容面板
	}

	private void setSed(int to_select) {
		setNSed(nButtonSelected);
		nButtonSelected = to_select;
		buttons[to_select].setIcon(button_icons[to_select][1]);		
	}
	// setNSed = set (looks like) not selected
	private void setNSed(int selected) {
		buttons[selected].setIcon(button_icons[selected][0]);
	}
}