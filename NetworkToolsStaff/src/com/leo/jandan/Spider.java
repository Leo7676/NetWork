package com.leo.jandan;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Scanner;
 
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
 

 
// 抓网页, 并分析出图片地址
class Spider implements Runnable {
    private String firstUrl = "http://jandan.net/ooxx/page-"; //1111#comments
    private String connUrl = "#comments";
    private int beginIndex = 1114;
    private String preHtml;
     
    public static int failedCount = 0;
     
    private String mSavePath;
     
    public Spider(String savePath) {
        mSavePath = savePath;
    }
     
    @Override
    public void run() {
        DefaultHttpClient client = new DefaultHttpClient();
        HttpGet get ;
        HttpResponse response ;
        String html ;
         
        client.setParams(setParams(client));
        for(;;) {
            if(Spider.failedCount > 14) {
                System.out.println("没有更多了，停止抓取");
                return;
            }
             
            System.out.println("开始分析第" + beginIndex + "页");
            try {
                get = new HttpGet(firstUrl + beginIndex + connUrl);
                response = client.execute(get);
                if(200 == response.getStatusLine().getStatusCode()) {
                    html = EntityUtils.toString(response.getEntity(), "utf-8");
                    if(html.equals(preHtml)) {
                        break;
                    }
                     
                    preHtml = html;
                     
                    Document doc = Jsoup.parse(html);
                    Elements elements = doc.select(".row img");
                    for(Element e : elements) {
                        String imgSrc = e.attr("src");
                        new Thread(new DownloadImage(imgSrc, mSavePath)).start();
                    }
                }
                beginIndex++;
            }catch(Exception e) {
                e.printStackTrace();
                break;
            }
        }
    }
     
    // 设置头信息和超时
    public static HttpParams setParams(DefaultHttpClient client) {
        HttpParams params = client.getParams();
         
        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);
        HttpProtocolParams.setUseExpectContinue(params, true);
        HttpProtocolParams
                .setUserAgent(
                        params,
                        "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/36.0.1941.0 Safari/537.36");
         
        HttpConnectionParams.setConnectionTimeout(params, 50000);
        HttpConnectionParams.setSoTimeout(params, 50000);
         
        return params;
    }
}
 
