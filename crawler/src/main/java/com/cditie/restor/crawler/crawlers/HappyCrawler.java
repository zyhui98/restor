package com.cditie.restor.crawler.crawlers;

/**
 * Created by zhuyunhui on 9/21/2017.
 */

import cn.wanghaomiao.seimi.annotation.Crawler;
import cn.wanghaomiao.seimi.def.BaseSeimiCrawler;
import cn.wanghaomiao.seimi.struct.Request;
import cn.wanghaomiao.seimi.struct.Response;
import cn.wanghaomiao.xpath.model.JXDocument;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cditie.restor.crawler.dao.mybatis.MybatisStoreDAO;
import com.cditie.restor.crawler.model.BlogContent;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Nullable;
import java.util.List;

@Crawler(name = "happy")
public class HappyCrawler extends BaseSeimiCrawler {

	@Autowired
	private MybatisStoreDAO storeToDbDAO;

	@Override
	public String[] startUrls() {
		return new String[]{"http://haha.sogou.com/tag/li/%E5%86%B7%E7%AC%91%E8%AF%9D/","http://haha.sogou.com/tag/li/%E7%BE%8E%E5%A5%B3/","http://haha.sogou.com/tag/li/%E6%9C%89%E7%88%B1/"};
	}

	@Override
	public void start(Response response) {
		JXDocument doc = response.document();
		try {
			List<Object> urls = doc.sel("//a[@class='tit']/@href");
			if(urls != null){
				urls = Lists.transform(urls,new Function(){
					@Nullable
					@Override
					public Object apply(@Nullable Object o) {
						return "http://haha.sogou.com/port/getNextJoke?jid=${id}&pos=991&cnt=1".replace("${id}", o.toString().replace("/",""));
					}
				});
			}
			logger.info("{}", urls.size());
			for (Object s : urls) {
				push(Request.build(s.toString(), "renderBean"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void renderBean(Response response) {
		try {
			if(response.getContent()!=null && response.getContent().trim().startsWith("<HTML")){
				BlogContent blog = response.render(BlogContent.class);
				logger.info("bean resolve res={},url={}", blog, response.getUrl());
				//使用神器paoding-jade存储到DB
				int changeNum = storeToDbDAO.save(blog);
				int blogId = blog.getId();
				logger.info("store success,blogId = {},changeNum={}", blogId, changeNum);
			}else{
				JSONObject jsonObject = JSONObject.parseObject(response.getContent());
				JSONArray jsonArray = jsonObject.getJSONArray("list");
				BlogContent blog = new BlogContent();
				blog.setTitle(jsonArray.getJSONObject(0).get("title").toString());
				blog.setContent(jsonArray.getJSONObject(0).get("text").toString());
				blog.setImage(jsonArray.getJSONObject(0).get("image_url").toString());
				logger.info("bean resolve res={},url={}", blog, response.getUrl());
				//使用神器paoding-jade存储到DB
				int changeNum = storeToDbDAO.save(blog);
				int blogId = blog.getId();
				logger.info("store success,blogId = {},changeNum={}", blogId, changeNum);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
