package com.cditie.restor.crawler.crawlers;

import cn.wanghaomiao.seimi.annotation.Crawler;
import cn.wanghaomiao.seimi.def.BaseSeimiCrawler;
import cn.wanghaomiao.seimi.struct.Response;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cditie.restor.crawler.dao.mybatis.MybatisStoreDAO;
import com.cditie.restor.crawler.model.FeedDetail;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author zhuyunhui
 * @since 4/27/2018
 */
@Crawler(name = "today")
@Slf4j
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
        log.info("response.content:{}" ,content );
        JSONObject jsonObject = JSONObject.parseObject(content);
        List<FeedDetail> list = new ArrayList<FeedDetail>();
        if(jsonObject.containsKey("result")){
            JSONArray array = jsonObject.getJSONArray("result");
            for(int i=0;i<array.size();i++){
                FeedDetail feedDetail = new FeedDetail();
                feedDetail.setFeedId(1);
                feedDetail.setFeedCode("today");
                feedDetail.setTitle(array.getJSONObject(i).getString("year")
                        + ","
                        + array.getJSONObject(i).getString("title"));

                storeToDbDAO.saveFeedDetail(feedDetail);


            }
        }



    }
}
