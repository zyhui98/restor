package com.cditie.restor.crawler.crawlers;

import cn.wanghaomiao.seimi.annotation.Crawler;
import cn.wanghaomiao.seimi.def.BaseSeimiCrawler;
import cn.wanghaomiao.seimi.struct.Response;

/**
 * @author zhuyunhui
 * @since 4/27/2018
 */
@Crawler(name = "today")
public class TodayCrawler extends BaseSeimiCrawler {

    @Override
    public String[] startUrls() {
        return new String[]{"http://www.ipip5.com/today/api.php?type=json"};
    }

    @Override
    public void start(Response response) {

        System.out.println(response.getContent());

    }
}
