package com.leo.jandan;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

//判断图片是否已经下载，并下载图片
//没张图片都单独开一个线程下载
//是不是太消耗资源？
//使用线程池？
class DownloadImage implements Runnable {
 private String imageSrc;
 private String imageName;
  
 private String mSavePath;
  
 public DownloadImage(String imageSrc, String savePath) {
     this.imageSrc = imageSrc;
     mSavePath = savePath;
 }
  
 private boolean isImageExists() {
     File dir = new File(mSavePath);
     if(!dir.exists()) {
         dir.mkdirs();
     }
      
     String[] splits = imageSrc.split("/");
     imageName = splits[splits.length - 1];
      
     return new File(dir + File.separator + imageName).exists();
 }
  
 @Override
 public void run() {
     if(isImageExists()) {
         Spider.failedCount++;
         return ;
     }
      
     Spider.failedCount = 0;
      
     DefaultHttpClient client = null;
     HttpGet get ;
     HttpResponse response ;
     System.out.println("开始下载妹子：" + imageName);
      
     InputStream in = null;
     FileOutputStream fos = null;
      
     try {
         client = new DefaultHttpClient();
         get= new HttpGet(imageSrc);
         response= client.execute(get);
         if(200 == response.getStatusLine().getStatusCode()) {
             in = response.getEntity().getContent();
             byte[] by = new byte[1024];
             int len = -1;
             fos = new FileOutputStream(mSavePath + File.separator + imageName);
             while(-1 != (len = in.read(by))) {
                 fos.write(by, 0, len);
             }
              
             fos.flush();
             in.close();
             fos.close();
         }
          
         System.out.println("妹子" + imageName + "下载完成");
         client.getConnectionManager().shutdown();
     }catch(Exception e) {
         try {
             in.close();
             fos.close();
         } catch (Exception e1) {
         }
         client.getConnectionManager().shutdown();
         System.err.println("抛出异常并不可怕， 这个妹子我不要了！\n删除妹子" + imageName);
         new File(mSavePath + File.separator + imageName).delete();
         return;
     }
 }
}