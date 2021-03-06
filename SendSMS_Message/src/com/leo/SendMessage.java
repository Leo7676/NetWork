package com.leo;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

public class SendMessage extends JFrame {

	private JPanel contentPane;
	private JTextField cellphone_Number_Field;
	private JTextArea send_Contents;

	/**
	 * Launch the application.
	 */
	public void printInfo() {

		System.out.println("发送到：" + cellphone_Number_Field.getText());
		System.out.println("内容为：" + send_Contents.getText());
		// SendMsg_webchinese sendSMS = new SendMsg_webchinese();
		// boolean flag = sendSMS.sendMessage(cellphone_Number_Field.getText()
		// .trim(), send_Contents.getText().trim());
		// boolean flag=true;
		// if (flag) {
		// JOptionPane.showMessageDialog(this, "短信发送成功！");
		// } else {
		// JOptionPane.showMessageDialog(this, "短信发送失败！");
		// }
	}

	public static void main(String[] args) {

		// try {
		// UIManager.setLookAndFeel(UIManager
		// .getSystemLookAndFeelClassName());
		// } catch (ClassNotFoundException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// } catch (InstantiationException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// } catch (IllegalAccessException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// } catch (UnsupportedLookAndFeelException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// }

		// UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		try {

			// BeautyEyeLNFHelper.frameBorderStyle =
			// BeautyEyeLNFHelper.frameBorderStyle.osLookAndFeelDecorated;
//			BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.frameBorderStyle.translucencyAppleLike;
			BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.frameBorderStyle.translucencySmallShadow;
			
			
//			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
			
			
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			
		} catch (Exception e) {
			// TODO exception
		}
		UIManager.put("RootPane.setupButtonVisible", false);

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SendMessage frame = new SendMessage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SendMessage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 585, 485);
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image image = tk.createImage("images/logo1.png");
		setIconImage(image);
		setTitle("短信发送程序");
		// setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel main_panel = new JPanel();
		main_panel.setBounds(10, 10, 549, 427);
		contentPane.add(main_panel);
		main_panel.setLayout(null);

		JPanel up_panel = new JPanel();
		up_panel.setBorder(new EtchedBorder(EtchedBorder.RAISED, new Color(255, 200, 0), new Color(0, 0, 255)));
		up_panel.setBounds(21, 10, 507, 91);
		main_panel.add(up_panel);
		up_panel.setLayout(null);

		cellphone_Number_Field = new JTextField();
		cellphone_Number_Field.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		cellphone_Number_Field.setBounds(84, 35, 162, 33);
		up_panel.add(cellphone_Number_Field);
		cellphone_Number_Field
				.setToolTipText("\u8F93\u5165\u4F60\u8981\u53D1\u9001\u77ED\u4FE1\u7684\u624B\u673A\u53F7\u7801");
		cellphone_Number_Field.setColumns(10);

		JLabel cellphone_Number_Label = new JLabel("\u624B\u673A\u53F7\uFF1A");
		cellphone_Number_Label.setFont(new Font("微软雅黑", Font.PLAIN, 17));
		cellphone_Number_Label.setBounds(11, 30, 86, 51);
		up_panel.add(cellphone_Number_Label);

		JButton send_Button = new JButton("\u53D1\u9001");
//		JButton send_Button = new JButton(new ImageIcon("images\\send_button1.png"));
		send_Button.setForeground(Color.WHITE);
		send_Button.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		send_Button.setBounds(278, 10, 198, 58);
		send_Button.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue)); //设置按钮的颜色
		up_panel.add(send_Button);

		JPanel down_panel = new JPanel();
		down_panel.setBounds(30, 130, 493, 254);
		main_panel.add(down_panel);
		down_panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u77ED\u4FE1\u5185\u5BB9\u533A", TitledBorder.LEADING, TitledBorder.TOP, null, Color.MAGENTA));
		down_panel.setLayout(null);

		send_Contents = new JTextArea();
		send_Contents.setBounds(20, 51, 458, 180);
		down_panel.add(send_Contents);
		send_Contents.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		send_Contents.setLineWrap(true);
		send_Contents
				.setToolTipText("\u8F93\u5165\u4F60\u8981\u53D1\u9001\u7684\u77ED\u4FE1\u5185\u5BB9");
		send_Contents.setWrapStyleWord(true);

		JLabel send_Contents_Label = new JLabel(
				"\u8F93\u5165\u4F60\u53D1\u9001\u7684\u77ED\u4FE1\u5185\u5BB9\uFF1A");
		send_Contents_Label.setBounds(20, 16, 171, 25);
		down_panel.add(send_Contents_Label);
		send_Contents_Label.setFont(new Font("微软雅黑", Font.PLAIN, 13));

		send_Button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				printInfo();
				// TODO Auto-generated method stub
//				if (cellphone_Number_Field.getText().trim() != ""
//						&& cellphone_Number_Field != null) {
//
//					System.out.println("手机号码:"+cellphone_Number_Field.getText().trim());
//					printInfo();
//				} else {
//					JOptionPane.showMessageDialog(null, "请输入手机号码");
//				}

			}

		});
	}
}
