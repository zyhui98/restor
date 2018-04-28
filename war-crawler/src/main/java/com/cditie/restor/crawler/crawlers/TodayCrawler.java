package com.cditie.restor.crawler.crawlers;

import cn.wanghaomiao.seimi.annotation.Crawler;
import cn.wanghaomiao.seimi.def.BaseSeimiCrawler;
import cn.wanghaomiao.seimi.struct.Response;
import com.cditie.restor.crawler.dao.mybatis.MybatisStoreDAO;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhuyunhui
 * @since 4/27/2018
 */
@Crawler(name = "today")
public class TodayCrawler extends BaseSeimiCrawler {
    @Autowired
    private MybatisStoreDAO storeToDbDAO;

    @Override
    public String[] startUrls() {
        return new String[]{"http://www.ipip5.com/today/api.php?type=json"};
    }

    @Override
    public void start(Response response) {

        String content = response.getContent();


    }
}
