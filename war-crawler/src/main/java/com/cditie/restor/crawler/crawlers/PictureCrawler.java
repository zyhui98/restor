package com.cditie.restor.crawler.crawlers;

import cn.wanghaomiao.seimi.annotation.Crawler;
import cn.wanghaomiao.seimi.def.BaseSeimiCrawler;
import cn.wanghaomiao.seimi.struct.Request;
import cn.wanghaomiao.seimi.struct.Response;
import cn.wanghaomiao.xpath.model.JXDocument;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cditie.restor.crawler.dao.mybatis.MybatisStoreDAO;
import com.cditie.restor.crawler.model.BlogContent;
import com.cditie.restor.crawler.model.FeedDetail;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Nullable;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author zhuyunhui
 * @since 4/27/2018
 */
@Crawler(name = "picture")
@Slf4j
public class PictureCrawler extends BaseSeimiCrawler {
    @Autowired
    private MybatisStoreDAO storeToDbDAO;

    @Override
    public String[] startUrls() {
        return new String[]{"https://movie.douban.com/subject/27038183/reviews?sort=time&start=40"};
    }

    @Override
    public void start(Response response) {
        JXDocument doc = response.document();
        try {

            List<Object> avators = doc.sel("//a[@class='avator']/img/@src");
            log.info("avators size:" + avators.size());
            List<Object> names = doc.sel("//a[@class='name']/text()");
            log.info("names size:" + names.size());

            for(int i=1;i<=20;i++){
                System.out.println(names.get(i-1));
            }

            for(int i=1;i<=20;i++){
                String fileName = avators.get(i - 1).toString().replaceAll("https://img[1-9].doubanio.com/icon/","");
                //System.out.println(fileName);
                File file = new File("E:\\data\\img\\" + "61.190.4.72$" + fileName);
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                URL urlConnection = new URL(avators.get(i - 1).toString());
                fileOutputStream.write(readInputStream(urlConnection.openConnection().getInputStream()));
                fileOutputStream.flush();
                fileOutputStream.close();
                System.out.println(fileName);
            }







        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static byte[] readInputStream(InputStream inStream) throws Exception{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        //创建一个Buffer字符串
        byte[] buffer = new byte[1024];
        //每次读取的字符串长度，如果为-1，代表全部读取完毕
        int len = 0;
        //使用一个输入流从buffer里把数据读取出来
        while( (len=inStream.read(buffer)) != -1 ){
            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
            outStream.write(buffer, 0, len);
        }
        //关闭输入流
        inStream.close();
        //把outStream里的数据写入内存
        return outStream.toByteArray();
    }



}
