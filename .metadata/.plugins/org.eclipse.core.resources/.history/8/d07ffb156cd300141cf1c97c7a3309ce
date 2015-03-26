package com.leo.douban;


import java.util.concurrent.ThreadPoolExecutor;
/**
 * 准备下载的URL
 * @author CheWenliang
 *
 */
public class DownLoadMain {
    public static ThreadPoolExecutor threadPool;
    //排行榜
    private String rank = "http://www.dbmeizi.com/img/rank";
    //各种类型
    private String category = "http://www.dbmeizi.com/category/:category?p=:page";
    static{
        threadPool = ThreadPool.getInstance();
    }
    public static void main(String[] args) {
        new DownLoadMain().runTask();
    }
    /**
     * "http://www.dbmeizi.com/img/rank" 排行榜
     * http://www.dbmeizi.com/category/2?p=1 各种类型的妹纸
     * http://www.dbmeizi.com/category/1?p=1 总共有5种妹纸
     * @param resource
     */
    public void runTask(){
        HtmlParserTool parserTool = new HtmlParserTool();
        parserTool.setResource(rank);
        parserTool.setCategory("排行榜");
        threadPool.execute(parserTool);
        //此处存放各种类型的妹子
        int cateArr[] = {1,2,3,11,12};
        //各种类型
        for(int i = 0;i < cateArr.length;i++){
            //此处暂时设定1000页
            for(int j = 1;j <= 4;j++){
                //将category里的动态参数替换,类型和页码
                String categoryUrl = category.replaceAll(":category", ""+cateArr[i]).
                                    replaceAll(":page", ""+j);
                HtmlParserTool parserHtml = new HtmlParserTool(); 
                parserHtml.setResource(categoryUrl);
                parserHtml.setCategory(getCategory_ZH(cateArr[i]));
                threadPool.execute(parserHtml);
            }
        }
    }
     
    public String getCategory_ZH(int category){
        String category_ZH = "";
        switch (category) {
            case 1:category_ZH = "性感";break;
            case 2:category_ZH = "有沟";break;
            case 3:category_ZH = "美腿";break;
            case 11:category_ZH = "小清新";break;
            case 12:category_ZH = "文艺范";break;
            default:category_ZH = "未知";break;
        }
        return category_ZH;
    }
}