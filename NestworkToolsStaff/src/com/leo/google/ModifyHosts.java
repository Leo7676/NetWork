package com.leo.google;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
 
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
 
/**
 * 修改Hosts文件...目的你懂得
 * ip来源于:http://www.360kb.com/kb/2_122.html,感谢
 * 代码copy了一部分网上的代码,原因是实在不想敲...感谢
 * 
 * OSC有一个Python版本的,这个是java的,有一个简单的UI
 * 点击按钮即可修改Hosts文件,覆盖式的,所以如果你的Hosts文件中有重要的东西,请先备份或修改本程序
 * 点击按钮后下方文本域即可显示修改结果...
 * 
 * 你可以把这个打成个jar包,每天点一点...
 * @author shenhongliang
 *
 */
public class ModifyHosts {
     
    public static String readInputStream(InputStream inputStream)
            throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        String data = new String(bos.toByteArray(), "UTF-8");
        return data;
    }
 
    public static String file_put_contents(String filename, String content) throws IOException{
        File files = new File(filename);
        FileOutputStream out = new FileOutputStream(files);
        byte by[] = content.getBytes();
        out.write(by);
        out.close();
        return file_get_contents(filename);
    }
 
    public static String file_get_contents(String filename) throws IOException{
        File files = new File(filename);
        FileInputStream in = new FileInputStream(files);
        byte byt[] = new byte[32768];
        int length = in.read(byt);
        in.close();
        return new String(byt, 0, length);
    }
 
    public static String modifyHost() throws IOException {
        URL url = new URL("http://www.360kb.com/kb/2_122.html");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        InputStream inputStream = conn.getInputStream(); // 通过输入流获得网站数据
        String htmlString = readInputStream(inputStream);
 
        htmlString = htmlString.replaceAll("&nbsp;", "");
        htmlString = htmlString.replaceAll("<span>", "");
        htmlString = htmlString.replaceAll("</span>", "");
        htmlString = htmlString.replaceAll("<br />", "");
 
        htmlString = htmlString.substring(htmlString.indexOf("#base services"),
                htmlString.indexOf("#google hosts 2015 end"));
 
        String filename = "C:/Windows/System32/drivers/etc/Hosts";
        return file_put_contents(filename, htmlString);
    }
 
    public static void main(String[] args) {
        myJFrame frame1 = new myJFrame();
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 一定要设置关闭
        frame1.setVisible(true);
    }
 
}
 
class myJFrame extends JFrame {
    private static final long serialVersionUID = -6921659932048296022L;
 
    public myJFrame() {
        super();
        //init myframe
        this.setSize(600, 600);
        this.setLocation(100, 100);
        this.setTitle("Get Google New IP");
        //add button and scrollPanel to panel
        panel.add(button);
        panel.add(scrollPanel);
        add(panel);
        //add button event listener
        button.addActionListener(new ButtonListen(ipJTextArea));
        button.setLocation(290, 20);
        //set TextColor Red when selected
        ipJTextArea.setSelectedTextColor(Color.RED);
        //Auto appear
        scrollPanel.setHorizontalScrollBarPolicy( 
        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
        scrollPanel.setVerticalScrollBarPolicy( 
        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
    }
 
    JButton button = new JButton("Modify Host!");
    JPanel panel = new JPanel();
    JTextArea ipJTextArea = new JTextArea("这里显示的是Hosts文件的内容", 30, 50);
    JScrollPane scrollPanel = new JScrollPane(ipJTextArea);
}
 
class ButtonListen implements ActionListener {
    JTextArea ipJTextArea;
    public ButtonListen(JTextArea ipJTextArea){
        this.ipJTextArea = ipJTextArea;
    }
     
    public void actionPerformed(ActionEvent e) {
        ipJTextArea.setText("");
        try {
            String hostFileContent = ModifyHosts.modifyHost();
            ipJTextArea.append(hostFileContent);
        } catch (Exception e1) {
            ipJTextArea.append("Modify Failure!");
        }
    }
}