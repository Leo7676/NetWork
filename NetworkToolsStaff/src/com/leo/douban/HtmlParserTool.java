package com.leo.douban;


import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.util.NodeList;
/**
 * 抓取网页中的图片
 * @author CheWenliang
 *
 */
public class HtmlParserTool extends Thread{
    public String resource;
    public String category;
     
    public static void main(String[] args) {
        //doParser("http://www.dbmeizi.com/img/rank");
    }
    /**
     * http://www.dbmeizi.com/category/2?p=1
     * http://www.dbmeizi.com/category/1?p=1
     * @param resource
     */
    public void run(){
        try {
            Parser myParser = new Parser(resource);
            // 设置编码
            myParser.setEncoding("GBK");
            String filterStr = "img";
            NodeFilter filter = new TagNameFilter(filterStr);
            NodeList nodeList = myParser.extractAllNodesThatMatch(filter);
            for (int i = 1; i < nodeList.size(); i++) {
                ImageTag imgtag = (ImageTag) nodeList.elementAt(i);
                String imageUrl = imgtag.getImageURL();
                downLoadUtils.downLoadPic(imageUrl,category);
                System.out.println(imageUrl+"-"+category+"-下载完成");
            }
        } catch (Exception e) {
            System.out.println("无内容");
        }
    }
    public String getResource() {
        return resource;
    }
    public void setResource(String resource) {
        this.resource = resource;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
     
}