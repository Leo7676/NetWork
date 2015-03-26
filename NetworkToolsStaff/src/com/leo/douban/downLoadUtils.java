package com.leo.douban;


import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
 
/**
 * 通过图片url下载图片
 * 
 * @author Chewl
 * 
 */
public class downLoadUtils {
    public static void main(String[] args) throws Exception {
        downLoadPic("http://pic.dbmeizi.com/npics/6ec/539/s_p26401749.jpg","小清新");
    }
 
    /**
     * http://www.dbmeizi.com/category/2?p=1
     * http://www.dbmeizi.com/category/1?p=1
     * 
     * @param picUrl
     * @throws Exception
     */
    public static void downLoadPic(String picUrl,String category) throws Exception {
        URL url = new URL(picUrl);
        URLConnection uc = url.openConnection();
        InputStream is = uc.getInputStream();
        String picName = picUrl.split("/")[picUrl.split("/").length - 1];
        File dir = new File("D:/douban/" + category);
        if(!dir.exists()){
            dir.mkdirs();
        }
        File imageFile = new File("D:/douban/" + category + "/"+ picName);
        FileOutputStream out = new FileOutputStream(imageFile);
        int i = 0;
        while ((i = is.read()) != -1) {
            out.write(i);
        }
        is.close();
        out.close();
    }
}